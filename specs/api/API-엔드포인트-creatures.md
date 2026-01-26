# 종족 API 엔드포인트

이 문서는 Fantasy Builder API의 종족(Creatures) 관련 엔드포인트를 정의합니다.

## 7. 생물/종족 (Creatures)

### 목록 조회
```
GET /projects/:prjNo/creatures
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 종족(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `creature_no_list`: string (optional) - 종족 번호 리스트 (쉼표로 구분)
- `creature_type`: string (optional) - 생물 유형 필터
- `danger_grd`: string (optional) - 위험도 등급 필터
- `ident_stat`: string (optional) - 식별 상태 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "creature_no": "number",
        "prj_no": "number",
        "creature_nm": "string",
        "creature_type": "string",
        "danger_grd": "string | null",
        "ident_stat": "string",
        "creature_expln": "string | null",
        "bio_char": "string | null",
        "lifespan_growth": "string | null",
        "body_feat": "string | null",
        "sense_diet": "string | null",
        "reprod_info": "string | null",
        "eco_habit": "string | null",
        "habitat_env": "string | null",
        "lang_name": "string | null",
        "life_style": "string | null",
        "faith_taboo": "string | null",
        "soc_struct": "string | null",
        "psych_tend": "string | null",
        "abil_weak": "string | null",
        "civ_tech_lvl": "string | null",
        "spec_trait": "string | null",
        "weakness": "string | null",
        "est_eco": "string | null",
        "rumor_lore": "string | null",
        "poten_thrt": "string | null",
        "intel_lvl": "string | null",
        "drop_rsrc": "string | null",
        "hostile_rel": "string | null",
        "hist_desc": "string | null",
        "rmk": "string | null",
        "use_yn": "string",
        "shrn_yn": "string",
        "del_yn": "string",
        "crt_no": "number | null",
        "crt_dt": "string (datetime) | null",
        "updt_no": "number | null",
        "updt_dt": "string (datetime) | null",
        "del_no": "number | null",
        "del_dt": "string (datetime) | null",
        "total_cnt": "number",
        "row_no": "number"
      }
    ],
    "totalCnt": "number"
  },
  "error": false,
  "code": "OK",
  "message": "조회에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

---

### 상세 조회
```
GET /projects/:prjNo/creatures/:creatureNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 종족(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "creature_no": "number",
    "prj_no": "number",
    "creature_nm": "string",
    "creature_type": "string",
    "danger_grd": "string | null",
    "ident_stat": "string",
    "creature_expln": "string | null",
    "bio_char": "string | null",
    "lifespan_growth": "string | null",
    "body_feat": "string | null",
    "sense_diet": "string | null",
    "reprod_info": "string | null",
    "eco_habit": "string | null",
    "habitat_env": "string | null",
    "lang_name": "string | null",
    "life_style": "string | null",
    "faith_taboo": "string | null",
    "soc_struct": "string | null",
    "psych_tend": "string | null",
    "abil_weak": "string | null",
    "civ_tech_lvl": "string | null",
    "spec_trait": "string | null",
    "weakness": "string | null",
    "est_eco": "string | null",
    "rumor_lore": "string | null",
    "poten_thrt": "string | null",
    "intel_lvl": "string | null",
    "drop_rsrc": "string | null",
    "hostile_rel": "string | null",
    "hist_desc": "string | null",
    "rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "조회에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 종족을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/creatures
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (프로젝트 소유자만 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "creature_nm": "string (required)",
  "creature_type": "string (required)",
  "danger_grd": "string (optional)",
  "ident_stat": "string (required)",
  "creature_expln": "string (optional)",
  "bio_char": "string (optional)",
  "lifespan_growth": "string (optional)",
  "body_feat": "string (optional)",
  "sense_diet": "string (optional)",
  "reprod_info": "string (optional)",
  "eco_habit": "string (optional)",
  "habitat_env": "string (optional)",
  "lang_name": "string (optional)",
  "life_style": "string (optional)",
  "faith_taboo": "string (optional)",
  "soc_struct": "string (optional)",
  "psych_tend": "string (optional)",
  "abil_weak": "string (optional)",
  "civ_tech_lvl": "string (optional)",
  "spec_trait": "string (optional)",
  "weakness": "string (optional)",
  "est_eco": "string (optional)",
  "rumor_lore": "string (optional)",
  "poten_thrt": "string (optional)",
  "intel_lvl": "string (optional)",
  "drop_rsrc": "string (optional)",
  "hostile_rel": "string (optional)",
  "hist_desc": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "creature_no": "number",
    "prj_no": "number",
    "creature_nm": "string",
    "creature_type": "string",
    "danger_grd": "string | null",
    "ident_stat": "string",
    "creature_expln": "string | null",
    "bio_char": "string | null",
    "lifespan_growth": "string | null",
    "body_feat": "string | null",
    "sense_diet": "string | null",
    "reprod_info": "string | null",
    "eco_habit": "string | null",
    "habitat_env": "string | null",
    "lang_name": "string | null",
    "life_style": "string | null",
    "faith_taboo": "string | null",
    "soc_struct": "string | null",
    "psych_tend": "string | null",
    "abil_weak": "string | null",
    "civ_tech_lvl": "string | null",
    "spec_trait": "string | null",
    "weakness": "string | null",
    "est_eco": "string | null",
    "rumor_lore": "string | null",
    "poten_thrt": "string | null",
    "intel_lvl": "string | null",
    "drop_rsrc": "string | null",
    "hostile_rel": "string | null",
    "hist_desc": "string | null",
    "rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "생성에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 프로젝트 소유자가 아님

---

### 수정
```
PATCH /projects/:prjNo/creatures/:creatureNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "creature_nm": "string (optional)",
  "creature_type": "string (optional)",
  "danger_grd": "string (optional)",
  "ident_stat": "string (optional)",
  "creature_expln": "string (optional)",
  "bio_char": "string (optional)",
  "lifespan_growth": "string (optional)",
  "body_feat": "string (optional)",
  "sense_diet": "string (optional)",
  "reprod_info": "string (optional)",
  "eco_habit": "string (optional)",
  "habitat_env": "string (optional)",
  "lang_name": "string (optional)",
  "life_style": "string (optional)",
  "faith_taboo": "string (optional)",
  "soc_struct": "string (optional)",
  "psych_tend": "string (optional)",
  "abil_weak": "string (optional)",
  "civ_tech_lvl": "string (optional)",
  "spec_trait": "string (optional)",
  "weakness": "string (optional)",
  "est_eco": "string (optional)",
  "rumor_lore": "string (optional)",
  "poten_thrt": "string (optional)",
  "intel_lvl": "string (optional)",
  "drop_rsrc": "string (optional)",
  "hostile_rel": "string (optional)",
  "hist_desc": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "creature_no": "number",
    "prj_no": "number",
    "creature_nm": "string",
    "creature_type": "string",
    "danger_grd": "string | null",
    "ident_stat": "string",
    "creature_expln": "string | null",
    "bio_char": "string | null",
    "lifespan_growth": "string | null",
    "body_feat": "string | null",
    "sense_diet": "string | null",
    "reprod_info": "string | null",
    "eco_habit": "string | null",
    "habitat_env": "string | null",
    "lang_name": "string | null",
    "life_style": "string | null",
    "faith_taboo": "string | null",
    "soc_struct": "string | null",
    "psych_tend": "string | null",
    "abil_weak": "string | null",
    "civ_tech_lvl": "string | null",
    "spec_trait": "string | null",
    "weakness": "string | null",
    "est_eco": "string | null",
    "rumor_lore": "string | null",
    "poten_thrt": "string | null",
    "intel_lvl": "string | null",
    "drop_rsrc": "string | null",
    "hostile_rel": "string | null",
    "hist_desc": "string | null",
    "rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "수정에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 종족을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/creatures/:creatureNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "삭제에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 종족을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

## 21. 종족-특성 매핑 (Creature Trait Maps)

### 목록 조회
```
GET /projects/:prjNo/creatures/:creatureNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (종족이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `trait_type`: string (optional) - 특성 타입 필터 (GLOBAL, PROJECT)
- `trait_no_list`: string (optional) - 특성 번호 리스트 (쉼표로 구분)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "map_no": "number",
        "creature_no": "number",
        "trait_no": "number",
        "trait_type": "string",
        "trait_rmk": "string | null",
        "use_yn": "string",
        "shrn_yn": "string",
        "del_yn": "string",
        "crt_no": "number | null",
        "crt_dt": "string (datetime) | null",
        "updt_no": "number | null",
        "updt_dt": "string (datetime) | null",
        "del_no": "number | null",
        "del_dt": "string (datetime) | null",
        "total_cnt": "number",
        "row_no": "number"
      }
    ],
    "totalCnt": "number"
  },
  "error": false,
  "code": "OK",
  "message": "조회에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

