# 어빌리티(Abilities) API 엔드포인트

이 문서는 Fantasy Builder API의 어빌리티(Abilities) 관련 엔드포인트를 정의합니다.

## 1. 전역 어빌리티 (Abilities)

### 목록 조회
```
GET /abilities
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 어빌리티(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `ability_no_list`: string (optional) - 어빌리티 번호 리스트 (쉼표로 구분)
- `ability_type`: string (optional) - 어빌리티 유형 (Active / Passive)
- `ability_lcls`: string (optional) - 어빌리티 계통

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "ability_no": "number",
        "ability_nm": "string",
        "ability_type": "string",
        "ability_lcls": "string",
        "ability_expln": "string | null",
        "trgt_type": "string | null",
        "dmg_type": "string | null",
        "stat_eff_type": "string | null",
        "use_cost": "string | null",
        "cool_time": "string | null",
        "cast_time": "string | null",
        "use_cnd": "string | null",
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
GET /abilities/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 어빌리티(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `abilityNo`: number (required) - 어빌리티 번호

**Response (Success):**
```json
{
  "data": {
    "ability_no": "number",
    "ability_nm": "string",
    "ability_type": "string",
    "ability_lcls": "string",
    "ability_expln": "string | null",
    "trgt_type": "string | null",
    "dmg_type": "string | null",
    "stat_eff_type": "string | null",
    "use_cost": "string | null",
    "cool_time": "string | null",
    "cast_time": "string | null",
    "use_cnd": "string | null",
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
  "code": "ABILITY_NOT_FOUND",
  "message": "어빌리티를 찾을 수 없습니다."
}
```

---

### 생성
```
POST /abilities
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (생성자는 자동으로 소유자가 됨)

**Request Headers:**
```
Authorization: Bearer {token}
```

**Request Body:**
```json
{
  "ability_nm": "string (required)",
  "ability_type": "string (required)",
  "ability_lcls": "string (required)",
  "ability_expln": "string (optional)",
  "trgt_type": "string (optional)",
  "dmg_type": "string (optional)",
  "stat_eff_type": "string (optional)",
  "use_cost": "string (optional)",
  "cool_time": "string (optional)",
  "cast_time": "string (optional)",
  "use_cnd": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "ability_no": "number",
    "ability_nm": "string",
    "ability_type": "string",
    "ability_lcls": "string",
    "ability_expln": "string | null",
    "trgt_type": "string | null",
    "dmg_type": "string | null",
    "stat_eff_type": "string | null",
    "use_cost": "string | null",
    "cool_time": "string | null",
    "cast_time": "string | null",
    "use_cnd": "string | null",
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
  "message": "어빌리티가 생성되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "VALIDATION_ERROR",
  "message": "입력값이 올바르지 않습니다."
}
```

---

### 수정
```
PATCH /abilities/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner or Admin (소유자 또는 관리자만 수정 가능)

**Request Headers:**
```
Authorization: Bearer {token}
```

**Path Parameters:**
- `abilityNo`: number (required) - 어빌리티 번호

**Request Body:**
```json
{
  "ability_nm": "string (optional)",
  "ability_type": "string (optional)",
  "ability_lcls": "string (optional)",
  "ability_expln": "string (optional)",
  "trgt_type": "string (optional)",
  "dmg_type": "string (optional)",
  "stat_eff_type": "string (optional)",
  "use_cost": "string (optional)",
  "cool_time": "string (optional)",
  "cast_time": "string (optional)",
  "use_cnd": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "ability_no": "number",
    "ability_nm": "string",
    "ability_type": "string",
    "ability_lcls": "string",
    "ability_expln": "string | null",
    "trgt_type": "string | null",
    "dmg_type": "string | null",
    "stat_eff_type": "string | null",
    "use_cost": "string | null",
    "cool_time": "string | null",
    "cast_time": "string | null",
    "use_cnd": "string | null",
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
  "message": "어빌리티가 수정되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ABILITY_NOT_FOUND",
  "message": "어빌리티를 찾을 수 없습니다."
}
```

---

### 삭제
```
DELETE /abilities/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner only (소유자만 삭제 가능, 관리자는 권한은 있으나 정책상 사용하지 않음)

**Request Headers:**
```
Authorization: Bearer {token}
```

**Path Parameters:**
- `abilityNo`: number (required) - 어빌리티 번호

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "어빌리티가 삭제되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "ABILITY_NOT_FOUND",
  "message": "어빌리티를 찾을 수 없습니다."
}
```

---

### 사용 현황 조회
```
GET /abilities/:abilityNo/usage
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 어빌리티(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `abilityNo`: number (required) - 어빌리티 번호

**Response (Success):**
```json
{
  "data": {
    "characters": "number",
    "creatures": "number",
    "items": "number",
    "nations": "number",
    "organizations": "number",
    "regions": "number"
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
  "code": "ABILITY_NOT_FOUND",
  "message": "어빌리티를 찾을 수 없습니다."
}
```

---

## 2. 프로젝트 종속 어빌리티 (Project Abilities)

### 목록 조회
```
GET /projects/:prjNo/abilities
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (해당 프로젝트가 공개되어 있거나(`shrn_yn = 'Y'`) 프로젝트 소유자인 경우만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `ability_no_list`: string (optional) - 어빌리티 번호 리스트 (쉼표로 구분)
- `ability_type`: string (optional) - 어빌리티 유형 (Active / Passive)
- `ability_lcls`: string (optional) - 어빌리티 계통

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "ability_no": "number",
        "prj_no": "number",
        "ability_nm": "string",
        "ability_type": "string",
        "ability_lcls": "string",
        "ability_expln": "string | null",
        "trgt_type": "string | null",
        "dmg_type": "string | null",
        "stat_eff_type": "string | null",
        "use_cost": "string | null",
        "cool_time": "string | null",
        "cast_time": "string | null",
        "use_cnd": "string | null",
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
GET /projects/:prjNo/abilities/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (해당 프로젝트가 공개되어 있거나(`shrn_yn = 'Y'`) 프로젝트 소유자인 경우만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `abilityNo`: number (required) - 어빌리티 번호

**Response (Success):**
```json
{
  "data": {
    "ability_no": "number",
    "prj_no": "number",
    "ability_nm": "string",
    "ability_type": "string",
    "ability_lcls": "string",
    "ability_expln": "string | null",
    "trgt_type": "string | null",
    "dmg_type": "string | null",
    "stat_eff_type": "string | null",
    "use_cost": "string | null",
    "cool_time": "string | null",
    "cast_time": "string | null",
    "use_cnd": "string | null",
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
  "code": "PROJECT_ABILITY_NOT_FOUND",
  "message": "프로젝트 종속 어빌리티를 찾을 수 없습니다."
}
```

---

### 생성
```
POST /projects/:prjNo/abilities
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner or Admin (프로젝트 소유자 또는 관리자만 생성 가능)

**Request Headers:**
```
Authorization: Bearer {token}
```

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Request Body:**
```json
{
  "ability_nm": "string (required)",
  "ability_type": "string (required)",
  "ability_lcls": "string (required)",
  "ability_expln": "string (optional)",
  "trgt_type": "string (optional)",
  "dmg_type": "string (optional)",
  "stat_eff_type": "string (optional)",
  "use_cost": "string (optional)",
  "cool_time": "string (optional)",
  "cast_time": "string (optional)",
  "use_cnd": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "ability_no": "number",
    "prj_no": "number",
    "ability_nm": "string",
    "ability_type": "string",
    "ability_lcls": "string",
    "ability_expln": "string | null",
    "trgt_type": "string | null",
    "dmg_type": "string | null",
    "stat_eff_type": "string | null",
    "use_cost": "string | null",
    "cool_time": "string | null",
    "cast_time": "string | null",
    "use_cnd": "string | null",
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
  "message": "프로젝트 종속 어빌리티가 생성되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "VALIDATION_ERROR",
  "message": "입력값이 올바르지 않습니다."
}
```

---

### 수정
```
PATCH /projects/:prjNo/abilities/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner or Admin (프로젝트 소유자 또는 관리자만 수정 가능)

**Request Headers:**
```
Authorization: Bearer {token}
```

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `abilityNo`: number (required) - 어빌리티 번호

**Request Body:**
```json
{
  "ability_nm": "string (optional)",
  "ability_type": "string (optional)",
  "ability_lcls": "string (optional)",
  "ability_expln": "string (optional)",
  "trgt_type": "string (optional)",
  "dmg_type": "string (optional)",
  "stat_eff_type": "string (optional)",
  "use_cost": "string (optional)",
  "cool_time": "string (optional)",
  "cast_time": "string (optional)",
  "use_cnd": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "ability_no": "number",
    "prj_no": "number",
    "ability_nm": "string",
    "ability_type": "string",
    "ability_lcls": "string",
    "ability_expln": "string | null",
    "trgt_type": "string | null",
    "dmg_type": "string | null",
    "stat_eff_type": "string | null",
    "use_cost": "string | null",
    "cool_time": "string | null",
    "cast_time": "string | null",
    "use_cnd": "string | null",
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
  "message": "프로젝트 종속 어빌리티가 수정되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "PROJECT_ABILITY_NOT_FOUND",
  "message": "프로젝트 종속 어빌리티를 찾을 수 없습니다."
}
```

---

### 삭제
```
DELETE /projects/:prjNo/abilities/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner only (프로젝트 소유자만 삭제 가능, 관리자는 권한은 있으나 정책상 사용하지 않음)

**Request Headers:**
```
Authorization: Bearer {token}
```

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `abilityNo`: number (required) - 어빌리티 번호

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "프로젝트 종속 어빌리티가 삭제되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "PROJECT_ABILITY_NOT_FOUND",
  "message": "프로젝트 종속 어빌리티를 찾을 수 없습니다."
}
```
