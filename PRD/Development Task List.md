# Development Task List
## Fantasy Builder API (Spring Boot)

**작성일**: 2026-01-27  
**버전**: 1.0

---

## Phase 1: 인증 및 사용자 관리

### 1.1. 사용자 엔티티 및 기본 구조
- [x] **UserEntity 생성**
  - `CommonEntity` 상속
  - 필드: `userNo`, `userEml`, `userNm`, `userRole`, `proflImgUrl`, `biogp`, `enpswd`, `reshToken`, `acntLckYn`, `lgnFailNmtm`, `lastLgnDt`, `lastLgnIp`, `lastPswdChgDt`, `emlAuthYn`, `mktRecpAgreYn`
  - `@Entity`, `@Table(name = "users")` 적용
  - 인덱스 설정 (`user_eml`)
- [x] **UserVO 생성**
  - `CommonVO` 상속
  - Entity → VO 변환 생성자
  - VO → Entity 변환 메서드
  - 검색용 필드 추가 (`searchType`, `searchKeyword`)
- [x] **UserRepository 생성**
  - `JpaRepository<UserEntity, Long>`, `JpaSpecificationExecutor<UserEntity>` 상속
  - 검색 메서드: `findByUserEml`, `findByUserEmlAndDelYn`, `findByUserNmContainingAndDelYn`, `findByDelYn`
  - 페이징 지원 메서드
- [x] **UserService 생성**
  - CRUD 메서드 구현
  - 비밀번호 해싱 (bcrypt) - `PasswordHelper` 활용
  - 이메일 중복 검증
  - 사용자 목록 조회 메서드 (페이징 및 검색 지원)
  - `PageUtils` 활용
- [x] **UserController 생성**
  - `GET /api/users` - 사용자 목록 조회 (페이징, 검색 지원)
  - `GET /api/users/me` - 현재 사용자 정보 조회 (Authenticated, TODO: 인증 구현 필요)
  - `PATCH /api/users/me` - 현재 사용자 정보 수정 (Authenticated, TODO: 인증 구현 필요)
  - `GET /api/users/{userNo}` - 사용자 조회
  - `POST /api/users` - 회원가입
  - `DELETE /api/users/{userNo}` - 회원 탈퇴 (소프트 삭제, Authenticated, TODO: 인증 구현 필요)

### 1.2. 인증 기능
- [x] **JWT 토큰 생성 및 검증**
  - Access Token 생성 (1시간)
  - Refresh Token 생성 (1주일)
  - 토큰 검증 로직
  - JWT 유틸리티 클래스 생성 (`JwtHelper`)
- [x] **비밀번호 해싱 유틸리티**
  - bcrypt 해싱 함수
  - 비밀번호 검증 함수
  - 비밀번호 유틸리티 클래스 생성 (`PasswordHelper`) ✅ 이미 구현됨
- [x] **인증 Service 생성**
  - 로그인 메서드 (`signin`)
  - 토큰 갱신 메서드 (`refresh`) - Access Token과 Refresh Token 모두 갱신
  - 로그아웃 메서드 (`signout`)
  - 비밀번호 재설정 요청 메서드 (`forgotPassword`)
  - 비밀번호 재설정 처리 메서드 (`resetPassword`)
  - 비밀번호 변경 메서드 (`changePassword`)
- [x] **인증 Controller 생성**
  - `POST /api/auth/signin` - 로그인 (Public, 토큰을 쿠키에 저장)
  - `POST /api/auth/signup` - 회원가입 (Public)
  - `POST /api/auth/signout` - 로그아웃 (Authenticated, 쿠키 삭제)
  - `POST /api/auth/refresh` - 토큰 갱신 (Public, Refresh Token 필요, 새로운 토큰을 쿠키에 저장)
  - `POST /api/auth/forgot-password` - 비밀번호 재설정 요청 (Public)
  - `POST /api/auth/reset-password` - 비밀번호 재설정 (Public, Token 필요)
  - `POST /api/auth/change-password` - 비밀번호 변경 (Authenticated)

### 1.3. 보안 기능
- [x] **계정 잠금 기능**
  - 로그인 실패 횟수 추적 (`lgnFailNmtm`)
  - 계정 잠금 처리 (`acntLckYn`)
  - 로그인 시 계정 잠금 상태 확인
  - 최대 실패 횟수 설정 (5회, application.yml에서 설정 가능)
- [x] **비밀번호 정책**
  - 비밀번호 강도 검증 (최소 8자, 대문자/소문자/숫자/특수문자 포함)
  - 비밀번호 변경 이력 관리 (`lastPswdChgDt`)
  - 현재 비밀번호 확인 로직 (비밀번호 변경 시)
- [x] **JWT 토큰 검증 미들웨어/필터**
  - JWT 토큰 검증 필터 생성 (`JwtAuthenticationFilter`)
  - 쿠키에서 Access Token 읽기
  - SecurityContext에 인증 정보 설정
  - 현재 사용자 추출 (SecurityContext에서)
