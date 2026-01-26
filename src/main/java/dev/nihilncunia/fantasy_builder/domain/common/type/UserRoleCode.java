package dev.nihilncunia.fantasy_builder.domain.common.type;

import lombok.Getter;

/**
 * 사용자 역할 코드
 * 
 * <p>
 * 일반 클래스로 구현된 역할 코드입니다.
 * 
 * @author api-center
 */
@Getter
public enum UserRoleCode {

  /**
   * 일반 사용자
   */
  USER("ROLE_USER", "일반 사용자"),

  /**
   * 관리자
   */
  ADMIN("ROLE_ADMIN", "관리자");

  private final String value;
  private final String description;

  UserRoleCode(String value, String description) {
    this.value = value;
    this.description = description;
  }

  /**
   * 문자열 값으로 UserRoleCode를 찾습니다.
   * 
   * @param value 문자열 값
   * @return UserRoleCode 인스턴스, 없으면 null
   */
  public static UserRoleCode fromValue(String value) {
    if (value == null) {
      return null;
    }
    for (UserRoleCode role : UserRoleCode.values()) {
      if (role.value.equalsIgnoreCase(value)) {
        return role;
      }
    }
    return null;
  }

  /**
   * Enum 값을 문자열로 반환합니다.
   * 
   * @return 역할 코드 문자열 값
   */
  @Override
  public String toString() {
    return value;
  }
}
