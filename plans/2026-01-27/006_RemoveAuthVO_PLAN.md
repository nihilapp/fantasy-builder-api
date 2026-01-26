# 006_RemoveAuthVO_PLAN.md

## Goal
AuthVO를 제거하고 UserVO를 직접 사용하도록 리팩토링합니다. 모든 요청에 VO만 던져두고 알아서 꺼내 쓰는 방식을 채택했기 때문에 별도의 Request 객체가 필요하지 않습니다.

## Context
- **Date**: 2026-01-27
- **Original Command**: AuthVO는 필요 없을 것으로 보임. UserVO가 그 역할을 할 것이기 때문에. 또한 모든 요청에 VO만 던져두고 알아서 꺼내 쓰는 방식을 채택했기 때문에 굳이 리퀘스트 객체가 필요하지도 않음.

## Strategy
1. AuthService에서 AuthVO의 내부 클래스들을 UserVO로 변경
2. Response 클래스들(SigninResponse, SignupResponse)도 UserVO로 대체 (ApiResponse<UserVO> 사용)
3. UserVO에 필요한 필드가 있는지 확인하고 없으면 추가
   - currentPassword, newPassword (비밀번호 변경용)
   - token (비밀번호 재설정용)
4. AuthVO 파일 삭제
5. 모든 import 문 정리

## Task List

### 1. UserVO 필드 확인 및 추가
- [ ] UserVO에 currentPassword, newPassword 필드 추가 (비밀번호 변경용)
- [ ] UserVO에 token 필드 추가 (비밀번호 재설정용, 선택적)

### 2. AuthService 리팩토링
- [ ] signin() 메서드: SigninRequest → UserVO로 변경
- [ ] signin() 메서드: SigninResponse → UserVO로 변경 (ApiResponse<UserVO> 사용)
- [ ] signup() 메서드: SignupRequest → UserVO로 변경
- [ ] signup() 메서드: SignupResponse → UserVO로 변경 (ApiResponse<UserVO> 사용)
- [ ] forgotPassword() 메서드: ForgotPasswordRequest → UserVO로 변경
- [ ] resetPassword() 메서드: ResetPasswordRequest → UserVO로 변경
- [ ] changePassword() 메서드: ChangePasswordRequest → UserVO로 변경
- [ ] SigninResult 내부 클래스 수정 (SigninResponse → UserVO)
- [ ] 모든 AuthVO import 제거

### 3. AuthController 확인 및 수정 (존재하는 경우)
- [ ] AuthController에서 AuthVO 사용 확인
- [ ] AuthController에서 UserVO로 변경

### 4. 정리 작업
- [ ] AuthVO.java 파일 삭제
- [ ] 컴파일 에러 확인 및 수정
- [ ] 다른 파일에서 AuthVO 사용 여부 확인

## Files to Modify
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/user/UserVO.java` (필드 추가)
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/auth/AuthService.java` (리팩토링)
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/auth/AuthVO.java` (삭제)
- `src/main/java/dev/nihilncunia/fantasy_builder/domain/auth/AuthController.java` (존재하는 경우 수정)
