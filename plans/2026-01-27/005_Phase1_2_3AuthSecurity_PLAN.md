# Phase 1.2 & 1.3: 인증 및 보안 기능 구현 계획

**작성일**: 2026-01-27  
**시퀀스 번호**: 005  
**키워드**: Phase1_2_3AuthSecurity

---

## Goal
Phase 1.2 (인증 기능)과 Phase 1.3 (보안 기능)을 구현하여 사용자 인증 및 보안 기능을 완성합니다.

---

## Context
- **날짜**: 2026-01-27
- **원본 명령**: Phase 1.2와 Phase 1.3 진행
- **현재 상태**:
  - `PasswordHelper`는 이미 구현됨 (bcrypt 해싱 및 검증)
  - `UserEntity`에 필요한 필드들이 모두 존재 (reshToken, acntLckYn, lgnFailNmtm, lastLgnDt, lastLgnIp, lastPswdChgDt)
  - JWT 라이브러리 미추가
  - 이메일 발송 라이브러리 미추가
  - JWT 필터 미구현
  - 인증 관련 Service/Controller 미구현

---

## Strategy

### 1. 의존성 추가
- JWT 라이브러리: `io.jsonwebtoken:jjwt-api`, `io.jsonwebtoken:jjwt-impl`, `io.jsonwebtoken:jjwt-jackson`
- 이메일 발송: `org.springframework.boot:spring-boot-starter-mail`

### 2. JWT 유틸리티 구현
- `JwtHelper` 클래스 생성
  - Access Token 생성 (1시간 유효)
  - Refresh Token 생성 (1주일 유효)
  - 토큰 검증 및 파싱
  - 토큰에서 사용자 정보 추출

### 3. 인증 Service 구현
- `AuthService` 생성
  - `signin()`: 로그인 (비밀번호 검증, 계정 잠금 확인, 로그인 실패 횟수 관리, 토큰 생성)
  - `signup()`: 회원가입 (UserService.createUser와 통합 고려)
  - `refresh()`: 토큰 갱신
  - `signout()`: 로그아웃 (Refresh Token 무효화)
  - `forgotPassword()`: 비밀번호 재설정 요청 (이메일 발송)
  - `resetPassword()`: 비밀번호 재설정 처리
  - `changePassword()`: 비밀번호 변경 (현재 비밀번호 확인)

### 4. 인증 Controller 구현
- `AuthController` 생성
  - `POST /api/auth/signin` - 로그인 (토큰을 쿠키에 저장)
  - `POST /api/auth/signup` - 회원가입
  - `POST /api/auth/signout` - 로그아웃 (쿠키 삭제)
  - `POST /api/auth/refresh` - 토큰 갱신 (쿠키에서 Refresh Token 읽기, 새로운 토큰을 쿠키에 저장)
  - `POST /api/auth/forgot-password` - 비밀번호 재설정 요청
  - `POST /api/auth/reset-password` - 비밀번호 재설정
  - `POST /api/auth/change-password` - 비밀번호 변경

### 5. JWT 필터 구현
- `JwtAuthenticationFilter` 생성
  - 요청 쿠키에서 JWT 토큰 추출 (Access Token)
  - 토큰 검증
  - SecurityContext에 인증 정보 설정
  - 인증 실패 시 적절한 에러 응답

### 6. SecurityConfig 업데이트
- JWT 필터를 SecurityFilterChain에 추가
- Public 엔드포인트 설정 (`/api/auth/signin`, `/api/auth/signup`, `/api/auth/refresh`, `/api/auth/forgot-password`, `/api/auth/reset-password`)
- 인증 필요한 엔드포인트 설정

### 7. 보안 기능 구현
- 계정 잠금 기능: 로그인 실패 횟수 추적 및 계정 잠금 처리
- 비밀번호 정책: 비밀번호 강도 검증 (최소 길이, 복잡도)
- 비밀번호 변경 이력 관리: `lastPswdChgDt` 업데이트 (이미 UserEntity.changePassword에 구현됨)

### 8. 이메일 유틸리티 구현
- `EmailHelper` 생성
  - SMTP 설정 관리
  - 이메일 발송 함수
  - 비밀번호 재설정 링크 이메일 발송

