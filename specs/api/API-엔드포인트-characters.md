# 인물 API 엔드포인트

이 문서는 Fantasy Builder API의 인물(Characters) 관련 엔드포인트를 정의합니다.

## 8. 인물 (Characters)

### 목록 조회
```
GET /projects/:prjNo/characters
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 인물(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `search_type`: string (optional) - 검색 유형
- `search_keyword`: string (optional) - 검색 키워드
- `char_no_list`: string (optional) - 인물 번호 리스트 (쉼표로 구분)
- `role_type`: string (optional) - 역할 유형 필터
- `race_no`: number (optional) - 종족 번호 필터
- `ntn_no`: number (optional) - 국가 번호 필터
- `org_no`: number (optional) - 조직 번호 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "char_no": "number",
        "prj_no": "number",
        "char_nm": "string",
        "alias_nm": "string | null",
        "role_type": "string",
        "logline": "string | null",
        "narr_func": "string | null",
        "race_no": "number | null",
        "ntn_no": "number | null",
        "org_no": "number | null",
        "org_rank": "string | null",
        "origin_desc": "string | null",
        "join_rsn": "string | null",
        "org_rel_stat": "string | null",
        "real_age": "string | null",
        "app_age": "string | null",
        "gender": "string | null",
        "sex_orient": "string | null",
        "sex_pref": "string | null",
        "height_val": "string | null",
        "weight_val": "string | null",
        "body_desc": "string | null",
        "health_stat": "string | null",
        "dsbl_desc": "string | null",
        "visual_pnt": "string | null",
        "fst_impr": "string | null",
        "mslw_lv1_phys": "string | null",
        "mslw_lv2_safe": "string | null",
        "mslw_lv3_soc": "string | null",
        "mslw_lv4_estm": "string | null",
        "mslw_lv5_self": "string | null",
        "like_list": "string | null",
        "hate_list": "string | null",
        "align_ord": "string | null",
        "align_moral": "string | null",
        "core_val": "string | null",
        "val_cnfl": "string | null",
        "world_view": "string | null",
        "pers_pos": "string | null",
        "pers_neg": "string | null",
        "main_emot": "string | null",
        "tone_type": "string | null",
        "soc_mthd": "string | null",
        "habit_desc": "string | null",
        "sign_line": "string | null",
        "emot_expr_json": "string | null",
        "core_desire": "string | null",
        "core_dfcn": "string | null",
        "core_taboo": "string | null",
        "goal_short": "string | null",
        "obstacle": "string | null",
        "exp_cost": "string | null",
        "rule_abandon": "string | null",
        "rule_keep": "string | null",
        "moral_accept": "string | null",
        "moral_reject": "string | null",
        "cnfl_trgr": "string | null",
        "emot_accum": "string | null",
        "expl_type": "string | null",
        "self_perc": "string | null",
        "ext_perc": "string | null",
        "secret_json": "string | null",
        "trma_core": "string | null",
        "trma_evnt": "string | null",
        "false_blf": "string | null",
        "main_fear": "string | null",
        "trma_trgr": "string | null",
        "cmbt_styl": "string | null",
        "cmbt_str": "string | null",
        "cmbt_weak": "string | null",
        "abil_cost": "string | null",
        "arc_start": "string | null",
        "arc_end": "string | null",
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
GET /projects/:prjNo/characters/:charNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (공유된 인물(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "char_no": "number",
    "prj_no": "number",
    "char_nm": "string",
    "alias_nm": "string | null",
    "role_type": "string",
    "logline": "string | null",
    "narr_func": "string | null",
    "race_no": "number | null",
    "ntn_no": "number | null",
    "org_no": "number | null",
    "org_rank": "string | null",
    "origin_desc": "string | null",
    "join_rsn": "string | null",
    "org_rel_stat": "string | null",
    "real_age": "string | null",
    "app_age": "string | null",
    "gender": "string | null",
    "sex_orient": "string | null",
    "sex_pref": "string | null",
    "height_val": "string | null",
    "weight_val": "string | null",
    "body_desc": "string | null",
    "health_stat": "string | null",
    "dsbl_desc": "string | null",
    "visual_pnt": "string | null",
    "fst_impr": "string | null",
    "mslw_lv1_phys": "string | null",
    "mslw_lv2_safe": "string | null",
    "mslw_lv3_soc": "string | null",
    "mslw_lv4_estm": "string | null",
    "mslw_lv5_self": "string | null",
    "like_list": "string | null",
    "hate_list": "string | null",
    "align_ord": "string | null",
    "align_moral": "string | null",
    "core_val": "string | null",
    "val_cnfl": "string | null",
    "world_view": "string | null",
    "pers_pos": "string | null",
    "pers_neg": "string | null",
    "main_emot": "string | null",
    "tone_type": "string | null",
    "soc_mthd": "string | null",
    "habit_desc": "string | null",
    "sign_line": "string | null",
    "emot_expr_json": "string | null",
    "core_desire": "string | null",
    "core_dfcn": "string | null",
    "core_taboo": "string | null",
    "goal_short": "string | null",
    "obstacle": "string | null",
    "exp_cost": "string | null",
    "rule_abandon": "string | null",
    "rule_keep": "string | null",
    "moral_accept": "string | null",
    "moral_reject": "string | null",
    "cnfl_trgr": "string | null",
    "emot_accum": "string | null",
    "expl_type": "string | null",
    "self_perc": "string | null",
    "ext_perc": "string | null",
    "secret_json": "string | null",
    "trma_core": "string | null",
    "trma_evnt": "string | null",
    "false_blf": "string | null",
    "main_fear": "string | null",
    "trma_trgr": "string | null",
    "cmbt_styl": "string | null",
    "cmbt_str": "string | null",
    "cmbt_weak": "string | null",
    "abil_cost": "string | null",
    "arc_start": "string | null",
    "arc_end": "string | null",
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
- `NOT_FOUND`: 인물을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/characters
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
  "char_nm": "string (required)",
  "role_type": "string (required)",
  "alias_nm": "string (optional)",
  "logline": "string (optional)",
  "narr_func": "string (optional)",
  "race_no": "number (optional)",
  "ntn_no": "number (optional)",
  "org_no": "number (optional)",
  "org_rank": "string (optional)",
  "origin_desc": "string (optional)",
  "join_rsn": "string (optional)",
  "org_rel_stat": "string (optional)",
  "real_age": "string (optional)",
  "app_age": "string (optional)",
  "gender": "string (optional)",
  "sex_orient": "string (optional)",
  "sex_pref": "string (optional)",
  "height_val": "string (optional)",
  "weight_val": "string (optional)",
  "body_desc": "string (optional)",
  "health_stat": "string (optional)",
  "dsbl_desc": "string (optional)",
  "visual_pnt": "string (optional)",
  "fst_impr": "string (optional)",
  "mslw_lv1_phys": "string (optional)",
  "mslw_lv2_safe": "string (optional)",
  "mslw_lv3_soc": "string (optional)",
  "mslw_lv4_estm": "string (optional)",
  "mslw_lv5_self": "string (optional)",
  "like_list": "string (optional)",
  "hate_list": "string (optional)",
  "align_ord": "string (optional)",
  "align_moral": "string (optional)",
  "core_val": "string (optional)",
  "val_cnfl": "string (optional)",
  "world_view": "string (optional)",
  "pers_pos": "string (optional)",
  "pers_neg": "string (optional)",
  "main_emot": "string (optional)",
  "tone_type": "string (optional)",
  "soc_mthd": "string (optional)",
  "habit_desc": "string (optional)",
  "sign_line": "string (optional)",
  "emot_expr_json": "string (optional)",
  "core_desire": "string (optional)",
  "core_dfcn": "string (optional)",
  "core_taboo": "string (optional)",
  "goal_short": "string (optional)",
  "obstacle": "string (optional)",
  "exp_cost": "string (optional)",
  "rule_abandon": "string (optional)",
  "rule_keep": "string (optional)",
  "moral_accept": "string (optional)",
  "moral_reject": "string (optional)",
  "cnfl_trgr": "string (optional)",
  "emot_accum": "string (optional)",
  "expl_type": "string (optional)",
  "self_perc": "string (optional)",
  "ext_perc": "string (optional)",
  "secret_json": "string (optional)",
  "trma_core": "string (optional)",
  "trma_evnt": "string (optional)",
  "false_blf": "string (optional)",
  "main_fear": "string (optional)",
  "trma_trgr": "string (optional)",
  "cmbt_styl": "string (optional)",
  "cmbt_str": "string (optional)",
  "cmbt_weak": "string (optional)",
  "abil_cost": "string (optional)",
  "arc_start": "string (optional)",
  "arc_end": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional, default: 'N')"
}
```

