# VibeApp 프로젝트 명세서

VibeApp은 개발자들이 고품질의 미학과 깔끔한 아키텍처에 집중하여 게시물을 공유하고 관리할 수 있도록 설계된 프리미엄 Spring Boot 웹 애플리케이션입니다.

## 기술 스택

- **프레임워크**: Spring Boot 3.4.2
- **언어**: Java 21 (Record 활용)
- **빌드 도구**: Gradle 8.12.1 (Groovy DSL)
- **뷰 엔진**: Thymeleaf
- **지속성 프레임워크**: MyBatis
- **데이터베이스**: H2 Database (파일 기반: `./data/testdb`)
- **스타일링**: Vanilla CSS + Tailwind CSS (Glassmorphism 디자인)
- **트랜잭션 관리**: Spring Declarative Transaction (@Transactional)

## 프로젝트 구조 (기능 중심)

유지보수성 향상을 위해 기능별 패키지 구조를 따릅니다.

### Java 패키지 (`src/main/java/com/example/vibeapp`)
- `com.example.vibeapp`: 메인 애플리케이션 클래스 (`VibeApp.java`) 및 도메인 엔티티 (`Post.java`, `PostTag.java`)
- `com.example.vibeapp.home`: 사이트 랜딩 관련 컨트롤러 (`HomeController.java`)
- `com.example.vibeapp.post`: 게시판 시스템의 핵심 로직
    - `PostController.java` (웹 계층)
    - `PostService.java` (비즈니스 계층)
    - `PostRepository.java`, `PostTagRepository.java` (MyBatis Mapper 인터페이스)
    - `dto/`: 데이터 전송 객체 (Java Records 활용: `PostCreateDto`, `PostUpdateDto`, `PostResponseDTO`, `PostListDto`)

### 리소스 및 XML 매퍼 (`src/main/resources`)
- `mapper/post/`: `PostMapper.xml`, `PostTagMapper.xml` (MyBatis SQL 매핑)
- `templates/`: Thymeleaf 뷰 템플릿 (기능별 디렉토리 구성)

## 주요 기능

### 게시글 및 태그 관리
- **CRUD 연동**: 제목, 내용, 태그를 포함한 게시글 등록/수정/삭제 기능을 제공합니다.
- **태그 시스템**: 
    - 쉼표(,)로 구분된 태그 문자열을 입력받아 `POST_TAGS` 테이블에 개별 저장합니다.
    - 게시글 상세 페이지에서 세련된 배지(Badge) 형태로 시각화합니다.
- **조회 기능**: 
    - 생성일 기준 내림차순(DESC) 정렬 및 DB 레벨 페이징을 지원합니다.
    - 게시글 상세 조회 시 조회수가 원자적으로 증가합니다.
- **데이터 안정성**: 게시글과 태그 작업은 단일 트랜잭션으로 처리되어 데이터 정합성을 보장합니다.

### 페이지네이션 시스템
- **페이지당 5개의 게시글**을 표시합니다.
- 페이지 번호, "이전", "다음" 컨트롤이 포함된 인터랙티브 내비게이션 바를 제공합니다.

### UI/UX 디자인
- **프리미엄 미학**: Glassmorphism 효과와 다크 모드가 조화된 세련된 인터페이스.
- **반응형 레이아웃**: 모든 모바일 및 데스크톱 환경 대응.
- **동적 요소**: 부드러운 전환 효과와 호버 트랜지션을 통한 몰입감 있는 사용자 경험.

## 데이터베이스 스키마
- **POSTS**: 게시글 기본 정보 (NO, TITLE, CONTENT, CREATED_AT, UPDATED_AT, VIEWS)
- **POST_TAGS**: 게시글 태그 정보 (ID, POST_NO, TAG_NAME, FOREIGN KEY references POSTS)
