# Coding Rules & Guidelines
## Fantasy Builder API (Spring Boot)

**작성일**: 2026-01-27  
**버전**: 1.0

---

## 1. Architectural Principles

### Design Pattern
**도메인 주도 설계 (Domain-Driven Design, DDD) 패턴**을 적용합니다.

**선택 이유**:
- 복잡한 비즈니스 로직을 도메인 모델 중심으로 구조화
- 엔티티와 VO의 명확한 분리로 데이터 계층과 표현 계층 분리
- 도메인별 패키지 구조로 코드 조직화 및 유지보수성 향상
- Spring Data JPA와 자연스럽게 통합

### Directory Strategy
**도메인별 패키지 구조**를 사용합니다. 각 도메인은 독립적인 패키지로 구성되며, 공통 기능은 `common` 패키지에 위치합니다.

```
src/main/java/dev/nihilncunia/fantasy_builder/
├── domain/                          # 도메인 계층
│   ├── common/                      # 공통 도메인
│   │   ├── CommonEntity.java        # 공통 엔티티 (JPA)
│   │   ├── CommonVO.java            # 공통 VO
│   │   ├── ResponseCode.java        # 응답 코드 Enum
│   │   └── UserRoleCode.java        # 사용자 역할 Enum
│   ├── user/                        # 사용자 도메인
│   │   ├── UserEntity.java          # 사용자 엔티티
│   │   ├── UserVO.java              # 사용자 VO
│   │   ├── UserRepository.java       # 사용자 Repository
│   │   ├── UserService.java          # 사용자 Service
│   │   └── UserController.java      # 사용자 Controller
│   ├── project/                     # 프로젝트 도메인
│   ├── trait/                       # 특성 도메인 (전역)
│   ├── projectTrait/                # 프로젝트 종속 특성 도메인
│   ├── ability/                     # 어빌리티 도메인 (전역)
│   ├── projectAbility/             # 프로젝트 종속 어빌리티 도메인
│   ├── character/                   # 인물 도메인
│   ├── creature/                    # 종족 도메인
│   ├── item/                        # 아이템 도메인
│   ├── region/                      # 지역 도메인
│   ├── nation/                      # 국가 도메인
│   ├── organization/                # 조직 도메인
│   ├── event/                       # 사건 도메인
│   └── lore/                        # 전승/설화 도메인
└── FantasyBuilderApplication.java   # Spring Boot 메인 클래스
```

---

## 2. Folder Structure (Tree View)

```
src/
├── main/
│   ├── java/
│   │   └── dev/nihilncunia/fantasy_builder/
│   │       ├── domain/                      # 도메인 계층
│   │       │   ├── common/                  # 공통 도메인
│   │       │   │   ├── CommonEntity.java
│   │       │   │   ├── CommonVO.java
│   │       │   │   ├── ResponseCode.java
│   │       │   │   └── UserRoleCode.java
│   │       │   ├── {domain}/                # 각 도메인별 패키지
│   │       │   │   ├── {Domain}Entity.java  # JPA Entity
│   │       │   │   ├── {Domain}VO.java       # Value Object
│   │       │   │   ├── {Domain}Repository.java  # Spring Data JPA Repository
│   │       │   │   ├── {Domain}Service.java     # 비즈니스 로직
│   │       │   │   └── {Domain}Controller.java   # REST Controller
│   │       └── FantasyBuilderApplication.java
│   └── resources/
│       ├── application.yml                   # Spring Boot 설정
│       └── application-{profile}.yml        # 프로파일별 설정
└── test/
    └── java/
        └── dev/nihilncunia/fantasy_builder/
            └── {domain}/
                └── {Domain}ServiceTest.java   # 단위 테스트
                └── {Domain}ControllerTest.java # 통합 테스트
```

---

## 3. Naming Conventions (Strict)

### Files
- **Java 클래스**: `PascalCase` (예: `UserEntity.java`, `UserVO.java`, `UserService.java`)
- **Enum 클래스**: `PascalCase` + `Code` 또는 `Type` 접미사 (예: `ResponseCode.java`, `UserRoleCode.java`, `TraitType.java`)
- **인터페이스**: `PascalCase` (예: `UserRepository.java` - Spring Data JPA 인터페이스)

### Classes
- **Entity**: `{Domain}Entity` (예: `UserEntity`, `ProjectEntity`, `TraitEntity`)
- **VO**: `{Domain}VO` (예: `UserVO`, `ProjectVO`, `TraitVO`)
- **Repository**: `{Domain}Repository` (예: `UserRepository`, `ProjectRepository`)
- **Service**: `{Domain}Service` (예: `UserService`, `ProjectService`)
- **Controller**: `{Domain}Controller` (예: `UserController`, `ProjectController`)