**Response (Success):**
```json
{
  "data": {
    "char_no": "number",
    "prj_no": "number",
    "char_nm": "string",
    "alias_nm": "string | null",
    "role_type": "string",
    "logline": "string | null",
    "narr_func": "string | null",
    "race_no": "number | null",
    "ntn_no": "number | null",
    "org_no": "number | null",
    "org_rank": "string | null",
    "origin_desc": "string | null",
    "join_rsn": "string | null",
    "org_rel_stat": "string | null",
    "real_age": "string | null",
    "app_age": "string | null",
    "gender": "string | null",
    "sex_orient": "string | null",
    "sex_pref": "string | null",
    "height_val": "string | null",
    "weight_val": "string | null",
    "body_desc": "string | null",
    "health_stat": "string | null",
    "dsbl_desc": "string | null",
    "visual_pnt": "string | null",
    "fst_impr": "string | null",
    "mslw_lv1_phys": "string | null",
    "mslw_lv2_safe": "string | null",
    "mslw_lv3_soc": "string | null",
    "mslw_lv4_estm": "string | null",
    "mslw_lv5_self": "string | null",
    "like_list": "string | null",
    "hate_list": "string | null",
    "align_ord": "string | null",
    "align_moral": "string | null",
    "core_val": "string | null",
    "val_cnfl": "string | null",
    "world_view": "string | null",
    "pers_pos": "string | null",
    "pers_neg": "string | null",
    "main_emot": "string | null",
    "tone_type": "string | null",
    "soc_mthd": "string | null",
    "habit_desc": "string | null",
    "sign_line": "string | null",
    "emot_expr_json": "string | null",
    "core_desire": "string | null",
    "core_dfcn": "string | null",
    "core_taboo": "string | null",
    "goal_short": "string | null",
    "obstacle": "string | null",
    "exp_cost": "string | null",
    "rule_abandon": "string | null",
    "rule_keep": "string | null",
    "moral_accept": "string | null",
    "moral_reject": "string | null",
    "cnfl_trgr": "string | null",
    "emot_accum": "string | null",
    "expl_type": "string | null",
    "self_perc": "string | null",
    "ext_perc": "string | null",
    "secret_json": "string | null",
    "trma_core": "string | null",
    "trma_evnt": "string | null",
    "false_blf": "string | null",
    "main_fear": "string | null",
    "trma_trgr": "string | null",
    "cmbt_styl": "string | null",
    "cmbt_str": "string | null",
    "cmbt_weak": "string | null",
    "abil_cost": "string | null",
    "arc_start": "string | null",
    "arc_end": "string | null",
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
PATCH /projects/:prjNo/characters/:charNo
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
- `charNo`: number (required) - 인물 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "char_nm": "string (optional)",
  "role_type": "string (optional)",
  "alias_nm": "string (optional)",
  "logline": "string (optional)",
  "narr_func": "string (optional)",
  "race_no": "number (optional)",
  "ntn_no": "number (optional)",
  "org_no": "number (optional)",
  "org_rank": "string (optional)",
  "origin_desc": "string (optional)",
  "join_rsn": "string (optional)",
  "org_rel_stat": "string (optional)",
  "real_age": "string (optional)",
  "app_age": "string (optional)",
  "gender": "string (optional)",
  "sex_orient": "string (optional)",
  "sex_pref": "string (optional)",
  "height_val": "string (optional)",
  "weight_val": "string (optional)",
  "body_desc": "string (optional)",
  "health_stat": "string (optional)",
  "dsbl_desc": "string (optional)",
  "visual_pnt": "string (optional)",
  "fst_impr": "string (optional)",
  "mslw_lv1_phys": "string (optional)",
  "mslw_lv2_safe": "string (optional)",
  "mslw_lv3_soc": "string (optional)",
  "mslw_lv4_estm": "string (optional)",
  "mslw_lv5_self": "string (optional)",
  "like_list": "string (optional)",
  "hate_list": "string (optional)",
  "align_ord": "string (optional)",
  "align_moral": "string (optional)",
  "core_val": "string (optional)",
  "val_cnfl": "string (optional)",
  "world_view": "string (optional)",
  "pers_pos": "string (optional)",
  "pers_neg": "string (optional)",
  "main_emot": "string (optional)",
  "tone_type": "string (optional)",
  "soc_mthd": "string (optional)",
  "habit_desc": "string (optional)",
  "sign_line": "string (optional)",
  "emot_expr_json": "string (optional)",
  "core_desire": "string (optional)",
  "core_dfcn": "string (optional)",
  "core_taboo": "string (optional)",
  "goal_short": "string (optional)",
  "obstacle": "string (optional)",
  "exp_cost": "string (optional)",
  "rule_abandon": "string (optional)",
  "rule_keep": "string (optional)",
  "moral_accept": "string (optional)",
  "moral_reject": "string (optional)",
  "cnfl_trgr": "string (optional)",
  "emot_accum": "string (optional)",
  "expl_type": "string (optional)",
  "self_perc": "string (optional)",
  "ext_perc": "string (optional)",
  "secret_json": "string (optional)",
  "trma_core": "string (optional)",
  "trma_evnt": "string (optional)",
  "false_blf": "string (optional)",
  "main_fear": "string (optional)",
  "trma_trgr": "string (optional)",
  "cmbt_styl": "string (optional)",
  "cmbt_str": "string (optional)",
  "cmbt_weak": "string (optional)",
  "abil_cost": "string (optional)",
  "arc_start": "string (optional)",
  "arc_end": "string (optional)",
  "rmk": "string (optional)",
  "shrn_yn": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "char_no": "number",
    "prj_no": "number",
    "char_nm": "string",
    "alias_nm": "string | null",
    "role_type": "string",
    "logline": "string | null",
    "narr_func": "string | null",
    "race_no": "number | null",
    "ntn_no": "number | null",
    "org_no": "number | null",
    "org_rank": "string | null",
    "origin_desc": "string | null",
    "join_rsn": "string | null",
    "org_rel_stat": "string | null",
    "real_age": "string | null",
    "app_age": "string | null",
    "gender": "string | null",
    "sex_orient": "string | null",
    "sex_pref": "string | null",
    "height_val": "string | null",
    "weight_val": "string | null",
    "body_desc": "string | null",
    "health_stat": "string | null",
    "dsbl_desc": "string | null",
    "visual_pnt": "string | null",
    "fst_impr": "string | null",
    "mslw_lv1_phys": "string | null",
    "mslw_lv2_safe": "string | null",
    "mslw_lv3_soc": "string | null",
    "mslw_lv4_estm": "string | null",
    "mslw_lv5_self": "string | null",
    "like_list": "string | null",
    "hate_list": "string | null",
    "align_ord": "string | null",
    "align_moral": "string | null",
    "core_val": "string | null",
    "val_cnfl": "string | null",
    "world_view": "string | null",
    "pers_pos": "string | null",
    "pers_neg": "string | null",
    "main_emot": "string | null",
    "tone_type": "string | null",
    "soc_mthd": "string | null",
    "habit_desc": "string | null",
    "sign_line": "string | null",
    "emot_expr_json": "string | null",
    "core_desire": "string | null",
    "core_dfcn": "string | null",
    "core_taboo": "string | null",
    "goal_short": "string | null",
    "obstacle": "string | null",
    "exp_cost": "string | null",
    "rule_abandon": "string | null",
    "rule_keep": "string | null",
    "moral_accept": "string | null",
    "moral_reject": "string | null",
    "cnfl_trgr": "string | null",
    "emot_accum": "string | null",
    "expl_type": "string | null",
    "self_perc": "string | null",
    "ext_perc": "string | null",
    "secret_json": "string | null",
    "trma_core": "string | null",
    "trma_evnt": "string | null",
    "false_blf": "string | null",
    "main_fear": "string | null",
    "trma_trgr": "string | null",
    "cmbt_styl": "string | null",
    "cmbt_str": "string | null",
    "cmbt_weak": "string | null",
    "abil_cost": "string | null",
    "arc_start": "string | null",
    "arc_end": "string | null",
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
- `NOT_FOUND`: 인물을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/characters/:charNo
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
- `charNo`: number (required) - 인물 번호

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
- `NOT_FOUND`: 인물을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 소유자가 아님

---

## 16. 인물-어빌리티 매핑 (Char Ability Maps)

### 목록 조회
```
GET /projects/:prjNo/characters/:charNo/abilitys
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

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
        "char_no": "number",
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
GET /projects/:prjNo/characters/:charNo/abilitys/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `abilityNo`: number (required) - 어빌리티 번호

