# API 구상 문서

이 문서는 Fantasy Builder API 서버 개발을 위한 구상 문서입니다.

## 프로젝트 개요

### 기술 스택
- **백엔드**: 별도 API 서버 (분리된 아키텍처)
- **프론트엔드**: Nuxt.js (별도 프로젝트)

## 핵심 개념

### 특성과 어빌리티: 전역 풀 vs 프로젝트 종속
**⚠️ 가장 중요한 핵심 기능**

특성(Traits)과 어빌리티(Abilitys)은 **전역 풀**과 **프로젝트 종속** 두 가지 형태로 관리됩니다.

#### 데이터베이스 구조

**전역 풀 (Global Pool):**
- **특성 테이블 (`traits`)**: `prj_no` 필드 없음, 모든 프로젝트가 공유
- **어빌리티 테이블 (`abilitys`)**: `prj_no` 필드 없음, 모든 프로젝트가 공유

**프로젝트 종속 (Project-Specific):**
- **프로젝트 특성 테이블 (`project_traits`)**: `prj_no` 필드 포함, 특정 프로젝트 전용
- **프로젝트 어빌리티 테이블 (`project_abilitys`)**: `prj_no` 필드 포함, 특정 프로젝트 전용

> **참고**: 이미지 테이블 분리 패턴과 동일하게, 전역과 프로젝트 종속을 별도 테이블로 분리

#### API 설계 고려사항

**엔드포인트 분리:**
- 전역 풀: `/api/traits`, `/api/abilitys` (프로젝트 독립적)
- 프로젝트 종속: `/api/projects/:prjNo/traits`, `/api/projects/:prjNo/abilitys`

**검색 및 조회:**
- 전역 풀과 프로젝트 종속을 통합 검색하거나 분리 검색 가능
- 프로젝트별 설정 페이지에서는 해당 프로젝트의 종속 특성/어빌리티도 함께 표시

**권한 관리:**
- 전역 풀 수정 권한: 관리자 또는 모든 사용자 (정책 결정 필요)
- 프로젝트 종속 수정 권한: 해당 프로젝트 소유자/편집자

**사용 추적:**
- 전역 특성/어빌리티: 어떤 프로젝트에서 사용 중인지 추적
- 프로젝트 종속 특성/어빌리티: 해당 프로젝트 내에서만 사용

**삭제 정책:**
- 전역 풀: 사용 중인 경우 삭제 제한 또는 경고
- 프로젝트 종속: 프로젝트 삭제 시 함께 삭제 또는 프로젝트 내에서만 삭제 가능

### 설정 엔티티
각 프로젝트 내에서 사용자는 다음 설정들을 자유롭게 생성하고 관리할 수 있습니다:

1. **프로젝트 (Projects)**: 작품/세계관의 최상위 컨테이너
2. **코어 설정 (Core Rules)**: 세계관의 법칙과 구조
3. **생물/종족 (Creatures)**: 종족 및 생물 설정
4. **인물 (Characters)**: 캐릭터 설정
5. **아이템 (Items)**: 아이템 및 도구 설정
6. **지역 (Regions)**: 지역 및 장소 설정
7. **국가 (Nations)**: 국가 및 정치 체제 설정
8. **조직 (Organizations)**: 단체 및 조직 설정
9. **사건 (Events)**: 역사적 사건 및 이벤트
10. **전승/설화 (Lores)**: 전설, 설화, 전승 설정

### 관계 및 매핑

#### 특성/어빌리티 매핑 (전역 풀 + 프로젝트 종속 모두 지원)
매핑 테이블은 전역 풀과 프로젝트 종속 특성/어빌리티을 모두 참조할 수 있어야 합니다.

**인물 매핑:**
- **인물 ↔ 어빌리티**: `char_ability_maps` 
  - 전역 어빌리티(`abilitys.ability_no`) 또는 프로젝트 어빌리티(`project_abilitys.ability_no`) 참조
  - 참조 타입 필드로 구분 (`ability_type`: 'GLOBAL' | 'PROJECT')
- **인물 ↔ 특성**: `char_trait_maps`
  - 전역 특성(`traits.trait_no`) 또는 프로젝트 특성(`project_traits.trait_no`) 참조
  - 참조 타입 필드로 구분 (`trait_type`: 'GLOBAL' | 'PROJECT')

**종족 매핑:**
- **종족 ↔ 특성**: `creature_trait_maps`
  - 전역 특성(`traits.trait_no`) 또는 프로젝트 특성(`project_traits.trait_no`) 참조
  - 참조 타입 필드로 구분 (`trait_type`: 'GLOBAL' | 'PROJECT')
