package dev.nihilncunia.fantasy_builder.domain.ability;

import org.hibernate.annotations.DynamicInsert;

import dev.nihilncunia.fantasy_builder.domain.common.entity.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 전역 어빌리티 엔티티
 * 
 * <p>
 * 모든 프로젝트가 공유하는 전역 어빌리티를 관리합니다.
 */
@Entity
@Getter
@Table(name = "abilities", indexes = {
    @Index(name = "idx_abilities_ability_nm", columnList = "ability_nm")
})
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbilityEntity extends CommonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ability_no")
  private Long abilityNo;

  @Column(name = "ability_nm", nullable = false)
  private String abilityNm; // 어빌리티명

  @Column(name = "ability_type")
  private String abilityType; // 어빌리티 유형

  @Column(name = "ability_lcls")
  private String abilityLcls; // 어빌리티 계통

  @Column(name = "ability_expln", columnDefinition = "TEXT")
  private String abilityExpln; // 어빌리티 설명

  @Column(name = "trgt_type")
  private String trgtType; // 대상 유형

  @Column(name = "dmg_type")
  private String dmgType; // 피해 유형

  @Column(name = "stat_eff_type")
  private String statEffType; // 상태 이상 유형

  @Column(name = "use_cost")
  private String useCost; // 소모 비용

  @Column(name = "cool_time")
  private Integer coolTime; // 재사용 대기시간

  @Column(name = "cast_time")
  private Integer castTime; // 시전 시간

  @Column(name = "use_cnd", columnDefinition = "TEXT")
  private String useCnd; // 사용 조건

  // 빌더 패턴용 생성자
  public AbilityEntity(String abilityNm, String abilityType, String abilityLcls, String abilityExpln,
      String trgtType, String dmgType, String statEffType, String useCost, Integer coolTime,
      Integer castTime, String useCnd) {
    this.abilityNm = abilityNm;
    this.abilityType = abilityType;
    this.abilityLcls = abilityLcls;
    this.abilityExpln = abilityExpln;
    this.trgtType = trgtType;
    this.dmgType = dmgType;
    this.statEffType = statEffType;
    this.useCost = useCost;
    this.coolTime = coolTime;
    this.castTime = castTime;
    this.useCnd = useCnd;
  }

  // 비즈니스 로직: 어빌리티 정보 수정
  public void updateAbility(String abilityNm, String abilityType, String abilityLcls, String abilityExpln,
      String trgtType, String dmgType, String statEffType, String useCost, Integer coolTime,
      Integer castTime, String useCnd) {
    if (abilityNm != null) {
      this.abilityNm = abilityNm;
    }
    if (abilityType != null) {
      this.abilityType = abilityType;
    }
    if (abilityLcls != null) {
      this.abilityLcls = abilityLcls;
    }
    if (abilityExpln != null) {
      this.abilityExpln = abilityExpln;
    }
    if (trgtType != null) {
      this.trgtType = trgtType;
    }
    if (dmgType != null) {
      this.dmgType = dmgType;
    }
    if (statEffType != null) {
      this.statEffType = statEffType;
    }
    if (useCost != null) {
      this.useCost = useCost;
    }
    if (coolTime != null) {
      this.coolTime = coolTime;
    }
    if (castTime != null) {
      this.castTime = castTime;
    }
    if (useCnd != null) {
      this.useCnd = useCnd;
    }
  }
}