### Variables
- **필드 변수**: `camelCase` (예: `userNo`, `userEml`, `userNm`)
- **Boolean 필드**: `is` 접두사 또는 `Yn` 접미사 (예: `isActive`, `useYn`, `delYn`)
- **컬렉션**: 복수형 사용 (예: `userList`, `traitList`)
- **로컬 변수**: `camelCase` (예: `userEntity`, `userVO`)

### Constants
- **Enum 값**: `UPPER_SNAKE_CASE` (예: `OK`, `BAD_REQUEST`, `USER`, `ADMIN`)
- **정적 상수**: `UPPER_SNAKE_CASE` (예: `DEFAULT_PAGE_SIZE = 20`)

### Methods
- **Repository 메서드**: Spring Data JPA 네이밍 규칙 (예: `findByUserEml`, `findByPrjNoAndDelYn`)
- **Service 메서드**: 동사로 시작 (예: `createUser`, `updateUser`, `deleteUser`, `findUserById`)
- **Controller 메서드**: HTTP 메서드 기반 (예: `getUser`, `createUser`, `updateUser`, `deleteUser`)

### Database
- **테이블명**: `snake_case` 복수형 (예: `users`, `projects`, `traits`, `char_trait_maps`)
- **컬럼명**: `snake_case` (예: `user_no`, `user_eml`, `prj_no`, `trait_no`)
- **인덱스명**: `idx_{table}_{column}` (예: `idx_users_user_eml`, `idx_projects_prj_no`)

### Commits
**Conventional Commits** 형식을 사용합니다:
- `feat: 새로운 기능 추가`
- `fix: 버그 수정`
- `docs: 문서 수정`
- `style: 코드 포맷팅`
- `refactor: 코드 리팩토링`
- `test: 테스트 추가`
- `chore: 빌드 설정 변경`

---

## 4. Coding Standards

### Type Safety
- **`any` 타입 금지**: Java는 정적 타입 언어이므로 타입 안정성 보장
- **제네릭 활용**: 컬렉션 사용 시 제네릭 타입 명시 (예: `List<UserEntity>`, `Map<Long, UserVO>`)
- **Optional 활용**: null 가능성이 있는 반환값은 `Optional<T>` 사용

### Entity 작성 규칙

#### 1. CommonEntity 상속
모든 Entity는 `CommonEntity`를 상속받아야 합니다:

```java
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends CommonEntity {
  // 필드 정의
}
```

#### 2. 필수 어노테이션
- `@Entity`: JPA Entity 표시
- `@Table(name = "{table_name}")`: 테이블명 명시
- `@Getter`: Lombok (필드 접근자)
- `@NoArgsConstructor(access = AccessLevel.PROTECTED)`: 기본 생성자 (JPA 요구사항)
- `@Id`: Primary Key
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: 자동 증가 전략

#### 3. 필드 작성 규칙
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_no")
private Long userNo;

@Column(name = "user_eml", nullable = false, unique = true)
private String userEml;

@Enumerated(EnumType.STRING)
@Column(name = "user_role")
private UserRoleCode userRole;

@Column(name = "biogp", columnDefinition = "TEXT")
private String biogp; // TEXT 타입인 경우
```

#### 4. 관계 매핑
```java
// Many-to-One
@ManyToOne
@JoinColumn(name = "prj_no")
private ProjectEntity project;

// One-to-Many (양방향 관계인 경우)
@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
private List<ProjectTraitEntity> projectTraits;
```

#### 5. Builder 패턴 (선택)
복잡한 생성 로직이 있는 경우 `@Builder` 사용:

```java
@Builder
public UserEntity(String userEml, String userNm, String enpswd, UserRoleCode userRole) {
  this.userEml = userEml;
  this.userNm = userNm;
  this.enpswd = enpswd;
  this.userRole = userRole != null ? userRole : UserRoleCode.USER;
}
```

#### 6. 비즈니스 로직 메서드
Entity 내에 비즈니스 로직 메서드를 포함할 수 있습니다:

```java
public void updateProfile(String userNm, String biogp) {
  if (userNm != null) this.userNm = userNm;
  if (biogp != null) this.biogp = biogp;
}