### 9. VO/DTO 생성
- `SigninRequest`, `SigninResponse` (또는 `AuthVO`)
- `SignupRequest`, `SignupResponse`
- `RefreshRequest`, `RefreshResponse` (또는 쿠키에서 직접 읽기)
- `ForgotPasswordRequest`
- `ResetPasswordRequest`
- `ChangePasswordRequest`

### 10. 쿠키 유틸리티 구현
- 쿠키 생성 및 설정 유틸리티
  - HTTP-only 쿠키 생성
  - Secure 쿠키 설정 (HTTPS 환경)
  - SameSite 설정
  - 쿠키 만료 시간 설정

---

## Task List

### 1. 의존성 추가
- [ ] `build.gradle`에 JWT 라이브러리 추가
- [ ] `build.gradle`에 이메일 라이브러리 추가

### 2. JWT 유틸리티 구현
- [ ] `JwtHelper` 클래스 생성
  - [ ] Access Token 생성 메서드 (1시간)
  - [ ] Refresh Token 생성 메서드 (1주일)
  - [ ] 토큰 검증 메서드
  - [ ] 토큰에서 사용자 정보 추출 메서드
  - [ ] 토큰 만료 확인 메서드

### 3. 인증 VO/DTO 생성
- [ ] `SigninRequest` 생성
- [ ] `SigninResponse` 생성 (또는 `AuthVO`로 통합)
- [ ] `SignupRequest` 생성
- [ ] `RefreshRequest` 생성
- [ ] `RefreshResponse` 생성
- [ ] `ForgotPasswordRequest` 생성
- [ ] `ResetPasswordRequest` 생성
- [ ] `ChangePasswordRequest` 생성

### 4. 인증 Service 구현
- [ ] `AuthService` 생성
  - [ ] `signin()` 메서드 구현
    - [ ] 이메일로 사용자 조회
    - [ ] 계정 잠금 상태 확인
    - [ ] 비밀번호 검증
    - [ ] 로그인 실패 횟수 관리
    - [ ] 로그인 성공 시 실패 횟수 초기화, lastLgnDt, lastLgnIp 업데이트
    - [ ] Access Token 및 Refresh Token 생성
    - [ ] Refresh Token을 UserEntity에 저장
    - [ ] 토큰을 HTTP-only 쿠키에 저장 (응답에 쿠키 설정)
  - [ ] `signup()` 메서드 구현 (UserService.createUser 활용)
  - [ ] `refresh()` 메서드 구현
    - [ ] Refresh Token 검증
    - [ ] 새로운 Access Token 생성 (1시간)
    - [ ] 새로운 Refresh Token 생성 (1주일)
    - [ ] Refresh Token을 UserEntity에 저장
  - [ ] `signout()` 메서드 구현
    - [ ] Refresh Token 무효화 (UserEntity.reshToken = null)
  - [ ] `forgotPassword()` 메서드 구현
    - [ ] 이메일로 사용자 조회
    - [ ] 비밀번호 재설정 토큰 생성
    - [ ] 이메일 발송
  - [ ] `resetPassword()` 메서드 구현
    - [ ] 토큰 검증
    - [ ] 비밀번호 재설정
  - [ ] `changePassword()` 메서드 구현
    - [ ] 현재 비밀번호 확인
    - [ ] 비밀번호 강도 검증
    - [ ] 비밀번호 변경

### 5. 인증 Controller 구현
- [ ] `AuthController` 생성
  - [ ] `POST /api/auth/signin` 엔드포인트 (토큰을 쿠키에 저장)
  - [ ] `POST /api/auth/signup` 엔드포인트
  - [ ] `POST /api/auth/signout` 엔드포인트 (Authenticated, 쿠키 삭제)
  - [ ] `POST /api/auth/refresh` 엔드포인트 (쿠키에서 Refresh Token 읽기, 새로운 토큰을 쿠키에 저장)
  - [ ] `POST /api/auth/forgot-password` 엔드포인트
  - [ ] `POST /api/auth/reset-password` 엔드포인트
  - [ ] `POST /api/auth/change-password` 엔드포인트 (Authenticated)

