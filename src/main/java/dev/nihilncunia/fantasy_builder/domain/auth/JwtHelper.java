package dev.nihilncunia.fantasy_builder.domain.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * JWT 토큰 생성 및 검증 유틸리티
 * 
 * <p>
 * JWT 토큰의 생성, 검증, 파싱을 담당합니다.
 */
@Component
public class JwtHelper {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.access-token-expiration}")
  private Long accessTokenExpiration;

  @Value("${jwt.refresh-token-expiration}")
  private Long refreshTokenExpiration;

  /**
   * SecretKey 생성
   * 
   * @return SecretKey
   */
  private SecretKey getSigningKey() {
    byte[] keyBytes = secret.getBytes();
    return Keys.hmacShaKeyFor(keyBytes);
  }

  /**
   * Access Token 생성
   * 
   * @param userNo 사용자 번호
   * @param userEml 사용자 이메일
   * @param userRole 사용자 역할
   * @return Access Token
   */
  public String generateAccessToken(Long userNo, String userEml, String userRole) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userNo", userNo);
    claims.put("userEml", userEml);
    claims.put("userRole", userRole);
    claims.put("type", "access");

    return createToken(claims, userNo.toString(), accessTokenExpiration);
  }

  /**
   * Refresh Token 생성
   * 
   * @param userNo 사용자 번호
   * @return Refresh Token
   */
  public String generateRefreshToken(Long userNo) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userNo", userNo);
    claims.put("type", "refresh");

    return createToken(claims, userNo.toString(), refreshTokenExpiration);
  }

  /**
   * 토큰 생성
   * 
   * @param claims 클레임
   * @param subject 주제 (사용자 번호)
   * @param expiration 만료 시간 (밀리초)
   * @return JWT 토큰
   */
  private String createToken(Map<String, Object> claims, String subject, Long expiration) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiration);

    return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(now)
        .expiration(expiryDate)
        .signWith(getSigningKey())
        .compact();
  }

  /**
   * 토큰에서 사용자 번호 추출
   * 
   * @param token JWT 토큰
   * @return 사용자 번호
   */
  public Long getUserIdFromToken(String token) {
    String subject = getClaimFromToken(token, Claims::getSubject);
    return subject != null ? Long.parseLong(subject) : null;
  }

  /**
   * 토큰에서 이메일 추출
   * 
   * @param token JWT 토큰
   * @return 이메일
   */
  public String getEmailFromToken(String token) {
    return getClaimFromToken(token, claims -> claims.get("userEml", String.class));
  }

  /**
   * 토큰에서 역할 추출
   * 
   * @param token JWT 토큰
   * @return 역할
   */
  public String getRoleFromToken(String token) {
    return getClaimFromToken(token, claims -> claims.get("userRole", String.class));
  }

  /**
   * 토큰에서 토큰 타입 추출 (access 또는 refresh)
   * 
   * @param token JWT 토큰
   * @return 토큰 타입
   */
  public String getTokenTypeFromToken(String token) {
    return getClaimFromToken(token, claims -> claims.get("type", String.class));
  }

  /**
   * 토큰에서 만료일 추출
   * 
   * @param token JWT 토큰
   * @return 만료일
   */
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  /**
   * 토큰에서 클레임 추출
   * 
   * @param <T> 반환 타입
   * @param token JWT 토큰
   * @param claimsResolver 클레임 리졸버
   * @return 클레임 값
   */
  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  /**
   * 토큰에서 모든 클레임 추출
   * 
   * @param token JWT 토큰
   * @return Claims
   */
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  /**
   * 토큰 만료 여부 확인
   * 
   * @param token JWT 토큰
   * @return 만료 여부
   */
  public Boolean isTokenExpired(String token) {
    try {
      final Date expiration = getExpirationDateFromToken(token);
      return expiration.before(new Date());
    } catch (Exception e) {
      return true;
    }
  }

  /**
   * 토큰 검증
   * 
   * @param token JWT 토큰
   * @param expectedType 예상 토큰 타입 (access 또는 refresh)
   * @return 검증 성공 여부
   */
  public Boolean validateToken(String token, String expectedType) {
    try {
      if (isTokenExpired(token)) {
        return false;
      }

      String tokenType = getTokenTypeFromToken(token);
      if (expectedType != null && !expectedType.equals(tokenType)) {
        return false;
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 토큰 검증 (타입 체크 없음)
   * 
   * @param token JWT 토큰
   * @return 검증 성공 여부
   */
  public Boolean validateToken(String token) {
    return validateToken(token, null);
  }
}
