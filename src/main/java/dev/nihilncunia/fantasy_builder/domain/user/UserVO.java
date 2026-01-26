package dev.nihilncunia.fantasy_builder.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import dev.nihilncunia.fantasy_builder.domain.common.entity.CommonVO;
import dev.nihilncunia.fantasy_builder.domain.common.type.UserRoleCode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVO extends CommonVO {

  // 검색용 필드
  private List<Long> userNoList;
  private String searchType;
  private String searchKeyword;
  private String password; // 요청에서 들어오는 비밀번호 필드
  private String currentPassword; // 비밀번호 변경 시 현재 비밀번호
  private String newPassword; // 비밀번호 변경/재설정 시 새 비밀번호
  private String token; // 비밀번호 재설정용 토큰
  private Integer totalCnt;
  private Integer rowNo;

  private Long userNo;
  private String userEml;
  private String userNm;
  private UserRoleCode userRole;
  private String proflImgUrl;
  private String biogp;
  private String enpswd; // DB상의 비밀번호
  private String reshToken;
  private String acntLckYn;
  private Integer lgnFailNmtm;
  private LocalDateTime lastLgnDt;
  private String lastLgnIp;
  private LocalDateTime lastPswdChgDt;
  private String emlAuthYn;
  private String mktRecpAgreYn;

  // [기존] 상세 조회용 생성자 (Entity -> VO)
  public UserVO(UserEntity userEntity) {
    this.mapFromEntity(userEntity);
  }

  // 1. [추가] 목록 조회용 생성자 (Entity + 계산된 값)
  public UserVO(UserEntity userEntity, Integer totalCnt, Integer rowNo) {
    this.mapFromEntity(userEntity);
    this.totalCnt = totalCnt;
    this.rowNo = rowNo;
  }

  // (편의상 분리한 매핑 메소드)
  private void mapFromEntity(UserEntity userEntity) {
    this.userNo = userEntity.getUserNo();
    this.userEml = userEntity.getUserEml();
    this.userNm = userEntity.getUserNm();
    this.userRole = userEntity.getUserRole();
    this.proflImgUrl = userEntity.getProflImgUrl();
    this.biogp = userEntity.getBiogp();
    this.enpswd = userEntity.getEnpswd();
    this.reshToken = userEntity.getReshToken();
    this.acntLckYn = userEntity.getAcntLckYn();
    this.lgnFailNmtm = userEntity.getLgnFailNmtm();
    this.lastLgnDt = userEntity.getLastLgnDt();
    this.lastLgnIp = userEntity.getLastLgnIp();
    this.lastPswdChgDt = userEntity.getLastPswdChgDt();
    this.emlAuthYn = userEntity.getEmlAuthYn();
    this.mktRecpAgreYn = userEntity.getMktRecpAgreYn();
    this.useYn = userEntity.getUseYn();
    this.shrnYn = userEntity.getShrnYn();
    this.delYn = userEntity.getDelYn();
    this.crtNo = userEntity.getCrtNo();
    this.crtDt = userEntity.getCrtDt();
    this.updtNo = userEntity.getUpdtNo();
    this.updtDt = userEntity.getUpdtDt();
    this.delNo = userEntity.getDelNo();
    this.delDt = userEntity.getDelDt();
  }

  // 2. [추가] 신규 저장용 (VO -> Entity)
  public UserEntity toEntity() {
    return UserEntity.builder()
        .userEml(this.userEml)
        .userNm(this.userNm)
        .enpswd(this.enpswd)
        .userRole(this.userRole)
        .biogp(this.biogp)
        .proflImgUrl(this.proflImgUrl)
        .mktRecpAgreYn(this.mktRecpAgreYn)
        .build();
  }

  // 3. [추가] 수정용 (VO의 값으로 Entity 업데이트)
  // 주의: 비밀번호 변경은 UserService에서 직접 처리합니다 (updtNo 전달을 위해)
  public void updateEntity(UserEntity entity) {
    // Entity의 수정 메소드를 호출합니다.
    entity.updateProfile(this.userNm, this.biogp);
    // 비밀번호 변경은 UserService.updateUser()에서 처리합니다

    // 관리자 권한으로 상태 변경이 필요하다면
    if (this.getUseYn() != null) {
      // Entity에는 setter가 없으므로, Entity에 changeStatus() 같은 메소드가 필요할 수 있음
      // 혹은 급한 경우, Entity에 @Setter를 특정 필드만 열어줄 수도 있습니다.
    }
  }
}
