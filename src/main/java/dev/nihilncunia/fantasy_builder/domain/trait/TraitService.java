package dev.nihilncunia.fantasy_builder.domain.trait;

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
 * 전역 특성 Service
 * 
 * <p>
 * 전역 특성 관련 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TraitService {

  private final TraitRepository traitRepository;

  private static final int DEFAULT_PAGE_SIZE = 10;

  /**
   * 특성 목록 조회 (페이징)
   * 
   * @param searchVO 검색 조건
   * @param page     페이지 번호 (1-based)
   * @return ListType<TraitVO>
   */
  public ListType<TraitVO> findTraits(TraitVO searchVO, int page) {
    // 페이지 번호를 받아서 Pageable 생성 (0-based index)
    Pageable pageable = PageRequest.of(page - 1, DEFAULT_PAGE_SIZE);

    // 검색 조건 구성
    Specification<TraitEntity> spec = buildSpecification(searchVO);
    Page<TraitEntity> entityPage = traitRepository.findAll(spec, pageable);

    // Page를 ListType으로 변환
    List<TraitVO> voList = entityPage.getContent().stream()
        .map(TraitVO::new)
        .collect(Collectors.toList());

    return new ListType<>(
        voList,
        entityPage.getTotalElements(),
        DEFAULT_PAGE_SIZE,
        page,
        entityPage.getTotalPages(),
        entityPage.isFirst(),
        entityPage.isLast());
  }

  /**
   * 특성 상세 조회
   * 
   * @param traitNo 특성 번호
   * @return TraitVO
   * @throws EntityNotFoundException 특성을 찾을 수 없을 때
   */
  public TraitVO findTraitById(Long traitNo) {
    TraitEntity entity = traitRepository.findById(traitNo)
        .orElseThrow(() -> new EntityNotFoundException("특성을 찾을 수 없습니다. traitNo: " + traitNo));

    // 삭제된 특성은 조회 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("삭제된 특성입니다. traitNo: " + traitNo);
    }

    return new TraitVO(entity);
  }

  /**
   * 특성 생성
   * 
   * @param traitVO   특성 정보
   * @param creatorNo 생성자 번호
   * @return TraitVO
   */
  @Transactional
  public TraitVO createTrait(TraitVO traitVO, Long creatorNo) {
    TraitEntity entity = traitVO.toEntity();

    // 상충 특성 설정
    if (traitVO.getCnflTraitNo() != null) {
      TraitEntity cnflTrait = traitRepository.findById(traitVO.getCnflTraitNo())
          .orElseThrow(() -> new EntityNotFoundException("상충 특성을 찾을 수 없습니다. traitNo: " + traitVO.getCnflTraitNo()));
      entity = new TraitEntity(
          traitVO.getTraitNm(),
          traitVO.getTraitExpln(),
          traitVO.getTraitLcls(),
          traitVO.getTraitMcls(),
          traitVO.getAplyTrgt(),
          cnflTrait);
    }

    entity.setCrtNo(creatorNo);
    TraitEntity saved = traitRepository.save(entity);
    return new TraitVO(saved);
  }

  /**
   * 특성 수정
   * 
   * @param traitNo   특성 번호
   * @param traitVO   수정할 특성 정보
   * @param updaterNo 수정자 번호
   * @return TraitVO
   * @throws EntityNotFoundException 특성을 찾을 수 없을 때
   */
  @Transactional
  public TraitVO updateTrait(Long traitNo, TraitVO traitVO, Long updaterNo) {
    TraitEntity entity = traitRepository.findById(traitNo)
        .orElseThrow(() -> new EntityNotFoundException("특성을 찾을 수 없습니다. traitNo: " + traitNo));

    // 삭제된 특성은 수정 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("삭제된 특성입니다. traitNo: " + traitNo);
    }

    // 상충 특성 조회
    TraitEntity cnflTrait = null;
    if (traitVO.getCnflTraitNo() != null) {
      cnflTrait = traitRepository.findById(traitVO.getCnflTraitNo())
          .orElseThrow(() -> new EntityNotFoundException("상충 특성을 찾을 수 없습니다. traitNo: " + traitVO.getCnflTraitNo()));
    }

    // Entity 업데이트
    traitVO.updateEntity(entity, cnflTrait);
    entity.setUpdtNo(updaterNo);

    TraitEntity saved = traitRepository.save(entity);
    return new TraitVO(saved);
  }

  /**
   * 특성 삭제 (소프트 삭제)
   * 
   * @param traitNo   특성 번호
   * @param deleterNo 삭제자 번호
   * @throws EntityNotFoundException 특성을 찾을 수 없을 때
   */
  @Transactional
  public void deleteTrait(Long traitNo, Long deleterNo) {
    TraitEntity entity = traitRepository.findById(traitNo)
        .orElseThrow(() -> new EntityNotFoundException("특성을 찾을 수 없습니다. traitNo: " + traitNo));

    // 이미 삭제된 특성은 삭제 불가
    if ("Y".equals(entity.getDelYn())) {
      throw new EntityNotFoundException("이미 삭제된 특성입니다. traitNo: " + traitNo);
    }

    entity.delete(deleterNo);
    traitRepository.save(entity);
  }

  /**
   * 특성 사용 현황 조회
   * 
   * <p>
   * 향후 매핑 테이블 구현 시 완성될 예정입니다.
   * 현재는 기본 구조만 제공합니다.
   * 
   * @param traitNo 특성 번호
   * @return 사용 현황 정보 (향후 구현)
   */
  public Object findUsage(Long traitNo) {
    // TODO: 향후 매핑 테이블 구현 시 완성
    // char_trait_maps, creature_trait_maps 등에서 사용 현황 조회
    return null;
  }

  /**
   * 검색 조건 빌더
   * 
   * @param searchVO 검색 조건
   * @return Specification<TraitEntity>
   */
  private Specification<TraitEntity> buildSpecification(TraitVO searchVO) {
    Specification<TraitEntity> spec = Specification.where(
        // 삭제되지 않은 것만
        (root, query, cb) -> cb.equal(root.get("delYn"), "N"));

    // 특성명 검색
    if (searchVO.getSearchKeyword() != null && searchVO.getTraitNm() != null) {
      spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("traitNm")),
          "%" + searchVO.getTraitNm().toLowerCase() + "%"));
    }

    // 특성 대분류 필터
    if (searchVO.getTraitLcls() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("traitLcls"), searchVO.getTraitLcls()));
    }

    // 특성 중분류 필터
    if (searchVO.getTraitMcls() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("traitMcls"), searchVO.getTraitMcls()));
    }

    // 적용 대상 필터
    if (searchVO.getAplyTrgt() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("aplyTrgt"), searchVO.getAplyTrgt()));
    }

    // 특성 번호 리스트 필터
    if (searchVO.getTraitNoList() != null && !searchVO.getTraitNoList().isEmpty()) {
      spec = spec.and((root, query, cb) -> root.get("traitNo").in(searchVO.getTraitNoList()));
    }

    return spec;
  }
}