- [x] **이메일 발송 유틸리티 (비밀번호 재설정용)**
  - 이메일 발송 함수 (`EmailHelper`)
  - SMTP 설정 관리 (application.yml)
  - 비밀번호 재설정 링크 이메일 발송
- [ ] **이메일 인증 (향후 구현)**
  - 이메일 인증 토큰 생성
  - 이메일 인증 처리 (`emlAuthYn`)

---

## Phase 2: 공통 인프라 및 기반 구조

### 2.1. 공통 응답 구조
- [x] **ApiResponse 래퍼 클래스 생성**
  - 제네릭 타입 지원 (`ApiResponse<T>`)
  - 필드: `data` (T), `error` (boolean), `code` (String), `message` (String)
  - `ResponseCode` Enum 활용
  - 정적 팩토리 메서드:
    - `success(T data)`: 성공 응답 생성 (기본 코드: OK)
    - `success(ResponseCode code, T data)`: 성공 응답 생성 (커스텀 코드)
    - `error(ResponseCode code, String message)`: 에러 응답 생성
- [x] **ListType 클래스 생성**
  - 제네릭 타입 지원 (`ListType<T>`)
  - 필드: `list` (List<T>, 필수), `totalCnt` (Long, 필수), `pageSize` (Integer, nullable), `page` (Integer, nullable), `totalPage` (Integer, nullable), `isFirst` (Boolean, nullable), `isLast` (Boolean, nullable)
  - **모든 목록 응답에 사용** (페이징 여부와 무관)
  - **`totalCnt`는 반드시 채움** (페이징 여부와 무관하게 전체 아이템 개수)
  - 페이징이 있는 경우: 모든 필드 채움
  - 페이징이 없는 경우: `list`와 `totalCnt`는 채우고, 나머지 페이징 필드는 `null`
  - `Page<T>`를 `ListType<T>`로 변환하는 유틸리티 메서드
  - `List<T>`를 `ListType<T>`로 변환하는 유틸리티 메서드 (페이징 없이, `totalCnt`는 리스트 크기로 설정)
- [x] **GlobalExceptionHandler 구현**
  - `@RestControllerAdvice` 사용
  - 주요 예외 타입별 핸들러 구현
  - `ResponseCode` 기반 에러 응답
  - 로깅 추가

### 2.2. 공통 유틸리티
- [x] **페이징 유틸리티**
  - `Pageable` 변환 유틸리티 (페이지 번호를 받아서 PageRequest 생성)
  - 기본 페이지 크기: 10개 (고정)
  - `Page<T>`를 `ListType<T>`로 변환하는 유틸리티 메서드 (페이징 있는 경우)
  - `List<T>`를 `ListType<T>`로 변환하는 유틸리티 메서드 (페이징 없는 경우, `totalCnt`는 리스트 크기로 설정, 나머지 페이징 필드는 null)
- [x] **검색 조건 빌더**
  - `Specification` 빌더 유틸리티
  - 동적 쿼리 생성 지원
  - 각 Service에서 직접 구현하는 것으로 결정

---

## Phase 3: 전역 풀 엔티티 구현 ⭐ 최우선

### 3.1. Trait (전역 특성) 엔티티
- [x] **TraitEntity 생성**
  - `CommonEntity` 상속
  - 필드: `traitNo`, `traitNm`, `traitExpln`, `traitLcls`, `traitMcls`, `aplyTrgt`, `cnflTraitNo`
  - `@Entity`, `@Table(name = "traits")` 적용
  - 인덱스 설정 (`trait_nm`, `cnfl_trait_no`)
- [x] **TraitVO 생성**
  - `CommonVO` 상속
  - Entity → VO 변환 생성자
  - VO → Entity 변환 메서드
- [x] **TraitRepository 생성**
  - `JpaRepository<TraitEntity, Long>` 상속
  - 검색 메서드: `findByTraitNmContaining`, `findByTraitLcls`, `findByAplyTrgt`
- [x] **TraitService 생성**
  - CRUD 메서드 구현
  - 검색 및 페이징 지원
  - [~] 사용 현황 조회 메서드 (`findUsage`) - 기본 구조만 구현 (향후 매핑 테이블 구현 시 완성)
- [x] **TraitController 생성**
  - `GET /api/traits` - 목록 조회
  - `GET /api/traits/{traitNo}` - 상세 조회
  - `POST /api/traits` - 생성
  - `PATCH /api/traits/{traitNo}` - 수정
  - `DELETE /api/traits/{traitNo}` - 삭제 (소프트 삭제)
  - `GET /api/traits/{traitNo}/usage` - 사용 현황 조회

