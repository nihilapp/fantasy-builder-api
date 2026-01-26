# 003_Phase2CommonInfra_PLAN.md

## Goal
Phase 2: 공통 인프라 및 기반 구조 구현
- GlobalExceptionHandler 구현
- 페이징 유틸리티 (PageUtils) 구현
- 검색 조건 빌더 유틸리티 구현

## Context
- **날짜**: 2026-01-27
- **원본 명령**: Phase 2 진행
- **프로젝트 상태**: Phase 3 완료, ApiResponse와 ListType은 이미 생성됨
- **기술 스택**: Spring Boot 4.0.2, Java 25, PostgreSQL, JPA, Lombok
- **참고 파일**: 
  - `PRD/Development Task List.md` (Phase 2 작업 목록)
  - `PRD/Coding Rules & Guidelines.md` (코딩 규칙 및 예시)
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/ApiResponse.java` (이미 생성됨)
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/ListType.java` (이미 생성됨)

## Strategy
1. **GlobalExceptionHandler 구현**: 전역 예외 처리
   - 주요 예외 타입별 핸들러 구현
   - ResponseCode 기반 에러 응답
   - BusinessException 커스텀 예외 클래스 생성

2. **페이징 유틸리티 구현**: PageUtils 클래스
   - `Page<T>`를 `ListType<T>`로 변환
   - `List<T>`를 `ListType<T>`로 변환
   - Pageable 생성 유틸리티

3. **검색 조건 빌더 유틸리티**: Specification 빌더
   - 동적 쿼리 생성 지원
   - 재사용 가능한 빌더 패턴

## Task List

### 2.1. 공통 응답 구조 (이미 완료)
- [x] **ApiResponse 래퍼 클래스 생성** (Phase 3에서 이미 생성됨)
- [x] **ListType 클래스 생성** (Phase 3에서 이미 생성됨)
- [ ] **GlobalExceptionHandler 구현**
  - `@RestControllerAdvice` 사용
  - 주요 예외 타입별 핸들러:
    - `EntityNotFoundException` → `NOT_FOUND`
    - `MethodArgumentNotValidException` → `VALIDATION_ERROR`
    - `IllegalArgumentException` → `BAD_REQUEST`
    - `BusinessException` (커스텀) → 커스텀 ResponseCode
    - `Exception` (기타) → `INTERNAL_SERVER_ERROR`
  - `ResponseCode` 기반 에러 응답
  - 로깅 추가

### 2.2. 공통 유틸리티
- [ ] **페이징 유틸리티 (PageUtils) 생성**
  - `Page<T>`를 `ListType<T>`로 변환하는 메서드 (`toListType(Page<T> page, int currentPage)`)
  - `List<T>`를 `ListType<T>`로 변환하는 메서드 (`toListType(List<T> list)`)
  - `Pageable` 생성 유틸리티 (`createPageable(int page)`) - 기본 크기: 10개
- [ ] **검색 조건 빌더 유틸리티 (SpecificationBuilder) 생성**
  - 제네릭 타입 지원
  - 동적 쿼리 생성 지원
  - 재사용 가능한 빌더 패턴
  - 기본 조건 (delYn = 'N') 추가 메서드

## Files to Modify

### 새로 생성할 파일
1. `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/GlobalExceptionHandler.java`
2. `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/BusinessException.java`
3. `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/PageUtils.java`
4. `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/SpecificationBuilder.java` (선택)

### 참고사항
- ApiResponse와 ListType은 이미 Phase 3에서 생성되어 있음
- GlobalExceptionHandler는 모든 예외를 일관된 형식으로 처리
- PageUtils는 모든 Service에서 재사용 가능하도록 구현
- SpecificationBuilder는 선택 사항 (각 Service에서 직접 구현해도 됨)