public void delete(Long deleterNo) {
  this.useYn = "N";
  this.delYn = "Y";
  this.delNo = deleterNo;
  this.delDt = LocalDateTime.now();
  this.updtNo = deleterNo;
  this.updtDt = LocalDateTime.now();
}
```

### VO 작성 규칙

#### 1. CommonVO 상속
모든 VO는 `CommonVO`를 상속받아야 합니다:

```java
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserVO extends CommonVO {
  // 필드 정의
}
```

#### 2. 필수 어노테이션
- `@Data`: Lombok (Getter, Setter, toString, equals, hashCode)
- `@NoArgsConstructor`: 기본 생성자
- `@EqualsAndHashCode(callSuper = true)`: 부모 클래스 포함
- `@ToString(callSuper = true)`: 부모 클래스 포함

#### 3. Entity → VO 변환 생성자
```java
public UserVO(UserEntity userEntity) {
  this.mapFromEntity(userEntity);
}

private void mapFromEntity(UserEntity userEntity) {
  this.userNo = userEntity.getUserNo();
  this.userEml = userEntity.getUserEml();
  this.userNm = userEntity.getUserNm();
  // CommonVO 필드도 매핑
  this.useYn = userEntity.getUseYn();
  this.crtDt = userEntity.getCrtDt();
  // ...
}
```

#### 4. VO → Entity 변환 메서드
```java
public UserEntity toEntity() {
  return UserEntity.builder()
      .userEml(this.userEml)
      .userNm(this.userNm)
      .enpswd(this.enpswd)
      .userRole(this.userRole)
      .build();
}
```

#### 5. Entity 업데이트 메서드
```java
public void updateEntity(UserEntity entity) {
  entity.updateProfile(this.userNm, this.biogp);
  if (this.enpswd != null && !this.enpswd.isEmpty()) {
    entity.changePassword(this.enpswd);
  }
}
```

### Repository 작성 규칙

#### 1. Spring Data JPA 인터페이스
```java
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByUserEml(String userEml);
  
  @Query("SELECT u FROM UserEntity u WHERE u.userEml = :email AND u.delYn = 'N'")
  Optional<UserEntity> findActiveUserByEmail(@Param("email") String email);
  
  @Modifying
  @Query("UPDATE UserEntity u SET u.delYn = 'Y' WHERE u.userNo IN :userNos")
  void softDeleteUsers(@Param("userNos") List<Long> userNos);
}
```

#### 2. 커스텀 쿼리
- 복잡한 쿼리는 `@Query` 어노테이션 사용
- JPQL 또는 Native Query 사용 가능
- 페이징은 `Pageable` 파라미터 사용

### Service 작성 규칙

#### 1. 비즈니스 로직 중심
```java
@Service
@Transactional(readOnly = true)
public class UserService {
  private final UserRepository userRepository;
  
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Transactional
  public UserVO createUser(UserVO userVO) {
    // 비즈니스 로직
    UserEntity userEntity = userVO.toEntity();
    UserEntity saved = userRepository.save(userEntity);
    return new UserVO(saved);
  }
  
  public UserVO findUserById(Long userNo) {
    UserEntity userEntity = userRepository.findById(userNo)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));
    return new UserVO(userEntity);
  }
}
```

#### 2. 트랜잭션 관리
- 읽기 전용 메서드: `@Transactional(readOnly = true)`
- 쓰기 메서드: `@Transactional`

#### 3. 반환 타입 규칙
- **Service 메서드는 항상 VO를 반환합니다**
  - Entity를 직접 반환하지 않음
  - Entity → VO 변환 후 반환
  - 예: `public UserVO findUserById(Long userNo)` → `UserVO` 반환
  - 예: `public ListType<UserVO> findUsers(UserVO searchVO, int page)` → `ListType<UserVO>` 반환

#### 4. 예외 처리
- 비즈니스 예외: 커스텀 예외 클래스 사용
- 데이터 없음: `EntityNotFoundException` 또는 커스텀 예외

### Controller 작성 규칙

#### 1. RESTful API 설계
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;
  
  public UserController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping
  public ResponseEntity<ApiResponse<ListType<UserVO>>> getUsers(
      @RequestParam(defaultValue = "1") int page,
      UserVO searchVO) {
    ListType<UserVO> listType = userService.findUsers(searchVO, page);
    return ResponseEntity.ok(ApiResponse.success(listType));
  }
  
  @GetMapping("/{userNo}")
  public ResponseEntity<ApiResponse<UserVO>> getUser(@PathVariable Long userNo) {
    UserVO userVO = userService.findUserById(userNo);
    return ResponseEntity.ok(ApiResponse.success(userVO));
  }
  
  @PostMapping
  public ResponseEntity<ApiResponse<UserVO>> createUser(@RequestBody UserVO userVO) {
    UserVO created = userService.createUser(userVO);
    return ResponseEntity.ok(ApiResponse.success(ResponseCode.CREATED, created));
  }
  
  // 에러 응답 예시
  @GetMapping("/{userNo}/invalid")
  public ResponseEntity<ApiResponse<Void>> getInvalidUser(@PathVariable Long userNo) {
    return ResponseEntity.ok(ApiResponse.error(ResponseCode.NOT_FOUND, "사용자를 찾을 수 없습니다."));
  }
}
```

