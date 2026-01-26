# API 구현 우선순위

이 문서는 Fantasy Builder API의 구현 우선순위를 정의합니다. 엔드포인트 간 의존성, 비즈니스 로직 중요도, 구현 복잡도를 고려하여 단계별로 정리했습니다.

## 우선순위 분석 기준

### 1. 의존성 기준
- 다른 엔드포인트가 의존하는 엔드포인트가 우선
- 인증/사용자 관리 → 전역 풀 → 프로젝트 → 프로젝트 종속 엔티티 → 기본 엔티티 → 관계/매핑

### 2. 비즈니스 중요도
- 핵심 기능 (인증, 프로젝트, 전역 풀) 우선
- 기본 엔티티 (Character, Item 등) 다음
- 관계/매핑 엔티티 마지막

### 3. 구현 복잡도
- 단순 CRUD 우선
- 복잡한 관계/매핑 나중

---

## Phase 1: 인증 및 사용자 관리 (필수) ✅ 완료

**의존성**: 없음 (최상위 우선순위)

**목표**: 사용자 인증 및 기본 사용자 관리 기능 구현

**진행 상태**: ✅ **완료** (2026-01-24)

### 엔드포인트 목록

#### 인증 (Authentication)
- [x] `POST /auth/signin` - 로그인
- [x] `POST /auth/signout` - 로그아웃
- [x] `POST /auth/signup` - 회원가입
- [x] `POST /auth/refresh` - 토큰 갱신

#### 비밀번호 관리
- [x] `POST /auth/forgot-password` - 비밀번호 재설정 요청
- [x] `POST /auth/reset-password` - 비밀번호 재설정
- [x] `POST /auth/change-password` - 비밀번호 변경

#### 사용자 프로필
- [x] `GET /users/me` - 현재 사용자 정보 조회
- [x] `PATCH /users/me` - 현재 사용자 정보 수정

### 구현 복잡도
- **낮음**: 표준 인증 플로우, JWT 토큰 관리

### 예상 작업량
- 인증 로직: 2-3일
- 비밀번호 관리: 1-2일
- 사용자 프로필: 1일
- **총 예상: 4-6일**

### 비고
- 모든 다른 API가 인증을 필요로 하므로 최우선 구현
- JWT 토큰 기반 인증 구현
- 비밀번호 암호화 및 보안 정책 적용

---

## Phase 2: 프로젝트 관리 (필수)

**의존성**: Phase 1 (인증 필요)

**목표**: 프로젝트 CRUD 및 기본 프로젝트 관리 기능 구현

**진행 상태**: ⏳ **대기 중**

### 엔드포인트 목록

#### 프로젝트 CRUD
- [ ] `GET /projects` - 프로젝트 목록 조회
- [ ] `GET /projects/:prjNo` - 프로젝트 상세 조회
- [ ] `POST /projects` - 프로젝트 생성
- [ ] `PATCH /projects/:prjNo` - 프로젝트 수정
- [ ] `DELETE /projects/:prjNo` - 프로젝트 삭제

### 구현 복잡도
- **낮음**: 단순 CRUD 작업

### 예상 작업량
- 프로젝트 CRUD: 2-3일
- 권한 검증 로직: 1일
- **총 예상: 3-4일**

### 비고
- 모든 프로젝트 종속 엔티티의 기반이 되는 핵심 기능
- `user_no` FK를 통한 소유자 관리
- `shrn_yn` 기반 공유 정책 적용

---

## Phase 3: 전역 풀 (Traits & Abilitys)

**의존성**: Phase 1 (인증 필요), Phase 2와 독립적

**목표**: 전역 특성 및 어빌리티 관리 기능 구현

**진행 상태**: ⏳ **대기 중**

### 엔드포인트 목록

#### 전역 특성 (Traits)
- [ ] `GET /traits` - 전역 특성 목록 조회
- [ ] `GET /traits/:traitNo` - 전역 특성 상세 조회
- [ ] `POST /traits` - 전역 특성 생성
- [ ] `PATCH /traits/:traitNo` - 전역 특성 수정
- [ ] `DELETE /traits/:traitNo` - 전역 특성 삭제

