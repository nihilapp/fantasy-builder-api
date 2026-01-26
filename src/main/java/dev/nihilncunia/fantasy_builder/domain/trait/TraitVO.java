package dev.nihilncunia.fantasy_builder.domain.trait;

import java.util.List;

import dev.nihilncunia.fantasy_builder.domain.common.entity.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 전역 특성 VO
 * 
 * <p>
 * 특성 관련 요청/응답 데이터를 담는 Value Object입니다.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TraitVO extends CommonVO {

  // 기본 필드
  private Long traitNo;
  private String traitNm;
  private String traitExpln;
  private String traitLcls;
  private String traitMcls;
  private String aplyTrgt;
  private Long cnflTraitNo; // 상충 특성 번호

  // 검색용 필드
  private List<Long> traitNoList;
  private String searchType;
  private String searchKeyword;
  private Integer totalCnt;
  private Integer rowNo;

  /**
   * Entity → VO 변환 생성자 (상세 조회용)
   * 
   * @param entity TraitEntity
   */
  public TraitVO(TraitEntity entity) {
    this.mapFromEntity(entity);
  }

  /**
   * Entity → VO 변환 생성자 (목록 조회용)
   * 
   * @param entity TraitEntity
   * @param totalCnt 전체 개수
   * @param rowNo 행 번호
   */
  public TraitVO(TraitEntity entity, Integer totalCnt, Integer rowNo) {
    this.mapFromEntity(entity);
    this.totalCnt = totalCnt;
    this.rowNo = rowNo;
  }

  /**
   * Entity에서 VO로 매핑
   * 
   * @param entity TraitEntity
   */
  private void mapFromEntity(TraitEntity entity) {
    this.traitNo = entity.getTraitNo();
    this.traitNm = entity.getTraitNm();
    this.traitExpln = entity.getTraitExpln();
    this.traitLcls = entity.getTraitLcls();
    this.traitMcls = entity.getTraitMcls();
    this.aplyTrgt = entity.getAplyTrgt();
    this.cnflTraitNo = entity.getCnflTrait() != null ? entity.getCnflTrait().getTraitNo() : null;

    // CommonVO 필드 매핑
    this.useYn = entity.getUseYn();
    this.shrnYn = entity.getShrnYn();
    this.delYn = entity.getDelYn();
    this.crtNo = entity.getCrtNo();
    this.crtDt = entity.getCrtDt();
    this.updtNo = entity.getUpdtNo();
    this.updtDt = entity.getUpdtDt();
    this.delNo = entity.getDelNo();
    this.delDt = entity.getDelDt();
  }

  /**
   * VO → Entity 변환 메서드 (신규 저장용)
   * 
   * @return TraitEntity
   */
  public TraitEntity toEntity() {
    TraitEntity entity = new TraitEntity(
      this.traitNm,
      this.traitExpln,
      this.traitLcls,
      this.traitMcls,
      this.aplyTrgt,
      null // cnflTrait는 별도로 설정해야 함
    );
    return entity;
  }

  /**
   * Entity 업데이트 메서드 (수정용)
   * 
   * @param entity TraitEntity
   * @param cnflTrait 상충 특성 엔티티 (null 가능)
   */
  public void updateEntity(TraitEntity entity, TraitEntity cnflTrait) {
    entity.updateTrait(
      this.traitNm,
      this.traitExpln,
      this.traitLcls,
      this.traitMcls,
      this.aplyTrgt,
      cnflTrait
    );
  }
}