**Query Parameters:**
- `ability_type`: string (required) - 어빌리티 타입 (GLOBAL, PROJECT)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "char_no": "number",
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
POST /projects/:prjNo/characters/:charNo/abilitys
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

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
    "char_no": "number",
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
- `NOT_OWNER`: 인물 소유자가 아님
- `SKILL_NOT_FOUND`: 어빌리티을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/characters/:charNo/abilitys/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `abilityNo`: number (required) - 어빌리티 번호

**Query Parameters:**
- `ability_type`: string (required) - 어빌리티 타입 (GLOBAL, PROJECT)

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
    "char_no": "number",
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
- `NOT_OWNER`: 인물 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/characters/:charNo/abilitys/:abilityNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `abilityNo`: number (required) - 어빌리티 번호

**Query Parameters:**
- `ability_type`: string (required) - 어빌리티 타입 (GLOBAL, PROJECT)

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
- `NOT_OWNER`: 인물 소유자가 아님

---

## 17. 인물-특성 매핑 (Char Trait Maps)

### 목록 조회
```
GET /projects/:prjNo/characters/:charNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

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
        "char_no": "number",
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
GET /projects/:prjNo/characters/:charNo/traits/:traitNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `traitNo`: number (required) - 특성 번호

**Query Parameters:**
- `trait_type`: string (required) - 특성 타입 (GLOBAL, PROJECT)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "char_no": "number",
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
POST /projects/:prjNo/characters/:charNo/traits
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

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
    "char_no": "number",
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
- `NOT_OWNER`: 인물 소유자가 아님
- `TRAIT_NOT_FOUND`: 특성을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/characters/:charNo/traits/:traitNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `traitNo`: number (required) - 특성 번호

**Query Parameters:**
- `trait_type`: string (required) - 특성 타입 (GLOBAL, PROJECT)

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
    "char_no": "number",
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
- `NOT_OWNER`: 인물 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/characters/:charNo/traits/:traitNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `traitNo`: number (required) - 특성 번호

**Query Parameters:**
- `trait_type`: string (required) - 특성 타입 (GLOBAL, PROJECT)

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
- `NOT_OWNER`: 인물 소유자가 아님

---

## 18. 인물-아이템 매핑 (Char Item Maps)

### 목록 조회
```
GET /projects/:prjNo/characters/:charNo/items
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `item_no_list`: string (optional) - 아이템 번호 리스트 (쉼표로 구분)
- `equip_yn`: string (optional) - 착용 여부 필터 (Y, N)

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "own_no": "number",
        "char_no": "number",
        "item_no": "number",
        "item_cnt": "number",
        "equip_yn": "string",
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
GET /projects/:prjNo/characters/:charNo/items/:ownNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `ownNo`: number (required) - 소유 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "own_no": "number",
    "char_no": "number",
    "item_no": "number",
    "item_cnt": "number",
    "equip_yn": "string",
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
- `NOT_FOUND`: 매핑을 찾을 수 없음
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_PUBLIC`: 비공개 리소스임

---

### 생성
```
POST /projects/:prjNo/characters/:charNo/items
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "item_no": "number (required)",
  "item_cnt": "number (optional, default: 1)",
  "equip_yn": "string (optional, default: 'N')",
  "rmk": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "own_no": "number",
    "char_no": "number",
    "item_no": "number",
    "item_cnt": "number",
    "equip_yn": "string",
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
- `NOT_OWNER`: 인물 소유자가 아님
- `ITEM_NOT_FOUND`: 아이템을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/characters/:charNo/items/:ownNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `ownNo`: number (required) - 소유 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "item_cnt": "number (optional)",
  "equip_yn": "string (optional)",
  "rmk": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "own_no": "number",
    "char_no": "number",
    "item_no": "number",
    "item_cnt": "number",
    "equip_yn": "string",
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
- `NOT_FOUND`: 매핑을 찾을 수 없음
- `VALIDATION_ERROR`: 요청 데이터 검증 실패
- `UNAUTHORIZED`: 인증이 필요함
- `FORBIDDEN`: 권한이 없음
- `NOT_OWNER`: 인물 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/characters/:charNo/items/:ownNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `ownNo`: number (required) - 소유 번호

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
- `NOT_OWNER`: 인물 소유자가 아님

