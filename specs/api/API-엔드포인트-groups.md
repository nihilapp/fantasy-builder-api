# 집단 API 엔드포인트

이 문서는 Fantasy Builder API의 집단(국가 및 조직) 관련 엔드포인트를 정의합니다.

## 11. 국가 (Nations)

### 목록 조회
```
GET /projects/:prjNo/nations
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 국가(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `ntn_no_list`: string (optional) - 국가 번호 리스트 (쉼표로 구분)
- `ntn_type`: string (optional) - 국가 유형 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "ntn_no": "number",
        "prj_no": "number",
        "ntn_nm": "string",
        "ntn_type": "string | null",
        "logline": "string | null",
        "capital_nm": "string | null",
        "ruler_txt": "string | null",
        "pol_sys": "string | null",
        "admin_law": "string | null",
        "state_rlgn": "string | null",
        "rlgn_desc": "string | null",
        "nat_idlg": "string | null",
        "main_plcy": "string | null",
        "taboo_act": "string | null",
        "dipl_plcy": "string | null",
        "intr_cnfl": "string | null",
        "hidden_fact": "string | null",
        "econ_struct": "string | null",
        "soc_cltr": "string | null",
        "mil_pwr": "string | null",
        "hist_desc": "string | null",
        "curr_issue": "string | null",
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
GET /projects/:prjNo/nations/:ntnNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 국가(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `ntnNo`: number (required) - 국가 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "ntn_no": "number",
    "prj_no": "number",
    "ntn_nm": "string",
    "ntn_type": "string | null",
    "logline": "string | null",
    "capital_nm": "string | null",
    "ruler_txt": "string | null",
    "pol_sys": "string | null",
    "admin_law": "string | null",
    "state_rlgn": "string | null",
    "rlgn_desc": "string | null",
    "nat_idlg": "string | null",
    "main_plcy": "string | null",
    "taboo_act": "string | null",
    "dipl_plcy": "string | null",
    "intr_cnfl": "string | null",
    "hidden_fact": "string | null",
    "econ_struct": "string | null",
    "soc_cltr": "string | null",
    "mil_pwr": "string | null",
    "hist_desc": "string | null",
    "curr_issue": "string | null",
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
- `NOT_FOUND`: 국가를 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/nations
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
  "ntn_nm": "string (required)",
  "ntn_type": "string (optional)",
  "logline": "string (optional)",
  "capital_nm": "string (optional)",
  "ruler_txt": "string (optional)",
  "pol_sys": "string (optional)",
  "admin_law": "string (optional)",
  "state_rlgn": "string (optional)",
  "rlgn_desc": "string (optional)",
  "nat_idlg": "string (optional)",
  "main_plcy": "string (optional)",
  "taboo_act": "string (optional)",
  "dipl_plcy": "string (optional)",
  "intr_cnfl": "string (optional)",
  "hidden_fact": "string (optional)",
  "econ_struct": "string (optional)",
  "soc_cltr": "string (optional)",
  "mil_pwr": "string (optional)",
  "hist_desc": "string (optional)",
  "curr_issue": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "ntn_no": "number",
    "prj_no": "number",
    "ntn_nm": "string",
    "ntn_type": "string | null",
    "logline": "string | null",
    "capital_nm": "string | null",
    "ruler_txt": "string | null",
    "pol_sys": "string | null",
    "admin_law": "string | null",
    "state_rlgn": "string | null",
    "rlgn_desc": "string | null",
    "nat_idlg": "string | null",
    "main_plcy": "string | null",
    "taboo_act": "string | null",
    "dipl_plcy": "string | null",
    "intr_cnfl": "string | null",
    "hidden_fact": "string | null",
    "econ_struct": "string | null",
    "soc_cltr": "string | null",
    "mil_pwr": "string | null",
    "hist_desc": "string | null",
    "curr_issue": "string | null",
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
PATCH /projects/:prjNo/nations/:ntnNo
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
- `ntnNo`: number (required) - 국가 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "ntn_nm": "string (optional)",
  "ntn_type": "string (optional)",
  "logline": "string (optional)",
  "capital_nm": "string (optional)",
  "ruler_txt": "string (optional)",
  "pol_sys": "string (optional)",
  "admin_law": "string (optional)",
  "state_rlgn": "string (optional)",
  "rlgn_desc": "string (optional)",
  "nat_idlg": "string (optional)",
  "main_plcy": "string (optional)",
  "taboo_act": "string (optional)",
  "dipl_plcy": "string (optional)",
  "intr_cnfl": "string (optional)",
  "hidden_fact": "string (optional)",
  "econ_struct": "string (optional)",
  "soc_cltr": "string (optional)",
  "mil_pwr": "string (optional)",
  "hist_desc": "string (optional)",
  "curr_issue": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "ntn_no": "number",
    "prj_no": "number",
    "ntn_nm": "string",
    "ntn_type": "string | null",
    "logline": "string | null",
    "capital_nm": "string | null",
    "ruler_txt": "string | null",
    "pol_sys": "string | null",
    "admin_law": "string | null",
    "state_rlgn": "string | null",
    "rlgn_desc": "string | null",
    "nat_idlg": "string | null",
    "main_plcy": "string | null",
    "taboo_act": "string | null",
    "dipl_plcy": "string | null",
    "intr_cnfl": "string | null",
    "hidden_fact": "string | null",
    "econ_struct": "string | null",
    "soc_cltr": "string | null",
    "mil_pwr": "string | null",
    "hist_desc": "string | null",
    "curr_issue": "string | null",
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
- `NOT_FOUND`: 국가를 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/nations/:ntnNo
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
- `ntnNo`: number (required) - 국가 번호

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
- `NOT_FOUND`: 국가를 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

## 26. 국가-특성 매핑 (Nation Trait Maps)

### 목록 조회
```
GET /projects/:prjNo/nations/:ntnNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (국가가 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `ntnNo`: number (required) - 국가 번호

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
        "ntn_no": "number",
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
GET /projects/:prjNo/nations/:ntnNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (국가가 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `ntnNo`: number (required) - 국가 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "ntn_no": "number",
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
POST /projects/:prjNo/nations/:ntnNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (국가 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `ntnNo`: number (required) - 국가 번호

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
    "ntn_no": "number",
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
- `NOT_OWNER`: 국가 소유자가 아님
- `TRAIT_NOT_FOUND`: 특성을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/nations/:ntnNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (국가 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `ntnNo`: number (required) - 국가 번호
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
    "ntn_no": "number",
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
- `NOT_OWNER`: 국가 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/nations/:ntnNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (국가 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `ntnNo`: number (required) - 국가 번호
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
- `NOT_OWNER`: 국가 소유자가 아님

---

## 12. 조직 (Organizations)

### 목록 조회
```
GET /projects/:prjNo/organizations
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 조직(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `org_no_list`: string (optional) - 조직 번호 리스트 (쉼표로 구분)
- `org_type`: string (optional) - 조직 유형 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "org_no": "number",
        "prj_no": "number",
        "org_nm": "string",
        "org_type": "string | null",
        "logline": "string | null",
        "org_theme": "string | null",
        "purp_pub": "string | null",
        "purp_hid": "string | null",
        "fnd_bg": "string | null",
        "org_strc": "string | null",
        "org_scale": "string | null",
        "join_cond": "string | null",
        "exit_rule": "string | null",
        "main_act": "string | null",
        "act_area": "string | null",
        "act_mthd": "string | null",
        "fund_src": "string | null",
        "key_fig": "string | null",
        "hist_desc": "string | null",
        "curr_stat": "string | null",
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
GET /projects/:prjNo/organizations/:orgNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 조직(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `orgNo`: number (required) - 조직 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "org_no": "number",
    "prj_no": "number",
    "org_nm": "string",
    "org_type": "string | null",
    "logline": "string | null",
    "org_theme": "string | null",
    "purp_pub": "string | null",
    "purp_hid": "string | null",
    "fnd_bg": "string | null",
    "org_strc": "string | null",
    "org_scale": "string | null",
    "join_cond": "string | null",
    "exit_rule": "string | null",
    "main_act": "string | null",
    "act_area": "string | null",
    "act_mthd": "string | null",
    "fund_src": "string | null",
    "key_fig": "string | null",
    "hist_desc": "string | null",
    "curr_stat": "string | null",
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
- `NOT_FOUND`: 조직을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/organizations
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
  "org_nm": "string (required)",
  "org_type": "string (optional)",
  "logline": "string (optional)",
  "org_theme": "string (optional)",
  "purp_pub": "string (optional)",
  "purp_hid": "string (optional)",
  "fnd_bg": "string (optional)",
  "org_strc": "string (optional)",
  "org_scale": "string (optional)",
  "join_cond": "string (optional)",
  "exit_rule": "string (optional)",
  "main_act": "string (optional)",
  "act_area": "string (optional)",
  "act_mthd": "string (optional)",
  "fund_src": "string (optional)",
  "key_fig": "string (optional)",
  "hist_desc": "string (optional)",
  "curr_stat": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "org_no": "number",
    "prj_no": "number",
    "org_nm": "string",
    "org_type": "string | null",
    "logline": "string | null",
    "org_theme": "string | null",
    "purp_pub": "string | null",
    "purp_hid": "string | null",
    "fnd_bg": "string | null",
    "org_strc": "string | null",
    "org_scale": "string | null",
    "join_cond": "string | null",
    "exit_rule": "string | null",
    "main_act": "string | null",
    "act_area": "string | null",
    "act_mthd": "string | null",
    "fund_src": "string | null",
    "key_fig": "string | null",
    "hist_desc": "string | null",
    "curr_stat": "string | null",
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
PATCH /projects/:prjNo/organizations/:orgNo
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
- `orgNo`: number (required) - 조직 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "org_nm": "string (optional)",
  "org_type": "string (optional)",
  "logline": "string (optional)",
  "org_theme": "string (optional)",
  "purp_pub": "string (optional)",
  "purp_hid": "string (optional)",
  "fnd_bg": "string (optional)",
  "org_strc": "string (optional)",
  "org_scale": "string (optional)",
  "join_cond": "string (optional)",
  "exit_rule": "string (optional)",
  "main_act": "string (optional)",
  "act_area": "string (optional)",
  "act_mthd": "string (optional)",
  "fund_src": "string (optional)",
  "key_fig": "string (optional)",
  "hist_desc": "string (optional)",
  "curr_stat": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "org_no": "number",
    "prj_no": "number",
    "org_nm": "string",
    "org_type": "string | null",
    "logline": "string | null",
    "org_theme": "string | null",
    "purp_pub": "string | null",
    "purp_hid": "string | null",
    "fnd_bg": "string | null",
    "org_strc": "string | null",
    "org_scale": "string | null",
    "join_cond": "string | null",
    "exit_rule": "string | null",
    "main_act": "string | null",
    "act_area": "string | null",
    "act_mthd": "string | null",
    "fund_src": "string | null",
    "key_fig": "string | null",
    "hist_desc": "string | null",
    "curr_stat": "string | null",
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
- `NOT_FOUND`: 조직을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/organizations/:orgNo
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
- `orgNo`: number (required) - 조직 번호

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
- `NOT_FOUND`: 조직을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

## 27. 조직-특성 매핑 (Organization Trait Maps)

### 목록 조회
```
GET /projects/:prjNo/organizations/:orgNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (조직이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `orgNo`: number (required) - 조직 번호

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
        "org_no": "number",
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
GET /projects/:prjNo/organizations/:orgNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (조직이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `orgNo`: number (required) - 조직 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "org_no": "number",
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
POST /projects/:prjNo/organizations/:orgNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (조직 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `orgNo`: number (required) - 조직 번호

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
    "org_no": "number",
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
- `NOT_OWNER`: 조직 소유자가 아님
- `TRAIT_NOT_FOUND`: 특성을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/organizations/:orgNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (조직 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `orgNo`: number (required) - 조직 번호
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
    "org_no": "number",
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
- `NOT_OWNER`: 조직 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/organizations/:orgNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (조직 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `orgNo`: number (required) - 조직 번호
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
- `NOT_OWNER`: 조직 소유자가 아님

---

## 31. 집단 관계 (Group Relations)

집단 관계는 국가(Nations)와 조직(Organizations) 간의 관계를 관리합니다. 다형 참조를 사용하여 국가-국가, 국가-조직, 조직-조직 간의 다양한 관계를 표현할 수 있습니다.

### 목록 조회
```
GET /projects/:prjNo/group-relations
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (관계에 포함된 집단 중 하나라도 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `rel_no_list`: string (optional) - 관계 번호 리스트 (쉼표로 구분)
- `src_type`: string (optional) - 주체 유형 필터 (NTN: 국가, ORG: 조직)
- `src_no`: number (optional) - 주체 번호 필터
- `trgt_type`: string (optional) - 대상 유형 필터 (NTN: 국가, ORG: 조직)
- `trgt_no`: number (optional) - 대상 번호 필터
- `rel_type`: string (optional) - 관계 유형 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "rel_no": "number",
        "prj_no": "number",
        "src_type": "string",
        "src_no": "number",
        "trgt_type": "string",
        "trgt_no": "number",
        "rel_type": "string",
        "rel_desc": "string | null",
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
GET /projects/:prjNo/group-relations/:relNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (관계에 포함된 집단 중 하나라도 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `relNo`: number (required) - 관계 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "prj_no": "number",
    "src_type": "string",
    "src_no": "number",
    "trgt_type": "string",
    "trgt_no": "number",
    "rel_type": "string",
    "rel_desc": "string | null",
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
- `NOT_FOUND`: 관계를 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/group-relations
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
  "src_type": "string (required, 'NTN' | 'ORG')",
  "src_no": "number (required)",
  "trgt_type": "string (required, 'NTN' | 'ORG')",
  "trgt_no": "number (required)",
  "rel_type": "string (required)",
  "rel_desc": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "prj_no": "number",
    "src_type": "string",
    "src_no": "number",
    "trgt_type": "string",
    "trgt_no": "number",
    "rel_type": "string",
    "rel_desc": "string | null",
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
- `SOURCE_NOT_FOUND`: 주체를 찾을 수 없음
- `TARGET_NOT_FOUND`: 대상을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/group-relations/:relNo
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
- `relNo`: number (required) - 관계 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "rel_type": "string (optional)",
  "rel_desc": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "prj_no": "number",
    "src_type": "string",
    "src_no": "number",
    "trgt_type": "string",
    "trgt_no": "number",
    "rel_type": "string",
    "rel_desc": "string | null",
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
- `NOT_FOUND`: 관계를 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/group-relations/:relNo
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
- `relNo`: number (required) - 관계 번호

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
- `NOT_FOUND`: 관계를 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---
