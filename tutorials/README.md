# Fantasy Builder 프로젝트 튜토리얼

이 폴더에는 Fantasy Builder 프로젝트의 코드를 이해하기 위한 단계별 튜토리얼 문서가 포함되어 있습니다.

## 대상 독자

- Java 입문자
- Spring Boot 입문자
- 이 프로젝트의 코드를 이해하고 싶은 개발자

## 튜토리얼 목차

### 1. 기초 개념
- **[01. 프로젝트 시작과 기본 구조](./01_프로젝트_시작과_기본_구조.md)**
  - Spring Boot 프로젝트란?
  - 프로젝트 구조 살펴보기
  - 메인 애플리케이션 클래스 이해하기
  - 설정 파일 이해하기

- **[02. 공통 엔티티와 VO](./02_공통_엔티티와_VO.md)**
  - Entity와 VO란?
  - CommonEntity 이해하기
  - CommonVO 이해하기
  - Entity와 VO의 관계

### 2. 기본 기능 구현
- **[03. 사용자 관리 기능 구현하기](./03_사용자_관리_기능_구현하기.md)**
  - UserEntity - 데이터베이스 구조
  - UserVO - 데이터 전달 객체
  - UserRepository - 데이터 접근 계층
  - UserService - 비즈니스 로직 계층
  - UserController - API 계층
  - 전체 흐름 정리

### 3. 인증 시스템
- **[04. 인증 시스템 구현하기](./04_인증_시스템_구현하기.md)**
  - JWT 토큰 이해하기
  - JwtHelper - 토큰 생성 및 검증
  - 비밀번호 해싱 - PasswordHelper
  - AuthService - 인증 비즈니스 로직
  - JwtAuthenticationFilter - 인증 필터
  - SecurityConfig - 보안 설정
  - 전체 인증 흐름

### 4. 공통 기능
- **[05. 공통 응답 구조와 예외 처리](./05_공통_응답_구조와_예외_처리.md)**
  - 공통 응답 구조의 필요성
  - ApiResponse - 응답 래퍼 클래스
  - ResponseCode - 응답 코드 Enum
  - ListType - 목록 응답 구조
  - GlobalExceptionHandler - 전역 예외 처리
  - BusinessException - 커스텀 예외
  - 전체 흐름 정리

### 5. 도메인 기능
- **[06. 전역 풀 엔티티 구현하기](./06_전역_풀_엔티티_구현하기.md)**
  - 전역 풀 엔티티란?
  - TraitEntity - 전역 특성 엔티티
  - TraitService - 특성 비즈니스 로직
  - TraitController - 특성 API
  - AbilityEntity - 전역 어빌리티 엔티티
  - 전체 흐름 정리

### 6. 전체 정리
- **[07. 프로젝트 구조 정리](./07_프로젝트_구조_정리.md)**
  - 전체 프로젝트 구조
  - 계층별 역할 정리
  - 주요 패턴과 원칙
  - 어노테이션 정리
  - 학습 가이드

## 학습 방법

### 추천 학습 순서

1. **기본 개념 이해** (01-02)
   - 프로젝트의 전체 구조와 기본 개념을 이해합니다.

2. **기본 기능 구현** (03)
   - 사용자 관리 기능을 통해 전체 흐름을 이해합니다.

3. **인증 시스템** (04)
   - JWT 기반 인증 시스템의 동작 방식을 이해합니다.

4. **공통 기능** (05)
   - 공통 응답 구조와 예외 처리 방식을 이해합니다.

5. **도메인 기능** (06)
   - 전역 풀 엔티티의 구현 방식을 이해합니다.

6. **전체 정리** (07)
   - 프로젝트의 전체 구조와 패턴을 정리합니다.

### 실습 추천

- **코드 읽기**: 각 튜토리얼을 읽으면서 실제 코드를 함께 확인하세요.
- **API 테스트**: Postman이나 Swagger UI로 API를 직접 호출해보세요.
- **디버깅**: 브레이크포인트를 설정하고 실행 흐름을 따라가보세요.
- **작은 기능 추가**: 기존 코드를 참고하여 새로운 기능을 추가해보세요.

## 주요 학습 내용

이 튜토리얼을 통해 다음을 학습할 수 있습니다:

1. ✅ Spring Boot 프로젝트 구조 이해
2. ✅ JPA를 사용한 데이터베이스 연동
3. ✅ 계층 분리 아키텍처 (Controller-Service-Repository)
4. ✅ JWT 기반 인증 시스템
5. ✅ 공통 응답 구조와 예외 처리
6. ✅ 전역 풀 엔티티 구현
7. ✅ 주요 어노테이션과 패턴 이해

## 참고 자료

- **Spring Boot 공식 문서**: https://spring.io/projects/spring-boot
- **Spring Data JPA 공식 문서**: https://spring.io/projects/spring-data-jpa
- **Spring Security 공식 문서**: https://spring.io/projects/spring-security
- **JWT 공식 문서**: https://jwt.io/

## 문의

튜토리얼에 대한 질문이나 개선 사항이 있으면 이슈를 등록해주세요.