**중요 규칙**:
- **ApiResponse의 data 필드에는 항상 VO를 넘깁니다**
  - Entity를 직접 반환하지 않음
  - Service에서 Entity를 VO로 변환하여 반환
  - Controller에서 Service로부터 받은 VO를 그대로 ApiResponse에 담아 반환

#### 2. 응답 형식

**ApiResponse<T> 래퍼 클래스 구조**:
```java
public class ApiResponse<T> {
  private T data;              // 응답 데이터 (제네릭 타입)
  private boolean error;       // 에러 여부 (true: 에러, false: 성공)
  private String code;         // 응답 코드 (ResponseCode Enum의 value)
  private String message;      // 응답 메시지
  
  // 정적 팩토리 메서드
  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(data, false, ResponseCode.OK.getValue(), ResponseCode.OK.getDescription());
  }
  
  public static <T> ApiResponse<T> success(ResponseCode code, T data) {
    return new ApiResponse<>(data, false, code.getValue(), code.getDescription());
  }
  
  public static <T> ApiResponse<T> error(ResponseCode code, String message) {
    return new ApiResponse<>(null, true, code.getValue(), message);
  }
}
```

**사용 규칙**:
- 모든 응답은 `ApiResponse<T>` 래퍼 클래스 사용
- `ResponseCode` Enum으로 상태 코드 관리
- HTTP 상태 코드는 항상 200 (실제 상태는 `code` 필드로 구분)
- 성공 시: `error = false`, `data`에 응답 데이터 포함
- 실패 시: `error = true`, `data = null`, `message`에 에러 메시지 포함
- **중요: ApiResponse의 data 필드에는 항상 VO를 넘깁니다**
  - Entity를 직접 반환하지 않음
  - Service에서 Entity를 VO로 변환하여 반환
  - Controller에서 VO를 ApiResponse에 담아 반환
- **리스트 응답**: `ApiResponse<ListType<T>>` 형태 사용 (페이징 여부와 무관)
  - `ListType<T>`는 데이터 배열과 페이징 정보를 포함
  - `ListType<T>`의 `list` 필드에도 VO 배열이 들어감
  - **페이징이 있는 경우**: 모든 필드 채움 (`list`, `totalCnt`, `pageSize`, `page`, `totalPage`, `isFirst`, `isLast`)
  - **페이징이 없는 경우**: `list`와 `totalCnt`는 채우고, 나머지 페이징 필드(`pageSize`, `page`, `totalPage`, `isFirst`, `isLast`)는 `null`
  - **`totalCnt`는 반드시 채움** (페이징 여부와 무관하게 전체 아이템 개수)
  - 단일 객체 응답: `ApiResponse<UserVO>`
  - 리스트 응답 (페이징): `ApiResponse<ListType<UserVO>>`
  - 리스트 응답 (비페이징): `ApiResponse<ListType<UserVO>>` (`totalCnt` 포함, 나머지 페이징 필드 null)

### Error Handling

#### 1. Global Exception Handler
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleEntityNotFound(EntityNotFoundException e) {
    return ResponseEntity.ok(ApiResponse.error(ResponseCode.NOT_FOUND, e.getMessage()));
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
    return ResponseEntity.ok(ApiResponse.error(ResponseCode.VALIDATION_ERROR, "입력값 검증 실패"));
  }
  
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
    return ResponseEntity.ok(ApiResponse.error(e.getResponseCode(), e.getMessage()));
  }
}
```

**응답 구조 예시**:
```json
// 성공 응답 (error: false)
{
  "data": { "userNo": 1, "userEml": "user@example.com", "userNm": "홍길동" },
  "error": false,
  "code": "OK",
  "message": "요청 성공"
}

