# PRD (Product Requirements Document)
## Fantasy Builder API

**작성일**: 2026-01-27  
**버전**: 1.0  
**프로젝트 상태**: FastAPI → Spring Boot 전환 중

---

## 1. Project Overview

### Goal
창작자(작가, 게임 기획자, 만화가, 시나리오 작가 등)가 창작 과정에서 필요한 세계관 설정을 체계적으로 정리하고 기록할 수 있는 World Building Tool을 제공합니다. 각 프로젝트별로 독립적인 설정 공간을 제공하며, 전역 풀과 프로젝트 종속 특성/어빌리티를 통해 유연한 설정 관리가 가능합니다.

### Target User
- **주요 사용자**: 창작자 (작가, 게임 기획자, 만화가, 시나리오 작가 등)
- **사용 목적**: 작품/세계관 설정의 체계적 관리 및 기록
- **핵심 니즈**: 
  - 프로젝트별 독립적인 설정 공간
  - 특성(Traits)과 어빌리티(Abilities)를 통한 설정의 특색 파악
  - 엔티티 간 관계 설정 및 관리

### Key Value
1. **프로젝트별 독립 관리**: 각 작품/세계관을 프로젝트 단위로 완전히 분리하여 관리
2. **전역 풀 + 프로젝트 종속 구조**: 범용 특성/어빌리티와 프로젝트 고유 특성/어빌리티를 모두 지원
3. **체계적인 데이터 구조화**: DB 스키마 기반의 구조화된 데이터 입력 및 관리
4. **관계 시각화**: 엔티티 간 다대다 관계 설정 및 탐색

---

## 2. Tech Stack & Environment (Specific Versions)

### Language & Framework
- **Java**: 25 (JavaLanguageVersion.of(25))
- **Spring Boot**: 4.0.2
- **Spring Data JPA**: Spring Boot 4.0.2 내장
- **Spring Web MVC**: RESTful API 구현
- **Spring Validation**: 입력값 검증

### Database
- **PostgreSQL**: 최신 안정 버전 (runtimeOnly)
- **JPA/Hibernate**: ORM 프레임워크

### Build Tool
- **Gradle**: 8.x (Gradle Wrapper 사용)
- **Dependency Management**: Spring Dependency Management Plugin 1.1.7

### Development Tools
- **Lombok**: 코드 생성 (Getter, Setter, Builder 등)
- **Spring Boot DevTools**: 개발 편의성 향상
- **SpringDoc OpenAPI**: API 문서화 (2.3.0)

### Testing
- **JUnit Platform**: 단위 테스트 및 통합 테스트
- **Spring Boot Test**: 테스트 지원

### Infra/Deployment
- **배포 환경**: 미정 (AWS, Docker 등 고려 예정)
- **API 문서**: SpringDoc OpenAPI UI 제공

---

## 3. System Architecture & Features

### 3.1. User Flow (Core Scenarios)

#### Flow 1: 사용자 등록 및 프로젝트 생성
1. 사용자 회원가입 (이메일, 비밀번호)
2. 로그인 (JWT 토큰 발급)
3. 프로젝트 생성 (프로젝트명, 장르, 설명 등)
4. 프로젝트별 설정 공간 접근

#### Flow 2: 특성/어빌리티 관리 및 매칭
1. 전역 특성/어빌리티 풀에서 검색 및 선택
2. 프로젝트 종속 특성/어빌리티 생성
3. 설정 엔티티(인물, 종족, 아이템 등)에 특성/어빌리티 매칭
4. 매칭된 특성/어빌리티를 통한 설정의 특색 파악

#### Flow 3: 설정 엔티티 생성 및 관계 설정
1. 프로젝트 내에서 설정 엔티티 생성 (인물, 종족, 아이템, 지역, 국가, 조직, 사건, 전승 등)
2. 엔티티 간 관계 설정 (인물-인물, 인물-조직, 국가-조직 등)
3. 관계 탐색 및 시각화

### 3.2. Core Features (Detailed)

