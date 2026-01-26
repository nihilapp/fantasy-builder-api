# 007_ReorganizeCommonPackage_PLAN.md

## Goal
common 패키지의 파일들을 논리적인 하위 패키지 구조로 재구성합니다.

## Context
- **Date**: 2026-01-27
- **Original Command**: common 에다가 넣는 건 좋은데 하위 폴더 구조를 좀 신경썼으면 좋겠어.

## Strategy
현재 common 패키지에 평면적으로 존재하는 13개의 파일을 다음과 같이 하위 패키지로 분류합니다:

1. **exception**: 예외 관련 클래스
2. **response**: API 응답 관련 클래스
3. **entity**: 공통 엔티티/VO 클래스
4. **util**: 유틸리티/헬퍼 클래스
5. **type**: 타입/Enum 클래스

## Task List

### 1. 하위 패키지 생성 및 파일 이동
- [ ] `common/exception` 패키지 생성
  - [ ] `BusinessException.java` 이동
  - [ ] `GlobalExceptionHandler.java` 이동
- [ ] `common/response` 패키지 생성
  - [ ] `ApiResponse.java` 이동
  - [ ] `ResponseCode.java` 이동
- [ ] `common/entity` 패키지 생성
  - [ ] `CommonEntity.java` 이동
  - [ ] `CommonVO.java` 이동
- [ ] `common/util` 패키지 생성
  - [ ] `CookieHelper.java` 이동
  - [ ] `EmailHelper.java` 이동
  - [ ] `PasswordHelper.java` 이동
  - [ ] `PasswordPolicyHelper.java` 이동
  - [ ] `PageUtils.java` 이동
- [ ] `common/type` 패키지 생성
  - [ ] `ListType.java` 이동
  - [ ] `UserRoleCode.java` 이동

### 2. 패키지 선언 수정
- [ ] 모든 이동된 파일의 package 선언 수정

### 3. Import 문 수정
- [ ] 모든 파일에서 common 패키지 import 문 수정
  - [ ] AuthService.java
  - [ ] UserService.java
  - [ ] UserController.java
  - [ ] AbilityController.java
  - [ ] TraitController.java
  - [ ] JwtAuthenticationFilter.java
  - [ ] UserVO.java
  - [ ] UserEntity.java
  - [ ] AbilityEntity.java
  - [ ] TraitEntity.java
  - [ ] AbilityVO.java
  - [ ] TraitVO.java
  - [ ] 기타 common 패키지를 사용하는 모든 파일

### 4. 내부 참조 수정
- [ ] GlobalExceptionHandler에서 ApiResponse, ResponseCode, BusinessException import 수정
- [ ] ApiResponse에서 ResponseCode import 수정
- [ ] BusinessException에서 ResponseCode import 수정
- [ ] PasswordPolicyHelper에서 BusinessException, ResponseCode import 수정
- [ ] CommonVO에서 CommonEntity 참조 확인 (필요시 수정)

### 5. 검증
- [ ] 컴파일 에러 확인
- [ ] 모든 import 문이 올바르게 수정되었는지 확인

## Files to Modify

### 이동할 파일
- `BusinessException.java` → `common/exception/BusinessException.java`
- `GlobalExceptionHandler.java` → `common/exception/GlobalExceptionHandler.java`
- `ApiResponse.java` → `common/response/ApiResponse.java`
- `ResponseCode.java` → `common/response/ResponseCode.java`
- `CommonEntity.java` → `common/entity/CommonEntity.java`
- `CommonVO.java` → `common/entity/CommonVO.java`
- `CookieHelper.java` → `common/util/CookieHelper.java`
- `EmailHelper.java` → `common/util/EmailHelper.java`
- `PasswordHelper.java` → `common/util/PasswordHelper.java`
- `PasswordPolicyHelper.java` → `common/util/PasswordPolicyHelper.java`
- `PageUtils.java` → `common/util/PageUtils.java`
- `ListType.java` → `common/type/ListType.java`
- `UserRoleCode.java` → `common/type/UserRoleCode.java`

### 수정할 파일 (import 문)
- 모든 도메인 클래스에서 common 패키지 import를 사용하는 파일들
