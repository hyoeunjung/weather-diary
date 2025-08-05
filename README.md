Weather Diary (날씨 일기) 프로젝트
📖 프로젝트 소개
외부 OpenWeatherMap API를 활용해 날씨 데이터를 주기적으로 받아 저장합니다.

사용자는 특정 날짜에 날씨와 함께 일기(CRUD)를 작성, 조회, 수정, 삭제할 수 있습니다.

스프링 부트(Spring Boot)와 JPA, MySQL을 사용한 백엔드 REST API 프로젝트입니다.

🛠 주요 기능
매일 새벽 1시에 날씨 정보를 자동으로 DB에 저장 (스케줄링)

일기 생성(Create)

날짜별 날씨 데이터와 함께 일기 작성

일기 조회(Read)

특정 날짜 일기 조회

기간 내 일기 목록 조회

일기 수정(Update)

특정 날짜 일기 내용 수정

일기 삭제(Delete)

특정 날짜 모든 일기 삭제

예외 처리 및 로그 기능 포함

Swagger UI를 통한 API 문서 자동 생성

🚀 기술 스택
Java 17

Spring Boot 2.6.4

Spring Data JPA

MySQL

OpenWeatherMap API

Spring Scheduling

Swagger (springdoc-openapi)

Lombok
