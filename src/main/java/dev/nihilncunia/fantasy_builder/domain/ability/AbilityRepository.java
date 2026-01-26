package dev.nihilncunia.fantasy_builder.domain.ability;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 전역 어빌리티 Repository
 * 
 * <p>
 * 전역 어빌리티 엔티티에 대한 데이터 접근을 담당합니다.
 */
@Repository
public interface AbilityRepository extends JpaRepository<AbilityEntity, Long>, JpaSpecificationExecutor<AbilityEntity> {

  /**
   * 어빌리티명으로 검색 (삭제되지 않은 것만)
   * 
   * @param abilityNm 어빌리티명 (부분 일치)
   * @param delYn 삭제 여부
   * @return 어빌리티 목록
   */
  List<AbilityEntity> findByAbilityNmContainingAndDelYn(String abilityNm, String delYn);

  /**
   * 어빌리티명으로 검색 (페이징, 삭제되지 않은 것만)
   * 
   * @param abilityNm 어빌리티명 (부분 일치)
   * @param delYn 삭제 여부
   * @param pageable 페이징 정보
   * @return 어빌리티 페이지
   */
  Page<AbilityEntity> findByAbilityNmContainingAndDelYn(String abilityNm, String delYn, Pageable pageable);

  /**
   * 어빌리티 유형으로 검색 (삭제되지 않은 것만)
   * 
   * @param abilityType 어빌리티 유형
   * @param delYn 삭제 여부
   * @return 어빌리티 목록
   */
  List<AbilityEntity> findByAbilityTypeAndDelYn(String abilityType, String delYn);

  /**
   * 어빌리티 유형으로 검색 (페이징, 삭제되지 않은 것만)
   * 
   * @param abilityType 어빌리티 유형
   * @param delYn 삭제 여부
   * @param pageable 페이징 정보
   * @return 어빌리티 페이지
   */
  Page<AbilityEntity> findByAbilityTypeAndDelYn(String abilityType, String delYn, Pageable pageable);

  /**
   * 어빌리티 계통으로 검색 (삭제되지 않은 것만)
   * 
   * @param abilityLcls 어빌리티 계통
   * @param delYn 삭제 여부
   * @return 어빌리티 목록
   */
  List<AbilityEntity> findByAbilityLclsAndDelYn(String abilityLcls, String delYn);

  /**
   * 어빌리티 계통으로 검색 (페이징, 삭제되지 않은 것만)
   * 
   * @param abilityLcls 어빌리티 계통
   * @param delYn 삭제 여부
   * @param pageable 페이징 정보
   * @return 어빌리티 페이지
   */
  Page<AbilityEntity> findByAbilityLclsAndDelYn(String abilityLcls, String delYn, Pageable pageable);
}