### 6. JWT 필터 구현
- [ ] `JwtAuthenticationFilter` 생성
  - [ ] `doFilterInternal()` 메서드 구현
  - [ ] 쿠키에서 Access Token 추출
  - [ ] 토큰 검증 및 사용자 정보 추출
  - [ ] SecurityContext에 인증 정보 설정
  - [ ] 예외 처리

### 7. SecurityConfig 업데이트
- [ ] JWT 필터를 SecurityFilterChain에 추가
- [ ] Public 엔드포인트 설정
- [ ] 인증 필요한 엔드포인트 설정
- [ ] CORS 설정 (필요 시)

### 8. 보안 기능 구현
- [ ] 계정 잠금 기능
  - [ ] 로그인 실패 횟수 추적 (`lgnFailNmtm`)
  - [ ] 계정 잠금 처리 (`acntLckYn = 'Y'`)
  - [ ] 로그인 시 계정 잠금 상태 확인
  - [ ] 최대 실패 횟수 설정 (예: 5회)
- [ ] 비밀번호 정책
  - [ ] 비밀번호 강도 검증 유틸리티 생성
    - [ ] 최소 길이 검증 (예: 8자 이상)
    - [ ] 복잡도 검증 (대문자, 소문자, 숫자, 특수문자 포함)
  - [ ] 비밀번호 변경 시 현재 비밀번호 확인 로직

### 9. 이메일 유틸리티 구현
- [ ] `EmailHelper` 생성
  - [ ] SMTP 설정 관리
  - [ ] 이메일 발송 메서드
  - [ ] 비밀번호 재설정 링크 이메일 발송 메서드
- [ ] `application.yml`에 SMTP 설정 추가

### 11. UserEntity 메서드 추가
- [ ] 로그인 성공 처리 메서드 (`recordLoginSuccess()`)
- [ ] 로그인 실패 처리 메서드 (`recordLoginFailure()`)
- [ ] 계정 잠금 처리 메서드 (`lockAccount()`)

### 12. 설정 파일 업데이트
- [ ] `application.yml`에 JWT 시크릿 키 설정 추가
- [ ] `application.yml`에 JWT 만료 시간 설정 추가 (Access: 1시간, Refresh: 1주일)
- [ ] `application.yml`에 SMTP 설정 추가
- [ ] `application.yml`에 쿠키 설정 추가 (HttpOnly, Secure, SameSite)

### 13. PRD 문서 업데이트
- [ ] `PRD/Development Task List.md`의 Phase 1.2 작업 체크
- [ ] `PRD/Development Task List.md`의 Phase 1.3 작업 체크
- [ ] `PRD/TODO.md` 업데이트 (인증 관련 TODO 제거)

---

## Files to Modify

### 생성할 파일
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/auth/JwtHelper.java`
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/auth/AuthService.java`
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/auth/AuthController.java`
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/auth/AuthVO.java` (또는 개별 Request/Response VO)
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/auth/JwtAuthenticationFilter.java`
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/EmailHelper.java`
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/PasswordPolicyHelper.java` (비밀번호 정책)
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/CookieHelper.java` (쿠키 유틸리티)

### 수정할 파일
- `build.gradle` - JWT 및 이메일 라이브러리 추가
- `src/main/java/dev/nihilncunia/fantasy_builder/config/SecurityConfig.java` - JWT 필터 추가
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/user/UserEntity.java` - 로그인 관련 메서드 추가
- `src/main/resources/application.yml` - JWT 및 SMTP 설정 추가
- `PRD/Development Task List.md` - 작업 체크
- `PRD/TODO.md` - 인증 관련 TODO 업데이트

---

## 참고사항

- JWT 토큰은 HTTP-only 쿠키에 저장
- Access Token: 1시간 유효
- Refresh Token: 1주일 유효
- 토큰 리프레시 시 Access Token과 Refresh Token 모두 갱신
- 쿠키 설정: HttpOnly=true, Secure=true (HTTPS 환경), SameSite=Strict
- 계정 잠금: 로그인 실패 5회 시 계정 잠금
- 비밀번호 정책: 최소 8자, 대문자/소문자/숫자/특수문자 포함
- 이메일 인증은 향후 구현으로 제외
