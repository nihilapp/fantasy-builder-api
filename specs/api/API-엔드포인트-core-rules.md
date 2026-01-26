# 코어 설정 API 엔드포인트

이 문서는 Fantasy Builder API의 코어 설정(Core Rules) 관련 엔드포인트를 정의합니다.

## 6. 코어 설정 (Core Rules)

### 목록 조회
```
GET /projects/:prjNo/core-rules
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 코어 설정(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `core_no_list`: string (optional) - 코어 설정 번호 리스트 (쉼표로 구분)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "core_no": "number",
        "prj_no": "number",
        "core_nm": "string",
        "def_desc": "string | null",
        "aply_scope": "string | null",
        "strc_elem": "string | null",
        "mech_desc": "string | null",
        "narr_aply": "string | null",
        "keywords": "string | null",
        "link_docs": "string | null",
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
GET /projects/:prjNo/core-rules/:coreNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 코어 설정(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `coreNo`: number (required) - 코어 설정 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "core_no": "number",
    "prj_no": "number",
    "core_nm": "string",
    "def_desc": "string | null",
    "aply_scope": "string | null",
    "strc_elem": "string | null",
    "mech_desc": "string | null",
    "narr_aply": "string | null",
    "keywords": "string | null",
    "link_docs": "string | null",
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
- `NOT_FOUND`: 코어 설정을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/core-rules
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
  "core_nm": "string (required)",
  "def_desc": "string (optional)",
  "aply_scope": "string (optional)",
  "strc_elem": "string (optional)",
  "mech_desc": "string (optional)",
  "narr_aply": "string (optional)",
  "keywords": "string (optional)",
  "link_docs": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "core_no": "number",
    "prj_no": "number",
    "core_nm": "string",
    "def_desc": "string | null",
    "aply_scope": "string | null",
    "strc_elem": "string | null",
    "mech_desc": "string | null",
    "narr_aply": "string | null",
    "keywords": "string | null",
    "link_docs": "string | null",
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
PATCH /projects/:prjNo/core-rules/:coreNo
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
- `coreNo`: number (required) - 코어 설정 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "core_nm": "string (optional)",
  "def_desc": "string (optional)",
  "aply_scope": "string (optional)",
  "strc_elem": "string (optional)",
  "mech_desc": "string (optional)",
  "narr_aply": "string (optional)",
  "keywords": "string (optional)",
  "link_docs": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "core_no": "number",
    "prj_no": "number",
    "core_nm": "string",
    "def_desc": "string | null",
    "aply_scope": "string | null",
    "strc_elem": "string | null",
    "mech_desc": "string | null",
    "narr_aply": "string | null",
    "keywords": "string | null",
    "link_docs": "string | null",
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
- `NOT_FOUND`: 코어 설정을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/core-rules/:coreNo
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
- `coreNo`: number (required) - 코어 설정 번호

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
- `NOT_FOUND`: 코어 설정을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---