---

## 19. 인물-인물 관계 (Char Relations)

### 목록 조회
```
GET /projects/:prjNo/characters/:charNo/relations
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `rel_type`: string (optional) - 관계 유형 필터
- `trgt_char_no`: number (optional) - 대상 인물 번호 필터

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "list": [
      {
        "rel_no": "number",
        "src_char_no": "number",
        "trgt_char_no": "number",
        "rel_type": "string",
        "rel_desc": "string | null",
        "intimacy_lvl": "number | null",
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
GET /projects/:prjNo/characters/:charNo/relations/:relNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `relNo`: number (required) - 관계 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "src_char_no": "number",
    "trgt_char_no": "number",
    "rel_type": "string",
    "rel_desc": "string | null",
    "intimacy_lvl": "number | null",
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
POST /projects/:prjNo/characters/:charNo/relations
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호 (주체 인물)

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "trgt_char_no": "number (required)",
  "rel_type": "string (required)",
  "rel_desc": "string (optional)",
  "intimacy_lvl": "number (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "src_char_no": "number",
    "trgt_char_no": "number",
    "rel_type": "string",
    "rel_desc": "string | null",
    "intimacy_lvl": "number | null",
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
- `NOT_OWNER`: 인물 소유자가 아님
- `CHARACTER_NOT_FOUND`: 대상 인물을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/characters/:charNo/relations/:relNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `relNo`: number (required) - 관계 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "rel_type": "string (optional)",
  "rel_desc": "string (optional)",
  "intimacy_lvl": "number (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "src_char_no": "number",
    "trgt_char_no": "number",
    "rel_type": "string",
    "rel_desc": "string | null",
    "intimacy_lvl": "number | null",
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
- `NOT_OWNER`: 인물 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/characters/:charNo/relations/:relNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
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
- `NOT_OWNER`: 인물 소유자가 아님

---

## 20. 인물-그룹 관계 (Char Group Relations)

### 목록 조회
```
GET /projects/:prjNo/characters/:charNo/group-relations
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

