package dev.nihilncunia.fantasy_builder.domain.ability;

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
 * 전역 어빌리티 Controller
 * 
 * <p>
 * 전역 어빌리티 관련 REST API 엔드포인트를 제공합니다.
 */
@RestController
@RequestMapping("/abilities")
@RequiredArgsConstructor
public class AbilityController {

  private final AbilityService abilityService;

  /**
   * 어빌리티 목록 조회
   * 
   * @param page 페이지 번호 (기본값: 1)
   * @param searchVO 검색 조건
   * @return ApiResponse<ListType<AbilityVO>>
   */
  @GetMapping
  public ResponseEntity<ApiResponse<ListType<AbilityVO>>> getAbilities(
      @RequestParam(defaultValue = "1") int page,
      @Valid AbilityVO searchVO) {
    ListType<AbilityVO> listType = abilityService.findAbilities(searchVO, page);
    return ResponseEntity.ok(ApiResponse.success(listType));
  }

  /**
   * 어빌리티 상세 조회
   * 
   * @param abilityNo 어빌리티 번호
   * @return ApiResponse<AbilityVO>
   */
  @GetMapping("/{abilityNo}")
  public ResponseEntity<ApiResponse<AbilityVO>> getAbility(@PathVariable Long abilityNo) {
    AbilityVO abilityVO = abilityService.findAbilityById(abilityNo);
    return ResponseEntity.ok(ApiResponse.success(abilityVO));
  }

  /**
   * 어빌리티 생성
   * 
   * @param abilityVO 어빌리티 정보
   * @return ApiResponse<AbilityVO>
   */
  @PostMapping
  public ResponseEntity<ApiResponse<AbilityVO>> createAbility(@Valid @RequestBody AbilityVO abilityVO) {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long creatorNo = 1L; // 임시 값
    AbilityVO created = abilityService.createAbility(abilityVO, creatorNo);
    return ResponseEntity.ok(ApiResponse.success(ResponseCode.CREATED, created));
  }

  /**
   * 어빌리티 수정
   * 
   * @param abilityNo 어빌리티 번호
   * @param abilityVO 수정할 어빌리티 정보
   * @return ApiResponse<AbilityVO>
   */
  @PatchMapping("/{abilityNo}")
  public ResponseEntity<ApiResponse<AbilityVO>> updateAbility(
      @PathVariable Long abilityNo,
      @Valid @RequestBody AbilityVO abilityVO) {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long updaterNo = 1L; // 임시 값
    AbilityVO updated = abilityService.updateAbility(abilityNo, abilityVO, updaterNo);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  /**
   * 어빌리티 삭제 (소프트 삭제)
   * 
   * @param abilityNo 어빌리티 번호
   * @return ApiResponse<Void>
   */
  @DeleteMapping("/{abilityNo}")
  public ResponseEntity<ApiResponse<Void>> deleteAbility(@PathVariable Long abilityNo) {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long deleterNo = 1L; // 임시 값
    abilityService.deleteAbility(abilityNo, deleterNo);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  /**
   * 어빌리티 사용 현황 조회
   * 
   * @param abilityNo 어빌리티 번호
   * @return ApiResponse<Object>
   */
  @GetMapping("/{abilityNo}/usage")
  public ResponseEntity<ApiResponse<Object>> getAbilityUsage(@PathVariable Long abilityNo) {
    Object usage = abilityService.findUsage(abilityNo);
    return ResponseEntity.ok(ApiResponse.success(usage));
  }
}
