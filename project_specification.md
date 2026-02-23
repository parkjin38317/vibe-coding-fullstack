# 프로젝트 명세서 (Project Specification)

본 문서는 최소 기능을 갖춘 스프링 부트(Spring Boot) 애플리케이션 프로젝트의 기술적 명세를 정의합니다.

## 1. 프로젝트 개요 (Project Overview)
- **프로젝트 명**: vibeapp
- **설명**: 최신 기술 스택을 활용한 최소 기능 스프링 부트 웹 애플리케이션.
- **그룹 ID (Group)**: `com.example`
- **아티팩트 ID (Artifact)**: `vibeapp`
- **메인 클래스**: `com.example.vibeapp.VibeApp`

## 2. 개발 환경 및 요구사항 (Development Requirements)
- **언어 (Language)**: Java
- **JDK 버전**: 25 이상
- **프레임워크**: Spring Boot 4.0.1 이상
- **빌드 도구**: Gradle 9.3.0 이상 (Groovy DSL 사용)

## 3. 프로젝트 설정 (Configuration)
- **설정 파일 형식**: YAML (`application.yml`)
- **의존성 (Dependencies)**: 없음 (최소 기능 프로젝트)
- **플러그인 (Plugins)**:
  - `org.springframework.boot` (Spring Boot 버전 준수)
  - `io.spring.dependency-management` (Spring Boot 버전에 따른 의존성 관리)
  - `java`

## 4. 아키텍처 및 구조 (Structure)
- **패키지 경로**: `src/main/java/com/example/vibeapp`
- **설정 파일 경로**: `src/main/resources/application.yml`

## 5. 특징
- 최소한의 설정으로 즉시 구동 가능한 구조.
- 최신 Java 버전(JDK 25) 및 Spring Boot(4.0.1) 버전 적용.
