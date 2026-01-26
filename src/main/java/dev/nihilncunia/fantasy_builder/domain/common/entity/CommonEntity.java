package dev.nihilncunia.fantasy_builder.domain.common.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonEntity {

  @Column(name = "use_yn", length = 1)
  @ColumnDefault("'Y'") // DDL 생성 시 기본값
  protected String useYn = "Y";

  @Column(name = "shrn_yn", length = 1)
  @ColumnDefault("'N'") // DDL 생성 시 기본값
  protected String shrnYn = "N";

  @Column(name = "del_yn", length = 1)
  @ColumnDefault("'N'") // DDL 생성 시 기본값
  protected String delYn = "N";

  @Column(name = "crt_no")
  protected Long crtNo;

  @Column(name = "crt_dt", updatable = false)
  @CreatedDate
  protected LocalDateTime crtDt;

  @Column(name = "updt_no")
  protected Long updtNo;

  @Column(name = "updt_dt")
  @LastModifiedDate
  protected LocalDateTime updtDt;

  @Column(name = "del_no")
  protected Long delNo;

  @Column(name = "del_dt")
  protected LocalDateTime delDt;

  public void delete(Long deleterNo) {
    this.useYn = "N";
    this.delYn = "Y";
    this.delNo = deleterNo;
    this.delDt = LocalDateTime.now();
    this.updtNo = deleterNo;
    this.updtDt = LocalDateTime.now();
  }

  // Setter 메서드 (Lombok @Setter가 제대로 작동하지 않을 경우를 대비)
  public void setCrtNo(Long crtNo) {
    this.crtNo = crtNo;
  }

  public void setUpdtNo(Long updtNo) {
    this.updtNo = updtNo;
  }
}