#### Feature A: 사용자 인증 및 권한 관리
- **Logic**: 
  - JWT 기반 인증 (Access Token + Refresh Token)
  - 비밀번호 bcrypt 해싱
  - 이메일 인증 (향후 구현)
  - 계정 잠금 기능 (로그인 실패 횟수 추적)
- **Validation**: 
  - 이메일 형식 검증
  - 비밀번호 강도 검증
  - 중복 이메일 검증

#### Feature B: 프로젝트 관리
- **Logic**: 
  - 프로젝트 생성, 수정, 삭제 (소프트 삭제)
  - 프로젝트별 독립적인 설정 공간 제공
  - 프로젝트 메타데이터 관리 (장르, 버전, 설명 등)
- **Validation**: 
  - 프로젝트명 필수 입력
  - 프로젝트 소유자 권한 검증

#### Feature C: 전역 특성/어빌리티 관리 ⭐ 최우선
- **Logic**: 
  - 전역 특성/어빌리티 풀 관리 (모든 프로젝트 공유)
  - 키워드, 대분류, 중분류, 적용 대상별 검색
  - 특성 간 상충 관계 설정
  - 사용 현황 추적 (어떤 프로젝트에서 사용 중인지)
- **Validation**: 
  - 특성명/어빌리티명 필수 입력
  - 중복 검증 (전역 풀 내)
  - 삭제 시 사용 중인 경우 경고

#### Feature D: 프로젝트 종속 특성/어빌리티 관리 ⭐ 최우선
- **Logic**: 
  - 프로젝트별 고유 특성/어빌리티 생성 및 관리
  - 전역 특성/어빌리티와 동일한 구조
  - 프로젝트 내에서만 사용 가능
- **Validation**: 
  - 프로젝트 소유자/편집자 권한 검증
  - 프로젝트 내 중복 검증

#### Feature E: 특성/어빌리티 통합 검색
- **Logic**: 
  - 전역 풀 + 프로젝트 종속 풀 통합 검색
  - 타입 구분 필터링 (GLOBAL | PROJECT)
  - 키워드, 카테고리별 필터링
- **Validation**: 
  - 프로젝트 접근 권한 검증

#### Feature F: 설정 엔티티 관리
- **Logic**: 
  - 코어 설정, 생물/종족, 인물, 아이템, 지역, 국가, 조직, 사건, 전승/설화 CRUD
  - 프로젝트별 독립 관리
  - 페이징 및 검색 기능
- **Validation**: 
  - 프로젝트 소유자/편집자 권한 검증
  - 필수 필드 검증

#### Feature G: 특성/어빌리티 매칭
- **Logic**: 
  - 설정 엔티티에 전역/프로젝트 종속 특성/어빌리티 매칭
  - 매핑 테이블을 통한 다대다 관계 관리
  - 참조 타입 필드로 전역/프로젝트 종속 구분
- **Validation**: 
  - 특성/어빌리티 존재 여부 검증
  - 적용 대상 검증 (CHAR, ITEM, NATION 등)

#### Feature H: 엔티티 간 관계 관리
- **Logic**: 
  - 인물-인물 관계, 인물-조직 관계, 국가-조직 관계 등
  - 관계 유형 및 상세 정보 관리
  - 다형 참조 지원 (EventEntry 등)
- **Validation**: 
  - 관계 대상 존재 여부 검증
  - 순환 참조 방지

---

## 4. Data Structure (Schema)

### 4.1. 공통 필드 (CommonEntity / CommonVO)

모든 엔티티는 `CommonEntity`를 상속받으며, 다음 공통 필드를 포함합니다:

```java
// CommonEntity (JPA Entity)
- useYn: String (사용 여부, 기본값: 'Y')
- shrnYn: String (공유 여부, 기본값: 'N')
- delYn: String (삭제 여부, 기본값: 'N')
- crtNo: Long (생성자 번호)
- crtDt: LocalDateTime (생성 일시, @CreatedDate)
- updtNo: Long (수정자 번호)
- updtDt: LocalDateTime (수정 일시, @LastModifiedDate)
- delNo: Long (삭제자 번호)
- delDt: LocalDateTime (삭제 일시)
```

