package dev.nihilncunia.fantasy_builder.domain.trait;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 전역 특성 Repository
 * 
 * <p>
 * 전역 특성 엔티티에 대한 데이터 접근을 담당합니다.
 */
@Repository
public interface TraitRepository extends JpaRepository<TraitEntity, Long>, JpaSpecificationExecutor<TraitEntity> {

  /**
   * 특성명으로 검색 (삭제되지 않은 것만)
   * 
   * @param traitNm 특성명 (부분 일치)
   * @param delYn 삭제 여부
   * @return 특성 목록
   */
  List<TraitEntity> findByTraitNmContainingAndDelYn(String traitNm, String delYn);

  /**
   * 특성명으로 검색 (페이징, 삭제되지 않은 것만)
   * 
   * @param traitNm 특성명 (부분 일치)
   * @param delYn 삭제 여부
   * @param pageable 페이징 정보
   * @return 특성 페이지
   */
  Page<TraitEntity> findByTraitNmContainingAndDelYn(String traitNm, String delYn, Pageable pageable);

  /**
   * 특성 대분류로 검색 (삭제되지 않은 것만)
   * 
   * @param traitLcls 특성 대분류
   * @param delYn 삭제 여부
   * @return 특성 목록
   */
  List<TraitEntity> findByTraitLclsAndDelYn(String traitLcls, String delYn);

  /**
   * 특성 대분류로 검색 (페이징, 삭제되지 않은 것만)
   * 
   * @param traitLcls 특성 대분류
   * @param delYn 삭제 여부
   * @param pageable 페이징 정보
   * @return 특성 페이지
   */
  Page<TraitEntity> findByTraitLclsAndDelYn(String traitLcls, String delYn, Pageable pageable);

  /**
   * 적용 대상으로 검색 (삭제되지 않은 것만)
   * 
   * @param aplyTrgt 적용 대상
   * @param delYn 삭제 여부
   * @return 특성 목록
   */
  List<TraitEntity> findByAplyTrgtAndDelYn(String aplyTrgt, String delYn);

  /**
   * 적용 대상으로 검색 (페이징, 삭제되지 않은 것만)
   * 
   * @param aplyTrgt 적용 대상
   * @param delYn 삭제 여부
   * @param pageable 페이징 정보
   * @return 특성 페이지
   */
  Page<TraitEntity> findByAplyTrgtAndDelYn(String aplyTrgt, String delYn, Pageable pageable);
}