// 에러 응답 (error: true)
{
  "data": null,
  "error": true,
  "code": "NOT_FOUND",
  "message": "사용자를 찾을 수 없습니다."
}
```

#### 2. 커스텀 예외
```java
public class BusinessException extends RuntimeException {
  private final ResponseCode responseCode;
  
  public BusinessException(ResponseCode responseCode, String message) {
    super(message);
    this.responseCode = responseCode;
  }
}
```

### Logging
- **로깅 프레임워크**: SLF4J + Logback (Spring Boot 기본)
- **로깅 레벨**: 
  - `ERROR`: 예외 및 심각한 오류
  - `WARN`: 경고 메시지
  - `INFO`: 주요 비즈니스 로직 (생성, 수정, 삭제)
  - `DEBUG`: 디버깅 정보 (개발 환경에서만)

```java
@Slf4j
@Service
public class UserService {
  public UserVO createUser(UserVO userVO) {
    log.info("Creating user: {}", userVO.getUserEml());
    // ...
  }
}
```

---

## 5. 공통 패턴 및 베스트 프랙티스

### 1. CommonEntity/CommonVO 상속 패턴
- **모든 Entity는 CommonEntity 상속 필수**
- **모든 VO는 CommonVO 상속 필수**
- 공통 필드(useYn, delYn, crtDt 등)는 자동으로 포함

### 2. 소프트 삭제 패턴
- 실제 삭제 대신 `delYn = 'Y'`로 표시
- `CommonEntity.delete(Long deleterNo)` 메서드 활용
- 조회 시 `delYn = 'N'` 조건 필수

### 3. 공유 여부 기반 접근 제어
- `shrn_yn = 'Y'`: 모든 인증된 사용자 조회 가능
- `shrn_yn = 'N'`: 소유자(`crt_no`) 또는 ADMIN만 조회 가능

### 4. 페이징 패턴

#### 페이징 규칙
- **프론트엔드 요청**: 쿼리스트링으로 페이지 번호만 전달 (`?page=1`, `?page=2` 등)
- **서버 처리**: 
  - 페이지 번호를 받아서 서버에서 페이징 계산 수행 (페이지 크기, 오프셋 등)
  - 기본 페이지 크기: **10개** (고정)
  - 계산된 페이징 정보를 응답에 포함하여 반환
- **응답 구조**: `ApiResponse<ListType<T>>` 형태로 반환

#### ListType 클래스 정의
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListType<T> {
  private List<T> list;           // 데이터 배열 (필수)
  private Long totalCnt;          // 모든 아이템 개수 (필수, 페이징 여부와 무관하게 항상 채움)
  private Integer pageSize;       // 페이지 당 아이템 개수 (페이징 시 필수, 비페이징 시 null)
  private Integer page;           // 현재 페이지 (1-based, 페이징 시 필수, 비페이징 시 null)
  private Integer totalPage;      // 전체 페이지 수 (페이징 시 필수, 비페이징 시 null)
  private Boolean isFirst;        // 첫 페이지 여부 (페이징 시 필수, 비페이징 시 null)
  private Boolean isLast;         // 마지막 페이지 여부 (페이징 시 필수, 비페이징 시 null)
}
```

**중요**: 
- **모든 목록 응답은 `ListType<T>` 구조를 사용**합니다.
- `totalCnt`: 페이징 여부와 무관하게 **반드시 채움** (전체 아이템 개수)
- 페이징이 있는 경우: 모든 필드를 채웁니다.
- 페이징이 없는 경우: `list`와 `totalCnt`는 채우고, 나머지 페이징 관련 필드(`pageSize`, `page`, `totalPage`, `isFirst`, `isLast`)는 `null`로 설정합니다.

#### 구현 예시 (페이징 있는 경우)
```java
@GetMapping
public ResponseEntity<ApiResponse<ListType<UserVO>>> getUsers(
    @RequestParam(defaultValue = "1") int page,
    UserVO searchVO) {
  // 페이지 번호를 받아서 Pageable 생성 (기본 크기: 10)
  Pageable pageable = PageRequest.of(page - 1, 10); // 0-based index
  
  // 검색 조건 구성
  Specification<UserEntity> spec = buildSpecification(searchVO);
  Page<UserEntity> entityPage = userRepository.findAll(spec, pageable);
  
  // Page를 ListType으로 변환
  ListType<UserVO> listType = new ListType<>(
    entityPage.map(UserVO::new).getContent(),  // list
    entityPage.getTotalElements(),              // totalCnt
    10,                                         // pageSize
    page,                                       // page (1-based)
    entityPage.getTotalPages(),                 // totalPage
    entityPage.isFirst(),                       // isFirst
    entityPage.isLast()                         // isLast
  );
  
  return ResponseEntity.ok(ApiResponse.success(listType));
}
```