#### 전역 어빌리티 (Abilitys)
- [ ] `GET /abilitys` - 전역 어빌리티 목록 조회
- [ ] `GET /abilitys/:abilityNo` - 전역 어빌리티 상세 조회
- [ ] `POST /abilitys` - 전역 어빌리티 생성
- [ ] `PATCH /abilitys/:abilityNo` - 전역 어빌리티 수정
- [ ] `DELETE /abilitys/:abilityNo` - 전역 어빌리티 삭제

### 구현 복잡도
- **중간**: 상충 특성(self FK) 처리, 전역 풀 관리 정책

### 예상 작업량
- 전역 특성 CRUD: 2-3일
- 전역 어빌리티 CRUD: 2-3일
- 상충 특성 로직: 1일
- **총 예상: 5-7일**

### 비고
- 프로젝트와 독립적이지만 프로젝트 종속 엔티티가 참조
- 전역 풀 수정 권한 정책 결정 필요 (관리자 전용 vs 모든 사용자)
- 삭제 시 사용 중인 프로젝트 확인 로직 필요

---

## Phase 4: 코어 설정 (Core Rules)

**의존성**: Phase 1 (인증 필요), Phase 2 (프로젝트 필요)

**목표**: 프로젝트별 코어 설정 관리 기능 구현

**진행 상태**: ⏳ **대기 중**

### 엔드포인트 목록

#### 코어 설정 CRUD
- [ ] `GET /projects/:prjNo/core-rules` - 코어 설정 목록 조회
- [ ] `GET /projects/:prjNo/core-rules/:coreNo` - 코어 설정 상세 조회
- [ ] `POST /projects/:prjNo/core-rules` - 코어 설정 생성
- [ ] `PATCH /projects/:prjNo/core-rules/:coreNo` - 코어 설정 수정
- [ ] `DELETE /projects/:prjNo/core-rules/:coreNo` - 코어 설정 삭제

### 구현 복잡도
- **낮음**: 단순 CRUD 작업

### 예상 작업량
- 코어 설정 CRUD: 2-3일
- **총 예상: 2-3일**

### 비고
- 프로젝트 종속 엔티티 중 가장 단순한 구조
- 프로젝트 내 설정 관리의 기초

---

## Phase 5: 프로젝트 종속 특성 및 어빌리티

**의존성**: Phase 1 (인증 필요), Phase 2 (프로젝트 필요), Phase 3 (전역 풀 참조 가능)

**목표**: 프로젝트별 특성 및 어빌리티 관리 기능 구현

**진행 상태**: ⏳ **대기 중**

### 엔드포인트 목록

#### 프로젝트 종속 특성 (Project Traits)
- [ ] `GET /projects/:prjNo/traits` - 프로젝트 종속 특성 목록 조회
- [ ] `GET /projects/:prjNo/traits/:traitNo` - 프로젝트 종속 특성 상세 조회
- [ ] `POST /projects/:prjNo/traits` - 프로젝트 종속 특성 생성
- [ ] `PATCH /projects/:prjNo/traits/:traitNo` - 프로젝트 종속 특성 수정
- [ ] `DELETE /projects/:prjNo/traits/:traitNo` - 프로젝트 종속 특성 삭제

#### 프로젝트 종속 어빌리티 (Project Abilitys)
- [ ] `GET /projects/:prjNo/abilitys` - 프로젝트 종속 어빌리티 목록 조회
- [ ] `GET /projects/:prjNo/abilitys/:abilityNo` - 프로젝트 종속 어빌리티 상세 조회
- [ ] `POST /projects/:prjNo/abilitys` - 프로젝트 종속 어빌리티 생성
- [ ] `PATCH /projects/:prjNo/abilitys/:abilityNo` - 프로젝트 종속 어빌리티 수정
- [ ] `DELETE /projects/:prjNo/abilitys/:abilityNo` - 프로젝트 종속 어빌리티 삭제

#### 통합 검색
- [ ] `GET /projects/:prjNo/traits/search` - 전역 + 프로젝트 종속 특성 통합 검색
- [ ] `GET /projects/:prjNo/abilitys/search` - 전역 + 프로젝트 종속 어빌리티 통합 검색

