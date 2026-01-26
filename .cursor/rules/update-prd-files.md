---
description: PRD 폴더 내 파일 업데이트 규칙
globs: 
alwaysApply: true
---

# PRD 파일 업데이트 규칙

## 개요
코드나 기능을 업데이트할 때는 관련된 PRD 폴더 내 문서들도 함께 업데이트해야 합니다.

## 규칙

### 1. 필수 업데이트 대상
다음과 같은 작업을 수행할 때는 반드시 PRD 폴더 내 관련 파일을 업데이트해야 합니다:

- **엔티티 생성/수정/삭제**: `PRD/Development Task List.md`의 해당 Phase 작업 체크
- **API 엔드포인트 추가/수정**: `PRD/Development Task List.md`의 해당 Phase 작업 체크
- **공통 인프라 변경**: `PRD/Development Task List.md`의 Phase 2 작업 체크
- **코딩 규칙 변경**: `PRD/Coding Rules & Guidelines.md` 업데이트
- **새로운 기능 추가**: `PRD/Development Task List.md`에 작업 추가 및 체크
- **버그 수정 또는 개선**: `PRD/TODO.md`에 관련 항목 업데이트

### 2. 업데이트 방법

#### Development Task List.md
- 작업 완료 시 `[ ]`를 `[x]`로 변경
- 부분 완료 시 `[~]`로 표시
- 새로운 작업 추가 시 적절한 Phase 섹션에 추가

#### Coding Rules & Guidelines.md
- 새로운 코딩 규칙 추가 시 해당 섹션에 문서화
- 기존 규칙 수정 시 해당 내용 업데이트
- 예제 코드가 있다면 함께 업데이트

#### TODO.md
- 완료된 항목은 상태 업데이트 또는 제거
- 새로운 TODO 항목 추가
- 우선순위 변경 시 섹션 이동

#### PRD.md
- 프로젝트 구조나 기술 스택 변경 시 업데이트
- 새로운 엔티티 추가 시 스키마 정보 업데이트

### 3. 업데이트 시점
- **코드 변경과 동시에**: 코드를 수정할 때 바로 PRD 파일도 함께 수정
- **작업 완료 시**: 작업이 완료되면 즉시 관련 문서 업데이트
- **리뷰 전**: 코드 리뷰 전에 문서 동기화 확인

### 4. 체크리스트
코드 변경 시 다음을 확인하세요:

- [ ] `PRD/Development Task List.md`의 해당 작업 체크 여부
- [ ] `PRD/Coding Rules & Guidelines.md`에 새로운 규칙이 필요한지 확인
- [ ] `PRD/TODO.md`에 관련 항목이 있다면 업데이트
- [ ] 변경 사항이 `PRD/PRD.md`의 스키마나 구조에 영향을 주는지 확인

### 5. 예외 사항
다음과 같은 경우는 PRD 파일 업데이트가 선택적입니다:

- 단순 오타 수정
- 주석 추가/수정
- 리팩토링 (기능 변경 없음)
- 테스트 코드만 추가

단, 리팩토링이라도 구조적 변경이 있다면 `PRD/Coding Rules & Guidelines.md`에 반영해야 합니다.
