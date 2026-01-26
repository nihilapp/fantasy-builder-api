# 002_Phase3GlobalPool_PLAN.md

## Goal
Phase 3: 전역 풀 엔티티 구현
- Trait (전역 특성) 엔티티 및 관련 CRUD 기능 구현
- Ability (전역 어빌리티) 엔티티 및 관련 CRUD 기능 구현

## Context
- **날짜**: 2026-01-27
- **원본 명령**: Phase 3 진행
- **프로젝트 상태**: Spring Boot 전환 중, Phase 1 (인증) 및 Phase 2 (공통 인프라) 완료 예정
- **기술 스택**: Spring Boot 4.0.2, Java 25, PostgreSQL, JPA, Lombok
- **참고 파일**: 
  - `CommonEntity.java`, `CommonVO.java` (공통 필드 패턴)
  - `UserEntity.java`, `UserVO.java` (엔티티/VO 작성 패턴)
  - `PRD/Development Task List.md` (Phase 3 작업 목록)
  - `PRD/PRD.md` (엔티티 스키마 정의)
  - `specs/api/API-엔드포인트-traits.md`, `specs/api/API-엔드포인트-abilities.md` (API 스펙)

## Strategy
1. **Trait 엔티티 구현**: 전역 특성 관리 기능
   - TraitEntity, TraitVO, TraitRepository, TraitService, TraitController 순서로 구현
   - 상충 특성(self FK) 관계 처리
   - 검색 및 페이징 지원
   - 사용 현황 조회 기능

2. **Ability 엔티티 구현**: 전역 어빌리티 관리 기능
   - AbilityEntity, AbilityVO, AbilityRepository, AbilityService, AbilityController 순서로 구현
   - 검색 및 페이징 지원
   - 사용 현황 조회 기능

3. **코딩 규칙 준수**: 
   - CommonEntity/CommonVO 상속 패턴
   - Entity → VO 변환 생성자
   - VO → Entity 변환 메서드
   - ApiResponse<ListType<T>> 응답 구조
   - 페이징 규칙 (페이지 번호만 받아서 서버에서 계산)

## Task List

### 3.1. Trait (전역 특성) 엔티티 구현

#### 3.1.1. TraitEntity 생성
- [ ] `domain/trait/TraitEntity.java` 생성
  - `CommonEntity` 상속
  - 필드: `traitNo`, `traitNm`, `traitExpln` (TEXT), `traitLcls`, `traitMcls`, `aplyTrgt`, `cnflTraitNo` (FK, 자기 참조)
  - `@Entity`, `@Table(name = "traits")` 적용
  - `@Index` 설정: `trait_nm`, `cnfl_trait_no`
  - `@ManyToOne` 관계: `cnflTrait` (TraitEntity 자기 참조)
  - 필수 어노테이션: `@Getter`, `@NoArgsConstructor(access = AccessLevel.PROTECTED)`
  - `@DynamicInsert` 적용

#### 3.1.2. TraitVO 생성
- [ ] `domain/trait/TraitVO.java` 생성
  - `CommonVO` 상속
  - 필드: `traitNo`, `traitNm`, `traitExpln`, `traitLcls`, `traitMcls`, `aplyTrgt`, `cnflTraitNo`
  - 검색용 필드: `traitNoList`, `searchType`, `searchKeyword`, `totalCnt`, `rowNo`
  - Entity → VO 변환 생성자 (`TraitVO(TraitEntity entity)`)
  - VO → Entity 변환 메서드 (`toEntity()`)
  - Entity 업데이트 메서드 (`updateEntity(TraitEntity entity)`)

#### 3.1.3. TraitRepository 생성
- [ ] `domain/trait/TraitRepository.java` 생성
  - `JpaRepository<TraitEntity, Long>` 상속
  - 검색 메서드:
    - `findByTraitNmContainingAndDelYn(String traitNm, String delYn)`
    - `findByTraitLclsAndDelYn(String traitLcls, String delYn)`
    - `findByAplyTrgtAndDelYn(String aplyTrgt, String delYn)`
  - 페이징 지원 메서드

#### 3.1.4. TraitService 생성
- [ ] `domain/trait/TraitService.java` 생성
  - CRUD 메서드 구현:
    - `findTraits(TraitVO searchVO, int page)`: 목록 조회 (페이징)
    - `findTraitById(Long traitNo)`: 상세 조회
    - `createTrait(TraitVO traitVO)`: 생성
    - `updateTrait(Long traitNo, TraitVO traitVO)`: 수정
    - `deleteTrait(Long traitNo, Long deleterNo)`: 삭제 (소프트 삭제)
    - `findUsage(Long traitNo)`: 사용 현황 조회
  - 검색 조건 빌더 (`Specification` 활용)
  - 페이징 처리 (`Page<T>` → `ListType<T>` 변환)
  - 상충 특성 검증 로직

