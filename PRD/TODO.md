# TODO
## 추가 작업 및 개선 사항

**작성일**: 2026-01-27  
**상태**: 진행 중인 작업 및 향후 개선 사항 정리

---

## 🔴 긴급 (인증 구현 후 필수)

### 1. 현재 사용자 번호 가져오기
**위치**: 여러 Controller 및 Service  
**상태**: 임시 값(1L) 사용 중

#### 수정 필요 파일:
- `UserController.java`
  - `getCurrentUser()` - 현재 사용자 번호 가져오기
  - `updateCurrentUser()` - 현재 사용자 번호 가져오기
  - `deleteUser()` - 삭제자 번호 가져오기
- `UserService.java`
  - `createUser()` - 생성자 번호 가져오기
  - `updateUser()` - 수정자 번호 가져오기
- `TraitController.java`
  - `createTrait()` - 생성자 번호 가져오기
  - `updateTrait()` - 수정자 번호 가져오기
  - `deleteTrait()` - 삭제자 번호 가져오기
- `AbilityController.java`
  - `createAbility()` - 생성자 번호 가져오기
  - `updateAbility()` - 수정자 번호 가져오기
  - `deleteAbility()` - 삭제자 번호 가져오기

**작업 내용**:
- JWT 토큰에서 현재 사용자 정보 추출
- `@AuthenticationPrincipal` 또는 커스텀 어노테이션 활용
- SecurityContext에서 사용자 정보 가져오기

---

## 🟡 중요 (기능 완성)

### 2. 사용 현황 조회 기능 완성
**위치**: TraitService, AbilityService  
**상태**: 기본 구조만 구현됨 (null 반환)

#### 수정 필요 파일:
- `TraitService.java` - `findUsage()` 메서드
- `AbilityService.java` - `findUsage()` 메서드

**작업 내용**:
- 매핑 테이블 구현 후 사용 현황 조회 로직 완성
- `char_trait_maps`, `char_ability_maps`, `creature_trait_maps`, `creature_ability_maps` 등에서 사용 현황 조회
- 어떤 프로젝트/엔티티에서 사용 중인지 추적

**의존성**: Phase 6 (매핑 테이블 구현) 완료 후 작업 가능

---

## 🟢 Phase 1.2: 인증 기능 구현 ✅

### 3. JWT 토큰 생성 및 검증 ✅
- [x] JWT 유틸리티 클래스 생성 (`JwtHelper`)
  - Access Token 생성 (1시간)
  - Refresh Token 생성 (1주일)
  - 토큰 검증 로직
- [x] 비밀번호 해싱 유틸리티 (`PasswordHelper`) ✅ 이미 구현됨
  - 비밀번호 검증 함수 포함

### 4. 인증 Service 및 Controller ✅
- [x] `AuthService` 생성
  - `signin()` - 로그인 (토큰을 쿠키에 저장)
  - `signup()` - 회원가입
  - `refresh()` - 토큰 갱신 (Access Token과 Refresh Token 모두 갱신)
  - `signout()` - 로그아웃
  - `forgotPassword()` - 비밀번호 재설정 요청
  - `resetPassword()` - 비밀번호 재설정
  - `changePassword()` - 비밀번호 변경
- [x] `AuthController` 생성
  - `POST /api/auth/signin` - 로그인 (토큰을 쿠키에 저장)
  - `POST /api/auth/signup` - 회원가입
  - `POST /api/auth/signout` - 로그아웃 (쿠키 삭제)
  - `POST /api/auth/refresh` - 토큰 갱신 (새로운 토큰을 쿠키에 저장)
  - `POST /api/auth/forgot-password` - 비밀번호 재설정 요청
  - `POST /api/auth/reset-password` - 비밀번호 재설정
  - `POST /api/auth/change-password` - 비밀번호 변경

---

## 🟢 Phase 1.3: 보안 기능 구현 ✅

### 5. 계정 잠금 기능 ✅
- [x] 로그인 실패 횟수 추적 (`lgnFailNmtm`)
- [x] 계정 잠금 처리 (`acntLckYn`)
- [x] 로그인 시 계정 잠금 상태 확인

### 6. 비밀번호 정책 ✅
- [x] 비밀번호 강도 검증 (`PasswordPolicyHelper`)
- [x] 비밀번호 변경 이력 관리 (`lastPswdChgDt`)
- [x] 현재 비밀번호 확인 로직 (비밀번호 변경 시)

