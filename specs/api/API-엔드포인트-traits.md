# 특성(Traits) API 엔드포인트

이 문서는 Fantasy Builder API의 특성(Traits) 관련 엔드포인트를 정의합니다.

## 1. 전역 특성 (Traits)

### 목록 조회
```
GET /traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 특성(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `trait_no_list`: string (optional) - 특성 번호 리스트 (쉼표로 구분)
- `trait_lcls`: string (optional) - 특성 대분류
- `trait_mcls`: string (optional) - 특성 중분류
- `aply_trgt`: string (optional) - 적용 대상

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "trait_no": "number",
        "trait_nm": "string",
        "trait_expln": "string | null",
        "trait_lcls": "string",
        "trait_mcls": "string",
        "aply_trgt": "string",
        "cnfl_trait_no": "number | null",
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
GET /traits/:traitNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 특성(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `traitNo`: number (required) - 특성 번호

**Response (Success):**
```json
{
  "data": {
    "trait_no": "number",
    "trait_nm": "string",
    "trait_expln": "string | null",
    "trait_lcls": "string",
    "trait_mcls": "string",
    "aply_trgt": "string",
    "cnfl_trait_no": "number | null",
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
  "code": "TRAIT_NOT_FOUND",
  "message": "특성을 찾을 수 없습니다."
}
```

---

### 생성
```
POST /traits
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
  "trait_nm": "string (required)",
  "trait_expln": "string (optional)",
  "trait_lcls": "string (required)",
  "trait_mcls": "string (required)",
  "aply_trgt": "string (required)",
  "cnfl_trait_no": "number (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "trait_no": "number",
    "trait_nm": "string",
    "trait_expln": "string | null",
    "trait_lcls": "string",
    "trait_mcls": "string",
    "aply_trgt": "string",
    "cnfl_trait_no": "number | null",
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
  "message": "특성이 생성되었습니다."
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
PATCH /traits/:traitNo
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
- `traitNo`: number (required) - 특성 번호

**Request Body:**
```json
{
  "trait_nm": "string (optional)",
  "trait_expln": "string (optional)",
  "trait_lcls": "string (optional)",
  "trait_mcls": "string (optional)",
  "aply_trgt": "string (optional)",
  "cnfl_trait_no": "number (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "trait_no": "number",
    "trait_nm": "string",
    "trait_expln": "string | null",
    "trait_lcls": "string",
    "trait_mcls": "string",
    "aply_trgt": "string",
    "cnfl_trait_no": "number | null",
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
  "message": "특성이 수정되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "TRAIT_NOT_FOUND",
  "message": "특성을 찾을 수 없습니다."
}
```

---

### 삭제
```
DELETE /traits/:traitNo
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
- `traitNo`: number (required) - 특성 번호

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "특성이 삭제되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "TRAIT_NOT_FOUND",
  "message": "특성을 찾을 수 없습니다."
}
```

---

### 사용 현황 조회
```
GET /traits/:traitNo/usage
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 특성(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `traitNo`: number (required) - 특성 번호

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
  "code": "TRAIT_NOT_FOUND",
  "message": "특성을 찾을 수 없습니다."
}
```

---

## 4. 프로젝트 종속 특성 (Project Traits)

### 목록 조회
```
GET /projects/:prjNo/traits
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
- `trait_no_list`: string (optional) - 특성 번호 리스트 (쉼표로 구분)
- `trait_lcls`: string (optional) - 특성 대분류
- `trait_mcls`: string (optional) - 특성 중분류
- `aply_trgt`: string (optional) - 적용 대상

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "trait_no": "number",
        "prj_no": "number",
        "trait_nm": "string",
        "trait_expln": "string | null",
        "trait_lcls": "string",
        "trait_mcls": "string",
        "aply_trgt": "string",
        "cnfl_trait_no": "number | null",
        "cnfl_trait_type": "GLOBAL | PROJECT | null",
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
GET /projects/:prjNo/traits/:traitNo
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
- `traitNo`: number (required) - 특성 번호

**Response (Success):**
```json
{
  "data": {
    "trait_no": "number",
    "prj_no": "number",
    "trait_nm": "string",
    "trait_expln": "string | null",
    "trait_lcls": "string",
    "trait_mcls": "string",
    "aply_trgt": "string",
    "cnfl_trait_no": "number | null",
        "cnfl_trait_type": "GLOBAL | PROJECT | null",
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
  "code": "PROJECT_TRAIT_NOT_FOUND",
  "message": "프로젝트 종속 특성을 찾을 수 없습니다."
}
```

---

### 생성
```
POST /projects/:prjNo/traits
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
  "trait_nm": "string (required)",
  "trait_expln": "string (optional)",
  "trait_lcls": "string (required)",
  "trait_mcls": "string (required)",
  "aply_trgt": "string (required)",
  "cnfl_trait_no": "number (optional)",
  "cnfl_trait_type": "GLOBAL | PROJECT (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "trait_no": "number",
    "prj_no": "number",
    "trait_nm": "string",
    "trait_expln": "string | null",
    "trait_lcls": "string",
    "trait_mcls": "string",
    "aply_trgt": "string",
    "cnfl_trait_no": "number | null",
        "cnfl_trait_type": "GLOBAL | PROJECT | null",
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
  "message": "프로젝트 종속 특성이 생성되었습니다."
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
PATCH /projects/:prjNo/traits/:traitNo
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
- `traitNo`: number (required) - 특성 번호

**Request Body:**
```json
{
  "trait_nm": "string (optional)",
  "trait_expln": "string (optional)",
  "trait_lcls": "string (optional)",
  "trait_mcls": "string (optional)",
  "aply_trgt": "string (optional)",
  "cnfl_trait_no": "number (optional)",
  "cnfl_trait_type": "GLOBAL | PROJECT (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "trait_no": "number",
    "prj_no": "number",
    "trait_nm": "string",
    "trait_expln": "string | null",
    "trait_lcls": "string",
    "trait_mcls": "string",
    "aply_trgt": "string",
    "cnfl_trait_no": "number | null",
        "cnfl_trait_type": "GLOBAL | PROJECT | null",
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
  "message": "프로젝트 종속 특성이 수정되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "PROJECT_TRAIT_NOT_FOUND",
  "message": "프로젝트 종속 특성을 찾을 수 없습니다."
}
```

---

### 삭제
```
DELETE /projects/:prjNo/traits/:traitNo
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
- `traitNo`: number (required) - 특성 번호

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "프로젝트 종속 특성이 삭제되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "PROJECT_TRAIT_NOT_FOUND",
  "message": "프로젝트 종속 특성을 찾을 수 없습니다."
}
```
