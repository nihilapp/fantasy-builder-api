package dev.nihilncunia.fantasy_builder.domain.trait;

import org.hibernate.annotations.DynamicInsert;

import dev.nihilncunia.fantasy_builder.domain.common.entity.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 전역 특성 엔티티
 * 
 * <p>
 * 모든 프로젝트가 공유하는 전역 특성을 관리합니다.
 * 상충 특성(self FK) 관계를 지원합니다.
 */
@Entity
@Getter
@Table(name = "traits", indexes = {
    @Index(name = "idx_traits_trait_nm", columnList = "trait_nm"),
    @Index(name = "idx_traits_cnfl_trait_no", columnList = "cnfl_trait_no")
})
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TraitEntity extends CommonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trait_no")
  private Long traitNo;

  @Column(name = "trait_nm", nullable = false)
  private String traitNm; // 특성명

  @Column(name = "trait_expln", columnDefinition = "TEXT")
  private String traitExpln; // 특성 설명

  @Column(name = "trait_lcls")
  private String traitLcls; // 특성 대분류

  @Column(name = "trait_mcls")
  private String traitMcls; // 특성 중분류

  @Column(name = "aply_trgt")
  private String aplyTrgt; // 적용 대상 (CHAR, ITEM, NATION, ORG, REGION)

  @ManyToOne
  @JoinColumn(name = "cnfl_trait_no")
  private TraitEntity cnflTrait; // 상충 특성 (전역 특성만 참조)

  // 빌더 패턴용 생성자
  public TraitEntity(String traitNm, String traitExpln, String traitLcls, String traitMcls,
      String aplyTrgt, TraitEntity cnflTrait) {
    this.traitNm = traitNm;
    this.traitExpln = traitExpln;
    this.traitLcls = traitLcls;
    this.traitMcls = traitMcls;
    this.aplyTrgt = aplyTrgt;
    this.cnflTrait = cnflTrait;
  }

  // 비즈니스 로직: 특성 정보 수정
  public void updateTrait(String traitNm, String traitExpln, String traitLcls, String traitMcls,
      String aplyTrgt, TraitEntity cnflTrait) {
    if (traitNm != null) {
      this.traitNm = traitNm;
    }
    if (traitExpln != null) {
      this.traitExpln = traitExpln;
    }
    if (traitLcls != null) {
      this.traitLcls = traitLcls;
    }
    if (traitMcls != null) {
      this.traitMcls = traitMcls;
    }
    if (aplyTrgt != null) {
      this.aplyTrgt = aplyTrgt;
    }
    if (cnflTrait != null) {
      this.cnflTrait = cnflTrait;
    }
  }
}
