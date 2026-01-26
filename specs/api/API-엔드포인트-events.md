# 사건 API 엔드포인트

이 문서는 Fantasy Builder API의 사건(Events) 관련 엔드포인트를 정의합니다.

## 13. 사건 (Events)

### 목록 조회
```
GET /projects/:prjNo/events
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 사건(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `event_no_list`: string (optional) - 사건 번호 리스트 (쉼표로 구분)
- `occur_time`: string (optional) - 발생 시기 필터
- `occur_loc`: string (optional) - 발생 장소 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "event_no": "number",
        "prj_no": "number",
        "event_nm": "string",
        "occur_time": "string | null",
        "occur_loc": "string | null",
        "smry": "string | null",
        "cause_pub": "string | null",
        "cause_real": "string | null",
        "side_a_char": "string | null",
        "side_a_org": "string | null",
        "side_a_ntn": "string | null",
        "side_b_char": "string | null",
        "side_b_org": "string | null",
        "side_b_ntn": "string | null",
        "side_c_char": "string | null",
        "side_c_org": "string | null",
        "side_c_ntn": "string | null",
        "flow_trgr": "string | null",
        "flow_crisis": "string | null",
        "flow_climax": "string | null",
        "flow_concl": "string | null",
        "dmg_rslt": "string | null",
        "soc_chng": "string | null",
        "curr_conn": "string | null",
        "rec_official": "string | null",
        "truth_hid": "string | null",
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
GET /projects/:prjNo/events/:eventNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 사건(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `eventNo`: number (required) - 사건 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "event_no": "number",
    "prj_no": "number",
    "event_nm": "string",
    "occur_time": "string | null",
    "occur_loc": "string | null",
    "smry": "string | null",
    "cause_pub": "string | null",
    "cause_real": "string | null",
    "side_a_char": "string | null",
    "side_a_org": "string | null",
    "side_a_ntn": "string | null",
    "side_b_char": "string | null",
    "side_b_org": "string | null",
    "side_b_ntn": "string | null",
    "side_c_char": "string | null",
    "side_c_org": "string | null",
    "side_c_ntn": "string | null",
    "flow_trgr": "string | null",
    "flow_crisis": "string | null",
    "flow_climax": "string | null",
    "flow_concl": "string | null",
    "dmg_rslt": "string | null",
    "soc_chng": "string | null",
    "curr_conn": "string | null",
    "rec_official": "string | null",
    "truth_hid": "string | null",
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
- `NOT_FOUND`: 사건을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/events
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
  "event_nm": "string (required)",
  "occur_time": "string (optional)",
  "occur_loc": "string (optional)",
  "smry": "string (optional)",
  "cause_pub": "string (optional)",
  "cause_real": "string (optional)",
  "side_a_char": "string (optional)",
  "side_a_org": "string (optional)",
  "side_a_ntn": "string (optional)",
  "side_b_char": "string (optional)",
  "side_b_org": "string (optional)",
  "side_b_ntn": "string (optional)",
  "side_c_char": "string (optional)",
  "side_c_org": "string (optional)",
  "side_c_ntn": "string (optional)",
  "flow_trgr": "string (optional)",
  "flow_crisis": "string (optional)",
  "flow_climax": "string (optional)",
  "flow_concl": "string (optional)",
  "dmg_rslt": "string (optional)",
  "soc_chng": "string (optional)",
  "curr_conn": "string (optional)",
  "rec_official": "string (optional)",
  "truth_hid": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "event_no": "number",
    "prj_no": "number",
    "event_nm": "string",
    "occur_time": "string | null",
    "occur_loc": "string | null",
    "smry": "string | null",
    "cause_pub": "string | null",
    "cause_real": "string | null",
    "side_a_char": "string | null",
    "side_a_org": "string | null",
    "side_a_ntn": "string | null",
    "side_b_char": "string | null",
    "side_b_org": "string | null",
    "side_b_ntn": "string | null",
    "side_c_char": "string | null",
    "side_c_org": "string | null",
    "side_c_ntn": "string | null",
    "flow_trgr": "string | null",
    "flow_crisis": "string | null",
    "flow_climax": "string | null",
    "flow_concl": "string | null",
    "dmg_rslt": "string | null",
    "soc_chng": "string | null",
    "curr_conn": "string | null",
    "rec_official": "string | null",
    "truth_hid": "string | null",
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
PATCH /projects/:prjNo/events/:eventNo
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
- `eventNo`: number (required) - 사건 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "event_nm": "string (optional)",
  "occur_time": "string (optional)",
  "occur_loc": "string (optional)",
  "smry": "string (optional)",
  "cause_pub": "string (optional)",
  "cause_real": "string (optional)",
  "side_a_char": "string (optional)",
  "side_a_org": "string (optional)",
  "side_a_ntn": "string (optional)",
  "side_b_char": "string (optional)",
  "side_b_org": "string (optional)",
  "side_b_ntn": "string (optional)",
  "side_c_char": "string (optional)",
  "side_c_org": "string (optional)",
  "side_c_ntn": "string (optional)",
  "flow_trgr": "string (optional)",
  "flow_crisis": "string (optional)",
  "flow_climax": "string (optional)",
  "flow_concl": "string (optional)",
  "dmg_rslt": "string (optional)",
  "soc_chng": "string (optional)",
  "curr_conn": "string (optional)",
  "rec_official": "string (optional)",
  "truth_hid": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "event_no": "number",
    "prj_no": "number",
    "event_nm": "string",
    "occur_time": "string | null",
    "occur_loc": "string | null",
    "smry": "string | null",
    "cause_pub": "string | null",
    "cause_real": "string | null",
    "side_a_char": "string | null",
    "side_a_org": "string | null",
    "side_a_ntn": "string | null",
    "side_b_char": "string | null",
    "side_b_org": "string | null",
    "side_b_ntn": "string | null",
    "side_c_char": "string | null",
    "side_c_org": "string | null",
    "side_c_ntn": "string | null",
    "flow_trgr": "string | null",
    "flow_crisis": "string | null",
    "flow_climax": "string | null",
    "flow_concl": "string | null",
    "dmg_rslt": "string | null",
    "soc_chng": "string | null",
    "curr_conn": "string | null",
    "rec_official": "string | null",
    "truth_hid": "string | null",
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
- `NOT_FOUND`: 사건을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/events/:eventNo
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
- `eventNo`: number (required) - 사건 번호

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
- `NOT_FOUND`: 사건을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

## 28. 사건 참여 (Event Entries)

### 목록 조회
```
GET /projects/:prjNo/events/:eventNo/entries
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (사건이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `eventNo`: number (required) - 사건 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `entry_type`: string (optional) - 참여 대상 유형 필터
- `entry_side`: string (optional) - 참여 진영 필터 (Side A/B/C 등)
- `entry_no_list`: string (optional) - 참여 번호 리스트 (쉼표로 구분)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "entry_no": "number",
        "event_no": "number",
        "entry_type": "string",
        "entry_trgt_no": "number",
        "entry_side": "string | null",
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
GET /projects/:prjNo/events/:eventNo/entries/:entryNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (사건이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `eventNo`: number (required) - 사건 번호
- `entryNo`: number (required) - 참여 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "entry_no": "number",
    "event_no": "number",
    "entry_type": "string",
    "entry_trgt_no": "number",
    "entry_side": "string | null",
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
- `NOT_FOUND`: 참여 정보를 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/events/:eventNo/entries
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (사건 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `eventNo`: number (required) - 사건 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "entry_type": "string (required)",
  "entry_trgt_no": "number (required)",
  "entry_side": "string (optional)",
  "role_desc": "string (optional)"
}
```

**참고:**
- `entry_type`: 참여 대상 유형 (예: "CHARACTER", "ORGANIZATION", "NATION" 등)
- `entry_trgt_no`: 참여 대상 번호 (인물 번호, 조직 번호, 국가 번호 등)
- `entry_side`: 참여 진영 (예: "Side A", "Side B", "Side C" 등)

**Response (Success):**
```json
{
  "data": {
    "entry_no": "number",
    "event_no": "number",
    "entry_type": "string",
    "entry_trgt_no": "number",
    "entry_side": "string | null",
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
- `NOT_OWNER`: 사건 소유자가 아님
- `TARGET_NOT_FOUND`: 참여 대상을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/events/:eventNo/entries/:entryNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (사건 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `eventNo`: number (required) - 사건 번호
- `entryNo`: number (required) - 참여 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "entry_side": "string (optional)",
  "role_desc": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "entry_no": "number",
    "event_no": "number",
    "entry_type": "string",
    "entry_trgt_no": "number",
    "entry_side": "string | null",
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
- `NOT_FOUND`: 참여 정보를 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 사건 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/events/:eventNo/entries/:entryNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (사건 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `eventNo`: number (required) - 사건 번호
- `entryNo`: number (required) - 참여 번호

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
- `NOT_FOUND`: 참여 정보를 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 사건 소유자가 아님

---