### 구현 복잡도
- **중간**: 전역 풀과 프로젝트 종속 통합 검색 로직

### 예상 작업량
- 프로젝트 종속 특성 CRUD: 2-3일
- 프로젝트 종속 어빌리티 CRUD: 2-3일
- 통합 검색 로직: 2-3일
- **총 예상: 6-9일**

### 비고
- 전역 풀과 프로젝트 종속을 구분하여 관리
- 통합 검색 시 `trait_type`, `ability_type` 필드로 구분
- 상충 특성 로직 (전역/프로젝트 종속 모두 참조 가능)

---

## Phase 6: 기본 엔티티 (Core Entities)

**의존성**: Phase 1 (인증 필요), Phase 2 (프로젝트 필요), Phase 3 (전역 풀 참조 가능), Phase 5 (프로젝트 종속 특성/어빌리티 참조 가능)

**목표**: 핵심 엔티티(종족, 인물, 아이템, 지역, 국가, 조직, 사건, 전승) 관리 기능 구현

**진행 상태**: ⏳ **대기 중**

### 엔드포인트 목록

#### 종족 (Creatures)
- [ ] `GET /projects/:prjNo/creatures` - 종족 목록 조회
- [ ] `GET /projects/:prjNo/creatures/:creatureNo` - 종족 상세 조회
- [ ] `POST /projects/:prjNo/creatures` - 종족 생성
- [ ] `PATCH /projects/:prjNo/creatures/:creatureNo` - 종족 수정
- [ ] `DELETE /projects/:prjNo/creatures/:creatureNo` - 종족 삭제

#### 인물 (Characters)
- [ ] `GET /projects/:prjNo/characters` - 인물 목록 조회
- [ ] `GET /projects/:prjNo/characters/:charNo` - 인물 상세 조회
- [ ] `POST /projects/:prjNo/characters` - 인물 생성
- [ ] `PATCH /projects/:prjNo/characters/:charNo` - 인물 수정
- [ ] `DELETE /projects/:prjNo/characters/:charNo` - 인물 삭제

#### 아이템 (Items)
- [ ] `GET /projects/:prjNo/items` - 아이템 목록 조회
- [ ] `GET /projects/:prjNo/items/:itemNo` - 아이템 상세 조회
- [ ] `POST /projects/:prjNo/items` - 아이템 생성
- [ ] `PATCH /projects/:prjNo/items/:itemNo` - 아이템 수정
- [ ] `DELETE /projects/:prjNo/items/:itemNo` - 아이템 삭제

#### 지역 (Regions)
- [ ] `GET /projects/:prjNo/regions` - 지역 목록 조회
- [ ] `GET /projects/:prjNo/regions/:regionNo` - 지역 상세 조회
- [ ] `POST /projects/:prjNo/regions` - 지역 생성
- [ ] `PATCH /projects/:prjNo/regions/:regionNo` - 지역 수정
- [ ] `DELETE /projects/:prjNo/regions/:regionNo` - 지역 삭제

#### 국가 (Nations)
- [ ] `GET /projects/:prjNo/nations` - 국가 목록 조회
- [ ] `GET /projects/:prjNo/nations/:ntnNo` - 국가 상세 조회
- [ ] `POST /projects/:prjNo/nations` - 국가 생성
- [ ] `PATCH /projects/:prjNo/nations/:ntnNo` - 국가 수정
- [ ] `DELETE /projects/:prjNo/nations/:ntnNo` - 국가 삭제

#### 조직 (Organizations)
- [ ] `GET /projects/:prjNo/organizations` - 조직 목록 조회
- [ ] `GET /projects/:prjNo/organizations/:orgNo` - 조직 상세 조회
- [ ] `POST /projects/:prjNo/organizations` - 조직 생성
- [ ] `PATCH /projects/:prjNo/organizations/:orgNo` - 조직 수정
- [ ] `DELETE /projects/:prjNo/organizations/:orgNo` - 조직 삭제