#### 3.1.5. TraitController 생성
- [ ] `domain/trait/TraitController.java` 생성
  - `@RestController`, `@RequestMapping("/api/traits")` 적용
  - 엔드포인트:
    - `GET /api/traits` - 목록 조회 (페이징, 검색 지원)
    - `GET /api/traits/{traitNo}` - 상세 조회
    - `POST /api/traits` - 생성
    - `PATCH /api/traits/{traitNo}` - 수정
    - `DELETE /api/traits/{traitNo}` - 삭제 (소프트 삭제)
    - `GET /api/traits/{traitNo}/usage` - 사용 현황 조회
  - 응답 형식: `ApiResponse<ListType<TraitVO>>` (목록), `ApiResponse<TraitVO>` (단일)
  - 입력값 검증 (`@Valid` 사용)

### 3.2. Ability (전역 어빌리티) 엔티티 구현

#### 3.2.1. AbilityEntity 생성
- [ ] `domain/ability/AbilityEntity.java` 생성
  - `CommonEntity` 상속
  - 필드: `abilityNo`, `abilityNm`, `abilityType`, `abilityLcls`, `abilityExpln` (TEXT), `trgtType`, `dmgType`, `statEffType`, `useCost`, `coolTime`, `castTime`, `useCnd` (TEXT)
  - `@Entity`, `@Table(name = "abilities")` 적용
  - `@Index` 설정: `ability_nm`
  - 필수 어노테이션: `@Getter`, `@NoArgsConstructor(access = AccessLevel.PROTECTED)`
  - `@DynamicInsert` 적용

#### 3.2.2. AbilityVO 생성
- [ ] `domain/ability/AbilityVO.java` 생성
  - `CommonVO` 상속
  - 필드: `abilityNo`, `abilityNm`, `abilityType`, `abilityLcls`, `abilityExpln`, `trgtType`, `dmgType`, `statEffType`, `useCost`, `coolTime`, `castTime`, `useCnd`
  - 검색용 필드: `abilityNoList`, `searchType`, `searchKeyword`, `totalCnt`, `rowNo`
  - Entity → VO 변환 생성자 (`AbilityVO(AbilityEntity entity)`)
  - VO → Entity 변환 메서드 (`toEntity()`)
  - Entity 업데이트 메서드 (`updateEntity(AbilityEntity entity)`)

#### 3.2.3. AbilityRepository 생성
- [ ] `domain/ability/AbilityRepository.java` 생성
  - `JpaRepository<AbilityEntity, Long>` 상속
  - 검색 메서드:
    - `findByAbilityNmContainingAndDelYn(String abilityNm, String delYn)`
    - `findByAbilityTypeAndDelYn(String abilityType, String delYn)`
    - `findByAbilityLclsAndDelYn(String abilityLcls, String delYn)`
  - 페이징 지원 메서드

#### 3.2.4. AbilityService 생성
- [ ] `domain/ability/AbilityService.java` 생성
  - CRUD 메서드 구현:
    - `findAbilities(AbilityVO searchVO, int page)`: 목록 조회 (페이징)
    - `findAbilityById(Long abilityNo)`: 상세 조회
    - `createAbility(AbilityVO abilityVO)`: 생성
    - `updateAbility(Long abilityNo, AbilityVO abilityVO)`: 수정
    - `deleteAbility(Long abilityNo, Long deleterNo)`: 삭제 (소프트 삭제)
    - `findUsage(Long abilityNo)`: 사용 현황 조회
  - 검색 조건 빌더 (`Specification` 활용)
  - 페이징 처리 (`Page<T>` → `ListType<T>` 변환)

#### 3.2.5. AbilityController 생성
- [ ] `domain/ability/AbilityController.java` 생성
  - `@RestController`, `@RequestMapping("/api/abilities")` 적용
  - 엔드포인트:
    - `GET /api/abilities` - 목록 조회 (페이징, 검색 지원)
    - `GET /api/abilities/{abilityNo}` - 상세 조회
    - `POST /api/abilities` - 생성
    - `PATCH /api/abilities/{abilityNo}` - 수정
    - `DELETE /api/abilities/{abilityNo}` - 삭제 (소프트 삭제)
    - `GET /api/abilities/{abilityNo}/usage` - 사용 현황 조회
  - 응답 형식: `ApiResponse<ListType<AbilityVO>>` (목록), `ApiResponse<AbilityVO>` (단일)
  - 입력값 검증 (`@Valid` 사용)

## Files to Modify
- 새로 생성할 파일:
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/trait/TraitEntity.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/trait/TraitVO.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/trait/TraitRepository.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/trait/TraitService.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/trait/TraitController.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/ability/AbilityEntity.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/ability/AbilityVO.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/ability/AbilityRepository.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/ability/AbilityService.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/ability/AbilityController.java`

## 참고사항
- Phase 2 (공통 인프라)가 완료되어 있어야 함 (ApiResponse, ListType, GlobalExceptionHandler 등)
- 상충 특성(self FK)은 TraitEntity에서만 구현 (Ability는 상충 관계 없음)
- 사용 현황 조회는 향후 매핑 테이블 구현 시 완성될 예정 (현재는 기본 구조만)
- 페이징은 페이지 번호만 받아서 서버에서 계산 (기본 크기: 10개)
- 모든 목록 응답은 `ApiResponse<ListType<T>>` 형태
- `totalCnt`는 페이징 여부와 무관하게 반드시 채움
