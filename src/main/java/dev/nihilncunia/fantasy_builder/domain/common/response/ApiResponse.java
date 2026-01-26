package dev.nihilncunia.fantasy_builder.domain.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API 응답 래퍼 클래스
 * 
 * <p>
 * 모든 API 응답은 이 클래스로 래핑되어 반환됩니다.
 * 제네릭 타입을 지원하여 다양한 데이터 타입을 포함할 수 있습니다.
 * 
 * @param <T> 응답 데이터 타입
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
  
  private T data;              // 응답 데이터 (제네릭 타입)
  private boolean error;       // 에러 여부 (true: 에러, false: 성공)
  private String code;         // 응답 코드 (ResponseCode Enum의 value)
  private String message;      // 응답 메시지

  /**
   * 성공 응답 생성 (기본 코드: OK)
   * 
   * @param <T> 응답 데이터 타입
   * @param data 응답 데이터
   * @return ApiResponse 인스턴스
   */
  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(
      data,
      false,
      ResponseCode.OK.getValue(),
      ResponseCode.OK.getDescription()
    );
  }

  /**
   * 성공 응답 생성 (커스텀 메시지)
   * 
   * @param <T> 응답 데이터 타입
   * @param data 응답 데이터
   * @param message 커스텀 메시지
   * @return ApiResponse 인스턴스
   */
  public static <T> ApiResponse<T> success(T data, String message) {
    return new ApiResponse<>(
      data,
      false,
      ResponseCode.OK.getValue(),
      message
    );
  }

  /**
   * 성공 응답 생성 (Void 타입, 커스텀 메시지)
   * 
   * @param message 커스텀 메시지
   * @return ApiResponse<Void> 인스턴스
   */
  public static ApiResponse<Void> successVoid(String message) {
    return new ApiResponse<>(
      null,
      false,
      ResponseCode.OK.getValue(),
      message
    );
  }

  /**
   * 성공 응답 생성 (커스텀 코드)
   * 
   * @param <T> 응답 데이터 타입
   * @param code 응답 코드
   * @param data 응답 데이터
   * @return ApiResponse 인스턴스
   */
  public static <T> ApiResponse<T> success(ResponseCode code, T data) {
    return new ApiResponse<>(
      data,
      false,
      code.getValue(),
      code.getDescription()
    );
  }

  /**
   * 에러 응답 생성
   * 
   * @param <T> 응답 데이터 타입
   * @param code 응답 코드
   * @param message 에러 메시지
   * @return ApiResponse 인스턴스
   */
  public static <T> ApiResponse<T> error(ResponseCode code, String message) {
    return new ApiResponse<>(
      null,
      true,
      code.getValue(),
      message
    );
  }
}
