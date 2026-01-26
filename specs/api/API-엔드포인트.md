# API 엔드포인트 명세

이 문서는 Fantasy Builder API의 모든 엔드포인트를 정의합니다.

## 공통 규칙

### 패스 파라미터 명명
- PK를 받을 때는 `id`가 아닌 `no`를 사용합니다.
  - 예: `:traitNo`, `:abilityNo`, `:charNo`, `:prjNo` 등

### 응답 형식
- 모든 목록 조회는 `SearchVo` 기반의 페이징 응답을 반환합니다.
- 상세 조회는 단일 객체를 반환합니다.
- 생성/수정은 생성/수정된 객체를 반환합니다.
- 삭제는 성공 여부를 반환합니다.

### 공통 쿼리 파라미터 (목록 조회)
- `page_no`: 페이지 번호 (1부터 시작)
- `page_size`: 페이지당 항목 수
- `search_type`: 검색 유형
- `search_keyword`: 검색 키워드
- 각 엔티티별 필터 필드 (예: `trait_lcls`, `ability_type` 등)

### 인증 및 권한 정책

#### 인증 요구사항
- **인증 불필요 (Public)**: 누구나 접근 가능
  - 로그인, 회원가입, 비밀번호 재설정 요청 등
- **인증 필요 (Authenticated)**: 로그인한 사용자만 접근 가능
  - 대부분의 조회, 생성, 수정, 삭제 엔드포인트

#### 공유 여부 기반 접근 제어
모든 리소스는 `shrn_yn` (공유 여부) 필드를 가지고 있으며, 이를 기반으로 접근이 제어됩니다:

- **공유함 (`shrn_yn = 'Y'`)**: 모든 인증된 사용자가 조회 가능
- **공유 안 함 (`shrn_yn = 'N'`)**: 
  - 소유자: 항상 조회 가능
  - 어드민: 항상 조회 가능
  - 다른 유저: 조회 불가

**소유자 판단 기준**: `crt_no`가 현재 사용자의 `user_no`와 일치하는 경우

#### 역할 기반 권한
- **ADMIN (관리자)**:
  - 모든 리소스에 대한 조회, 수정 권한 보유
  - 모든 리소스에 대한 삭제 권한 보유 (단, 다른 유저의 리소스 삭제는 권한은 있으나 정책상 사용하지 않음)
- **USER (일반 사용자)**:
  - 공유된 리소스(`shrn_yn = 'Y'`) 또는 자신이 생성한 리소스 조회 가능
  - 자신이 생성한 리소스(`crt_no == user_no`)만 수정/삭제 가능
  - 다른 유저의 리소스는 수정/삭제 불가

#### 권한 관련 에러 코드
- `UNAUTHORIZED`: 인증이 필요함 (로그인이 필요함)
- `FORBIDDEN`: 권한이 없음 (접근 권한이 없음)
- `NOT_OWNER`: 소유자가 아님 (수정/삭제 권한이 없음)
- `NOT_PUBLIC`: 비공개 리소스임 (공유되지 않은 리소스)

---

> **인증 API**: 인증 관련 엔드포인트는 [API-엔드포인트-auth.md](./API-엔드포인트-auth.md) 파일을 참조하세요.

> **프로젝트 API**: 프로젝트 관련 엔드포인트는 [API-엔드포인트-projects.md](./API-엔드포인트-projects.md) 파일을 참조하세요.

> **특성(Traits) API**: 전역 특성 및 프로젝트 종속 특성 관련 엔드포인트는 [API-엔드포인트-traits.md](./API-엔드포인트-traits.md) 파일을 참조하세요.

> **어빌리티(Abilities) API**: 전역 어빌리티 및 프로젝트 종속 어빌리티 관련 엔드포인트는 [API-엔드포인트-abilities.md](./API-엔드포인트-abilities.md) 파일을 참조하세요.

> **코어 설정(Core Rules) API**: 코어 설정 관련 엔드포인트는 [API-엔드포인트-core-rules.md](./API-엔드포인트-core-rules.md) 파일을 참조하세요.

> **종족(Creatures) API**: 종족 및 종족-특성/스킬 매핑 관련 엔드포인트는 [API-엔드포인트-creatures.md](./API-엔드포인트-creatures.md) 파일을 참조하세요.

> **인물(Characters) API**: 인물 및 인물-스킬/특성/아이템 매핑, 인물 관계 관련 엔드포인트는 [API-엔드포인트-characters.md](./API-엔드포인트-characters.md) 파일을 참조하세요.

> **아이템(Items) API**: 아이템 및 아이템-스킬/특성 매핑 관련 엔드포인트는 [API-엔드포인트-items.md](./API-엔드포인트-items.md) 파일을 참조하세요.

> **지역(Regions) API**: 지역 및 지역-특성 매핑 관련 엔드포인트는 [API-엔드포인트-regions.md](./API-엔드포인트-regions.md) 파일을 참조하세요.

> **집단(Groups) API**: 국가, 조직 및 집단 관계 관련 엔드포인트는 [API-엔드포인트-groups.md](./API-엔드포인트-groups.md) 파일을 참조하세요.

> **사건(Events) API**: 사건 및 사건 참여 관련 엔드포인트는 [API-엔드포인트-events.md](./API-엔드포인트-events.md) 파일을 참조하세요.