### 3.2. Ability (전역 어빌리티) 엔티티
- [x] **AbilityEntity 생성**
  - `CommonEntity` 상속
  - 필드: `abilityNo`, `abilityNm`, `abilityType`, `abilityLcls`, `abilityExpln`, `trgtType`, `dmgType`, `statEffType`, `useCost`, `coolTime`, `castTime`, `useCnd`
  - `@Entity`, `@Table(name = "abilities")` 적용
  - 인덱스 설정 (`ability_nm`)
- [x] **AbilityVO 생성**
  - `CommonVO` 상속
  - Entity → VO 변환 생성자
  - VO → Entity 변환 메서드
- [x] **AbilityRepository 생성**
  - `JpaRepository<AbilityEntity, Long>` 상속
  - 검색 메서드: `findByAbilityNmContaining`, `findByAbilityType`, `findByAbilityLcls`
- [x] **AbilityService 생성**
  - CRUD 메서드 구현
  - 검색 및 페이징 지원
  - [~] 사용 현황 조회 메서드 (`findUsage`) - 기본 구조만 구현 (향후 매핑 테이블 구현 시 완성)
- [x] **AbilityController 생성**
  - `GET /api/abilities` - 목록 조회
  - `GET /api/abilities/{abilityNo}` - 상세 조회
  - `POST /api/abilities` - 생성
  - `PATCH /api/abilities/{abilityNo}` - 수정
  - `DELETE /api/abilities/{abilityNo}` - 삭제 (소프트 삭제)
  - `GET /api/abilities/{abilityNo}/usage` - 사용 현황 조회

---

## Phase 4: 프로젝트 도메인 구현

### 4.1. Project 엔티티
- [ ] **ProjectEntity 생성**
  - `CommonEntity` 상속
  - 필드: `prjNo`, `userNo` (FK), `prjNm`, `genreType`, `prjDesc`, `cvrImgUrl`, `prjExpln`, `prjVer`
  - `@ManyToOne` 관계: `UserEntity`
  - 인덱스 설정 (`prj_nm`, `user_no`)
- [ ] **ProjectVO 생성**
  - `CommonVO` 상속
  - Entity → VO 변환 생성자
  - VO → Entity 변환 메서드
- [ ] **ProjectRepository 생성**
  - `JpaRepository<ProjectEntity, Long>` 상속
  - 검색 메서드: `findByUserNo`, `findByPrjNmContaining`
- [ ] **ProjectService 생성**
  - CRUD 메서드 구현
  - 소유자 권한 검증
  - 검색 및 페이징 지원
- [ ] **ProjectController 생성**
  - `GET /projects` - 목록 조회
  - `GET /projects/{prjNo}` - 상세 조회
  - `POST /projects` - 생성
  - `PATCH /projects/{prjNo}` - 수정
  - `DELETE /projects/{prjNo}` - 삭제 (소프트 삭제)

### 4.2. ProjectTrait (프로젝트 종속 특성) 엔티티
- [ ] **ProjectTraitEntity 생성**
  - `CommonEntity` 상속
  - 필드: `traitNo`, `prjNo` (FK), `traitNm`, `traitExpln`, `traitLcls`, `traitMcls`, `aplyTrgt`, `cnflTraitNo`, `cnflTraitType`
  - `@ManyToOne` 관계: `ProjectEntity`
  - `@Enumerated(EnumType.STRING)`: `cnflTraitType` (GLOBAL | PROJECT)
  - 인덱스 설정 (`prj_no`, `trait_nm`, `cnfl_trait_no`)
- [ ] **ProjectTraitVO 생성**
  - `CommonVO` 상속
  - Entity → VO 변환 생성자
  - VO → Entity 변환 메서드
- [ ] **ProjectTraitRepository 생성**
  - `JpaRepository<ProjectTraitEntity, Long>` 상속
  - 검색 메서드: `findByPrjNo`, `findByPrjNoAndTraitNmContaining`
- [ ] **ProjectTraitService 생성**
  - CRUD 메서드 구현
  - 프로젝트 소유자 권한 검증
  - 검색 및 페이징 지원
- [ ] **ProjectTraitController 생성**
  - `GET /projects/{prjNo}/traits` - 목록 조회
  - `GET /projects/{prjNo}/traits/{traitNo}` - 상세 조회
  - `POST /projects/{prjNo}/traits` - 생성
  - `PATCH /projects/{prjNo}/traits/{traitNo}` - 수정
  - `DELETE /projects/{prjNo}/traits/{traitNo}` - 삭제 (소프트 삭제)

### 4.3. ProjectAbility (프로젝트 종속 어빌리티) 엔티티
- [ ] **ProjectAbilityEntity 생성**
  - `CommonEntity` 상속
  - 필드: `abilityNo`, `prjNo` (FK), AbilityEntity와 동일한 필드 구조
  - `@ManyToOne` 관계: `ProjectEntity`
  - 인덱스 설정 (`prj_no`, `ability_nm`)
