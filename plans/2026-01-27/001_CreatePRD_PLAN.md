# 001_CreatePRD_PLAN.md

## Goal
FastAPI에서 Spring Boot로 전환된 Fantasy Builder 프로젝트를 위한 실행 가능한 PRD 문서 3종 작성
- PRD.md: 프로젝트 요구사항 문서
- Coding Rules & Guidelines.md: 코딩 규칙 및 가이드라인
- Development Task List.md: 개발 작업 목록

## Context
- **날짜**: 2026-01-27
- **원본 명령**: `/create-prd @specs 이 폴더 상황에 맞게 계획 문서들을 작성해줘. 이것들은 기존에fast api 로 작성이 되었던거고 이제 스프링이니까 코드를 업데이트 해야 해.`
- **프로젝트 상태**: FastAPI에서 Spring Boot로 전환 중
- **기술 스택**: Spring Boot 4.0.2, Java 25, PostgreSQL, JPA, Lombok
- **참고 파일**: 
  - CommonEntity.java, CommonVO.java (공통 필드 패턴)
  - UserEntity.java, UserVO.java (엔티티/VO 작성 패턴)
  - specs/ 폴더의 모든 기획 문서

## Strategy
1. **프로젝트 분석**: specs 폴더의 기획 문서들을 종합 분석하여 프로젝트 전체 구조 파악
2. **기술 스택 반영**: Spring Boot, JPA, Java 25 기반으로 기술 스택 명시
3. **엔티티/VO 패턴 정의**: CommonEntity, CommonVO, UserEntity, UserVO를 참고하여 일관된 패턴 제시
4. **문서 구조화**: 3가지 문서를 체계적으로 작성하여 개발팀이 즉시 착수 가능하도록 구성

## Task List
1. **PRD.md 작성**
   - 프로젝트 개요 및 목표
   - 기술 스택 (Spring Boot 4.0.2, Java 25, PostgreSQL 등)
   - 시스템 아키텍처 및 핵심 기능
   - 데이터 구조 (엔티티 관계도 포함)
   - 비기능 요구사항

2. **Coding Rules & Guidelines.md 작성**
   - 아키텍처 원칙 (Spring Boot 기반)
   - 폴더 구조 (도메인 주도 설계 패턴)
   - 네이밍 컨벤션 (Java 표준)
   - 코딩 표준 (Entity, VO, Repository, Service, Controller 패턴)
   - CommonEntity/CommonVO 상속 패턴 명시
   - 에러 처리 전략 (ResponseCode Enum 활용)

3. **Development Task List.md 작성**
   - Phase별 작업 목록 (Phase 1 제외)
   - 엔티티 생성 작업 (CommonEntity 상속 패턴)
   - VO 생성 작업 (CommonVO 상속 패턴)
   - Repository, Service, Controller 생성 작업
   - 우선순위별 작업 정렬

## Files to Modify
- `PRD/PRD.md` (신규 생성)
- `PRD/Coding Rules & Guidelines.md` (신규 생성)
- `PRD/Development Task List.md` (신규 생성)