모든 VO는 `CommonVO`를 상속받으며, 동일한 필드를 포함합니다.

### 4.2. 주요 엔티티

#### User (사용자)
```java
@Entity
@Table(name = "users")
public class UserEntity extends CommonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userNo;
  private String userEml; // 이메일 (인덱스)
  private String userNm; // 사용자명
  @Enumerated(EnumType.STRING)
  private UserRoleCode userRole; // USER | ADMIN
  private String proflImgUrl; // 프로필 이미지
  private String biogp; // 소개
  private String enpswd; // 암호화된 비밀번호
  private String reshToken; // 리프레시 토큰
  private String acntLckYn; // 계정 잠금 여부
  private Integer lgnFailNmtm; // 로그인 실패 횟수
  private LocalDateTime lastLgnDt; // 마지막 로그인 일시
  private String lastLgnIp; // 마지막 로그인 IP
  private LocalDateTime lastPswdChgDt; // 마지막 비밀번호 변경 일시
  private String emlAuthYn; // 이메일 인증 여부
  private String mktRecpAgreYn; // 마케팅 수신 동의 여부
}
```

#### Project (프로젝트)
```java
@Entity
@Table(name = "projects")
public class ProjectEntity extends CommonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long prjNo;
  @ManyToOne
  @JoinColumn(name = "user_no")
  private UserEntity user; // 프로젝트 소유자
  private String prjNm; // 프로젝트명 (인덱스)
  private String genreType; // 장르
  private String prjDesc; // 프로젝트 개요 (TEXT)
  private String cvrImgUrl; // 대표 이미지 URL
  private String prjExpln; // 프로젝트 설명
  private String prjVer; // 프로젝트 버전
}
```

#### Trait (전역 특성)
```java
@Entity
@Table(name = "traits")
public class TraitEntity extends CommonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long traitNo;
  private String traitNm; // 특성명 (인덱스)
  @Column(columnDefinition = "TEXT")
  private String traitExpln; // 특성 설명
  private String traitLcls; // 특성 대분류
  private String traitMcls; // 특성 중분류
  private String aplyTrgt; // 적용 대상 (CHAR, ITEM, NATION, ORG, REGION)
  @ManyToOne
  @JoinColumn(name = "cnfl_trait_no")
  private TraitEntity cnflTrait; // 상충 특성 (전역 특성만 참조)
}
```

#### ProjectTrait (프로젝트 종속 특성)
```java
@Entity
@Table(name = "project_traits")
public class ProjectTraitEntity extends CommonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long traitNo;
  @ManyToOne
  @JoinColumn(name = "prj_no")
  private ProjectEntity project; // 프로젝트 (FK, NOT NULL, 인덱스)
  private String traitNm; // 특성명 (인덱스)
  @Column(columnDefinition = "TEXT")
  private String traitExpln; // 특성 설명
  private String traitLcls; // 특성 대분류
  private String traitMcls; // 특성 중분류
  private String aplyTrgt; // 적용 대상
  private Long cnflTraitNo; // 상충 특성 번호 (인덱스)
  @Enumerated(EnumType.STRING)
  private TraitType cnflTraitType; // GLOBAL | PROJECT
}
```

#### Ability (전역 어빌리티)
```java
@Entity
@Table(name = "abilities")
public class AbilityEntity extends CommonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long abilityNo;
  private String abilityNm; // 어빌리티명 (인덱스)
  private String abilityType; // 어빌리티 유형
  private String abilityLcls; // 어빌리티 계통
  @Column(columnDefinition = "TEXT")
  private String abilityExpln; // 어빌리티 설명
  private String trgtType; // 대상 유형
  private String dmgType; // 피해 유형
  private String statEffType; // 상태 이상 유형
  private String useCost; // 소모 비용
  private Integer coolTime; // 재사용 대기시간
  private Integer castTime; // 시전 시간
  @Column(columnDefinition = "TEXT")
  private String useCnd; // 사용 조건
}
```

