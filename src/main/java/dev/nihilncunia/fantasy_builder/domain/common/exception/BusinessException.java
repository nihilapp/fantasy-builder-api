package dev.nihilncunia.fantasy_builder.domain.common.exception;

import dev.nihilncunia.fantasy_builder.domain.common.response.ResponseCode;

/**
 * 비즈니스 예외
 * 
 * <p>
 * 비즈니스 로직에서 발생하는 예외를 처리하기 위한 커스텀 예외 클래스입니다.
 * ResponseCode를 포함하여 일관된 에러 응답을 제공합니다.
 */
public class BusinessException extends RuntimeException {

  private final ResponseCode responseCode;

  /**
   * BusinessException 생성자
   * 
   * @param responseCode 응답 코드
   * @param message 에러 메시지
   */
  public BusinessException(ResponseCode responseCode, String message) {
    super(message);
    this.responseCode = responseCode;
  }

  /**
   * BusinessException 생성자 (원인 예외 포함)
   * 
   * @param responseCode 응답 코드
   * @param message 에러 메시지
   * @param cause 원인 예외
   */
  public BusinessException(ResponseCode responseCode, String message, Throwable cause) {
    super(message, cause);
    this.responseCode = responseCode;
  }

  /**
   * 응답 코드 반환
   * 
   * @return ResponseCode
   */
  public ResponseCode getResponseCode() {
    return responseCode;
  }
}