#### 사건 (Events)
- [ ] `GET /projects/:prjNo/events` - 사건 목록 조회
- [ ] `GET /projects/:prjNo/events/:eventNo` - 사건 상세 조회
- [ ] `POST /projects/:prjNo/events` - 사건 생성
- [ ] `PATCH /projects/:prjNo/events/:eventNo` - 사건 수정
- [ ] `DELETE /projects/:prjNo/events/:eventNo` - 사건 삭제

#### 전승/설화 (Lores)
- [ ] `GET /projects/:prjNo/lores` - 전승 목록 조회
- [ ] `GET /projects/:prjNo/lores/:loreNo` - 전승 상세 조회
- [ ] `POST /projects/:prjNo/lores` - 전승 생성
- [ ] `PATCH /projects/:prjNo/lores/:loreNo` - 전승 수정
- [ ] `DELETE /projects/:prjNo/lores/:loreNo` - 전승 삭제

### 구현 복잡도
- **중간-높음**: 각 엔티티별 복잡한 필드 구조, FK 관계 처리

### 예상 작업량
- 종족 CRUD: 2-3일
- 인물 CRUD: 3-4일 (복잡한 필드 구조)
- 아이템 CRUD: 2-3일
- 지역 CRUD: 2-3일 (상위 지역 self FK)
- 국가 CRUD: 2-3일
- 조직 CRUD: 2-3일
- 사건 CRUD: 2-3일
- 전승 CRUD: 2-3일
- **총 예상: 17-26일**

### 비고
- 각 엔티티는 독립적으로 구현 가능
- 인물은 가장 복잡한 필드 구조를 가짐
- 지역은 상위 지역(self FK) 처리 필요
- 인물은 종족, 국가, 조직 FK 참조

---

## Phase 7: 매핑 및 관계 엔티티 (Mapping & Relations)

**의존성**: Phase 1-6 (모든 기본 엔티티 필요)

**목표**: 엔티티 간 매핑 및 관계 관리 기능 구현

**진행 상태**: ⏳ **대기 중**

### 엔드포인트 목록

#### 종족 매핑
- [ ] `GET /projects/:prjNo/creatures/:creatureNo/traits` - 종족-특성 매핑 목록
- [ ] `POST /projects/:prjNo/creatures/:creatureNo/traits` - 종족-특성 매핑 생성
- [ ] `DELETE /projects/:prjNo/creatures/:creatureNo/traits/:mapNo` - 종족-특성 매핑 삭제
- [ ] `GET /projects/:prjNo/creatures/:creatureNo/abilitys` - 종족-어빌리티 매핑 목록
- [ ] `POST /projects/:prjNo/creatures/:creatureNo/abilitys` - 종족-어빌리티 매핑 생성
- [ ] `DELETE /projects/:prjNo/creatures/:creatureNo/abilitys/:mapNo` - 종족-어빌리티 매핑 삭제

#### 인물 매핑
- [ ] `GET /projects/:prjNo/characters/:charNo/traits` - 인물-특성 매핑 목록
- [ ] `POST /projects/:prjNo/characters/:charNo/traits` - 인물-특성 매핑 생성
- [ ] `DELETE /projects/:prjNo/characters/:charNo/traits` - 인물-특성 매핑 삭제 (복합 키)
- [ ] `GET /projects/:prjNo/characters/:charNo/abilitys` - 인물-어빌리티 매핑 목록
- [ ] `POST /projects/:prjNo/characters/:charNo/abilitys` - 인물-어빌리티 매핑 생성
- [ ] `DELETE /projects/:prjNo/characters/:charNo/abilitys` - 인물-어빌리티 매핑 삭제 (복합 키)
- [ ] `GET /projects/:prjNo/characters/:charNo/items` - 인물-아이템 매핑 목록
- [ ] `POST /projects/:prjNo/characters/:charNo/items` - 인물-아이템 매핑 생성
- [ ] `PATCH /projects/:prjNo/characters/:charNo/items/:ownNo` - 인물-아이템 매핑 수정
- [ ] `DELETE /projects/:prjNo/characters/:charNo/items/:ownNo` - 인물-아이템 매핑 삭제

