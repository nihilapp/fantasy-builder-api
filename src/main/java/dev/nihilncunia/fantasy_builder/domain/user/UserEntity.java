package dev.nihilncunia.fantasy_builder.domain.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import dev.nihilncunia.fantasy_builder.domain.common.entity.CommonEntity;
import dev.nihilncunia.fantasy_builder.domain.common.type.UserRoleCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users", indexes = {
    @Index(name = "idx_users_user_eml", columnList = "user_eml")
})
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends CommonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_no")
  private Long userNo;

  @Column(name = "user_eml")
  private String userEml;

  @Column(name = "user_nm")
  private String userNm;

  @Column(name = "user_role")
  @Enumerated(EnumType.STRING)
  private UserRoleCode userRole;

  @Column(name = "profl_img_url")
  private String proflImgUrl;

  @Column(name = "biogp")
  private String biogp;

  @Column(name = "enpswd")
  private String enpswd;

  @Column(name = "resh_token")
  private String reshToken;

  @Column(name = "acnt_lck_yn", length = 1)
  @ColumnDefault("'N'")
  private String acntLckYn = "N";

  @Column(name = "lgn_fail_nmtm")
  @ColumnDefault("0")
  private Integer lgnFailNmtm = 0;

  @Column(name = "last_lgn_dt")
  private LocalDateTime lastLgnDt;

  @Column(name = "last_lgn_ip")
  private String lastLgnIp;

  @Column(name = "last_pswd_chg_dt")
  private LocalDateTime lastPswdChgDt;

  @Column(name = "eml_auth_yn", length = 1)
  @ColumnDefault("'N'")
  private String emlAuthYn = "N";

  @Column(name = "mkt_recp_agre_yn", length = 1)
  @ColumnDefault("'N'")
  private String mktRecpAgreYn = "N";

  // ▼▼▼ [추가] 빌더 패턴용 생성자 (필수 값 위주) ▼▼▼
  @Builder
  public UserEntity(String userEml, String userNm, String enpswd, UserRoleCode userRole, String biogp,
      String proflImgUrl, String mktRecpAgreYn) {
    this.userEml = userEml;
    this.userNm = userNm;
    this.enpswd = enpswd;
    this.userRole = userRole != null ? userRole : UserRoleCode.USER; // 기본값 USER
    this.biogp = biogp;
    this.proflImgUrl = proflImgUrl;
    this.mktRecpAgreYn = mktRecpAgreYn != null ? mktRecpAgreYn : "N";
  }

  // ▼▼▼ [추가] 비즈니스 로직 (수정용) ▼▼▼
  public void updateProfile(String userNm, String biogp) {
    if (userNm != null)
      this.userNm = userNm;
    if (biogp != null)
      this.biogp = biogp;
  }

  // 비밀번호 변경
  public void changePassword(String newEncryptedPassword, Long updtNo) {
    this.enpswd = newEncryptedPassword;
    this.lastPswdChgDt = LocalDateTime.now(); // 비밀번호 변경일 자동 갱신
    this.updtNo = updtNo; // 수정자 번호 업데이트
    this.updtDt = LocalDateTime.now(); // 수정 일시 업데이트
  }

  // 로그인 성공 처리
  public void recordLoginSuccess(String clientIp) {
    this.lgnFailNmtm = 0; // 실패 횟수 초기화
    this.lastLgnDt = LocalDateTime.now();
    this.lastLgnIp = clientIp;
  }

  // 로그인 실패 처리
  public void recordLoginFailure() {
    if (this.lgnFailNmtm == null) {
      this.lgnFailNmtm = 0;
    }
    this.lgnFailNmtm++;
  }

  // 계정 잠금 처리
  public void lockAccount() {
    this.acntLckYn = "Y";
  }

  // Refresh Token 저장
  public void setReshToken(String refreshToken) {
    this.reshToken = refreshToken;
  }

  // Refresh Token 삭제
  public void clearReshToken() {
    this.reshToken = null;
  }
}