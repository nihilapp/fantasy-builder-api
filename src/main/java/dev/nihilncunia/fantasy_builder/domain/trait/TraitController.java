package dev.nihilncunia.fantasy_builder.domain.trait;

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
 * 전역 특성 Controller
 * 
 * <p>
 * 전역 특성 관련 REST API 엔드포인트를 제공합니다.
 */
@RestController
@RequestMapping("/traits")
@RequiredArgsConstructor
public class TraitController {

  private final TraitService traitService;

  /**
   * 특성 목록 조회
   * 
   * @param page 페이지 번호 (기본값: 1)
   * @param searchVO 검색 조건
   * @return ApiResponse<ListType<TraitVO>>
   */
  @GetMapping
  public ResponseEntity<ApiResponse<ListType<TraitVO>>> getTraits(
      @RequestParam(defaultValue = "1") int page,
      @Valid TraitVO searchVO) {
    ListType<TraitVO> listType = traitService.findTraits(searchVO, page);
    return ResponseEntity.ok(ApiResponse.success(listType));
  }

  /**
   * 특성 상세 조회
   * 
   * @param traitNo 특성 번호
   * @return ApiResponse<TraitVO>
   */
  @GetMapping("/{traitNo}")
  public ResponseEntity<ApiResponse<TraitVO>> getTrait(@PathVariable Long traitNo) {
    TraitVO traitVO = traitService.findTraitById(traitNo);
    return ResponseEntity.ok(ApiResponse.success(traitVO));
  }

  /**
   * 특성 생성
   * 
   * @param traitVO 특성 정보
   * @return ApiResponse<TraitVO>
   */
  @PostMapping
  public ResponseEntity<ApiResponse<TraitVO>> createTrait(@Valid @RequestBody TraitVO traitVO) {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long creatorNo = 1L; // 임시 값
    TraitVO created = traitService.createTrait(traitVO, creatorNo);
    return ResponseEntity.ok(ApiResponse.success(ResponseCode.CREATED, created));
  }

  /**
   * 특성 수정
   * 
   * @param traitNo 특성 번호
   * @param traitVO 수정할 특성 정보
   * @return ApiResponse<TraitVO>
   */
  @PatchMapping("/{traitNo}")
  public ResponseEntity<ApiResponse<TraitVO>> updateTrait(
      @PathVariable Long traitNo,
      @Valid @RequestBody TraitVO traitVO) {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long updaterNo = 1L; // 임시 값
    TraitVO updated = traitService.updateTrait(traitNo, traitVO, updaterNo);
    return ResponseEntity.ok(ApiResponse.success(updated));
  }

  /**
   * 특성 삭제 (소프트 삭제)
   * 
   * @param traitNo 특성 번호
   * @return ApiResponse<Void>
   */
  @DeleteMapping("/{traitNo}")
  public ResponseEntity<ApiResponse<Void>> deleteTrait(@PathVariable Long traitNo) {
    // TODO: 인증 구현 후 현재 사용자 번호 가져오기
    Long deleterNo = 1L; // 임시 값
    traitService.deleteTrait(traitNo, deleterNo);
    return ResponseEntity.ok(ApiResponse.success(null));
  }

  /**
   * 특성 사용 현황 조회
   * 
   * @param traitNo 특성 번호
   * @return ApiResponse<Object>
   */
  @GetMapping("/{traitNo}/usage")
  public ResponseEntity<ApiResponse<Object>> getTraitUsage(@PathVariable Long traitNo) {
    Object usage = traitService.findUsage(traitNo);
    return ResponseEntity.ok(ApiResponse.success(usage));
  }
}