#### 구현 예시 (페이징 없는 경우)
```java
@GetMapping("/all")
public ResponseEntity<ApiResponse<ListType<UserVO>>> getAllUsers(UserVO searchVO) {
  // 페이징 없이 전체 조회
  Specification<UserEntity> spec = buildSpecification(searchVO);
  List<UserEntity> entityList = userRepository.findAll(spec);
  
  // 페이징 정보 없이 ListType 생성 (totalCnt는 반드시 채움, 나머지 페이징 필드는 null)
  ListType<UserVO> listType = new ListType<>(
    entityList.stream().map(UserVO::new).collect(Collectors.toList()),  // list
    (long) entityList.size(),  // totalCnt (반드시 채움)
    null,  // pageSize (null)
    null,  // page (null)
    null,  // totalPage (null)
    null,  // isFirst (null)
    null   // isLast (null)
  );
  
  return ResponseEntity.ok(ApiResponse.success(listType));
}
```

#### 응답 JSON 예시 (페이징 있는 경우)
```json
{
  "data": {
    "list": [
      { "userNo": 1, "userEml": "user1@example.com", ... },
      { "userNo": 2, "userEml": "user2@example.com", ... }
    ],
    "totalCnt": 25,
    "pageSize": 10,
    "page": 1,
    "totalPage": 3,
    "isFirst": true,
    "isLast": false
  },
  "error": false,
  "code": "OK",
  "message": "요청 성공"
}
```

#### 응답 JSON 예시 (페이징 없는 경우)
```json
{
  "data": {
    "list": [
      { "userNo": 1, "userEml": "user1@example.com", ... },
      { "userNo": 2, "userEml": "user2@example.com", ... },
      { "userNo": 3, "userEml": "user3@example.com", ... }
    ],
    "totalCnt": 3,
    "pageSize": null,
    "page": null,
    "totalPage": null,
    "isFirst": null,
    "isLast": null
  },
  "error": false,
  "code": "OK",
  "message": "요청 성공"
}
```

#### ListType 변환 유틸리티 (선택)
```java
public class PageUtils {
  // 페이징이 있는 경우
  public static <T> ListType<T> toListType(Page<T> page, int currentPage) {
    return new ListType<>(
      page.getContent(),
      page.getTotalElements(),
      10,
      currentPage,
      page.getTotalPages(),
      page.isFirst(),
      page.isLast()
    );
  }
  
  // 페이징이 없는 경우
  public static <T> ListType<T> toListType(List<T> list) {
    return new ListType<>(
      list,
      (long) list.size(),  // totalCnt (반드시 채움)
      null,  // pageSize
      null,  // page
      null,  // totalPage
      null,  // isFirst
      null   // isLast
    );
  }
}
```

### 5. Enum 활용
- 응답 코드: `ResponseCode` Enum
- 사용자 역할: `UserRoleCode` Enum
- 특성/어빌리티 타입: `TraitType`, `AbilityType` Enum (GLOBAL | PROJECT)

---

## 6. 코드 리뷰 체크리스트

### Entity
- [ ] CommonEntity 상속 여부
- [ ] 필수 어노테이션 적용 여부
- [ ] 컬럼명 명시 여부 (`@Column(name = "...")`)
- [ ] 관계 매핑 정확성

### VO
- [ ] CommonVO 상속 여부
- [ ] Entity → VO 변환 생성자 존재 여부
- [ ] VO → Entity 변환 메서드 존재 여부

### Service
- [ ] 트랜잭션 어노테이션 적용 여부
- [ ] 예외 처리 적절성
- [ ] 비즈니스 로직 명확성

### Controller
- [ ] RESTful API 설계 준수 여부
- [ ] 응답 형식 일관성 (`ApiResponse` 사용)
- [ ] 입력값 검증 (`@Valid` 사용)

---

## 7. 참고 자료

- **Spring Boot 공식 문서**: https://spring.io/projects/spring-boot
- **Spring Data JPA 문서**: https://spring.io/projects/spring-data-jpa
- **Lombok 문서**: https://projectlombok.org/
- **기존 코드 패턴**: 
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/CommonEntity.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/common/CommonVO.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/user/UserEntity.java`
  - `src/main/java/dev/nihilncunia/fantasy_builder/domain/user/UserVO.java`
