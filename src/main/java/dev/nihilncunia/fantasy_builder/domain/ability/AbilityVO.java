package dev.nihilncunia.fantasy_builder.domain.ability;

import java.util.List;

import dev.nihilncunia.fantasy_builder.domain.common.entity.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 전역 어빌리티 VO
 * 
 * <p>
 * 어빌리티 관련 요청/응답 데이터를 담는 Value Object입니다.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AbilityVO extends CommonVO {

  // 기본 필드
  private Long abilityNo;
  private String abilityNm;
  private String abilityType;
  private String abilityLcls;
  private String abilityExpln;
  private String trgtType;
  private String dmgType;
  private String statEffType;
  private String useCost;
  private Integer coolTime;
  private Integer castTime;
  private String useCnd;

  // 검색용 필드
  private List<Long> abilityNoList;
  private String searchType;
  private String searchKeyword;
  private Integer totalCnt;
  private Integer rowNo;

  /**
   * Entity → VO 변환 생성자 (상세 조회용)
   * 
   * @param entity AbilityEntity
   */
  public AbilityVO(AbilityEntity entity) {
    this.mapFromEntity(entity);
  }

  /**
   * Entity → VO 변환 생성자 (목록 조회용)
   * 
   * @param entity AbilityEntity
   * @param totalCnt 전체 개수
   * @param rowNo 행 번호
   */
  public AbilityVO(AbilityEntity entity, Integer totalCnt, Integer rowNo) {
    this.mapFromEntity(entity);
    this.totalCnt = totalCnt;
    this.rowNo = rowNo;
  }

  /**
   * Entity에서 VO로 매핑
   * 
   * @param entity AbilityEntity
   */
  private void mapFromEntity(AbilityEntity entity) {
    this.abilityNo = entity.getAbilityNo();
    this.abilityNm = entity.getAbilityNm();
    this.abilityType = entity.getAbilityType();
    this.abilityLcls = entity.getAbilityLcls();
    this.abilityExpln = entity.getAbilityExpln();
    this.trgtType = entity.getTrgtType();
    this.dmgType = entity.getDmgType();
    this.statEffType = entity.getStatEffType();
    this.useCost = entity.getUseCost();
    this.coolTime = entity.getCoolTime();
    this.castTime = entity.getCastTime();
    this.useCnd = entity.getUseCnd();

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
   * @return AbilityEntity
   */
  public AbilityEntity toEntity() {
    return new AbilityEntity(
      this.abilityNm,
      this.abilityType,
      this.abilityLcls,
      this.abilityExpln,
      this.trgtType,
      this.dmgType,
      this.statEffType,
      this.useCost,
      this.coolTime,
      this.castTime,
      this.useCnd
    );
  }

  /**
   * Entity 업데이트 메서드 (수정용)
   * 
   * @param entity AbilityEntity
   */
  public void updateEntity(AbilityEntity entity) {
    entity.updateAbility(
      this.abilityNm,
      this.abilityType,
      this.abilityLcls,
      this.abilityExpln,
      this.trgtType,
      this.dmgType,
      this.statEffType,
      this.useCost,
      this.coolTime,
      this.castTime,
      this.useCnd
    );
  }
}