- **종족 ↔ 어빌리티**: `creature_ability_maps`
  - 전역 어빌리티(`abilitys.ability_no`) 또는 프로젝트 어빌리티(`project_abilitys.ability_no`) 참조
  - 참조 타입 필드로 구분 (`ability_type`: 'GLOBAL' | 'PROJECT')
  - 종족 고유 어빌리티도 지원 (기본 숙련도 등 관리 가능)

**아이템 매핑:**
- **아이템 ↔ 어빌리티**: `item_ability_maps` (전역/프로젝트 어빌리티 모두 지원)
- **아이템 ↔ 특성**: `item_trait_maps` (전역/프로젝트 특성 모두 지원)

**집단 매핑:**
- **국가 ↔ 특성**: `ntn_trait_maps` (전역/프로젝트 특성 모두 지원)
- **조직 ↔ 특성**: `org_trait_maps` (전역/프로젝트 특성 모두 지원)
- **지역 ↔ 특성**: `region_trait_maps` (전역/프로젝트 특성 모두 지원)

#### 프로젝트 종속 매핑
- **인물 ↔ 인물**: `char_relations` (인물 간 관계)
- **인물 ↔ 그룹**: `char_group_relations` (조직/국가 관계)
- **인물 ↔ 아이템**: `char_item_maps` (소유 관계)
- **전승 ↔ 인물**: `lore_char_maps`
- **전승 ↔ 아이템**: `lore_item_maps`
- **집단 ↔ 집단**: `group_relations` (국가/조직 간 관계)
- **사건 참여**: `event_entries` (다형 참조)

## 핵심 기능 구상

### 1. 특성 및 어빌리티 관리 ⭐ 최우선 기능
**프로젝트 관리와 동등한 중요도를 가지는 핵심 기능**

#### 특성 관리 (Traits Management)

**전역 특성 관리:**
- **검색**: 키워드, 대분류(`trait_lcls`), 중분류(`trait_mcls`), 적용 대상(`aply_trgt`)별 검색
- **추가**: 새로운 전역 특성 생성 및 등록
- **수정**: 기존 전역 특성 정보 수정
- **삭제**: 전역 특성 삭제 (사용 중인 경우 경고 및 영향도 분석)
- **상충 특성 관리**: 특성 간 상충 관계 설정 (`cnfl_trait_no`)
- **카테고리 관리**: 대분류/중분류 체계 관리

**프로젝트 종속 특성 관리:**
- **검색**: 특정 프로젝트 내에서 키워드, 대분류, 중분류, 적용 대상별 검색
- **추가**: 프로젝트 전용 특성 생성 및 등록
- **수정**: 프로젝트 종속 특성 정보 수정
- **삭제**: 프로젝트 종속 특성 삭제 (해당 프로젝트 내에서만 영향)
- **상충 특성 관리**: 전역 특성 또는 동일 프로젝트의 특성과 상충 관계 설정 가능

**API 엔드포인트:**
- **전역 특성:**
  - `GET /api/traits` - 전역 특성 목록 조회
  - `GET /api/traits/:id` - 전역 특성 상세 조회
  - `POST /api/traits` - 전역 특성 생성
  - `PATCH /api/traits/:id` - 전역 특성 수정
  - `DELETE /api/traits/:id` - 전역 특성 삭제
  - `GET /api/traits/:id/usage` - 전역 특성 사용 현황 조회
- **프로젝트 종속 특성:**
  - `GET /api/projects/:prjNo/traits` - 프로젝트 종속 특성 목록 조회
  - `GET /api/projects/:prjNo/traits/:id` - 프로젝트 종속 특성 상세 조회
  - `POST /api/projects/:prjNo/traits` - 프로젝트 종속 특성 생성
  - `PATCH /api/projects/:prjNo/traits/:id` - 프로젝트 종속 특성 수정
  - `DELETE /api/projects/:prjNo/traits/:id` - 프로젝트 종속 특성 삭제

**데이터 구조:**
- **전역 특성 (`traits`):**
  - `trait_no`: 특성 번호 (PK)
  - `trait_nm`: 특성 명
  - `trait_expln`: 특성 설명
  - `trait_lcls`: 특성 대분류
  - `trait_mcls`: 특성 중분류
  - `aply_trgt`: 적용 대상 (CHAR, ITEM, NATION, ORG, REGION 등)
  - `cnfl_trait_no`: 상충 특성 번호 (전역 특성만 참조)