- [ ] **ProjectAbilityVO 생성**
  - `CommonVO` 상속
  - Entity → VO 변환 생성자
  - VO → Entity 변환 메서드
- [ ] **ProjectAbilityRepository 생성**
  - `JpaRepository<ProjectAbilityEntity, Long>` 상속
  - 검색 메서드: `findByPrjNo`, `findByPrjNoAndAbilityNmContaining`
- [ ] **ProjectAbilityService 생성**
  - CRUD 메서드 구현
  - 프로젝트 소유자 권한 검증
  - 검색 및 페이징 지원
- [ ] **ProjectAbilityController 생성**
  - `GET /projects/{prjNo}/abilities` - 목록 조회
  - `GET /projects/{prjNo}/abilities/{abilityNo}` - 상세 조회
  - `POST /projects/{prjNo}/abilities` - 생성
  - `PATCH /projects/{prjNo}/abilities/{abilityNo}` - 수정
  - `DELETE /projects/{prjNo}/abilities/{abilityNo}` - 삭제 (소프트 삭제)

### 4.4. 통합 검색 기능
- [ ] **TraitSearchService 생성**
  - 전역 특성 + 프로젝트 종속 특성 통합 검색
  - 타입 구분 필터링 (GLOBAL | PROJECT)
  - 키워드, 카테고리별 필터링
- [ ] **AbilitySearchService 생성**
  - 전역 어빌리티 + 프로젝트 종속 어빌리티 통합 검색
  - 타입 구분 필터링 (GLOBAL | PROJECT)
  - 키워드, 유형별 필터링
- [ ] **통합 검색 Controller 엔드포인트**
  - `GET /projects/{prjNo}/traits/search` - 특성 통합 검색
  - `GET /projects/{prjNo}/abilities/search` - 어빌리티 통합 검색

---

## Phase 5: 설정 엔티티 구현

### 5.1. CoreRule (코어 설정) 엔티티
- [ ] **CoreRuleEntity 생성**
  - `CommonEntity` 상속
  - 필드: `coreNo`, `prjNo` (FK), `coreNm`, `defDesc`, `aplyScope`, `strcElem`, `mechDesc`, `narrAply`, `keywords`, `linkDocs`, `rmk`
  - `@ManyToOne` 관계: `ProjectEntity`
  - 인덱스 설정 (`prj_no`)
- [ ] **CoreRuleVO 생성**
- [ ] **CoreRuleRepository 생성**
- [ ] **CoreRuleService 생성**
- [ ] **CoreRuleController 생성**
  - `GET /projects/{prjNo}/core-rules` - 목록 조회
  - `GET /projects/{prjNo}/core-rules/{coreNo}` - 상세 조회
  - `POST /projects/{prjNo}/core-rules` - 생성
  - `PATCH /projects/{prjNo}/core-rules/{coreNo}` - 수정
  - `DELETE /projects/{prjNo}/core-rules/{coreNo}` - 삭제

### 5.2. Creature (생물/종족) 엔티티
- [ ] **CreatureEntity 생성**
  - `CommonEntity` 상속
  - 필드: `creatureNo`, `prjNo` (FK), `creatureNm`, `creatureType`, `dangerGrd`, `identStat`, `creatureExpln`, 기타 생물학적/사회적/문화적 특징 필드
  - `@ManyToOne` 관계: `ProjectEntity`
  - 인덱스 설정 (`prj_no`, `creature_nm`)
- [ ] **CreatureVO 생성**
- [ ] **CreatureRepository 생성**
- [ ] **CreatureService 생성**
- [ ] **CreatureController 생성**
  - `GET /projects/{prjNo}/creatures` - 목록 조회
  - `GET /projects/{prjNo}/creatures/{creatureNo}` - 상세 조회
  - `POST /projects/{prjNo}/creatures` - 생성
  - `PATCH /projects/{prjNo}/creatures/{creatureNo}` - 수정
  - `DELETE /projects/{prjNo}/creatures/{creatureNo}` - 삭제

### 5.3. Character (인물) 엔티티
- [ ] **CharacterEntity 생성**
  - `CommonEntity` 상속
  - 필드: `charNo`, `prjNo` (FK), `charNm`, `aliasNm`, `roleType`, `logline`, `narrFunc`, `raceNo` (FK), `ntnNo` (FK), `orgNo` (FK), `orgRank`, 기타 신체적/심리적/사회적 특징 필드
  - `@ManyToOne` 관계: `ProjectEntity`, `CreatureEntity`, `NationEntity`, `OrganizationEntity`
  - 인덱스 설정 (`prj_no`, `char_nm`, `race_no`, `ntn_no`, `org_no`)
- [ ] **CharacterVO 생성**
- [ ] **CharacterRepository 생성**
- [ ] **CharacterService 생성**
- [ ] **CharacterController 생성**
  - `GET /projects/{prjNo}/characters` - 목록 조회
  - `GET /projects/{prjNo}/characters/{charNo}` - 상세 조회
  - `POST /projects/{prjNo}/characters` - 생성
  - `PATCH /projects/{prjNo}/characters/{charNo}` - 수정
  - `DELETE /projects/{prjNo}/characters/{charNo}` - 삭제