#### ProjectAbility (프로젝트 종속 어빌리티)
```java
@Entity
@Table(name = "project_abilities")
public class ProjectAbilityEntity extends CommonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long abilityNo;
  @ManyToOne
  @JoinColumn(name = "prj_no")
  private ProjectEntity project; // 프로젝트 (FK, NOT NULL)
  // AbilityEntity와 동일한 필드 구조
}
```

#### Character (인물)
```java
@Entity
@Table(name = "characters")
public class CharacterEntity extends CommonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long charNo;
  @ManyToOne
  @JoinColumn(name = "prj_no")
  private ProjectEntity project; // 프로젝트 (FK, 인덱스)
  private String charNm; // 인물명 (인덱스)
  private String aliasNm; // 이명 및 별명
  private String roleType; // 역할 유형
  @Column(columnDefinition = "TEXT")
  private String logline; // 한 줄 정의
  private String narrFunc; // 서사적 기능
  @ManyToOne
  @JoinColumn(name = "race_no")
  private CreatureEntity race; // 종족 (FK, 인덱스)
  @ManyToOne
  @JoinColumn(name = "ntn_no")
  private NationEntity ntn; // 국가 (FK, 인덱스)
  @ManyToOne
  @JoinColumn(name = "org_no")
  private OrganizationEntity org; // 조직 (FK, 인덱스)
  private String orgRank; // 조직 내 직위
  // 기타 신체적, 심리적, 사회적 특징 필드 다수 (TEXT)
}
```

#### 매핑 테이블 예시: CharTraitMap (인물-특성 매핑)
```java
@Entity
@Table(name = "char_trait_maps")
@IdClass(CharTraitMapId.class)
public class CharTraitMapEntity {
  @Id
  @ManyToOne
  @JoinColumn(name = "char_no")
  private CharacterEntity character; // 인물 (FK, 인덱스)
  @Id
  private Long traitNo; // 특성 번호 (인덱스)
  @Id
  @Enumerated(EnumType.STRING)
  private TraitType traitType; // GLOBAL | PROJECT (인덱스)
  @Column(columnDefinition = "TEXT")
  private String traitRmk; // 특성 비고
}
```

### 4.3. 엔티티 관계도 (주요 관계)

```
User (1) ──< (N) Project
Project (1) ──< (N) ProjectTrait
Project (1) ──< (N) ProjectAbility
Project (1) ──< (N) Character
Project (1) ──< (N) Creature
Project (1) ──< (N) Item
Project (1) ──< (N) Region
Project (1) ──< (N) Nation
Project (1) ──< (N) Organization
Project (1) ──< (N) Event
Project (1) ──< (N) Lore

Trait (전역) ──< (N) CharTraitMap ──> (N) Character
ProjectTrait ──< (N) CharTraitMap ──> (N) Character
Ability (전역) ──< (N) CharAbilityMap ──> (N) Character
ProjectAbility ──< (N) CharAbilityMap ──> (N) Character

Character (N) ──< (N) CharRelation ──> (N) Character
Character (N) ──< (N) CharGroupRelation ──> (1) Organization/Nation
```

---

## 5. Non-Functional Requirements & Risks

### Performance
- **API 응답 속도**: 목록 조회 200ms 이내, 상세 조회 100ms 이내
- **페이징**: 기본 페이지 크기 20건, 최대 100건
- **인덱스 활용**: 검색 필드, 외래키, 복합 키에 인덱스 설정
- **쿼리 최적화**: N+1 문제 방지 (Fetch Join, Entity Graph 활용)

### Security
- **인증**: JWT Access Token (1시간) + Refresh Token (2주)
- **비밀번호**: bcrypt 해싱 (라운드 10 이상)
- **SQL Injection 방지**: JPA/Hibernate 사용 (PreparedStatement 자동 사용)
- **XSS 방지**: 입력값 검증 및 이스케이프 처리
- **CORS**: 프론트엔드 도메인만 허용
- **권한 관리**: 
  - 공유 여부(`shrn_yn`) 기반 접근 제어
  - 역할 기반 권한 (ADMIN, USER)
  - 소유자 기반 권한 (`crt_no` 기반)

