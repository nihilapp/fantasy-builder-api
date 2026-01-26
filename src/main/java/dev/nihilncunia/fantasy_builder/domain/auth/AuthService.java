package dev.nihilncunia.fantasy_builder.domain.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.nihilncunia.fantasy_builder.domain.common.exception.BusinessException;
import dev.nihilncunia.fantasy_builder.domain.common.util.EmailHelper;
import dev.nihilncunia.fantasy_builder.domain.common.util.PasswordHelper;
import dev.nihilncunia.fantasy_builder.domain.common.util.PasswordPolicyHelper;
import dev.nihilncunia.fantasy_builder.domain.common.response.ResponseCode;
import dev.nihilncunia.fantasy_builder.domain.user.UserEntity;
import dev.nihilncunia.fantasy_builder.domain.user.UserRepository;
import dev.nihilncunia.fantasy_builder.domain.user.UserService;
import dev.nihilncunia.fantasy_builder.domain.user.UserVO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 인증 Service
 * 
 * <p>
 * 로그인, 회원가입, 토큰 갱신, 비밀번호 관리 등의 인증 관련 비즈니스 로직을 처리합니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

  private final UserRepository userRepository;
  private final UserService userService;
  private final JwtHelper jwtHelper;
  private final PasswordHelper passwordHelper;
  private final PasswordPolicyHelper passwordPolicyHelper;
  private final EmailHelper emailHelper;

  @Value("${security.account.max-login-failures:5}")
  private int maxLoginFailures;

  /**
   * 로그인 결과
   */
  public static class SigninResult {
    private final UserVO response;
    private final TokenPair tokenPair;

    public SigninResult(UserVO response, TokenPair tokenPair) {
      this.response = response;
      this.tokenPair = tokenPair;
    }

    public UserVO getResponse() {
      return response;
    }

    public TokenPair getTokenPair() {
      return tokenPair;
    }
  }

  /**
   * 로그인
   * 
   * @param userVO 로그인 요청 (userEml, password 필수)
   * @param clientIp 클라이언트 IP 주소
   * @return SigninResult (사용자 정보 + 토큰 쌍)
   */
  @Transactional
  public SigninResult signin(UserVO userVO, String clientIp) {
    // 사용자 조회
    UserEntity user = userRepository.findByUserEml(userVO.getUserEml())
        .orElseThrow(() -> new BusinessException(ResponseCode.INVALID_CREDENTIALS,
            "이메일 또는 비밀번호가 올바르지 않습니다."));

    // 삭제된 사용자 확인
    if ("Y".equals(user.getDelYn())) {
      throw new BusinessException(ResponseCode.INVALID_CREDENTIALS,
          "이메일 또는 비밀번호가 올바르지 않습니다.");
    }

    // 계정 잠금 확인
    if ("Y".equals(user.getAcntLckYn())) {
      throw new BusinessException(ResponseCode.ACCOUNT_LOCKED, "계정이 잠금되었습니다.");
    }

    // 비밀번호 검증
    if (!passwordHelper.matches(userVO.getPassword(), user.getEnpswd())) {
      // 로그인 실패 처리
      user.recordLoginFailure();
      if (user.getLgnFailNmtm() >= maxLoginFailures) {
        user.lockAccount();
        userRepository.save(user);
        throw new BusinessException(ResponseCode.ACCOUNT_LOCKED,
            "로그인 실패 횟수가 초과되어 계정이 잠금되었습니다.");
      }
      userRepository.save(user);
      throw new BusinessException(ResponseCode.INVALID_CREDENTIALS,
          "이메일 또는 비밀번호가 올바르지 않습니다.");
    }

    // 로그인 성공 처리
    user.recordLoginSuccess(clientIp);

    // 토큰 생성
    String accessToken = jwtHelper.generateAccessToken(
        user.getUserNo(),
        user.getUserEml(),
        user.getUserRole().toString());
    String refreshToken = jwtHelper.generateRefreshToken(user.getUserNo());

    // Refresh Token 저장
    user.setReshToken(refreshToken);
    userRepository.save(user);

    // UserVO로 변환
    UserVO response = new UserVO(user);
    TokenPair tokenPair = new TokenPair(accessToken, refreshToken);

    return new SigninResult(response, tokenPair);
  }

  /**
   * 회원가입
   * 
   * @param userVO 회원가입 요청 (userEml, password 필수)
   * @return UserVO
   */
  @Transactional
  public UserVO signup(UserVO userVO) {
    // 비밀번호 정책 검증
    passwordPolicyHelper.validatePassword(userVO.getPassword());

    // 사용자 생성
    UserVO createdUser = userService.createUser(userVO);

    return createdUser;
  }

  /**
   * 토큰 갱신
   * 
   * @param refreshToken Refresh Token
   * @return 새로운 토큰 정보 (Access Token, Refresh Token)
   */
  @Transactional
  public TokenPair refresh(String refreshToken) {
    // Refresh Token 검증
    if (!jwtHelper.validateToken(refreshToken, "refresh")) {
      throw new BusinessException(ResponseCode.INVALID_TOKEN, "유효하지 않거나 만료된 토큰입니다.");
    }

    // 사용자 번호 추출
    Long userNo = jwtHelper.getUserIdFromToken(refreshToken);
    if (userNo == null) {
      throw new BusinessException(ResponseCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
    }

    // 사용자 조회 및 Refresh Token 확인
    UserEntity user = userRepository.findById(userNo)
        .orElseThrow(() -> new BusinessException(ResponseCode.INVALID_TOKEN, "유효하지 않은 토큰입니다."));

    if (!refreshToken.equals(user.getReshToken())) {
      throw new BusinessException(ResponseCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
    }

    // 새로운 토큰 생성
    String newAccessToken = jwtHelper.generateAccessToken(
        user.getUserNo(),
        user.getUserEml(),
        user.getUserRole().toString());
    String newRefreshToken = jwtHelper.generateRefreshToken(user.getUserNo());

    // Refresh Token 업데이트
    user.setReshToken(newRefreshToken);
    userRepository.save(user);

    return new TokenPair(newAccessToken, newRefreshToken);
  }

  /**
   * 토큰 쌍 (Access Token, Refresh Token)
   */
  public static class TokenPair {
    private final String accessToken;
    private final String refreshToken;

    public TokenPair(String accessToken, String refreshToken) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
      return accessToken;
    }

    public String getRefreshToken() {
      return refreshToken;
    }
  }

  /**
   * 로그아웃
   * 
   * @param userNo 사용자 번호
   */
  @Transactional
  public void signout(Long userNo) {
    UserEntity user = userRepository.findById(userNo)
        .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. userNo: " + userNo));

    // Refresh Token 삭제
    user.clearReshToken();
    userRepository.save(user);
  }

  /**
   * 비밀번호 재설정 요청
   * 
   * @param userVO 비밀번호 재설정 요청 (userEml 필수)
   */
  @Transactional
  public void forgotPassword(UserVO userVO) {
    UserEntity user = userRepository.findByUserEmlAndDelYn(userVO.getUserEml(), "N")
        .orElseThrow(() -> new BusinessException(ResponseCode.USER_NOT_FOUND,
            "해당 이메일로 등록된 사용자를 찾을 수 없습니다."));

    // 비밀번호 재설정 토큰 생성 (1시간 유효)
    String resetToken = jwtHelper.generateAccessToken(user.getUserNo(), user.getUserEml(),
        user.getUserRole().toString());

    // 이메일 발송
    emailHelper.sendPasswordResetEmail(user.getUserEml(), resetToken);
  }

  /**
   * 비밀번호 재설정 처리
   * 
   * @param userVO 비밀번호 재설정 요청 (token, newPassword 필수)
   */
  @Transactional
  public void resetPassword(UserVO userVO) {
    // 토큰 검증
    if (!jwtHelper.validateToken(userVO.getToken(), "access")) {
      throw new BusinessException(ResponseCode.INVALID_TOKEN, "유효하지 않거나 만료된 토큰입니다.");
    }

    // 비밀번호 정책 검증
    passwordPolicyHelper.validatePassword(userVO.getNewPassword());

    // 사용자 번호 추출
    Long userNo = jwtHelper.getUserIdFromToken(userVO.getToken());
    if (userNo == null) {
      throw new BusinessException(ResponseCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
    }

    // 사용자 조회
    UserEntity user = userRepository.findById(userNo)
        .orElseThrow(() -> new BusinessException(ResponseCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));

    // 비밀번호 변경
    String encryptedPassword = passwordHelper.encode(userVO.getNewPassword());
    user.changePassword(encryptedPassword, userNo);
    userRepository.save(user);
  }

  /**
   * 비밀번호 변경
   * 
   * @param userNo 사용자 번호
   * @param userVO 비밀번호 변경 요청 (currentPassword, newPassword 필수)
   */
  @Transactional
  public void changePassword(Long userNo, UserVO userVO) {
    // 사용자 조회
    UserEntity user = userRepository.findById(userNo)
        .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. userNo: " + userNo));

    // 현재 비밀번호 확인
    if (!passwordHelper.matches(userVO.getCurrentPassword(), user.getEnpswd())) {
      throw new BusinessException(ResponseCode.INVALID_PASSWORD, "현재 비밀번호가 올바르지 않습니다.");
    }

    // 비밀번호 정책 검증
    passwordPolicyHelper.validatePassword(userVO.getNewPassword());

    // 비밀번호 변경
    String encryptedPassword = passwordHelper.encode(userVO.getNewPassword());
    user.changePassword(encryptedPassword, userNo);
    userRepository.save(user);
  }
}