### 5.4. Item (아이템) 엔티티
- [ ] **ItemEntity 생성**
  - `CommonEntity` 상속
  - 필드: `itemNo`, `prjNo` (FK), `itemNm`, `clsMain`, `clsSub`, `itemGrd`, `logline`, `appDesc`, `visualFeat`, `attrType`, `dmgType`, `mainFunc`, 기타 기능/사용 조건/역사 필드
  - `@ManyToOne` 관계: `ProjectEntity`
  - 인덱스 설정 (`prj_no`, `item_nm`)
- [ ] **ItemVO 생성**
- [ ] **ItemRepository 생성**
- [ ] **ItemService 생성**
- [ ] **ItemController 생성**
  - `GET /projects/{prjNo}/items` - 목록 조회
  - `GET /projects/{prjNo}/items/{itemNo}` - 상세 조회
  - `POST /projects/{prjNo}/items` - 생성
  - `PATCH /projects/{prjNo}/items/{itemNo}` - 수정
  - `DELETE /projects/{prjNo}/items/{itemNo}` - 삭제

### 5.5. Region (지역) 엔티티
- [ ] **RegionEntity 생성**
  - `CommonEntity` 상속
  - 필드: `regionNo`, `prjNo` (FK), `upRegionNo` (FK, 자기 참조), `regionNm`, `regionType`, `explorStat`, `regionExpln`, `locDesc`, `climateEnv`, `terrainFeat`, `envSpec`, `funcFeat`, `dangerLvl`, `dangerFctr`, `inhabitInfo`, `unknownEntity`, `mainFclty`, `rsrcList`, `ntnNo` (FK)
  - `@ManyToOne` 관계: `ProjectEntity`, `RegionEntity` (상위 지역), `NationEntity`
  - 인덱스 설정 (`prj_no`, `region_nm`, `up_region_no`, `ntn_no`)
- [ ] **RegionVO 생성**
- [ ] **RegionRepository 생성**
- [ ] **RegionService 생성**
- [ ] **RegionController 생성**
  - `GET /projects/{prjNo}/regions` - 목록 조회
  - `GET /projects/{prjNo}/regions/{regionNo}` - 상세 조회
  - `POST /projects/{prjNo}/regions` - 생성
  - `PATCH /projects/{prjNo}/regions/{regionNo}` - 수정
  - `DELETE /projects/{prjNo}/regions/{regionNo}` - 삭제

### 5.6. Nation (국가) 엔티티
- [ ] **NationEntity 생성**
  - `CommonEntity` 상속
  - 필드: `ntnNo`, `prjNo` (FK), `ntnNm`, `ntnType`, `logline`, `capitalNm`, `rulerTxt`, `polSys`, `adminLaw`, `stateRlgn`, `rlgnDesc`, `natIdlg`, `mainPlcy`, `tabooAct`, `diplPlcy`, `intrCnfl`, `hiddenFact`, `econStruct`, `socCltr`, `milPwr`, `histDesc`, `currIssue`
  - `@ManyToOne` 관계: `ProjectEntity`
  - 인덱스 설정 (`prj_no`, `ntn_nm`)
- [ ] **NationVO 생성**
- [ ] **NationRepository 생성**
- [ ] **NationService 생성**
- [ ] **NationController 생성**
  - `GET /projects/{prjNo}/nations` - 목록 조회
  - `GET /projects/{prjNo}/nations/{ntnNo}` - 상세 조회
  - `POST /projects/{prjNo}/nations` - 생성
  - `PATCH /projects/{prjNo}/nations/{ntnNo}` - 수정
  - `DELETE /projects/{prjNo}/nations/{ntnNo}` - 삭제

### 5.7. Organization (조직) 엔티티
- [ ] **OrganizationEntity 생성**
  - `CommonEntity` 상속
  - 필드: `orgNo`, `prjNo` (FK), `orgNm`, `orgType`, `logline`, `orgTheme`, `purpPub`, `purpHid`, `fndBg`, `orgStrc`, `orgScale`, `joinCond`, `exitRule`, `mainAct`, `actArea`, `actMthd`, `fundSrc`, `keyFig`, `histDesc`, `currStat`
  - `@ManyToOne` 관계: `ProjectEntity`
  - 인덱스 설정 (`prj_no`, `org_nm`)
- [ ] **OrganizationVO 생성**
- [ ] **OrganizationRepository 생성**
- [ ] **OrganizationService 생성**
- [ ] **OrganizationController 생성**
  - `GET /projects/{prjNo}/organizations` - 목록 조회
  - `GET /projects/{prjNo}/organizations/{orgNo}` - 상세 조회
  - `POST /projects/{prjNo}/organizations` - 생성
  - `PATCH /projects/{prjNo}/organizations/{orgNo}` - 수정
  - `DELETE /projects/{prjNo}/organizations/{orgNo}` - 삭제