- **프로젝트 종속 특성 (`project_traits`):**
  - `trait_no`: 특성 번호 (PK)
  - `prj_no`: 프로젝트 번호 (FK, NOT NULL)
  - `trait_nm`: 특성 명
  - `trait_expln`: 특성 설명
  - `trait_lcls`: 특성 대분류
  - `trait_mcls`: 특성 중분류
  - `aply_trgt`: 적용 대상
  - `cnfl_trait_no`: 상충 특성 번호 (전역 또는 동일 프로젝트 특성 참조)
  - `cnfl_trait_type`: 상충 특성 타입 ('GLOBAL' | 'PROJECT')

#### 어빌리티 관리 (Abilitys Management)

**전역 어빌리티 관리:**
- **검색**: 키워드, 유형(`ability_type`), 계통(`ability_lcls`)별 검색
- **추가**: 새로운 전역 어빌리티 생성 및 등록
- **수정**: 기존 전역 어빌리티 정보 수정
- **삭제**: 전역 어빌리티 삭제 (사용 중인 경우 경고 및 영향도 분석)
- **상세 정보 관리**: 대상 유형(`trgt_type`), 피해 유형(`dmg_type`), 소모 비용(`use_cost`), 재사용 대기시간(`cool_time`), 시전 시간(`cast_time`), 사용 조건(`use_cnd`) 등

**프로젝트 종속 어빌리티 관리:**
- **검색**: 특정 프로젝트 내에서 키워드, 유형, 계통별 검색
- **추가**: 프로젝트 전용 어빌리티 생성 및 등록
- **수정**: 프로젝트 종속 어빌리티 정보 수정
- **삭제**: 프로젝트 종속 어빌리티 삭제 (해당 프로젝트 내에서만 영향)
- **상세 정보 관리**: 전역 어빌리티과 동일한 필드 구조

**API 엔드포인트:**
- **전역 어빌리티:**
  - `GET /api/abilitys` - 전역 어빌리티 목록 조회
  - `GET /api/abilitys/:id` - 전역 어빌리티 상세 조회
  - `POST /api/abilitys` - 전역 어빌리티 생성
  - `PATCH /api/abilitys/:id` - 전역 어빌리티 수정
  - `DELETE /api/abilitys/:id` - 전역 어빌리티 삭제
  - `GET /api/abilitys/:id/usage` - 전역 어빌리티 사용 현황 조회
- **프로젝트 종속 어빌리티:**
  - `GET /api/projects/:prjNo/abilitys` - 프로젝트 종속 어빌리티 목록 조회
  - `GET /api/projects/:prjNo/abilitys/:id` - 프로젝트 종속 어빌리티 상세 조회
  - `POST /api/projects/:prjNo/abilitys` - 프로젝트 종속 어빌리티 생성
  - `PATCH /api/projects/:prjNo/abilitys/:id` - 프로젝트 종속 어빌리티 수정
  - `DELETE /api/projects/:prjNo/abilitys/:id` - 프로젝트 종속 어빌리티 삭제

**데이터 구조:**
- **전역 어빌리티 (`abilitys`):**
  - `ability_no`: 어빌리티 번호 (PK)
  - `ability_nm`: 어빌리티 명
  - `ability_type`: 어빌리티 유형
  - `ability_lcls`: 어빌리티 계통
  - `ability_expln`: 어빌리티 설명
  - `trgt_type`: 대상 유형
  - `dmg_type`: 피해 유형
  - `stat_eff_type`: 상태 이상 유형
  - `use_cost`: 소모 비용
  - `cool_time`: 재사용 대기시간
  - `cast_time`: 시전 시간
  - `use_cnd`: 사용 조건
- **프로젝트 종속 어빌리티 (`project_abilitys`):**
  - `ability_no`: 어빌리티 번호 (PK)
  - `prj_no`: 프로젝트 번호 (FK, NOT NULL)
  - (전역 어빌리티과 동일한 필드 구조)

#### 특성/어빌리티 매칭 기능

**기능 요구사항:**
- 설정 엔티티에 전역 또는 프로젝트 종속 특성/어빌리티을 선택하여 매칭
- 전역 풀과 프로젝트 종속 풀을 통합하여 검색 및 선택 가능
- 매칭된 특성/어빌리티만으로도 설정의 특색 파악 가능
- 다중 선택 및 일괄 적용 기능
- 매칭된 특성/어빌리티 목록 표시 및 관리 (전역/프로젝트 종속 구분 표시)

**매핑 테이블 구조:**
매핑 테이블은 전역과 프로젝트 종속을 모두 참조할 수 있도록 설계:
- `char_ability_maps`: 인물 ↔ 어빌리티
  - `ability_no`: 어빌리티 번호
  - `ability_type`: 'GLOBAL' | 'PROJECT' (참조 타입)
  - `prof_lvl`: 숙련도 또는 레벨
