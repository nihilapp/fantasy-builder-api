package dev.nihilncunia.fantasy_builder.domain.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import dev.nihilncunia.fantasy_builder.domain.common.exception.BusinessException;
import dev.nihilncunia.fantasy_builder.domain.common.response.ResponseCode;

/**
 * 비밀번호 정책 유틸리티
 * 
 * <p>
 * 비밀번호 강도 검증을 담당합니다.
 */
@Component
public class PasswordPolicyHelper {

  @Value("${security.password.min-length:8}")
  private int minLength;

  @Value("${security.password.require-uppercase:true}")
  private boolean requireUppercase;

  @Value("${security.password.require-lowercase:true}")
  private boolean requireLowercase;

  @Value("${security.password.require-digit:true}")
  private boolean requireDigit;

  @Value("${security.password.require-special-char:true}")
  private boolean requireSpecialChar;

  /**
   * 비밀번호 강도 검증
   * 
   * @param password 비밀번호
   * @throws BusinessException 비밀번호 정책 위반 시
   */
  public void validatePassword(String password) {
    if (password == null || password.isEmpty()) {
      throw new BusinessException(ResponseCode.VALIDATION_ERROR, "비밀번호는 필수입니다.");
    }

    // 최소 길이 검증
    if (password.length() < minLength) {
      throw new BusinessException(ResponseCode.VALIDATION_ERROR,
          String.format("비밀번호는 최소 %d자 이상이어야 합니다.", minLength));
    }

    // 대문자 검증
    if (requireUppercase && !password.matches(".*[A-Z].*")) {
      throw new BusinessException(ResponseCode.VALIDATION_ERROR, "비밀번호에 대문자가 포함되어야 합니다.");
    }

    // 소문자 검증
    if (requireLowercase && !password.matches(".*[a-z].*")) {
      throw new BusinessException(ResponseCode.VALIDATION_ERROR, "비밀번호에 소문자가 포함되어야 합니다.");
    }

    // 숫자 검증
    if (requireDigit && !password.matches(".*[0-9].*")) {
      throw new BusinessException(ResponseCode.VALIDATION_ERROR, "비밀번호에 숫자가 포함되어야 합니다.");
    }

    // 특수문자 검증
    if (requireSpecialChar && !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
      throw new BusinessException(ResponseCode.VALIDATION_ERROR, "비밀번호에 특수문자가 포함되어야 합니다.");
    }
  }

  /**
   * 비밀번호 강도 검증 (예외 없이 boolean 반환)
   * 
   * @param password 비밀번호
   * @return 검증 통과 여부
   */
  public boolean isValidPassword(String password) {
    try {
      validatePassword(password);
      return true;
    } catch (BusinessException e) {
      return false;
    }
  }
}