### 5.8. Event (사건) 엔티티
- [ ] **EventEntity 생성**
  - `CommonEntity` 상속
  - 필드: `eventNo`, `prjNo` (FK), `eventNm`, `occurTime`, `occurLoc`, `smry`, `causePub`, `causeReal`, `sideAChar`, `sideAOrg`, `sideANtn`, `sideBChar`, `sideBOrg`, `sideBNtn`, `sideCChar`, `sideCOrg`, `sideCNtn`, `flowTrgr`, `flowCrisis`, `flowClimax`, `flowConcl`, `dmgRslt`, `socChng`, `currConn`, `recOfficial`, `truthHid`, `rmk`
  - `@ManyToOne` 관계: `ProjectEntity`
  - 인덱스 설정 (`prj_no`, `event_nm`)
- [ ] **EventVO 생성**
- [ ] **EventRepository 생성**
- [ ] **EventService 생성**
- [ ] **EventController 생성**
  - `GET /projects/{prjNo}/events` - 목록 조회
  - `GET /projects/{prjNo}/events/{eventNo}` - 상세 조회
  - `POST /projects/{prjNo}/events` - 생성
  - `PATCH /projects/{prjNo}/events/{eventNo}` - 수정
  - `DELETE /projects/{prjNo}/events/{eventNo}` - 삭제

### 5.9. Lore (전승/설화) 엔티티
- [ ] **LoreEntity 생성**
  - `CommonEntity` 상속
  - 필드: `loreNo`, `prjNo` (FK), `loreNm`, `loreType`, `mainSubj`, `smry`, `transMthd`, `pubPerc`, `lorePlot`, `realFact`, `distRsn`, `diffDesc`, `cltrImpact`, `plotConn`, `rmk`
  - `@ManyToOne` 관계: `ProjectEntity`
  - 인덱스 설정 (`prj_no`, `lore_nm`)
- [ ] **LoreVO 생성**
- [ ] **LoreRepository 생성**
- [ ] **LoreService 생성**
- [ ] **LoreController 생성**
  - `GET /projects/{prjNo}/lores` - 목록 조회
  - `GET /projects/{prjNo}/lores/{loreNo}` - 상세 조회
  - `POST /projects/{prjNo}/lores` - 생성
  - `PATCH /projects/{prjNo}/lores/{loreNo}` - 수정
  - `DELETE /projects/{prjNo}/lores/{loreNo}` - 삭제

---

## Phase 6: 매핑 테이블 구현

### 6.1. CharTraitMap (인물-특성 매핑)
- [ ] **CharTraitMapEntity 생성**
  - 복합 키: `charNo`, `traitNo`, `traitType`
  - `@IdClass(CharTraitMapId.class)` 사용
  - 필드: `charNo` (FK), `traitNo`, `traitType` (GLOBAL | PROJECT), `traitRmk`
  - 인덱스 설정 (`char_no`, `trait_no`, `trait_type`)
- [ ] **CharTraitMapId 복합 키 클래스 생성**
- [ ] **CharTraitMapVO 생성**
- [ ] **CharTraitMapRepository 생성**
- [ ] **CharTraitMapService 생성**
- [ ] **CharTraitMapController 생성**
  - `GET /projects/{prjNo}/characters/{charNo}/traits` - 목록 조회
  - `GET /projects/{prjNo}/characters/{charNo}/traits/{traitNo}` - 상세 조회 (Query: `traitType`)
  - `POST /projects/{prjNo}/characters/{charNo}/traits` - 매칭 생성
  - `PATCH /projects/{prjNo}/characters/{charNo}/traits/{traitNo}` - 매핑 수정 (Query: `traitType`)
  - `DELETE /projects/{prjNo}/characters/{charNo}/traits/{traitNo}` - 매핑 삭제 (Query: `traitType`)

### 6.2. CharAbilityMap (인물-어빌리티 매핑)
- [ ] **CharAbilityMapEntity 생성**
  - 복합 키: `charNo`, `abilityNo`, `abilityType`
  - 필드: `charNo` (FK), `abilityNo`, `abilityType` (GLOBAL | PROJECT), `profLvl`, `abilityRmk`
- [ ] **CharAbilityMapId 복합 키 클래스 생성**
- [ ] **CharAbilityMapVO 생성**
- [ ] **CharAbilityMapRepository 생성**
- [ ] **CharAbilityMapService 생성**
- [ ] **CharAbilityMapController 생성**
  - `GET /projects/{prjNo}/characters/{charNo}/abilities` - 목록 조회
  - `GET /projects/{prjNo}/characters/{charNo}/abilities/{abilityNo}` - 상세 조회 (Query: `abilityType`)
  - `POST /projects/{prjNo}/characters/{charNo}/abilities` - 매칭 생성
  - `PATCH /projects/{prjNo}/characters/{charNo}/abilities/{abilityNo}` - 매핑 수정 (Query: `abilityType`)
  - `DELETE /projects/{prjNo}/characters/{charNo}/abilities/{abilityNo}` - 매핑 삭제 (Query: `abilityType`)