> **전승/설화(Lores) API**: 전승/설화 및 전승-인물/아이템 매핑 관련 엔드포인트는 [API-엔드포인트-lores.md](./API-엔드포인트-lores.md) 파일을 참조하세요.

---

## 1. 전역 특성 (Traits)

> 전역 특성 관련 엔드포인트는 [API-엔드포인트-traits.md](./API-엔드포인트-traits.md) 파일을 참조하세요.

---

## 2. 전역 어빌리티 (Abilities)

> 전역 어빌리티 관련 엔드포인트는 [API-엔드포인트-abilities.md](./API-엔드포인트-abilities.md) 파일을 참조하세요.

---

## 3. 프로젝트 (Projects)

> 프로젝트 관련 엔드포인트는 [API-엔드포인트-projects.md](./API-엔드포인트-projects.md) 파일을 참조하세요.

---

## 4. 프로젝트 종속 특성 (Project Traits)

> 프로젝트 종속 특성 관련 엔드포인트는 [API-엔드포인트-traits.md](./API-엔드포인트-traits.md) 파일을 참조하세요.

---

## 5. 프로젝트 종속 어빌리티 (Project Abilities)

> 프로젝트 종속 어빌리티 관련 엔드포인트는 [API-엔드포인트-abilities.md](./API-엔드포인트-abilities.md) 파일을 참조하세요.

---

## 6. 코어 설정 (Core Rules)

> 코어 설정 관련 엔드포인트는 [API-엔드포인트-core-rules.md](./API-엔드포인트-core-rules.md) 파일을 참조하세요.

---

## 7. 생물/종족 (Creatures)

> 종족 및 종족-특성/어빌리티 매핑 관련 엔드포인트는 [API-엔드포인트-creatures.md](./API-엔드포인트-creatures.md) 파일을 참조하세요.

---

## 8. 인물 (Characters)

> 인물 및 인물-어빌리티/특성/아이템 매핑, 인물 관계 관련 엔드포인트는 [API-엔드포인트-characters.md](./API-엔드포인트-characters.md) 파일을 참조하세요.

---

## 9. 아이템 (Items)

> 아이템 및 아이템-어빌리티/특성 매핑 관련 엔드포인트는 [API-엔드포인트-items.md](./API-엔드포인트-items.md) 파일을 참조하세요.

---

## 10. 지역 (Regions)

> 지역 및 지역-특성 매핑 관련 엔드포인트는 [API-엔드포인트-regions.md](./API-엔드포인트-regions.md) 파일을 참조하세요.

---

## 11. 국가 (Nations)

> 국가, 조직 및 집단 관계 관련 엔드포인트는 [API-엔드포인트-groups.md](./API-엔드포인트-groups.md) 파일을 참조하세요.

---

## 12. 조직 (Organizations)

> 국가, 조직 및 집단 관계 관련 엔드포인트는 [API-엔드포인트-groups.md](./API-엔드포인트-groups.md) 파일을 참조하세요.

---

## 13. 사건 (Events)

> 사건 및 사건 참여 관련 엔드포인트는 [API-엔드포인트-events.md](./API-엔드포인트-events.md) 파일을 참조하세요.

---

## 14. 전승/설화 (Lores)

> 전승/설화 및 전승-인물/아이템 매핑 관련 엔드포인트는 [API-엔드포인트-lores.md](./API-엔드포인트-lores.md) 파일을 참조하세요.

---

## 15. 집단 관계 (Group Relations)

> 집단 관계(국가-국가, 국가-조직, 조직-조직 간의 관계) 관련 엔드포인트는 [API-엔드포인트-groups.md](./API-엔드포인트-groups.md) 파일의 "31. 집단 관계 (Group Relations)" 섹션을 참조하세요.

---

## 통합 검색 엔드포인트

## 16. 특성/어빌리티 통합 검색

### 전역 + 프로젝트 종속 특성 통합 검색
```
GET /projects/:prjNo/traits/search
Query: 
  - trait_type_list: GLOBAL,PROJECT (선택)
  - search_keyword: 검색 키워드
  - trait_lcls: 대분류
  - trait_mcls: 중분류
  - aply_trgt: 적용 대상
```

### 전역 + 프로젝트 종속 어빌리티 통합 검색
```
GET /projects/:prjNo/abilities/search
Query:
  - ability_type_list: GLOBAL,PROJECT (선택)
  - search_keyword: 검색 키워드
  - ability_type: 어빌리티 유형
  - ability_lcls: 어빌리티 계통
```

---

## 참고사항

1. **복합 키 처리**: 
   - `char_ability_maps`, `char_trait_maps`는 복합 키(`char_no`, `ability_no`/`trait_no`, `ability_type`/`trait_type`)를 사용하므로, 상세 조회/수정/삭제 시 쿼리 파라미터로 `ability_type` 또는 `trait_type`을 함께 전달해야 합니다.

2. **매핑 테이블의 PK**:
   - 대부분의 매핑 테이블은 `map_no`를 PK로 사용합니다.
   - `char_ability_maps`, `char_trait_maps`는 복합 키를 PK로 사용합니다.

3. **프로젝트 컨텍스트**:
   - 프로젝트 종속 엔티티와 매핑 테이블은 모두 `/projects/:prjNo/` 경로 하위에 위치합니다.
   - 전역 풀(`traits`, `abilities`)만 프로젝트 컨텍스트 없이 접근 가능합니다.