#### 인물 관계
- [ ] `GET /projects/:prjNo/characters/:charNo/relations` - 인물 관계 목록
- [ ] `POST /projects/:prjNo/characters/:charNo/relations` - 인물 관계 생성
- [ ] `PATCH /projects/:prjNo/characters/:charNo/relations/:relNo` - 인물 관계 수정
- [ ] `DELETE /projects/:prjNo/characters/:charNo/relations/:relNo` - 인물 관계 삭제
- [ ] `GET /projects/:prjNo/characters/:charNo/group-relations` - 인물-그룹 관계 목록
- [ ] `POST /projects/:prjNo/characters/:charNo/group-relations` - 인물-그룹 관계 생성
- [ ] `PATCH /projects/:prjNo/characters/:charNo/group-relations/:relNo` - 인물-그룹 관계 수정
- [ ] `DELETE /projects/:prjNo/characters/:charNo/group-relations/:relNo` - 인물-그룹 관계 삭제

#### 아이템 매핑
- [ ] `GET /projects/:prjNo/items/:itemNo/traits` - 아이템-특성 매핑 목록
- [ ] `POST /projects/:prjNo/items/:itemNo/traits` - 아이템-특성 매핑 생성
- [ ] `DELETE /projects/:prjNo/items/:itemNo/traits/:mapNo` - 아이템-특성 매핑 삭제
- [ ] `GET /projects/:prjNo/items/:itemNo/abilitys` - 아이템-어빌리티 매핑 목록
- [ ] `POST /projects/:prjNo/items/:itemNo/abilitys` - 아이템-어빌리티 매핑 생성
- [ ] `DELETE /projects/:prjNo/items/:itemNo/abilitys/:mapNo` - 아이템-어빌리티 매핑 삭제

#### 지역 매핑
- [ ] `GET /projects/:prjNo/regions/:regionNo/traits` - 지역-특성 매핑 목록
- [ ] `POST /projects/:prjNo/regions/:regionNo/traits` - 지역-특성 매핑 생성
- [ ] `DELETE /projects/:prjNo/regions/:regionNo/traits/:mapNo` - 지역-특성 매핑 삭제

#### 국가 매핑
- [ ] `GET /projects/:prjNo/nations/:ntnNo/traits` - 국가-특성 매핑 목록
- [ ] `POST /projects/:prjNo/nations/:ntnNo/traits` - 국가-특성 매핑 생성
- [ ] `DELETE /projects/:prjNo/nations/:ntnNo/traits/:mapNo` - 국가-특성 매핑 삭제

#### 조직 매핑
- [ ] `GET /projects/:prjNo/organizations/:orgNo/traits` - 조직-특성 매핑 목록
- [ ] `POST /projects/:prjNo/organizations/:orgNo/traits` - 조직-특성 매핑 생성
- [ ] `DELETE /projects/:prjNo/organizations/:orgNo/traits/:mapNo` - 조직-특성 매핑 삭제

#### 집단 관계
- [ ] `GET /projects/:prjNo/group-relations` - 집단 관계 목록
- [ ] `POST /projects/:prjNo/group-relations` - 집단 관계 생성
- [ ] `PATCH /projects/:prjNo/group-relations/:relNo` - 집단 관계 수정
- [ ] `DELETE /projects/:prjNo/group-relations/:relNo` - 집단 관계 삭제

#### 사건 참여
- [ ] `GET /projects/:prjNo/events/:eventNo/entries` - 사건 참여 목록
- [ ] `POST /projects/:prjNo/events/:eventNo/entries` - 사건 참여 생성
- [ ] `PATCH /projects/:prjNo/events/:eventNo/entries/:entryNo` - 사건 참여 수정
- [ ] `DELETE /projects/:prjNo/events/:eventNo/entries/:entryNo` - 사건 참여 삭제

