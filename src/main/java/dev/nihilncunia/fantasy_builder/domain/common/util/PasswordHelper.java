package dev.nihilncunia.fantasy_builder.domain.common.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * 비밀번호 해싱 유틸리티
 * 
 * <p>
 * 비밀번호 해싱 및 검증을 담당합니다.
 * BCrypt 알고리즘을 사용합니다.
 */
@Component
@RequiredArgsConstructor
public class PasswordHelper {

  private final PasswordEncoder passwordEncoder;

  /**
   * 비밀번호 해싱
   * 
   * @param rawPassword 평문 비밀번호
   * @return 해싱된 비밀번호
   */
  public String encode(String rawPassword) {
    if (rawPassword == null || rawPassword.isEmpty()) {
      throw new IllegalArgumentException("비밀번호는 필수입니다.");
    }
    return passwordEncoder.encode(rawPassword);
  }

  /**
   * 비밀번호 검증
   * 
   * @param rawPassword 평문 비밀번호
   * @param encodedPassword 해싱된 비밀번호
   * @return 일치 여부
   */
  public boolean matches(String rawPassword, String encodedPassword) {
    if (rawPassword == null || encodedPassword == null) {
      return false;
    }
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}
