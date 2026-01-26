package dev.nihilncunia.fantasy_builder.domain.auth;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.nihilncunia.fantasy_builder.domain.common.util.CookieHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT 인증 필터
 * 
 * <p>
 * 쿠키에서 JWT 토큰을 읽어서 검증하고, SecurityContext에 인증 정보를 설정합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtHelper jwtHelper;
  private final CookieHelper cookieHelper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    try {
      // 쿠키에서 Access Token 읽기
      String accessToken = cookieHelper.getAccessTokenFromCookie(request);

      if (accessToken != null && jwtHelper.validateToken(accessToken, "access")) {
        // 토큰에서 사용자 정보 추출
        Long userNo = jwtHelper.getUserIdFromToken(accessToken);
        String userEml = jwtHelper.getEmailFromToken(accessToken);
        String userRole = jwtHelper.getRoleFromToken(accessToken);

        if (userNo != null && userEml != null) {
          // 권한 설정
          ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
          if (userRole != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole));
          }

          // Authentication 객체 생성
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              userNo, // principal (사용자 번호)
              null, // credentials
              authorities);

          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          // SecurityContext에 인증 정보 설정
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (Exception e) {
      log.error("JWT 인증 처리 중 오류 발생", e);
      // 인증 실패 시에도 요청은 계속 진행 (다른 필터나 인증 메커니즘이 처리할 수 있도록)
    }

    filterChain.doFilter(request, response);
  }
}