### 6.3. CreatureTraitMap (종족-특성 매핑)
- [ ] **CreatureTraitMapEntity 생성**
  - PK: `mapNo`
  - 필드: `creatureNo` (FK), `traitNo`, `traitType` (GLOBAL | PROJECT), `traitRmk`
- [ ] **CreatureTraitMapVO 생성**
- [ ] **CreatureTraitMapRepository 생성**
- [ ] **CreatureTraitMapService 생성**
- [ ] **CreatureTraitMapController 생성**
  - `GET /projects/{prjNo}/creatures/{creatureNo}/traits` - 목록 조회
  - `GET /projects/{prjNo}/creatures/{creatureNo}/traits/{mapNo}` - 상세 조회
  - `POST /projects/{prjNo}/creatures/{creatureNo}/traits` - 매칭 생성
  - `PATCH /projects/{prjNo}/creatures/{creatureNo}/traits/{mapNo}` - 매핑 수정
  - `DELETE /projects/{prjNo}/creatures/{creatureNo}/traits/{mapNo}` - 매핑 삭제

### 6.4. CreatureAbilityMap (종족-어빌리티 매핑)
- [ ] **CreatureAbilityMapEntity 생성**
  - PK: `mapNo`
  - 필드: `creatureNo` (FK), `abilityNo`, `abilityType` (GLOBAL | PROJECT), `profLvl`, `abilityRmk`
- [ ] **CreatureAbilityMapVO 생성**
- [ ] **CreatureAbilityMapRepository 생성**
- [ ] **CreatureAbilityMapService 생성**
- [ ] **CreatureAbilityMapController 생성**
  - `GET /projects/{prjNo}/creatures/{creatureNo}/abilities` - 목록 조회
  - `GET /projects/{prjNo}/creatures/{creatureNo}/abilities/{mapNo}` - 상세 조회
  - `POST /projects/{prjNo}/creatures/{creatureNo}/abilities` - 매칭 생성
  - `PATCH /projects/{prjNo}/creatures/{creatureNo}/abilities/{mapNo}` - 매핑 수정
  - `DELETE /projects/{prjNo}/creatures/{creatureNo}/abilities/{mapNo}` - 매핑 삭제

### 6.5. ItemTraitMap, ItemAbilityMap (아이템 매핑)
- [ ] **ItemTraitMapEntity 생성**
- [ ] **ItemAbilityMapEntity 생성**
- [ ] **각각의 VO, Repository, Service, Controller 생성**
  - `GET /projects/{prjNo}/items/{itemNo}/traits` - 목록 조회
  - `GET /projects/{prjNo}/items/{itemNo}/abilities` - 목록 조회
  - (생성, 수정, 삭제 엔드포인트)

### 6.6. RegionTraitMap, NtnTraitMap, OrgTraitMap (집단 매핑)
- [ ] **RegionTraitMapEntity 생성**
- [ ] **NtnTraitMapEntity 생성**
- [ ] **OrgTraitMapEntity 생성**
- [ ] **각각의 VO, Repository, Service, Controller 생성**
  - `GET /projects/{prjNo}/regions/{regionNo}/traits` - 목록 조회
  - `GET /projects/{prjNo}/nations/{ntnNo}/traits` - 목록 조회
  - `GET /projects/{prjNo}/organizations/{orgNo}/traits` - 목록 조회
  - (생성, 수정, 삭제 엔드포인트)

### 6.7. CharItemMap, CharRelation, CharGroupRelation (인물 관계)
- [ ] **CharItemMapEntity 생성** (인물-아이템 소유 관계)
- [ ] **CharRelationEntity 생성** (인물-인물 관계)
- [ ] **CharGroupRelationEntity 생성** (인물-그룹 관계)
- [ ] **각각의 VO, Repository, Service, Controller 생성**

### 6.8. LoreCharMap, LoreItemMap (전승 매핑)
- [ ] **LoreCharMapEntity 생성**
- [ ] **LoreItemMapEntity 생성**
- [ ] **각각의 VO, Repository, Service, Controller 생성**

### 6.9. GroupRelation, EventEntry (집단 관계 및 사건 참여)
- [ ] **GroupRelationEntity 생성** (국가-국가, 국가-조직, 조직-조직 관계)
- [ ] **EventEntryEntity 생성** (사건 참여, 다형 참조)
- [ ] **각각의 VO, Repository, Service, Controller 생성**

---

## Phase 7: 권한 관리 및 보안