- `char_trait_maps`: 인물 ↔ 특성
  - `trait_no`: 특성 번호
  - `trait_type`: 'GLOBAL' | 'PROJECT' (참조 타입)
- `creature_trait_maps`: 종족 ↔ 특성
  - `trait_no`: 특성 번호
  - `trait_type`: 'GLOBAL' | 'PROJECT' (참조 타입)
  - `trait_rmk`: 종족 특화 설명
- `creature_ability_maps`: 종족 ↔ 어빌리티
  - `ability_no`: 어빌리티 번호
  - `ability_type`: 'GLOBAL' | 'PROJECT' (참조 타입)
  - `prof_lvl`: 기본 숙련도/레벨 (종족 고유 어빌리티의 경우)
  - `ability_rmk`: 종족 특화 설명
- `item_ability_maps`: 아이템 ↔ 어빌리티 (동일한 구조)
- `item_trait_maps`: 아이템 ↔ 특성 (동일한 구조)
- `ntn_trait_maps`: 국가 ↔ 특성 (동일한 구조)
- `org_trait_maps`: 조직 ↔ 특성 (동일한 구조)
- `region_trait_maps`: 지역 ↔ 특성 (동일한 구조)

**API 엔드포인트:**
- `POST /api/projects/:prjNo/characters/:charNo/traits` - 인물에 특성 매칭 (전역/프로젝트 종속 모두 가능)
  - Request Body: `{ trait_no, trait_type: 'GLOBAL' | 'PROJECT' }`
- `POST /api/projects/:prjNo/characters/:charNo/abilitys` - 인물에 어빌리티 매칭 (전역/프로젝트 종속 모두 가능)
  - Request Body: `{ ability_no, ability_type: 'GLOBAL' | 'PROJECT', prof_lvl? }`
- `POST /api/projects/:prjNo/creatures/:creatureNo/traits` - 종족에 특성 매칭 (전역/프로젝트 종속 모두 가능)
  - Request Body: `{ trait_no, trait_type: 'GLOBAL' | 'PROJECT' }`
- `POST /api/projects/:prjNo/creatures/:creatureNo/abilitys` - 종족에 어빌리티 매칭 (전역/프로젝트 종속 모두 가능)
  - Request Body: `{ ability_no, ability_type: 'GLOBAL' | 'PROJECT', prof_lvl? }` (종족 고유 어빌리티의 기본 숙련도)
- `DELETE /api/projects/:prjNo/characters/:charNo/traits/:traitNo` - 인물 특성 매칭 해제
  - Query: `trait_type=GLOBAL|PROJECT`
- `DELETE /api/projects/:prjNo/creatures/:creatureNo/traits/:traitNo` - 종족 특성 매칭 해제
  - Query: `trait_type=GLOBAL|PROJECT`
- (다른 엔티티도 유사한 패턴)

### 2. 프로젝트 관리
- 프로젝트 생성, 수정, 삭제
- 프로젝트별 독립적인 설정 공간
- 프로젝트 메타데이터 관리 (장르, 버전, 설명 등)

### 3. 설정 엔티티 관리
각 엔티티별로 다음 기능 제공:
- **CRUD 작업**: 생성, 조회, 수정, 삭제
- **검색 및 필터링**: 키워드 검색, 카테고리 필터
- **페이징**: 대량 데이터 처리
- **특성/어빌리티 매칭**: 전역 풀에서 특성/어빌리티 선택 및 적용
- **관계 설정**: 엔티티 간 연관 관계 관리

### 4. 관계 관리
- 엔티티 간 다대다 관계 설정
- 관계 유형 및 상세 정보 관리
- 관계 시각화 및 탐색

### 5. 데이터 구조화
- DB 스키마 기반의 구조화된 데이터 입력
- 필드별 상세 정보 관리
- 텍스트 기반의 자유로운 설명 필드

## API 엔드포인트 설계 원칙

### 전역 풀 엔드포인트
- `/api/traits` - 전역 특성 관리 (프로젝트 독립적)
- `/api/abilitys` - 전역 어빌리티 관리 (프로젝트 독립적)

### 프로젝트 종속 엔드포인트
- `/api/projects/:prjNo/traits` - 프로젝트 종속 특성 관리
- `/api/projects/:prjNo/abilitys` - 프로젝트 종속 어빌리티 관리
- `/api/projects/:prjNo/*` - 프로젝트별 설정 관리

### 통합 검색 엔드포인트
- `/api/projects/:prjNo/traits/search` - 전역 + 프로젝트 종속 특성 통합 검색
- `/api/projects/:prjNo/abilitys/search` - 전역 + 프로젝트 종속 어빌리티 통합 검색

