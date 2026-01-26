package dev.nihilncunia.fantasy_builder.domain.common.response;

import lombok.Getter;

/**
 * 응답 코드 Enum
 * 
 * <p>
 * 모든 API 응답에서 사용되는 응답 코드를 정의합니다.
 * HTTP 상태 코드의 영문 이름을 기준으로 정의되었습니다.
 * 
 * <p>
 * 이 프로젝트는 모든 응답을 HTTP 200 상태 코드로 반환하며,
 * 실제 성공/실패 여부는 응답 본문의 {@code code} 필드로 구분합니다.
 * 
 * @author api-center
 */
@Getter
public enum ResponseCode {

  // ============================================
  // 성공 응답 (2xx)
  // ============================================

  /**
   * 200 - 요청 성공
   */
  OK("OK", "요청 성공"),

  /**
   * 201 - 리소스 생성 성공
   */
  CREATED("CREATED", "리소스 생성 성공"),

  /**
   * 202 - 요청 수락됨 (비동기 처리)
   */
  ACCEPTED("ACCEPTED", "요청 수락됨 (비동기 처리)"),

  /**
   * 204 - 성공했으나 반환할 내용 없음
   */
  NO_CONTENT("NO_CONTENT", "성공했으나 반환할 내용 없음"),

  // ============================================
  // 클라이언트 에러 (4xx)
  // ============================================

  /**
   * 400 - 잘못된 요청
   */
  BAD_REQUEST("BAD_REQUEST", "잘못된 요청"),

  /**
   * 401 - 인증 필요 (로그인이 필요함)
   */
  UNAUTHORIZED("UNAUTHORIZED", "인증 필요 (로그인이 필요함)"),

  /**
   * 403 - 권한 없음 (접근 권한이 없음)
   */
  FORBIDDEN("FORBIDDEN", "권한 없음 (접근 권한이 없음)"),

  /**
   * 404 - 리소스를 찾을 수 없음
   */
  NOT_FOUND("NOT_FOUND", "리소스를 찾을 수 없음"),

  /**
   * 405 - 허용되지 않은 HTTP 메서드
   */
  METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "허용되지 않은 HTTP 메서드"),

  /**
   * 409 - 리소스 충돌 (중복 등)
   */
  CONFLICT("CONFLICT", "리소스 충돌 (중복 등)"),

  /**
   * 422 - 입력값 검증 실패
   */
  VALIDATION_ERROR("VALIDATION_ERROR", "입력값 검증 실패"),

  /**
   * 422 - 처리할 수 없는 엔티티
   */
  UNPROCESSABLE_ENTITY("UNPROCESSABLE_ENTITY", "처리할 수 없는 엔티티"),

  // ============================================
  // 권한 관련 에러 코드
  // ============================================

  /**
   * 소유자가 아님 (수정/삭제 권한이 없음)
   */
  NOT_OWNER("NOT_OWNER", "소유자가 아님 (수정/삭제 권한이 없음)"),

  /**
   * 비공개 리소스임 (공유되지 않은 리소스)
   */
  NOT_PUBLIC("NOT_PUBLIC", "비공개 리소스임 (공유되지 않은 리소스)"),

  // ============================================
  // 인증 관련 에러 코드
  // ============================================

  /**
   * 잘못된 인증 정보 (이메일 또는 비밀번호가 올바르지 않음)
   */
  INVALID_CREDENTIALS("INVALID_CREDENTIALS", "잘못된 인증 정보 (이메일 또는 비밀번호가 올바르지 않음)"),

  /**
   * 이메일이 이미 존재함
   */
  EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "이메일이 이미 존재함"),

  /**
   * 잘못된 토큰
   */
  INVALID_TOKEN("INVALID_TOKEN", "잘못된 토큰"),

  /**
   * 잘못된 비밀번호
   */
  INVALID_PASSWORD("INVALID_PASSWORD", "잘못된 비밀번호"),

  /**
   * 사용자를 찾을 수 없음
   */
  USER_NOT_FOUND("USER_NOT_FOUND", "사용자를 찾을 수 없음"),

  /**
   * 토큰이 만료됨
   */
  TOKEN_EXPIRED("TOKEN_EXPIRED", "토큰이 만료됨"),

  /**
   * 계정이 잠금됨
   */
  ACCOUNT_LOCKED("ACCOUNT_LOCKED", "계정이 잠금됨"),

  // ============================================
  // 리소스 관련 에러 코드
  // ============================================

  /**
   * 프로젝트를 찾을 수 없음
   */
  PROJECT_NOT_FOUND("PROJECT_NOT_FOUND", "프로젝트를 찾을 수 없음"),

  /**
   * 스킬을 찾을 수 없음
   */
  SKILL_NOT_FOUND("SKILL_NOT_FOUND", "스킬을 찾을 수 없음"),

  /**
   * 프로젝트 종속 스킬을 찾을 수 없음
   */
  PROJECT_SKILL_NOT_FOUND("PROJECT_SKILL_NOT_FOUND", "프로젝트 종속 스킬을 찾을 수 없음"),

  /**
   * 특성을 찾을 수 없음
   */
  TRAIT_NOT_FOUND("TRAIT_NOT_FOUND", "특성을 찾을 수 없음"),

  /**
   * 프로젝트 종속 특성을 찾을 수 없음
   */
  PROJECT_TRAIT_NOT_FOUND("PROJECT_TRAIT_NOT_FOUND", "프로젝트 종속 특성을 찾을 수 없음"),

  // ============================================
  // 서버 에러 (5xx)
  // ============================================

  /**
   * 500 - 내부 서버 오류
   */
  INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "내부 서버 오류"),

  /**
   * 502 - 게이트웨이 오류
   */
  BAD_GATEWAY("BAD_GATEWAY", "게이트웨이 오류"),

  /**
   * 503 - 서비스 사용 불가
   */
  SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", "서비스 사용 불가"),

  // ============================================
  // 기타 에러
  // ============================================

  /**
   * 일반적인 에러 (구체적인 에러 코드를 사용할 수 없을 때)
   */
  ERROR("ERROR", "일반적인 에러");

  private final String value;
  private final String description;

  ResponseCode(String value, String description) {
    this.value = value;
    this.description = description;
  }

  /**
   * 문자열 값으로 ResponseCode를 찾습니다.
   * 
   * @param value 문자열 값
   * @return ResponseCode Enum, 없으면 null
   */
  public static ResponseCode fromValue(String value) {
    if (value == null) {
      return null;
    }
    for (ResponseCode code : ResponseCode.values()) {
      if (code.value.equalsIgnoreCase(value)) {
        return code;
      }
    }
    return null;
  }

  /**
   * Enum 값을 문자열로 반환합니다.
   * 
   * @return 응답 코드 문자열 값
   */
  @Override
  public String toString() {
    return value;
  }
}
