# VibeApp 프로젝트 명세서

VibeApp은 개발자들이 고품질의 미학과 깔끔한 아키텍처에 집중하여 게시물을 공유하고 관리할 수 있도록 설계된 프리미엄 Spring Boot 웹 애플리케이션입니다.

## 기술 스택

- **프레임워크**: Spring Boot 3.4.2
- **언어**: Java 21
- **빌드 도구**: Gradle 8.12.1 (Groovy DSL)
- **뷰 엔진**: Thymeleaf
- **스타일링**: Vanilla CSS + Tailwind CSS (Glassmorphism 디자인)
- **데이터베이스**: 인메모리 (Collection 기반 `MemoryPostRepository`)

## 프로젝트 구조 (기능 중심)

유지보수성 향상을 위해 기능별 패키지 구조를 따릅니다.

### Java 패키지 (`src/main/java/com/example/vibeapp`)
- `com.example.vibeapp`: 메인 애플리케이션 클래스 (`VibeApp.java`)
- `com.example.vibeapp.home`: 사이트 랜딩 관련 컨트롤러 (`HomeController.java`)
- `com.example.vibeapp.post`: 게시판 시스템의 핵심 도메인 및 로직
    - `Post.java` (도메인 엔티티)
    - `PostController.java` (웹 계층)
    - `PostService.java` (비즈니스 계층)
    - `PostRepository.java` (인터페이스)
    - `MemoryPostRepository.java` (구현체)

### 뷰 템플릿 (`src/main/resources/templates`)
- `home/`: `home.html` (랜딩 페이지)
- `post/`:
    - `posts.html` (페이지네이션이 포함된 게시글 목록)
    - `post_detail.html` (게시글 상세 보기)
    - `post_new_form.html` (새 게시글 작성)
    - `post_edit_form.html` (기존 게시글 수정)

## 주요 기능

### 게시글 관리 (CRUD)
- **생성**: 제목과 내용을 입력하여 새로운 게시글을 등록합니다.
- **조회**: 생성일 기준 내림차순으로 게시글 목록을 확인하고, 개별 게시글의 상세 내용을 조회합니다.
    - **조회수 트래킹**: 게시글 상세 조회 시 조회수가 자동으로 증가합니다.
- **수정**: 게시글의 제목과 내용을 수정합니다.
- **삭제**: 확인 프롬프트를 거쳐 게시글을 삭제합니다.

### 페이지네이션 시스템
- **페이지당 5개의 게시글**을 표시합니다.
- 페이지 번호, "이전", "다음" 컨트롤이 포함된 인터랙티브 내비게이션 바를 제공합니다.
- 페이지 블록 단위(예: 1-5, 6-10)로 동적 계산되어 표시됩니다.

### UI/UX 디자인
- **프리미엄 미학**: Glassmorphism 효과(backdrop-filter blur, 은은한 테두리)가 적용된 다크 모드를 지원합니다.
- **반응형 레이아웃**: Tailwind CSS를 사용하여 다양한 화면 크기에 최적화되었습니다.
- **타이포그래피**: 현대적인 폰트(Space Grotesk, Inter)를 사용합니다.
- **마이크로 애니메이션**: 부드러운 호버 트랜지션 및 인터랙티브 요소를 통해 사용자 경험을 향상시킵니다.

## 향후 로드맵 (계획)
- [ ] 데이터베이스 영속성 적용 (JPA/MySQL)
- [ ] 사용자 인증 및 회원 관리
- [ ] 댓글/답글 시스템
- [ ] 카테고리 관리 기능
