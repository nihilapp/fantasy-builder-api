# 프로젝트 API 엔드포인트

이 문서는 Fantasy Builder API의 프로젝트 관련 엔드포인트를 정의합니다.

## 3. 프로젝트 (Projects)

### 목록 조회
```
GET /projects
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 프로젝트(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `prj_no_list`: string (optional) - 프로젝트 번호 리스트 (쉼표로 구분)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "prj_no": "number",
        "prj_nm": "string",
        "genre_type": "string | null",
        "prj_desc": "string | null",
        "cvr_img_url": "string | null",
        "prj_expln": "string | null",
        "prj_ver": "string | null",
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
GET /projects/:prjNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 프로젝트(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Response (Success):**
```json
{
  "data": {
    "prj_no": "number",
    "prj_nm": "string",
    "genre_type": "string | null",
    "prj_desc": "string | null",
    "cvr_img_url": "string | null",
    "prj_expln": "string | null",
    "prj_ver": "string | null",
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
  "code": "PROJECT_NOT_FOUND",
  "message": "프로젝트를 찾을 수 없습니다."
}
```

---

### 생성
```
POST /projects
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
  "prj_nm": "string (required)",
  "genre_type": "string (optional)",
  "prj_desc": "string (optional)",
  "cvr_img_url": "string (optional)",
  "prj_expln": "string (optional)",
  "prj_ver": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "prj_no": "number",
    "prj_nm": "string",
    "genre_type": "string | null",
    "prj_desc": "string | null",
    "cvr_img_url": "string | null",
    "prj_expln": "string | null",
    "prj_ver": "string | null",
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
  "message": "프로젝트가 생성되었습니다."
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
PATCH /projects/:prjNo
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
- `prjNo`: number (required) - 프로젝트 번호

**Request Body:**
```json
{
  "prj_nm": "string (optional)",
  "genre_type": "string (optional)",
  "prj_desc": "string (optional)",
  "cvr_img_url": "string (optional)",
  "prj_expln": "string (optional)",
  "prj_ver": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "prj_no": "number",
    "prj_nm": "string",
    "genre_type": "string | null",
    "prj_desc": "string | null",
    "cvr_img_url": "string | null",
    "prj_expln": "string | null",
    "prj_ver": "string | null",
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
  "message": "프로젝트가 수정되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "PROJECT_NOT_FOUND",
  "message": "프로젝트를 찾을 수 없습니다."
}
```

---

### 삭제
```
DELETE /projects/:prjNo
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
- `prjNo`: number (required) - 프로젝트 번호

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "프로젝트가 삭제되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "PROJECT_NOT_FOUND",
  "message": "프로젝트를 찾을 수 없습니다."
}
```
