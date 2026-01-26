package dev.nihilncunia.fantasy_builder.domain.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.nihilncunia.fantasy_builder.domain.common.response.ApiResponse;
import dev.nihilncunia.fantasy_builder.domain.common.response.ResponseCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * 전역 예외 처리 핸들러
 * 
 * <p>
 * 모든 컨트롤러에서 발생하는 예외를 일관된 형식으로 처리합니다.
 * ApiResponse를 사용하여 모든 에러 응답을 표준화합니다.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * EntityNotFoundException 처리
   * 
   * @param e EntityNotFoundException
   * @return ApiResponse<Void>
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleEntityNotFound(EntityNotFoundException e) {
    log.warn("Entity not found: {}", e.getMessage());
    return ResponseEntity.ok(ApiResponse.error(ResponseCode.NOT_FOUND, e.getMessage()));
  }

  /**
   * MethodArgumentNotValidException 처리 (입력값 검증 실패)
   * 
   * @param e MethodArgumentNotValidException
   * @return ApiResponse<Void>
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
    log.warn("Validation error: {}", e.getMessage());
    
    // 첫 번째 검증 오류 메시지 추출
    String errorMessage = e.getBindingResult().getFieldErrors().stream()
        .findFirst()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .orElse("입력값 검증 실패");
    
    return ResponseEntity.ok(ApiResponse.error(ResponseCode.VALIDATION_ERROR, errorMessage));
  }

  /**
   * IllegalArgumentException 처리 (잘못된 인자)
   * 
   * @param e IllegalArgumentException
   * @return ApiResponse<Void>
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException e) {
    log.warn("Illegal argument: {}", e.getMessage());
    return ResponseEntity.ok(ApiResponse.error(ResponseCode.BAD_REQUEST, e.getMessage()));
  }

  /**
   * BusinessException 처리 (커스텀 비즈니스 예외)
   * 
   * @param e BusinessException
   * @return ApiResponse<Void>
   */
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
    log.warn("Business exception: {} - {}", e.getResponseCode(), e.getMessage());
    return ResponseEntity.ok(ApiResponse.error(e.getResponseCode(), e.getMessage()));
  }

  /**
   * 기타 예외 처리 (모든 예외의 최종 처리)
   * 
   * @param e Exception
   * @return ApiResponse<Void>
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
    log.error("Unexpected error occurred", e);
    return ResponseEntity.ok(ApiResponse.error(
        ResponseCode.INTERNAL_SERVER_ERROR,
        "서버 내부 오류가 발생했습니다. 관리자에게 문의하세요."
    ));
  }
}