---

### 상세 조회
```
GET /projects/:prjNo/creatures/:creatureNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (종족이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "creature_no": "number",
    "trait_no": "number",
    "trait_type": "string",
    "trait_rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "조회에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 매핑을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/creatures/:creatureNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (종족 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "trait_no": "number (required)",
  "trait_type": "string (required)",
  "trait_rmk": "string (optional)"
}
```

**참고:**
- `trait_type`: "GLOBAL" (전역 특성) 또는 "PROJECT" (프로젝트 종속 특성)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "creature_no": "number",
    "trait_no": "number",
    "trait_type": "string",
    "trait_rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "생성에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 종족 소유자가 아님
- `TRAIT_NOT_FOUND`: 특성을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/creatures/:creatureNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (종족 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "trait_rmk": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "creature_no": "number",
    "trait_no": "number",
    "trait_type": "string",
    "trait_rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "수정에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 매핑을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 종족 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/creatures/:creatureNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (종족 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "삭제에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 매핑을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 종족 소유자가 아님

---

## 22. 종족-어빌리티 매핑 (Creature Ability Maps)

### 목록 조회
```
GET /projects/:prjNo/creatures/:creatureNo/abilitys
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (종족이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `ability_type`: string (optional) - 어빌리티 타입 필터 (GLOBAL, PROJECT)
- `ability_no_list`: string (optional) - 어빌리티 번호 리스트 (쉼표로 구분)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "map_no": "number",
        "creature_no": "number",
        "ability_no": "number",
        "ability_type": "string",
        "prof_lvl": "string | null",
        "ability_rmk": "string | null",
        "use_yn": "string",
        "shrn_yn": "string",
        "del_yn": "string",
        "crt_no": "number | null",
        "crt_dt": "string (datetime) | null",
        "updt_no": "number | null",
        "updt_dt": "string (datetime) | null",
        "del_no": "number | null",
        "del_dt": "string (datetime) | null",
        "total_cnt": "number",
        "row_no": "number"
      }
    ],
    "totalCnt": "number"
  },
  "error": false,
  "code": "OK",
  "message": "조회에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

---

### 상세 조회
```
GET /projects/:prjNo/creatures/:creatureNo/abilitys/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (종족이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "creature_no": "number",
    "ability_no": "number",
    "ability_type": "string",
    "prof_lvl": "string | null",
    "ability_rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "조회에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 매핑을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/creatures/:creatureNo/abilitys
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (종족 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "ability_no": "number (required)",
  "ability_type": "string (required)",
  "prof_lvl": "string (optional)",
  "ability_rmk": "string (optional)"
}
```

**참고:**
- `ability_type`: "GLOBAL" (전역 어빌리티) 또는 "PROJECT" (프로젝트 종속 어빌리티)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "creature_no": "number",
    "ability_no": "number",
    "ability_type": "string",
    "prof_lvl": "string | null",
    "ability_rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "생성에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 종족 소유자가 아님
- `SKILL_NOT_FOUND`: 어빌리티을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/creatures/:creatureNo/abilitys/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (종족 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "prof_lvl": "string (optional)",
  "ability_rmk": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "creature_no": "number",
    "ability_no": "number",
    "ability_type": "string",
    "prof_lvl": "string | null",
    "ability_rmk": "string | null",
    "use_yn": "string",
    "shrn_yn": "string",
    "del_yn": "string",
    "crt_no": "number | null",
    "crt_dt": "string (datetime) | null",
    "updt_no": "number | null",
    "updt_dt": "string (datetime) | null",
    "del_no": "number | null",
    "del_dt": "string (datetime) | null"
  },
  "error": false,
  "code": "OK",
  "message": "수정에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 매핑을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 종족 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/creatures/:creatureNo/abilitys/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (종족 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `creatureNo`: number (required) - 종족 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "삭제에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ERROR_CODE",
  "message": "에러 메시지"
}
```

**에러 코드:**
- `NOT_FOUND`: 매핑을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 종족 소유자가 아님

---