**Query Parameters:**
- `page_no`: number (optional, default: 1) - 페이지 번호
- `page_size`: number (optional, default: 10) - 페이지당 항목 수
- `trgt_ref_type`: string (optional) - 대상 유형 필터 (ORG, NTN)
- `trgt_ref_no`: number (optional) - 대상 식별자 필터
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
        "char_no": "number",
        "trgt_ref_type": "string",
        "trgt_ref_no": "number",
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
GET /projects/:prjNo/characters/:charNo/group-relations/:relNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: Owner or Public (인물이 공유된 경우(`shrn_yn = 'Y'`) 또는 소유자만 조회 가능)
- Write: N/A

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `relNo`: number (required) - 관계 번호

**Request Headers:**
- `Authorization`: string (optional) - Bearer 토큰 (인증된 요청의 경우)

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "char_no": "number",
    "trgt_ref_type": "string",
    "trgt_ref_no": "number",
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
POST /projects/:prjNo/characters/:charNo/group-relations
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 생성 가능, ADMIN은 모든 리소스 생성 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "trgt_ref_type": "string (required)",
  "trgt_ref_no": "number (required)",
  "rel_type": "string (required)",
  "rel_desc": "string (optional)"
}
```

**참고:**
- `trgt_ref_type`: "ORG" (조직) 또는 "NTN" (국가)

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "char_no": "number",
    "trgt_ref_type": "string",
    "trgt_ref_no": "number",
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
- `NOT_OWNER`: 인물 소유자가 아님
- `TARGET_NOT_FOUND`: 대상 그룹을 찾을 수 없음

---

### 수정
```
PATCH /projects/:prjNo/characters/:charNo/group-relations/:relNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 수정 가능, ADMIN은 모든 리소스 수정 가능)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
- `relNo`: number (required) - 관계 번호

**Request Headers:**
- `Authorization`: string (required) - Bearer 토큰

**Request Body:**
```json
{
  "rel_type": "string (optional)",
  "rel_desc": "string (optional)"
}
```

**Response (Success):**
```json
{
  "data": {
    "rel_no": "number",
    "char_no": "number",
    "trgt_ref_type": "string",
    "trgt_ref_no": "number",
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
- `NOT_OWNER`: 인물 소유자가 아님

---

### 삭제
```
DELETE /projects/:prjNo/characters/:charNo/group-relations/:relNo
Status: 200
```

**Authentication:**
- Required: Yes (Authenticated)
- Roles: ALL

**Authorization:**
- Read: N/A
- Write: Owner (인물 소유자만 삭제 가능, ADMIN은 모든 리소스 삭제 가능하나 정책상 다른 유저의 리소스 삭제는 사용하지 않음)

**Path Parameters:**
- `prjNo`: number (required) - 프로젝트 번호
- `charNo`: number (required) - 인물 번호
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
- `NOT_OWNER`: 인물 소유자가 아님

---