### 7.1. 접근 제어 구현
- [ ] **공유 여부 기반 접근 제어**
  - `shrn_yn = 'Y'`: 모든 인증된 사용자 조회 가능
  - `shrn_yn = 'N'`: 소유자(`crt_no`) 또는 ADMIN만 조회 가능
- [ ] **역할 기반 권한 검증**
  - ADMIN: 모든 리소스 접근 가능
  - USER: 소유자 또는 공유된 리소스만 접근
- [ ] **프로젝트 소유자 권한 검증**
  - 프로젝트 종속 리소스는 프로젝트 소유자만 수정/삭제 가능

### 7.2. 인증 미들웨어
- [ ] **JWT 토큰 검증 필터/인터셉터**
  - Access Token 검증
  - Refresh Token 갱신
- [ ] **현재 사용자 추출**
  - `@AuthenticationPrincipal` 또는 커스텀 어노테이션 활용
- [ ] **권한 검증 어노테이션**
  - `@PreAuthorize` 또는 커스텀 어노테이션 활용

---

## Phase 8: 검증 및 테스트

### 8.1. 단위 테스트
- [ ] **Service 레이어 테스트**
  - 각 Service 메서드별 단위 테스트
  - Mock Repository 활용
- [ ] **Repository 레이어 테스트**
  - `@DataJpaTest` 활용
  - 커스텀 쿼리 테스트

### 8.2. 통합 테스트
- [ ] **Controller 레이어 테스트**
  - `@WebMvcTest` 또는 `@SpringBootTest` 활용
  - MockMvc를 통한 API 엔드포인트 테스트
- [ ] **전체 플로우 테스트**
  - 엔드투엔드 테스트 시나리오

### 8.3. 성능 테스트
- [ ] **페이징 성능 테스트**
- [ ] **대량 데이터 처리 테스트**
- [ ] **쿼리 최적화 검증**

---

## Phase 9: 문서화 및 최적화

### 9.1. API 문서화
- [ ] **SpringDoc OpenAPI 설정**
  - API 엔드포인트 문서화
  - 요청/응답 스키마 정의
- [ ] **API 사용 예제 작성**

### 9.2. 코드 최적화
- [ ] **N+1 문제 해결**
  - `@EntityGraph` 또는 `Fetch Join` 활용
- [ ] **인덱스 최적화**
  - 검색 성능 향상을 위한 인덱스 추가
- [ ] **캐싱 전략**
  - 전역 풀 데이터 캐싱 고려

### 9.3. 최종 검토
- [ ] **코드 리뷰**
  - 코딩 규칙 준수 여부 확인
  - CommonEntity/CommonVO 상속 패턴 확인
- [ ] **문서 최종 검토**
  - API 문서 정확성 확인
  - README 업데이트

---

## 우선순위 가이드

### 최우선 (Phase 1)
1. **인증 및 사용자 관리** (Phase 1)
   - 사용자 엔티티 및 기본 CRUD
   - JWT 기반 인증 시스템
   - 로그인/로그아웃/토큰 갱신

### 높은 우선순위 (Phase 2, 3, 4.2, 4.3)
2. **공통 인프라 및 기반 구조** (Phase 2)
   - ApiResponse 래퍼 클래스
   - GlobalExceptionHandler
   - 공통 유틸리티
3. **전역 특성/어빌리티 관리** (Phase 3)
4. **프로젝트 종속 특성/어빌리티 관리** (Phase 4.2, 4.3)
5. **통합 검색 기능** (Phase 4.4)

### 중간 우선순위 (Phase 4.1, 5.1-5.3)
6. **프로젝트 관리** (Phase 4.1)
7. **코어 설정, 종족, 인물** (Phase 5.1-5.3)

### 낮은 우선순위 (Phase 5.4-5.9, 6.1-6.4)
8. **나머지 설정 엔티티** (Phase 5.4-5.9)
9. **주요 매핑 테이블** (Phase 6.1-6.4)

### 최하위 우선순위 (Phase 6.5-6.9, 7-9)
10. **나머지 매핑 테이블** (Phase 6.5-6.9)
11. **권한 관리 강화** (Phase 7)
12. **테스트 및 최적화** (Phase 8-9)

---

## 참고사항

- **Phase 1 우선**: 인증 및 사용자 관리 기능부터 구현 시작
- **엔티티 작성 시 필수 확인사항**:
  - [ ] CommonEntity 상속
  - [ ] 필수 어노테이션 적용 (`@Entity`, `@Table`, `@Getter`, `@NoArgsConstructor`)
  - [ ] 인덱스 설정 확인
  - [ ] 관계 매핑 정확성
- **VO 작성 시 필수 확인사항**:
  - [ ] CommonVO 상속
  - [ ] Entity → VO 변환 생성자
  - [ ] VO → Entity 변환 메서드
- **각 Phase 완료 후**: 코드 리뷰 및 테스트 수행 권장
