# 아이템 API 엔드포인트

이 문서는 Fantasy Builder API의 아이템(Items) 관련 엔드포인트를 정의합니다.

## 9. 아이템 (Items)

### 목록 조회
```
GET /projects/:prjNo/items
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 아이템(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `item_no_list`: string (optional) - 아이템 번호 리스트 (쉼표로 구분)
- `cls_main`: string (optional) - 대분류 필터
- `cls_sub`: string (optional) - 소분류 필터
- `item_grd`: string (optional) - 등급 필터
- `attr_type`: string (optional) - 속성 필터
- `dmg_type`: string (optional) - 피해 유형 필터
- `ego_type`: string (optional) - 자아 유형 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "item_no": "number",
        "prj_no": "number",
        "item_nm": "string",
        "cls_main": "string",
        "cls_sub": "string | null",
        "item_grd": "string | null",
        "logline": "string | null",
        "app_desc": "string | null",
        "visual_feat": "string | null",
        "attr_type": "string | null",
        "dmg_type": "string | null",
        "main_func": "string | null",
        "sub_eff": "string | null",
        "spec_abil": "string | null",
        "ego_type": "string | null",
        "ego_desc": "string | null",
        "use_cond": "string | null",
        "use_mthd": "string | null",
        "trns_cond": "string | null",
        "strg_mthd": "string | null",
        "use_lmt": "string | null",
        "use_cost": "string | null",
        "side_eff": "string | null",
        "durability_desc": "string | null",
        "hist_past": "string | null",
        "curr_stat": "string | null",
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
GET /projects/:prjNo/items/:itemNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 아이템(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "item_no": "number",
    "prj_no": "number",
    "item_nm": "string",
    "cls_main": "string",
    "cls_sub": "string | null",
    "item_grd": "string | null",
    "logline": "string | null",
    "app_desc": "string | null",
    "visual_feat": "string | null",
    "attr_type": "string | null",
    "dmg_type": "string | null",
    "main_func": "string | null",
    "sub_eff": "string | null",
    "spec_abil": "string | null",
    "ego_type": "string | null",
    "ego_desc": "string | null",
    "use_cond": "string | null",
    "use_mthd": "string | null",
    "trns_cond": "string | null",
    "strg_mthd": "string | null",
    "use_lmt": "string | null",
    "use_cost": "string | null",
    "side_eff": "string | null",
    "durability_desc": "string | null",
    "hist_past": "string | null",
    "curr_stat": "string | null",
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
- `NOT_FOUND`: 아이템을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/items
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
  "item_nm": "string (required)",
  "cls_main": "string (required)",
  "cls_sub": "string (optional)",
  "item_grd": "string (optional)",
  "logline": "string (optional)",
  "app_desc": "string (optional)",
  "visual_feat": "string (optional)",
  "attr_type": "string (optional)",
  "dmg_type": "string (optional)",
  "main_func": "string (optional)",
  "sub_eff": "string (optional)",
  "spec_abil": "string (optional)",
  "ego_type": "string (optional)",
  "ego_desc": "string (optional)",
  "use_cond": "string (optional)",
  "use_mthd": "string (optional)",
  "trns_cond": "string (optional)",
  "strg_mthd": "string (optional)",
  "use_lmt": "string (optional)",
  "use_cost": "string (optional)",
  "side_eff": "string (optional)",
  "durability_desc": "string (optional)",
  "hist_past": "string (optional)",
  "curr_stat": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "item_no": "number",
    "prj_no": "number",
    "item_nm": "string",
    "cls_main": "string",
    "cls_sub": "string | null",
    "item_grd": "string | null",
    "logline": "string | null",
    "app_desc": "string | null",
    "visual_feat": "string | null",
    "attr_type": "string | null",
    "dmg_type": "string | null",
    "main_func": "string | null",
    "sub_eff": "string | null",
    "spec_abil": "string | null",
    "ego_type": "string | null",
    "ego_desc": "string | null",
    "use_cond": "string | null",
    "use_mthd": "string | null",
    "trns_cond": "string | null",
    "strg_mthd": "string | null",
    "use_lmt": "string | null",
    "use_cost": "string | null",
    "side_eff": "string | null",
    "durability_desc": "string | null",
    "hist_past": "string | null",
    "curr_stat": "string | null",
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
PATCH /projects/:prjNo/items/:itemNo
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
- `itemNo`: number (required) - 아이템 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "item_nm": "string (optional)",
  "cls_main": "string (optional)",
  "cls_sub": "string (optional)",
  "item_grd": "string (optional)",
  "logline": "string (optional)",
  "app_desc": "string (optional)",
  "visual_feat": "string (optional)",
  "attr_type": "string (optional)",
  "dmg_type": "string (optional)",
  "main_func": "string (optional)",
  "sub_eff": "string (optional)",
  "spec_abil": "string (optional)",
  "ego_type": "string (optional)",
  "ego_desc": "string (optional)",
  "use_cond": "string (optional)",
  "use_mthd": "string (optional)",
  "trns_cond": "string (optional)",
  "strg_mthd": "string (optional)",
  "use_lmt": "string (optional)",
  "use_cost": "string (optional)",
  "side_eff": "string (optional)",
  "durability_desc": "string (optional)",
  "hist_past": "string (optional)",
  "curr_stat": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "item_no": "number",
    "prj_no": "number",
    "item_nm": "string",
    "cls_main": "string",
    "cls_sub": "string | null",
    "item_grd": "string | null",
    "logline": "string | null",
    "app_desc": "string | null",
    "visual_feat": "string | null",
    "attr_type": "string | null",
    "dmg_type": "string | null",
    "main_func": "string | null",
    "sub_eff": "string | null",
    "spec_abil": "string | null",
    "ego_type": "string | null",
    "ego_desc": "string | null",
    "use_cond": "string | null",
    "use_mthd": "string | null",
    "trns_cond": "string | null",
    "strg_mthd": "string | null",
    "use_lmt": "string | null",
    "use_cost": "string | null",
    "side_eff": "string | null",
    "durability_desc": "string | null",
    "hist_past": "string | null",
    "curr_stat": "string | null",
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
- `NOT_FOUND`: 아이템을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/items/:itemNo
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
- `itemNo`: number (required) - 아이템 번호

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
- `NOT_FOUND`: 아이템을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

## 23. 아이템-어빌리티 매핑 (Item Ability Maps)

### 목록 조회
```
GET /projects/:prjNo/items/:itemNo/abilitys
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (아이템이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호

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
        "item_no": "number",
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
GET /projects/:prjNo/items/:itemNo/abilitys/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (아이템이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "item_no": "number",
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
POST /projects/:prjNo/items/:itemNo/abilitys
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (아이템 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호

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
    "item_no": "number",
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
- `NOT_OWNER`: 아이템 소유자가 아님
- `SKILL_NOT_FOUND`: 어빌리티을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/items/:itemNo/abilitys/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (아이템 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호
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
    "item_no": "number",
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
- `NOT_OWNER`: 아이템 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/items/:itemNo/abilitys/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (아이템 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호
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
- `NOT_OWNER`: 아이템 소유자가 아님

---

## 24. 아이템-특성 매핑 (Item Trait Maps)

### 목록 조회
```
GET /projects/:prjNo/items/:itemNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (아이템이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호

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
        "item_no": "number",
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
GET /projects/:prjNo/items/:itemNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (아이템이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "item_no": "number",
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
POST /projects/:prjNo/items/:itemNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (아이템 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호

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
    "item_no": "number",
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
- `NOT_OWNER`: 아이템 소유자가 아님
- `TRAIT_NOT_FOUND`: 특성을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/items/:itemNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (아이템 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호
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
    "item_no": "number",
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
- `NOT_OWNER`: 아이템 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/items/:itemNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (아이템 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `itemNo`: number (required) - 아이템 번호
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
- `NOT_OWNER`: 아이템 소유자가 아님

---
