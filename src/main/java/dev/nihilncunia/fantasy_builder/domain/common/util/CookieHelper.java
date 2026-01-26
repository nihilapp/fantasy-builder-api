package dev.nihilncunia.fantasy_builder.domain.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 쿠키 유틸리티
 * 
 * <p>
 * HTTP 쿠키 생성 및 관리를 담당합니다.
 */
@Component
public class CookieHelper {

  @Value("${jwt.cookie.access-token-name:access_token}")
  private String accessTokenCookieName;

  @Value("${jwt.cookie.refresh-token-name:refresh_token}")
  private String refreshTokenCookieName;

  @Value("${jwt.cookie.http-only:true}")
  private Boolean httpOnly;

  @Value("${jwt.cookie.secure:true}")
  private Boolean secure;

  @Value("${jwt.cookie.same-site:Strict}")
  private String sameSite;

  @Value("${jwt.cookie.path:/}")
  private String cookiePath;

  /**
   * Access Token 쿠키 생성
   * 
   * @param token Access Token
   * @param maxAge 쿠키 만료 시간 (초)
   * @return ResponseCookie
   */
  public ResponseCookie createAccessTokenCookie(String token, long maxAge) {
    return createCookie(accessTokenCookieName, token, maxAge);
  }

  /**
   * Refresh Token 쿠키 생성
   * 
   * @param token Refresh Token
   * @param maxAge 쿠키 만료 시간 (초)
   * @return ResponseCookie
   */
  public ResponseCookie createRefreshTokenCookie(String token, long maxAge) {
    return createCookie(refreshTokenCookieName, token, maxAge);
  }

  /**
   * 쿠키 생성
   * 
   * @param name 쿠키 이름
   * @param value 쿠키 값
   * @param maxAge 쿠키 만료 시간 (초)
   * @return ResponseCookie
   */
  private ResponseCookie createCookie(String name, String value, long maxAge) {
    ResponseCookie.ResponseCookieBuilder builder = ResponseCookie.from(name, value)
        .path(cookiePath)
        .maxAge(maxAge)
        .httpOnly(httpOnly)
        .secure(secure)
        .sameSite(sameSite);

    return builder.build();
  }

  /**
   * Access Token 쿠키 삭제
   * 
   * @return ResponseCookie (maxAge = 0)
   */
  public ResponseCookie deleteAccessTokenCookie() {
    return ResponseCookie.from(accessTokenCookieName, "")
        .path(cookiePath)
        .maxAge(0)
        .httpOnly(httpOnly)
        .secure(secure)
        .sameSite(sameSite)
        .build();
  }

  /**
   * Refresh Token 쿠키 삭제
   * 
   * @return ResponseCookie (maxAge = 0)
   */
  public ResponseCookie deleteRefreshTokenCookie() {
    return ResponseCookie.from(refreshTokenCookieName, "")
        .path(cookiePath)
        .maxAge(0)
        .httpOnly(httpOnly)
        .secure(secure)
        .sameSite(sameSite)
        .build();
  }

  /**
   * 요청에서 Access Token 쿠키 읽기
   * 
   * @param request HttpServletRequest
   * @return Access Token 또는 null
   */
  public String getAccessTokenFromCookie(HttpServletRequest request) {
    return getCookieValue(request, accessTokenCookieName);
  }

  /**
   * 요청에서 Refresh Token 쿠키 읽기
   * 
   * @param request HttpServletRequest
   * @return Refresh Token 또는 null
   */
  public String getRefreshTokenFromCookie(HttpServletRequest request) {
    return getCookieValue(request, refreshTokenCookieName);
  }

  /**
   * 요청에서 쿠키 값 읽기
   * 
   * @param request HttpServletRequest
   * @param cookieName 쿠키 이름
   * @return 쿠키 값 또는 null
   */
  private String getCookieValue(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookieName.equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }

  /**
   * 응답에 쿠키 추가
   * 
   * @param response HttpServletResponse
   * @param cookie ResponseCookie
   */
  public void addCookieToResponse(jakarta.servlet.http.HttpServletResponse response, ResponseCookie cookie) {
    response.addHeader("Set-Cookie", cookie.toString());
  }
}
