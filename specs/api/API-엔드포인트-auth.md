# 인증 API 엔드포인트

이 문서는 Fantasy Builder API의 인증 관련 엔드포인트를 정의합니다.

## 0. 인증 (Authentication)

### 로그인
```
POST /auth/signin
Status: 200
```

**Authentication:**
- Required: No (Public)
- Roles: ALL

**Authorization:**
- Read: Public
- Write: Public

**Request Body:**
```json
{
  "user_eml": "string (required)",
  "enpswd": "string (required)"
}
```

**Response (Success):**
```json
{
  "data": {
    "user_no": "number",
    "user_eml": "string",
    "user_nm": "string | null",
    "user_role": "ADMIN | USER",
    "profl_img_url": "string | null",
    "biogp": "string | null",
    "enpswd": null,
    "resh_token": null,
    "acnt_lck_yn": "string",
    "lgn_fail_nmtm": "number",
    "last_lgn_dt": "string (datetime) | null",
    "last_lgn_ip": "string | null",
    "last_pswd_chg_dt": "string (datetime) | null",
    "eml_auth_yn": "string",
    "mkt_recp_agre_yn": "string",
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
  "message": "로그인에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "INVALID_CREDENTIALS",
  "message": "이메일 또는 비밀번호가 올바르지 않습니다."
}
```

---

### 로그아웃
```
POST /auth/signout
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Public
- Write: Owner only

**Request Headers:**
```
Authorization: Bearer {token}
```

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "로그아웃에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "UNAUTHORIZED",
  "message": "인증이 필요합니다."
}
```

---

### 회원가입
```
POST /auth/signup
Status: 200
```

**Authentication:**
- Required: No (Public)
- Roles: ALL

**Authorization:**
- Read: Public
- Write: Public

**Request Body:**
```json
{
  "user_eml": "string (required)",
  "enpswd": "string (required)",
  "user_nm": "string (optional)",
  "user_role": "ADMIN | USER (optional, default: USER)",
  "profl_img_url": "string (optional)",
  "biogp": "string (optional)",
  "mkt_recp_agre_yn": "string (optional, default: N)"
}
```

**Response (Success):**
```json
{
  "data": {
    "user_no": "number",
    "user_eml": "string",
    "user_nm": "string | null",
    "user_role": "ADMIN | USER",
    "profl_img_url": "string | null",
    "biogp": "string | null",
    "enpswd": null,
    "resh_token": null,
    "acnt_lck_yn": "string",
    "lgn_fail_nmtm": "number",
    "last_lgn_dt": "string (datetime) | null",
    "last_lgn_ip": "string | null",
    "last_pswd_chg_dt": "string (datetime) | null",
    "eml_auth_yn": "string",
    "mkt_recp_agre_yn": "string",
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
  "message": "회원가입에 성공했습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "EMAIL_ALREADY_EXISTS",
  "message": "이미 사용 중인 이메일입니다."
}
```

---

### 비밀번호 재설정 요청
```
POST /auth/forgot-password
Status: 200
```

**Authentication:**
- Required: No (Public)
- Roles: ALL

**Authorization:**
- Read: Public
- Write: Public

**Request Body:**
```json
{
  "user_eml": "string (required)"
}
```

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "비밀번호 재설정 링크가 이메일로 전송되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "USER_NOT_FOUND",
  "message": "해당 이메일로 등록된 사용자를 찾을 수 없습니다."
}
```

---

### 비밀번호 재설정
```
POST /auth/reset-password
Status: 200
```

**Authentication:**
- Required: No (Public)
- Roles: ALL

**Authorization:**
- Read: Public
- Write: Public

**Request Body:**
```json
{
  "token": "string (required)",
  "new_password": "string (required)"
}
```

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "비밀번호가 성공적으로 재설정되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "INVALID_TOKEN",
  "message": "유효하지 않거나 만료된 토큰입니다."
}
```

---

### 비밀번호 변경
```
POST /auth/change-password
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Public
- Write: Owner only

**Request Headers:**
```
Authorization: Bearer {token}
```

**Request Body:**
```json
{
  "current_password": "string (required)",
  "new_password": "string (required)"
}
```

**Response (Success):**
```json
{
  "data": null,
  "error": false,
  "code": "OK",
  "message": "비밀번호가 성공적으로 변경되었습니다."
}
```

**Response (Error):**
```json
{
  "data": null,
  "error": true,
  "code": "INVALID_PASSWORD",
  "message": "현재 비밀번호가 올바르지 않습니다."
}
```