#### 전승 매핑
- [ ] `GET /projects/:prjNo/lores/:loreNo/characters` - 전승-인물 매핑 목록
- [ ] `POST /projects/:prjNo/lores/:loreNo/characters` - 전승-인물 매핑 생성
- [ ] `DELETE /projects/:prjNo/lores/:loreNo/characters/:mapNo` - 전승-인물 매핑 삭제
- [ ] `GET /projects/:prjNo/lores/:loreNo/items` - 전승-아이템 매핑 목록
- [ ] `POST /projects/:prjNo/lores/:loreNo/items` - 전승-아이템 매핑 생성
- [ ] `DELETE /projects/:prjNo/lores/:loreNo/items/:mapNo` - 전승-아이템 매핑 삭제

### 구현 복잡도
- **높음**: 복합 키 처리, 다형 참조, 전역/프로젝트 종속 구분

### 예상 작업량
- 종족 매핑: 2-3일
- 인물 매핑 및 관계: 4-5일 (복합 키, 다형 참조)
- 아이템 매핑: 2-3일
- 지역/국가/조직 매핑: 3-4일
- 집단 관계: 2-3일 (다형 참조)
- 사건 참여: 2-3일 (다형 참조)
- 전승 매핑: 2-3일
- **총 예상: 17-24일**

### 비고
- 복합 키 처리 (`char_ability_maps`, `char_trait_maps`)
- 다형 참조 처리 (`char_group_relations`, `group_relations`, `event_entries`)
- 전역/프로젝트 종속 구분 (`trait_type`, `ability_type`)
- 매핑 삭제 시 참조 무결성 확인 필요

---

## 구현 순서 요약

### 단계별 우선순위

1. **Phase 1: 인증** (4-6일) - ✅ **완료** (2026-01-24)
2. **Phase 2: 프로젝트** (3-4일) - ⏳ **대기 중**
3. **Phase 3: 전역 풀** (5-7일) - ⏳ **대기 중**
4. **Phase 4: 코어 설정** (2-3일) - ⏳ **대기 중**
5. **Phase 5: 프로젝트 종속 특성/어빌리티** (6-9일) - ⏳ **대기 중**
6. **Phase 6: 기본 엔티티** (17-26일) - ⏳ **대기 중**
7. **Phase 7: 매핑 및 관계** (17-24일) - ⏳ **대기 중**

### 총 예상 작업량
- **최소**: 54일
- **최대**: 81일
- **평균**: 약 67일 (약 13-14주)

### 병렬 구현 가능 항목

- **Phase 3 (전역 풀)**과 **Phase 2 (프로젝트)**는 독립적으로 병렬 구현 가능
- **Phase 6 (기본 엔티티)** 내의 각 엔티티는 독립적으로 병렬 구현 가능
- **Phase 7 (매핑)** 내의 각 매핑 유형은 독립적으로 병렬 구현 가능

### 의존성 다이어그램

```
Phase 1 (인증)
    ↓
Phase 2 (프로젝트) ←→ Phase 3 (전역 풀)
    ↓
Phase 4 (코어 설정)
    ↓
Phase 5 (프로젝트 종속 특성/어빌리티)
    ↓
Phase 6 (기본 엔티티)
    ↓
Phase 7 (매핑 및 관계)
```

---

## 구현 시 고려사항

### 1. 권한 검증
- 모든 엔드포인트에서 인증 및 권한 검증 필요
- `shrn_yn` 기반 공유 정책 적용
- 소유자 판단 기준: `crt_no == user_no`

### 2. 데이터 무결성
- FK 제약조건 준수
- 삭제 시 참조 무결성 확인
- 전역 풀 삭제 시 사용 중인지 확인

### 3. 복합 키 처리
- `char_ability_maps`, `char_trait_maps`는 복합 키 사용
- 삭제/수정 시 `ability_type` 또는 `trait_type` 파라미터 필요

### 4. 다형 참조
- `char_group_relations`, `group_relations`, `event_entries`는 다형 참조
- FK 제약 없이 애플리케이션 레벨에서 검증

### 5. 전역/프로젝트 종속 구분
- `trait_type`, `ability_type` 필드로 구분
- 통합 검색 시 두 타입 모두 포함

---

## 다음 단계

각 Phase 완료 후:
1. 단위 테스트 작성
2. 통합 테스트 작성
3. API 문서 업데이트
4. 성능 최적화 (필요시)
5. 보안 검토