### 매핑 엔드포인트
- `/api/projects/:prjNo/characters/:charNo/traits` - 인물-특성 매핑 (전역/프로젝트 종속 모두 지원)
- `/api/projects/:prjNo/characters/:charNo/abilitys` - 인물-어빌리티 매핑 (전역/프로젝트 종속 모두 지원)
- `/api/projects/:prjNo/creatures/:creatureNo/traits` - 종족-특성 매핑 (전역/프로젝트 종속 모두 지원)
- `/api/projects/:prjNo/creatures/:creatureNo/abilitys` - 종족-어빌리티 매핑 (전역/프로젝트 종속 모두 지원, 종족 고유 어빌리티 지원)
- (다른 엔티티도 유사한 패턴)

## 고려사항 및 설계 이슈

### 특성/어빌리티의 전역 풀 및 프로젝트 종속 구조
**✅ DB 스키마 설계**: 전역 풀과 프로젝트 종속을 별도 테이블로 분리

**구현된 구조**: 
- **전역 풀**: `traits`, `abilitys` 테이블 (프로젝트 독립적)
- **프로젝트 종속**: `project_traits`, `project_abilitys` 테이블 (프로젝트별 독립 관리)
- 이미지 테이블 분리 패턴과 동일한 구조

**API 설계 고려사항**:
- [ ] 전역 풀 접근 권한 관리
  - 읽기: 모든 인증된 사용자
  - 생성/수정/삭제: 관리자 권한 또는 모든 사용자 (정책 결정 필요)
- [ ] 프로젝트 종속 접근 권한 관리
  - 읽기: 해당 프로젝트 접근 권한이 있는 사용자
  - 생성/수정/삭제: 해당 프로젝트 소유자/편집자
- [ ] 특성/어빌리티 사용 통계 API
  - 전역 특성/어빌리티: 어떤 프로젝트에서 사용 중인지 추적
  - 프로젝트 종속 특성/어빌리티: 해당 프로젝트 내에서만 사용
  - 사용 빈도 통계
  - 인기 특성/어빌리티 분석
- [ ] 특성/어빌리티 삭제 시 영향도 분석
  - 전역 풀: 삭제 전 사용 중인 프로젝트/설정 확인
  - 프로젝트 종속: 해당 프로젝트 내에서만 영향
  - 경고 메시지 및 삭제 확인 절차
  - 소프트 삭제 vs 하드 삭제 정책
- [ ] 매핑 테이블 설계
  - 전역과 프로젝트 종속을 모두 참조할 수 있는 구조
  - 참조 타입 필드 (`trait_type`, `ability_type`)로 구분

### 데이터 일관성 관리

**특성/어빌리티 삭제 정책:**
- 하드 삭제: 매핑 테이블의 참조도 함께 삭제 (CASCADE)
- 소프트 삭제: `del_yn = 'Y'`로 표시, 매핑은 유지
- 권장: 소프트 삭제 + 사용 중인 경우 삭제 불가

**매핑 데이터 무결성:**
- 전역 특성/어빌리티 삭제 시: 모든 프로젝트의 매핑 테이블에서 참조 확인 필요
- 프로젝트 종속 특성/어빌리티 삭제 시: 해당 프로젝트의 매핑 테이블에서만 참조 확인
- 삭제된 특성/어빌리티을 참조하는 매핑 처리 방법
  - 소프트 삭제: 매핑은 유지하되 특성/어빌리티은 비활성화
  - 하드 삭제: 매핑도 함께 삭제 (CASCADE)
  - 권장: 소프트 삭제 + 사용 중인 경우 삭제 불가

## API 프로젝트 관점

### 설계 원칙
- 전역 풀과 프로젝트 종속 엔티티의 명확한 분리
- RESTful API 설계 원칙 준수
- 일관된 응답 형식 (SearchVo 기반)
- 에러 처리 및 검증 로직
- 권한 관리 및 보안 고려
- 성능 최적화 (인덱스 활용, 페이징)
- 외부 등록 API 설계 (인증, 검증, 승인 프로세스)

### 공통 고려사항
- **용어 통일**: `abilities` → `abilitys`로 변경됨 (모든 문서 및 코드에서 일관성 유지)
- **데이터 모델**: VO 스키마 기반으로 API 요청/응답 구조 설계
- **확장성**: 새로운 특성/어빌리티 카테고리 추가 용이성
- **사용성**: 특성/어빌리티 검색 및 필터링의 직관성

## 참고 문서

- `DDL/database_schema.md`: DB 테이블 구조
- `DDL/vo_schema.md`: VO 필드 정의
