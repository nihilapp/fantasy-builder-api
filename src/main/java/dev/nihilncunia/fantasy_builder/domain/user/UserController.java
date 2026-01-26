package dev.nihilncunia.fantasy_builder.domain.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.nihilncunia.fantasy_builder.domain.common.response.ApiResponse;
import dev.nihilncunia.fantasy_builder.domain.common.type.ListType;
import dev.nihilncunia.fantasy_builder.domain.common.response.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 Controller
 * 
 * <p>
 * 사용자 관련 REST API 엔드포인트를 제공합니다.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /**
   * 사용자 목록 조회
   * 
   * @param page 페이지 번호 (기본값: 1)
   * @param searchVO 검색 조건
   * @return ApiResponse<ListType<UserVO>>
   */
  @GetMapping
  public ResponseEntity<ApiResponse<ListType<UserVO>>> getUsers(
      @RequestParam(defaultValue = "1") int page,
      @Valid UserVO searchVO) {
    ListType<UserVO> listType = userService.findUsers(searchVO, page);
    return ResponseEntity.ok(ApiResponse.success(listType));
  }

  /**
   * 현재 사용자 정보 조회
   * 
   * <p>
   * TODO: 인증 구현 후 현재 사용자 번호 가져오기
   * 
   * @return ApiResponse<UserVO>
   */
  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserVO>> getCurrentUser() {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long currentUserNo = 1L; // 임시 값
    UserVO userVO = userService.findUserById(currentUserNo);
    return ResponseEntity.ok(ApiResponse.success(userVO));
  }

  /**
   * 현재 사용자 정보 수정
   * 
   * <p>
   * TODO: 인증 구현 후 현재 사용자 번호 가져오기
   * 
   * @param userVO 수정할 사용자 정보
   * @return ApiResponse<UserVO>
   */
  @PatchMapping("/me")
  public ResponseEntity<ApiResponse<UserVO>> updateCurrentUser(@Valid @RequestBody UserVO userVO) {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long currentUserNo = 1L; // 임시 값
    UserVO updated = userService.updateUser(currentUserNo, userVO);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  /**
   * 사용자 상세 조회
   * 
   * @param userNo 사용자 번호
   * @return ApiResponse<UserVO>
   */
  @GetMapping("/{userNo}")
  public ResponseEntity<ApiResponse<UserVO>> getUser(@PathVariable Long userNo) {
    UserVO userVO = userService.findUserById(userNo);
    return ResponseEntity.ok(ApiResponse.success(userVO));
  }

  /**
   * 사용자 생성 (회원가입)
   * 
   * @param userVO 사용자 정보
   * @return ApiResponse<UserVO>
   */
  @PostMapping
  public ResponseEntity<ApiResponse<UserVO>> createUser(@Valid @RequestBody UserVO userVO) {
    UserVO created = userService.createUser(userVO);
    return ResponseEntity.ok(ApiResponse.success(ResponseCode.CREATED, created));
  }

  /**
   * 사용자 삭제 (회원 탈퇴, 소프트 삭제)
   * 
   * @param userNo 사용자 번호
   * @return ApiResponse<Void>
   */
  @DeleteMapping("/{userNo}")
  public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userNo) {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long deleterNo = 1L; // 임시 값
    userService.deleteUser(userNo, deleterNo);
    return ResponseEntity.ok(ApiResponse.success(null));
  }
}
