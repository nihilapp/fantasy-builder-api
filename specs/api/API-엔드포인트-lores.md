# 전승/설화 API 엔드포인트

이 문서는 Fantasy Builder API의 전승/설화(Lores) 관련 엔드포인트를 정의합니다.

## 14. 전승/설화 (Lores)

### 목록 조회
```
GET /projects/:prjNo/lores
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 전승(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `lore_no_list`: string (optional) - 전승 번호 리스트 (쉼표로 구분)
- `lore_type`: string (optional) - 전승 유형 필터
- `main_subj`: string (optional) - 주요 소재 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "lore_no": "number",
        "prj_no": "number",
        "lore_nm": "string",
        "lore_type": "string | null",
        "main_subj": "string | null",
        "smry": "string | null",
        "trans_mthd": "string | null",
        "pub_perc": "string | null",
        "lore_plot": "string | null",
        "real_fact": "string | null",
        "dist_rsn": "string | null",
        "diff_desc": "string | null",
        "cltr_impact": "string | null",
        "plot_conn": "string | null",
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
GET /projects/:prjNo/lores/:loreNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 전승(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "lore_no": "number",
    "prj_no": "number",
    "lore_nm": "string",
    "lore_type": "string | null",
    "main_subj": "string | null",
    "smry": "string | null",
    "trans_mthd": "string | null",
    "pub_perc": "string | null",
    "lore_plot": "string | null",
    "real_fact": "string | null",
    "dist_rsn": "string | null",
    "diff_desc": "string | null",
    "cltr_impact": "string | null",
    "plot_conn": "string | null",
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
- `NOT_FOUND`: 전승을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/lores
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
  "lore_nm": "string (required)",
  "lore_type": "string (optional)",
  "main_subj": "string (optional)",
  "smry": "string (optional)",
  "trans_mthd": "string (optional)",
  "pub_perc": "string (optional)",
  "lore_plot": "string (optional)",
  "real_fact": "string (optional)",
  "dist_rsn": "string (optional)",
  "diff_desc": "string (optional)",
  "cltr_impact": "string (optional)",
  "plot_conn": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "lore_no": "number",
    "prj_no": "number",
    "lore_nm": "string",
    "lore_type": "string | null",
    "main_subj": "string | null",
    "smry": "string | null",
    "trans_mthd": "string | null",
    "pub_perc": "string | null",
    "lore_plot": "string | null",
    "real_fact": "string | null",
    "dist_rsn": "string | null",
    "diff_desc": "string | null",
    "cltr_impact": "string | null",
    "plot_conn": "string | null",
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
PATCH /projects/:prjNo/lores/:loreNo
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
- `loreNo`: number (required) - 전승 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "lore_nm": "string (optional)",
  "lore_type": "string (optional)",
  "main_subj": "string (optional)",
  "smry": "string (optional)",
  "trans_mthd": "string (optional)",
  "pub_perc": "string (optional)",
  "lore_plot": "string (optional)",
  "real_fact": "string (optional)",
  "dist_rsn": "string (optional)",
  "diff_desc": "string (optional)",
  "cltr_impact": "string (optional)",
  "plot_conn": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "lore_no": "number",
    "prj_no": "number",
    "lore_nm": "string",
    "lore_type": "string | null",
    "main_subj": "string | null",
    "smry": "string | null",
    "trans_mthd": "string | null",
    "pub_perc": "string | null",
    "lore_plot": "string | null",
    "real_fact": "string | null",
    "dist_rsn": "string | null",
    "diff_desc": "string | null",
    "cltr_impact": "string | null",
    "plot_conn": "string | null",
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
- `NOT_FOUND`: 전승을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/lores/:loreNo
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
- `loreNo`: number (required) - 전승 번호

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
- `NOT_FOUND`: 전승을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

## 29. 전승-인물 매핑 (Lore Char Maps)

### 목록 조회
```
GET /projects/:prjNo/lores/:loreNo/characters
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (전승이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `char_no_list`: string (optional) - 인물 번호 리스트 (쉼표로 구분)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "map_no": "number",
        "lore_no": "number",
        "char_no": "number",
        "role_desc": "string | null",
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
GET /projects/:prjNo/lores/:loreNo/characters/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (전승이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "lore_no": "number",
    "char_no": "number",
    "role_desc": "string | null",
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
POST /projects/:prjNo/lores/:loreNo/characters
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (전승 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "char_no": "number (required)",
  "role_desc": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "lore_no": "number",
    "char_no": "number",
    "role_desc": "string | null",
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
- `NOT_OWNER`: 전승 소유자가 아님
- `CHARACTER_NOT_FOUND`: 인물을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/lores/:loreNo/characters/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (전승 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "role_desc": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "lore_no": "number",
    "char_no": "number",
    "role_desc": "string | null",
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
- `NOT_OWNER`: 전승 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/lores/:loreNo/characters/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (전승 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호
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
- `NOT_OWNER`: 전승 소유자가 아님

---

## 30. 전승-아이템 매핑 (Lore Item Maps)

### 목록 조회
```
GET /projects/:prjNo/lores/:loreNo/items
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (전승이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `item_no_list`: string (optional) - 아이템 번호 리스트 (쉼표로 구분)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "map_no": "number",
        "lore_no": "number",
        "item_no": "number",
        "role_desc": "string | null",
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
GET /projects/:prjNo/lores/:loreNo/items/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (전승이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "lore_no": "number",
    "item_no": "number",
    "role_desc": "string | null",
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
POST /projects/:prjNo/lores/:loreNo/items
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (전승 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "item_no": "number (required)",
  "role_desc": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "lore_no": "number",
    "item_no": "number",
    "role_desc": "string | null",
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
- `NOT_OWNER`: 전승 소유자가 아님
- `ITEM_NOT_FOUND`: 아이템을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/lores/:loreNo/items/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (전승 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "role_desc": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "lore_no": "number",
    "item_no": "number",
    "role_desc": "string | null",
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
- `NOT_OWNER`: 전승 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/lores/:loreNo/items/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (전승 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `loreNo`: number (required) - 전승 번호
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
- `NOT_OWNER`: 전승 소유자가 아님

---