### Data Integrity
- **소프트 삭제**: `del_yn = 'Y'`로 표시, 실제 데이터는 유지
- **사용 중인 리소스 삭제 방지**: 특성/어빌리티 사용 현황 확인 후 삭제
- **트랜잭션 관리**: `@Transactional` 어노테이션 활용
- **데이터 일관성**: JPA 영속성 컨텍스트 활용

### Scalability
- **확장 가능한 구조**: 도메인 주도 설계 패턴 적용
- **새로운 엔티티 추가 용이성**: CommonEntity/CommonVO 상속 패턴
- **API 버전 관리**: 향후 API 버전 관리 고려

### Risks
1. **전역 풀 권한 관리**: 전역 특성/어빌리티 수정 권한 정책 결정 필요
   - **대응**: 관리자 권한 또는 모든 사용자 권한 중 선택
2. **대량 데이터 처리**: 프로젝트별 설정이 많아질 경우 성능 저하
   - **대응**: 페이징, 인덱스 최적화, 캐싱 고려
3. **복합 키 관리**: 매핑 테이블의 복합 키 관리 복잡도
   - **대응**: 명확한 ID 클래스 정의 및 Repository 메서드 설계
4. **전역/프로젝트 종속 통합 검색**: 성능 및 복잡도
   - **대응**: 효율적인 쿼리 설계 및 인덱스 활용

---

## 6. API 설계 원칙

### RESTful API
- **엔드포인트 명명**: 리소스 중심, 복수형 사용
- **HTTP 메서드**: GET (조회), POST (생성), PATCH (수정), DELETE (삭제)
- **패스 파라미터**: PK는 `no` 형식 사용 (예: `:traitNo`, `:abilityNo`, `:charNo`, `:prjNo`)

### 응답 형식

**표준 응답 구조** (`ApiResponse<T>`):
```java
public class ApiResponse<T> {
  private T data;              // 응답 데이터 (제네릭 타입)
  private boolean error;       // 에러 여부 (true: 에러, false: 성공)
  private String code;         // 응답 코드 (ResponseCode Enum의 value)
  private String message;      // 응답 메시지
}
```

**응답 규칙**:
- **모든 응답**: HTTP 200 상태 코드 반환
- **실제 상태**: 응답 본문의 `code` 필드로 구분 (`ResponseCode` Enum 활용)
- **에러 여부**: `error` 필드로 성공/실패 구분 (true: 에러, false: 성공)
- **응답 데이터**: `data` 필드에 실제 데이터 포함 (제네릭 타입)
- **목록 조회**: `SearchVO` 기반 페이징 응답
- **상세 조회**: 단일 객체 반환
- **생성/수정**: 생성/수정된 객체 반환

**응답 예시**:
```json
// 성공 응답
{
  "data": { "userNo": 1, "userEml": "user@example.com" },
  "error": false,
  "code": "OK",
  "message": "요청 성공"
}

// 에러 응답
{
  "data": null,
  "error": true,
  "code": "NOT_FOUND",
  "message": "리소스를 찾을 수 없음"
}
```

### 인증 및 권한
- **인증 불필요 (Public)**: 로그인, 회원가입, 비밀번호 재설정 요청
- **인증 필요 (Authenticated)**: 대부분의 엔드포인트
- **공유 여부 기반 접근 제어**: `shrn_yn = 'Y'`인 경우 모든 인증된 사용자 조회 가능
- **역할 기반 권한**: ADMIN은 모든 리소스 접근 가능, USER는 소유자 또는 공유된 리소스만 접근

---

## 7. 참고 문서

- **프로젝트 목표 및 구상**: `specs/project/프로젝트-목표-및-구상.md`
- **프로젝트 현재 상태**: `specs/project/프로젝트-현재-상태.md`
- **API 구상**: `specs/api/API-구상.md`
- **API 엔드포인트**: `specs/api/API-엔드포인트.md`
- **기존 코드 패턴**: 
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/CommonEntity.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/CommonVO.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/user/UserEntity.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/user/UserVO.java`
