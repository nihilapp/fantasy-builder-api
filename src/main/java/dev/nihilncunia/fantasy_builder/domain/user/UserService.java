package dev.nihilncunia.fantasy_builder.domain.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.nihilncunia.fantasy_builder.domain.common.exception.BusinessException;
import dev.nihilncunia.fantasy_builder.domain.common.type.ListType;
import dev.nihilncunia.fantasy_builder.domain.common.util.PageUtils;
import dev.nihilncunia.fantasy_builder.domain.common.util.PasswordHelper;
import dev.nihilncunia.fantasy_builder.domain.common.response.ResponseCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 Service
 * 
 * <p>
 * 사용자 관련 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final PasswordHelper passwordHelper;

  /**
   * 사용자 목록 조회 (페이징)
   * 
   * @param searchVO 검색 조건
   * @param page 페이지 번호 (1-based)
   * @return ListType<UserVO>
   */
  public ListType<UserVO> findUsers(UserVO searchVO, int page) {
    // 페이지 번호를 받아서 Pageable 생성
    Pageable pageable = PageUtils.createPageable(page);

    // 검색 조건 구성
    Specification<UserEntity> spec = buildSpecification(searchVO);
    Page<UserEntity> entityPage = userRepository.findAll(spec, pageable);

    // Page를 VO로 변환
    Page<UserVO> voPage = entityPage.map(UserVO::new);
    
    // ListType으로 변환
    List<UserVO> voList = voPage.getContent();
    return new ListType<>(
        voList,
        voPage.getTotalElements(),
        PageUtils.DEFAULT_PAGE_SIZE,
        page,
        voPage.getTotalPages(),
        voPage.isFirst(),
        voPage.isLast()
    );
  }

  /**
   * 사용자 상세 조회
   * 
   * @param userNo 사용자 번호
   * @return UserVO
   * @throws EntityNotFoundException 사용자를 찾을 수 없을 때
   */
  public UserVO findUserById(Long userNo) {
    UserEntity entity = userRepository.findById(userNo)
        .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. userNo: " + userNo));

    // 삭제된 사용자는 조회 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("삭제된 사용자입니다. userNo: " + userNo);
    }

    return new UserVO(entity);
  }

  /**
   * 이메일로 사용자 조회
   * 
   * @param userEml 이메일
   * @return UserVO
   * @throws EntityNotFoundException 사용자를 찾을 수 없을 때
   */
  public UserVO findUserByEmail(String userEml) {
    UserEntity entity = userRepository.findByUserEmlAndDelYn(userEml, "N")
        .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. userEml: " + userEml));

    return new UserVO(entity);
  }

  /**
   * 사용자 생성 (회원가입)
   * 
   * @param userVO 사용자 정보
   * @return UserVO
   * @throws BusinessException 이메일 중복 시
   */
  @Transactional
  public UserVO createUser(UserVO userVO) {
    // 이메일 중복 검증
    if (userRepository.findByUserEml(userVO.getUserEml()).isPresent()) {
      throw new BusinessException(ResponseCode.EMAIL_ALREADY_EXISTS, "이미 사용 중인 이메일입니다.");
    }

    // 비밀번호 해싱
    if (userVO.getPassword() != null && !userVO.getPassword().isEmpty()) {
      userVO.setEnpswd(passwordHelper.encode(userVO.getPassword()));
    } else if (userVO.getEnpswd() != null && !userVO.getEnpswd().isEmpty()) {
      // 이미 해싱된 비밀번호인 경우 그대로 사용
    } else {
      throw new BusinessException(ResponseCode.VALIDATION_ERROR, "비밀번호는 필수입니다.");
    }

    UserEntity entity = userVO.toEntity();
    entity.setCrtNo(1L); // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    UserEntity saved = userRepository.save(entity);
    return new UserVO(saved);
  }

  /**
   * 사용자 정보 수정
   * 
   * @param userNo 사용자 번호
   * @param userVO 수정할 사용자 정보
   * @return UserVO
   * @throws EntityNotFoundException 사용자를 찾을 수 없을 때
   */
  @Transactional
  public UserVO updateUser(Long userNo, UserVO userVO) {
    UserEntity entity = userRepository.findById(userNo)
        .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. userNo: " + userNo));

    // 삭제된 사용자는 수정 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("삭제된 사용자입니다. userNo: " + userNo);
    }

    // 수정자 번호 (인증 구현 후 현재 사용자 번호로 변경 필요)
    Long updtNo = 1L; // TODO: 인증 구현 후 현재 사용자 번호 가져오기

    // 비밀번호 변경 (새 비밀번호가 제공된 경우)
    if (userVO.getPassword() != null && !userVO.getPassword().isEmpty()) {
      userVO.setEnpswd(passwordHelper.encode(userVO.getPassword()));
      // 비밀번호 변경 시 수정자 번호와 수정 일시 업데이트
      entity.changePassword(userVO.getEnpswd(), updtNo);
    } else {
      // Entity 업데이트 (비밀번호 변경이 아닌 경우)
      userVO.updateEntity(entity);
      entity.setUpdtNo(updtNo);
    }

    UserEntity saved = userRepository.save(entity);
    return new UserVO(saved);
  }

  /**
   * 사용자 삭제 (소프트 삭제)
   * 
   * @param userNo 사용자 번호
   * @param deleterNo 삭제자 번호
   * @throws EntityNotFoundException 사용자를 찾을 수 없을 때
   */
  @Transactional
  public void deleteUser(Long userNo, Long deleterNo) {
    UserEntity entity = userRepository.findById(userNo)
        .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. userNo: " + userNo));

    // 이미 삭제된 사용자는 삭제 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("이미 삭제된 사용자입니다. userNo: " + userNo);
    }

    entity.delete(deleterNo);
    userRepository.save(entity);
  }

  /**
   * 이메일 중복 검증
   * 
   * @param userEml 이메일
   * @return 중복 여부 (true: 중복, false: 사용 가능)
   */
  public boolean isEmailExists(String userEml) {
    return userRepository.findByUserEml(userEml).isPresent();
  }

  /**
   * 검색 조건 빌더
   * 
   * @param searchVO 검색 조건
   * @return Specification<UserEntity>
   */
  private Specification<UserEntity> buildSpecification(UserVO searchVO) {
    Specification<UserEntity> spec = Specification.where(
        // 삭제되지 않은 것만
        (root, query, cb) -> cb.equal(root.get("delYn"), "N"));

    // 사용자명 검색
    if (searchVO.getSearchKeyword() != null && searchVO.getUserNm() != null) {
      spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("userNm")),
          "%" + searchVO.getUserNm().toLowerCase() + "%"));
    }

    // 이메일 검색
    if (searchVO.getUserEml() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("userEml"), searchVO.getUserEml()));
    }

    // 사용자 번호 리스트 필터
    if (searchVO.getUserNoList() != null && !searchVO.getUserNoList().isEmpty()) {
      spec = spec.and((root, query, cb) -> root.get("userNo").in(searchVO.getUserNoList()));
    }

    return spec;
  }
}
