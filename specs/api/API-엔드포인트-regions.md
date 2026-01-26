# 지역 API 엔드포인트

이 문서는 Fantasy Builder API의 지역(Regions) 관련 엔드포인트를 정의합니다.

## 10. 지역 (Regions)

### 목록 조회
```
GET /projects/:prjNo/regions
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 지역(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `region_no_list`: string (optional) - 지역 번호 리스트 (쉼표로 구분)
- `up_region_no`: number (optional) - 상위 지역 번호 필터
- `region_type`: string (optional) - 지역 유형 필터
- `explor_stat`: string (optional) - 탐사 상태 필터
- `danger_lvl`: string (optional) - 위험도 등급 필터
- `ntn_no`: number (optional) - 소속 국가 번호 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "region_no": "number",
        "prj_no": "number",
        "up_region_no": "number | null",
        "region_nm": "string",
        "region_type": "string | null",
        "explor_stat": "string | null",
        "region_expln": "string | null",
        "loc_desc": "string | null",
        "climate_env": "string | null",
        "terrain_feat": "string | null",
        "env_spec": "string | null",
        "func_feat": "string | null",
        "danger_lvl": "string | null",
        "danger_fctr": "string | null",
        "inhabit_info": "string | null",
        "unknown_entity": "string | null",
        "main_fclty": "string | null",
        "rsrc_list": "string | null",
        "ntn_no": "number | null",
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
GET /projects/:prjNo/regions/:regionNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 지역(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `regionNo`: number (required) - 지역 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "region_no": "number",
    "prj_no": "number",
    "up_region_no": "number | null",
    "region_nm": "string",
    "region_type": "string | null",
    "explor_stat": "string | null",
    "region_expln": "string | null",
    "loc_desc": "string | null",
    "climate_env": "string | null",
    "terrain_feat": "string | null",
    "env_spec": "string | null",
    "func_feat": "string | null",
    "danger_lvl": "string | null",
    "danger_fctr": "string | null",
    "inhabit_info": "string | null",
    "unknown_entity": "string | null",
    "main_fclty": "string | null",
    "rsrc_list": "string | null",
    "ntn_no": "number | null",
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
- `NOT_FOUND`: 지역을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/regions
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
  "up_region_no": "number (optional)",
  "region_nm": "string (required)",
  "region_type": "string (optional)",
  "explor_stat": "string (optional)",
  "region_expln": "string (optional)",
  "loc_desc": "string (optional)",
  "climate_env": "string (optional)",
  "terrain_feat": "string (optional)",
  "env_spec": "string (optional)",
  "func_feat": "string (optional)",
  "danger_lvl": "string (optional)",
  "danger_fctr": "string (optional)",
  "inhabit_info": "string (optional)",
  "unknown_entity": "string (optional)",
  "main_fclty": "string (optional)",
  "rsrc_list": "string (optional)",
  "ntn_no": "number (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "region_no": "number",
    "prj_no": "number",
    "up_region_no": "number | null",
    "region_nm": "string",
    "region_type": "string | null",
    "explor_stat": "string | null",
    "region_expln": "string | null",
    "loc_desc": "string | null",
    "climate_env": "string | null",
    "terrain_feat": "string | null",
    "env_spec": "string | null",
    "func_feat": "string | null",
    "danger_lvl": "string | null",
    "danger_fctr": "string | null",
    "inhabit_info": "string | null",
    "unknown_entity": "string | null",
    "main_fclty": "string | null",
    "rsrc_list": "string | null",
    "ntn_no": "number | null",
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
PATCH /projects/:prjNo/regions/:regionNo
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
- `regionNo`: number (required) - 지역 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "up_region_no": "number (optional)",
  "region_nm": "string (optional)",
  "region_type": "string (optional)",
  "explor_stat": "string (optional)",
  "region_expln": "string (optional)",
  "loc_desc": "string (optional)",
  "climate_env": "string (optional)",
  "terrain_feat": "string (optional)",
  "env_spec": "string (optional)",
  "func_feat": "string (optional)",
  "danger_lvl": "string (optional)",
  "danger_fctr": "string (optional)",
  "inhabit_info": "string (optional)",
  "unknown_entity": "string (optional)",
  "main_fclty": "string (optional)",
  "rsrc_list": "string (optional)",
  "ntn_no": "number (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "region_no": "number",
    "prj_no": "number",
    "up_region_no": "number | null",
    "region_nm": "string",
    "region_type": "string | null",
    "explor_stat": "string | null",
    "region_expln": "string | null",
    "loc_desc": "string | null",
    "climate_env": "string | null",
    "terrain_feat": "string | null",
    "env_spec": "string | null",
    "func_feat": "string | null",
    "danger_lvl": "string | null",
    "danger_fctr": "string | null",
    "inhabit_info": "string | null",
    "unknown_entity": "string | null",
    "main_fclty": "string | null",
    "rsrc_list": "string | null",
    "ntn_no": "number | null",
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
- `NOT_FOUND`: 지역을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/regions/:regionNo
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
- `regionNo`: number (required) - 지역 번호

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
- `NOT_FOUND`: 지역을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

## 25. 지역-특성 매핑 (Region Trait Maps)

### 목록 조회
```
GET /projects/:prjNo/regions/:regionNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (지역이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `regionNo`: number (required) - 지역 번호

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
        "region_no": "number",
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
GET /projects/:prjNo/regions/:regionNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (지역이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `regionNo`: number (required) - 지역 번호
- `mapNo`: number (required) - 매핑 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "map_no": "number",
    "region_no": "number",
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
POST /projects/:prjNo/regions/:regionNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (지역 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `regionNo`: number (required) - 지역 번호

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
    "region_no": "number",
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
- `NOT_OWNER`: 지역 소유자가 아님
- `TRAIT_NOT_FOUND`: 특성을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/regions/:regionNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (지역 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `regionNo`: number (required) - 지역 번호
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
    "region_no": "number",
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
- `NOT_OWNER`: 지역 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/regions/:regionNo/traits/:mapNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (지역 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `regionNo`: number (required) - 지역 번호
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
- `NOT_OWNER`: 지역 소유자가 아님

---
