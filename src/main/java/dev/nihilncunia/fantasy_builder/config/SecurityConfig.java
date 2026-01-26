package dev.nihilncunia.fantasy_builder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.nihilncunia.fantasy_builder.domain.auth.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
 * Security 설정
 * 
 * <p>
 * Spring Security 설정을 담당합니다.
 * JWT 인증 필터를 사용하여 인증을 처리합니다.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  /**
   * PasswordEncoder Bean 생성
   * 
   * @return BCryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * SecurityFilterChain 설정
   * 
   * <p>
   * JWT 인증 필터를 추가하고, Public 엔드포인트와 인증 필요한 엔드포인트를 설정합니다.
   * 
   * @param http HttpSecurity
   * @return SecurityFilterChain
   * @throws Exception 설정 오류
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            // Public 엔드포인트 (인증 불필요)
            .requestMatchers("/auth/signin", "/auth/signup", "/auth/refresh",
                "/auth/forgot-password", "/auth/reset-password")
            .permitAll()
            // Swagger UI 및 API 문서
            .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/docs/**", "/v3/api-docs/**")
            .permitAll()
            // 나머지 모든 요청은 인증 필요
            .anyRequest().authenticated())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
