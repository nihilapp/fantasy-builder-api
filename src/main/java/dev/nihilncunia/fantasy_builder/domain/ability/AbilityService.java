package dev.nihilncunia.fantasy_builder.domain.ability;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.nihilncunia.fantasy_builder.domain.common.type.ListType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * 전역 어빌리티 Service
 * 
 * <p>
 * 전역 어빌리티 관련 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AbilityService {

  private final AbilityRepository abilityRepository;

  private static final int DEFAULT_PAGE_SIZE = 10;

  /**
   * 어빌리티 목록 조회 (페이징)
   * 
   * @param searchVO 검색 조건
   * @param page 페이지 번호 (1-based)
   * @return ListType<AbilityVO>
   */
  public ListType<AbilityVO> findAbilities(AbilityVO searchVO, int page) {
    // 페이지 번호를 받아서 Pageable 생성 (0-based index)
    Pageable pageable = PageRequest.of(page - 1, DEFAULT_PAGE_SIZE);

    // 검색 조건 구성
    Specification<AbilityEntity> spec = buildSpecification(searchVO);
    Page<AbilityEntity> entityPage = abilityRepository.findAll(spec, pageable);

    // Page를 ListType으로 변환
    List<AbilityVO> voList = entityPage.getContent().stream()
        .map(AbilityVO::new)
        .collect(Collectors.toList());

    return new ListType<>(
      voList,
      entityPage.getTotalElements(),
      DEFAULT_PAGE_SIZE,
      page,
      entityPage.getTotalPages(),
      entityPage.isFirst(),
      entityPage.isLast()
    );
  }

  /**
   * 어빌리티 상세 조회
   * 
   * @param abilityNo 어빌리티 번호
   * @return AbilityVO
   * @throws EntityNotFoundException 어빌리티를 찾을 수 없을 때
   */
  public AbilityVO findAbilityById(Long abilityNo) {
    AbilityEntity entity = abilityRepository.findById(abilityNo)
        .orElseThrow(() -> new EntityNotFoundException("어빌리티를 찾을 수 없습니다. abilityNo: " + abilityNo));
    
    // 삭제된 어빌리티는 조회 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("삭제된 어빌리티입니다. abilityNo: " + abilityNo);
    }

    return new AbilityVO(entity);
  }

  /**
   * 어빌리티 생성
   * 
   * @param abilityVO 어빌리티 정보
   * @param creatorNo 생성자 번호
   * @return AbilityVO
   */
  @Transactional
  public AbilityVO createAbility(AbilityVO abilityVO, Long creatorNo) {
    AbilityEntity entity = abilityVO.toEntity();
    entity.setCrtNo(creatorNo);
    AbilityEntity saved = abilityRepository.save(entity);
    return new AbilityVO(saved);
  }

  /**
   * 어빌리티 수정
   * 
   * @param abilityNo 어빌리티 번호
   * @param abilityVO 수정할 어빌리티 정보
   * @param updaterNo 수정자 번호
   * @return AbilityVO
   * @throws EntityNotFoundException 어빌리티를 찾을 수 없을 때
   */
  @Transactional
  public AbilityVO updateAbility(Long abilityNo, AbilityVO abilityVO, Long updaterNo) {
    AbilityEntity entity = abilityRepository.findById(abilityNo)
        .orElseThrow(() -> new EntityNotFoundException("어빌리티를 찾을 수 없습니다. abilityNo: " + abilityNo));

    // 삭제된 어빌리티는 수정 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("삭제된 어빌리티입니다. abilityNo: " + abilityNo);
    }

    // Entity 업데이트
    abilityVO.updateEntity(entity);
    entity.setUpdtNo(updaterNo);
    
    AbilityEntity saved = abilityRepository.save(entity);
    return new AbilityVO(saved);
  }

  /**
   * 어빌리티 삭제 (소프트 삭제)
   * 
   * @param abilityNo 어빌리티 번호
   * @param deleterNo 삭제자 번호
   * @throws EntityNotFoundException 어빌리티를 찾을 수 없을 때
   */
  @Transactional
  public void deleteAbility(Long abilityNo, Long deleterNo) {
    AbilityEntity entity = abilityRepository.findById(abilityNo)
        .orElseThrow(() -> new EntityNotFoundException("어빌리티를 찾을 수 없습니다. abilityNo: " + abilityNo));

    // 이미 삭제된 어빌리티는 삭제 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("이미 삭제된 어빌리티입니다. abilityNo: " + abilityNo);
    }

    entity.delete(deleterNo);
    abilityRepository.save(entity);
  }

  /**
   * 어빌리티 사용 현황 조회
   * 
   * <p>
   * 향후 매핑 테이블 구현 시 완성될 예정입니다.
   * 현재는 기본 구조만 제공합니다.
   * 
   * @param abilityNo 어빌리티 번호
   * @return 사용 현황 정보 (향후 구현)
   */
  public Object findUsage(Long abilityNo) {
    // TODO: 향후 매핑 테이블 구현 시 완성
    // char_ability_maps, creature_ability_maps 등에서 사용 현황 조회
    return null;
  }

  /**
   * 검색 조건 빌더
   * 
   * @param searchVO 검색 조건
   * @return Specification<AbilityEntity>
   */
  private Specification<AbilityEntity> buildSpecification(AbilityVO searchVO) {
    Specification<AbilityEntity> spec = Specification.where(
        // 삭제되지 않은 것만
        (root, query, cb) -> cb.equal(root.get("delYn"), "N"));

    // 어빌리티명 검색
    if (searchVO.getSearchKeyword() != null && searchVO.getAbilityNm() != null) {
      spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("abilityNm")),
          "%" + searchVO.getAbilityNm().toLowerCase() + "%"));
    }

    // 어빌리티 유형 필터
    if (searchVO.getAbilityType() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("abilityType"), searchVO.getAbilityType()));
    }

    // 어빌리티 계통 필터
    if (searchVO.getAbilityLcls() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("abilityLcls"), searchVO.getAbilityLcls()));
    }

    // 어빌리티 번호 리스트 필터
    if (searchVO.getAbilityNoList() != null && !searchVO.getAbilityNoList().isEmpty()) {
      spec = spec.and((root, query, cb) -> root.get("abilityNo").in(searchVO.getAbilityNoList()));
    }

    return spec;
  }
}