### 7. JWT 토큰 검증 미들웨어/필터 ✅
- [x] JWT 토큰 검증 필터 생성 (`JwtAuthenticationFilter`)
- [x] 쿠키에서 Access Token 읽기
- [x] SecurityContext에 인증 정보 설정
- [x] SecurityConfig 업데이트 (JWT 필터 추가)

### 8. 이메일 발송 유틸리티 ✅
- [x] `EmailHelper` 생성
- [x] SMTP 설정 관리 (application.yml)
- [x] 비밀번호 재설정 링크 이메일 발송

### 9. 이메일 인증 (향후 구현)
- [ ] 이메일 인증 토큰 생성
- [ ] 이메일 인증 처리 (`emlAuthYn`)

---

## 🔵 개선 사항

### 10. 코드 개선
- [ ] `UserService.createUser()`와 `AuthService.signup()` 통합 고려
  - 현재 UserController에 회원가입 엔드포인트가 있음
  - AuthController에도 회원가입이 필요함
  - 중복 제거 또는 역할 분리 필요

### 11. 검증 강화
- [ ] 입력값 검증 강화
  - 이메일 형식 검증 (`@Email` 또는 커스텀)
  - 비밀번호 강도 검증
  - 필수 필드 검증

### 12. 에러 처리 개선
- [ ] 비밀번호 관련 예외 추가
  - `INVALID_PASSWORD` - 잘못된 비밀번호
  - `PASSWORD_TOO_WEAK` - 비밀번호 강도 부족
- [ ] 계정 관련 예외 추가
  - `ACCOUNT_LOCKED` - 계정 잠금 (이미 ResponseCode에 있음)
  - `ACCOUNT_DISABLED` - 계정 비활성화

### 13. 로깅 개선
- [ ] 주요 비즈니스 로직에 로깅 추가
  - 사용자 생성/수정/삭제
  - 로그인/로그아웃
  - 비밀번호 변경

---

## 📋 Phase별 미완료 작업

### Phase 1 (인증 및 사용자 관리)
- [x] 1.1. 사용자 엔티티 및 기본 구조 ✅
- [ ] 1.2. 인증 기능 ⏳
- [ ] 1.3. 보안 기능 ⏳

### Phase 2 (공통 인프라)
- [x] 2.1. 공통 응답 구조 ✅
- [x] 2.2. 공통 유틸리티 ✅

### Phase 3 (전역 풀 엔티티)
- [x] 3.1. Trait (전역 특성) ✅
- [x] 3.2. Ability (전역 어빌리티) ✅
- [~] 사용 현황 조회 - 기본 구조만 (매핑 테이블 구현 후 완성)

### Phase 4 이후
- [ ] Phase 4: 프로젝트 도메인 구현
- [ ] Phase 5: 프로젝트 종속 특성/어빌리티
- [ ] Phase 6: 매핑 테이블 구현
- [ ] Phase 7: 권한 관리 및 보안
- [ ] Phase 8: 검증 및 테스트
- [ ] Phase 9: 문서화 및 최적화

---

## 🔧 기술적 개선 사항

### 14. 의존성 관리
- [x] Spring Security 추가 완료
- [ ] JWT 라이브러리 추가 필요 (java-jwt 또는 jjwt)
- [ ] 이메일 발송 라이브러리 추가 필요 (JavaMailSender)

### 15. 설정 파일
- [ ] `application.yml`에 JWT 시크릿 키 설정 추가
- [ ] `application.yml`에 SMTP 설정 추가
- [ ] 환경 변수 관리 (.env 파일 또는 Spring Profile 활용)

### 16. 테스트
- [ ] 단위 테스트 작성
- [ ] 통합 테스트 작성
- [ ] API 테스트 (Postman 또는 자동화 테스트)

### 17. Swagger 설정
- [ ] SpringDoc OpenAPI 설정 완성
  - API 엔드포인트 문서화
  - 요청/응답 스키마 정의
  - 인증 설정 (JWT 토큰) 문서화
  - 각 Controller에 API 문서 어노테이션 추가
  - Swagger UI 접근 경로 확인 및 테스트
- [ ] API 사용 예제 작성
  - 주요 엔드포인트별 사용 예제
  - 인증 플로우 예제

---

## 📝 참고사항

- 모든 TODO는 인증 구현(Phase 1.2, 1.3) 완료 후 진행 가능한 항목들이 많음
- 사용 현황 조회는 매핑 테이블 구현(Phase 6) 후 완성 가능
- 현재는 기본 구조만 구현되어 있어 동작은 하지만 완전한 기능은 아님
