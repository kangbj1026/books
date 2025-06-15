# books
# Books Management API

Spring Boot 기반의 도서 관리 RESTful API입니다. 도서의 CRUD(Create, Read, Update, Delete) 기능을 제공합니다.

## 📋 목차

- [기술 스택](#기술-스택)
- [프로젝트 구조](#프로젝트-구조)
- [설치 및 설정](#설치-및-설정)
- [실행 방법](#실행-방법)
- [API 문서](#api-문서)
- [배포](#배포)
- [테스트](#테스트)

## 🛠 기술 스택

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Web**
- **Lombok**
- **MySQL**
- **gradle**

## 📁 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── com/books/
│   │       ├── controller/
│   │       │   └── BooksController.java
│   │       ├── service/
│   │       │   └── BooksService.java
│   │       ├── repository/
│   │       │   └── BooksRepository.java
│   │       ├── entity/
│   │       │   └── BooksEntity.java
│   │       ├── dto/
│   │       │   ├── BooksDto.java
│   │       │   ├── CreateBooksRequest.java
│   │       │   ├── CreateBooksResponse.java
│   │       │   ├── UpdateBooksRequest.java
│   │       │   ├── UpdateBooksResponse.java
│   │       │   └── DeleteBooksRequest.java
│   │       ├── common/
│   │       │   ├── CommonResponse.java
│   │       │   ├── PaginationResponse.java
│   │       │   └── ShopSearchParameter.java
│   │       └── exception/
│   │           ├── BusinessException.java
│   │           └── ErrorCode.java
│   └── resources/
│       ├── application.yml
└── test/
    └── java/
        └── com/books/
            └── controller/
                └── BooksControllerTest.java
```

## ⚙️ 설치 및 설정

### 1. 프로젝트 클론

```bash
git clone https://github.com/kangbj1026/books.git
cd books
```

### 2. 환경 설정

#### application.yml 설정 (개발환경)

```yaml
server:
  port: ${API_CONFIG.PORT} // env 숨김 파일 데이터
  servlet:
    application-display-name: ${DB_CONFIG.DATABASE} // env 숨김 파일 데이터
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true

spring:
  datasource:
    url: ${DB_CONFIG.URL} // env 숨김 파일 데이터
    username: ${DB_CONFIG.USERNAME} // env 숨김 파일 데이터
    password: ${DB_CONFIG.PASSWORD} // env 숨김 파일 데이터
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      maximum-pool-size: 20
  jpa:
    database: mysql
    database-platform: org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: none
      naming:
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
  servlet:
    multipart:
      max-file-size: 300MB
      max-request-size: 300MB
  thymeleaf:
    suffix: .html
    cache: false
  profiles:
    active: local # 기본 활성화 프로파일 local 설정

jasypt:
  encryptor:
    bean: jasyptEncryptorAES

---
spring:
  config:
    activate:
      on-profile: was1

server:
  port: ${API_CONFIG.PORT1} // env 숨김 파일 데이터
---
spring:
  config:
    activate:
      on-profile: was2

server:
  port: ${API_CONFIG.PORT2} // env 숨김 파일 데이터

```

### 3. Gradle 의존성 설정

#### build.gradle

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.3.4'
    id 'io.spring.dependency-management' version '1.1.6'
}

group = 'com.books'
version = '0.0.1-SNAPSHOT'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {

    //	Java 파일 생성을 위한 라이브러리
    implementation 'com.squareup:javapoet:1.13.0'

    // Spring Boot Starter Dependencies
    // ============================
    // Spring Boot starter dependencies provide core functionality for building applications. 스프링 부트 애플리케이션의 핵심 기능을 제공하는 시작자 의존성을 선언합니다.
    // These starters simplify dependency management and configuration. 이 시작자는 의존성 관리와 설정을 간소화하여 개발을 용이하게 합니다.
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-webflux'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'

    // JWT (JSON Web Token)
    // ===================
    // Used for secure authentication and information exchange. 보안 인증 및 정보 교환을 위한 안전한 방법으로 사용됩니다.
    implementation 'io.jsonwebtoken:jjwt-api:0.12.3'
    implementation 'io.jsonwebtoken:jjwt-impl:0.12.3'
    implementation 'io.jsonwebtoken:jjwt-jackson:0.12.3'

    // Development Tools
    // =================
    // Used for development purposes only. 개발 목적으로만 사용되는 도구들을 선언합니다.
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'

    // Database Connection
    // ===================
    // MySQL JDBC driver for database connectivity. MySQL JDBC 드라이버를 통해 데이터베이스와 연결할 수 있게 해줍니다.
    runtimeOnly 'com.mysql:mysql-connector-j'
    implementation group: 'com.oracle.database.jdbc', name: 'ojdbc8', version: '19.8.0.0'

    // Annotation Processors
    // ====================
    // Used for annotation processing during compilation. 컴파일 시점에 어노테이션 처리를 위해 필요한 프로세서들을 선언합니다.
    annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
    annotationProcessor 'org.projectlombok:lombok'

    // Testing Dependencies
    // ===================
    // Used for unit testing and integration testing. 단위 테스트와 통합 테스트를 위한 의존성을 선언합니다.
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'

    // JUnit
    // ===================
    // 플랫폼 런처 테스트용 의존성
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

    // QueryDSL
    // ========
    // Object-relational mapping library for querying databases. 데이터베이스 쿼리를 객체 지향 방식으로 작성하고 실행할 수 있게 해주는 라이브러리입니다.
    implementation "com.querydsl:querydsl-jpa:5.0.0:jakarta"
    implementation "com.querydsl:querydsl-core"
    implementation "com.querydsl:querydsl-collections"
    annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta" // querydsl JPAAnnotationProcessor 사용 지정
    annotationProcessor "jakarta.annotation:jakarta.annotation-api" // java.lang.NoClassDefFoundError (javax.annotation.Generated) 대응 코드
    annotationProcessor "jakarta.persistence:jakarta.persistence-api" // java.lang.NoClassDefFoundError (javax.annotation.Entity) 대응 코드

    // AWS Integration , s3
    // ===============
    // Spring Cloud starter for AWS integration. AWS 클라우드 서비스와 통합할 수 있게 해주는 Spring Cloud starter입니다.
    implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE'

    // Cryptography
    // ============
    // Used for encrypting sensitive data at rest. 민감한 데이터를 안전하게 저장할 때 사용되는 암호화 라이브러리입니다.
    implementation 'com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5'

    // 엑셀 처리를 위한 Apache POI 라이브러리
    implementation 'org.apache.poi:poi:4.1.2'
    implementation 'org.apache.poi:poi-ooxml:4.1.2'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

## 🚀 실행 방법

# IntelliJ IDEA 실행 방법

### 1. 프로젝트 열기
1. IntelliJ IDEA → "Open" 클릭
2. `build.gradle` 파일이 있는 폴더 선택
3. "Open as Project" 클릭
4. Gradle 동기화 완료까지 대기

### 2. 실행하기 (3가지 방법)

#### 🔥 가장 간단한 방법
1. `BooksApplication.java` 파일 열기
2. 클래스명 옆 **녹색 실행 버튼** 클릭
3. 완료! 🎉

### 3. 실행 확인
- 콘솔에 `Started BooksApplication` 메시지 확인
- 브라우저에서 `http://localhost:8080/api/v1/books` 접속

### 4. 개발 환경 설정 (선택사항)

#### 포트 변경
- `application.yml`에서 `server.port: 8080` 설정

#### 디버그 모드
- 코드에 브레이크포인트 설정 (라인 번호 옆 클릭)
- **디버그 버튼(🐛)** 클릭

### 5. 자주 사용하는 단축키
- `Ctrl+Shift+F10`: 현재 파일 실행
- `Shift+F10`: 이전 설정으로 실행
- `Shift+F9`: 디버그 실행
- `Ctrl+F2`: 실행 중지

### 6. 문제 해결
- **Gradle 동기화 오류**: File → Reload Gradle Project
- **포트 충돌**: `application.yml`에서 포트 변경
- **Java 버전 오류**: File → Project Structure에서 Java 17 확인
``

## 📚 API 문서

### Base URL
- 개발: `http://localhost:1029/api/v1/books`
- 운영: `https://your-app-name.herokuapp.com/api/v1/books`

### 엔드포인트

#### 1. 도서 목록 조회
- **GET** `/api/v1/books`
- **Query Parameters:**
    - `page`: 페이지 번호 (기본값: 0)
    - `size`: 페이지 크기 (기본값: 10)
    - `title`: 제목 검색어 (선택사항)
    - `author`: 작가 검색어 (선택사항)

**응답 예시:**
```json
{
  "success": true,
  "message": "success",
  "statusCode": 200,
  "data": {
    "totalCount": 29,
    "currentPage": 1,
    "totalPages": 29,
    "items": [
      {
        "booksUID": 30,
        "title": "김치국물을 마시며",
        "author": "이영도",
        "description": "한국적 정서가 담긴 일상 에세이",
        "price": 11000,
        "stockQuantity": 30,
        "isbn": "9791165340896",
        "createdAt": "2024-02-14T12:30:00",
        "updatedAt": "2024-02-14T12:30:00"
      }
    ]
  }
}
```

#### 2. 특정 도서 조회
- **GET** `/api/v1/books/{booksUID}`

**응답 예시:**
```json
{
  "success": true,
  "message": "success",
  "statusCode": 200,
  "data": {
    "booksUID": 1,
    "title": "해리 포터와 마법사의 돌",
    "author": "J.K. 롤링",
    "description": "호그와트 마법학교에 입학한 해리 포터의 첫 번째 모험을 그린 판타지 소설",
    "price": 15000,
    "stockQuantity": 25,
    "isbn": "9788983920775",
    "createdAt": "2024-01-15T09:30:00",
    "updatedAt": "2024-01-15T09:30:00"
  }
}
```

#### 3. 도서 생성
- **POST** `/api/v1/books`

**요청 본문:**
```json
{
  "title" : "title",
  "author" : "author",
  "description" : "description",
  "price" : 1000,
  "stockQuantity" : 66,
  "isbn" : "202506-AFBETTGTRG01"
}
```

**응답 예시:**
```json
{
  "success": true,
  "message": "success",
  "statusCode": 202,
  "data": {
    "booksUID": 31
  }
}
```

#### 4. 도서 수정
- **PUT** `/api/v1/books/{uid}`

**요청 본문:**
```json
{
  "title" : "title1",
  "author" : "author1",
  "description" : "description1",
  "price" : 2000,
  "stockQuantity" : 77,
  "isbn" : "202506-AFBETTGTRG01"
}
```
**응답 예시:**
```json
{
  "success": true,
  "message": "success",
  "statusCode": 202,
  "data": {
    "booksUID": 31
  }
}
```

#### 5. 도서 삭제
- **DELETE** `/api/v1/books/{uid}`

**요청 본문:**
```json
{
  "title": "title1"
}
```
**응답 예시:**
```json
{
  "success": true,
  "message": "success",
  "statusCode": 205,
  "data": "31"
}
```

### API 테스트 (cURL 예시)

```bash
# 도서 목록 조회
curl -X GET "http://localhost:1029/api/v1/books"

# 도서 생성
curl -X POST "http://localhost:1029/api/v1/books" \
  -H "Content-Type: application/json" \
  -d '{
    "title" : "title",
    "author" : "author",
    "description" : "description",
    "price" : 1000,
    "stockQuantity" : 66,
    "isbn" : "202506-AFBETTGTRG01"
}'
```

## 🔧 문제 해결

### 자주 발생하는 문제들

1. **포트 충돌**
    - `application.yml`에서 다른 포트로 변경: `server.port: 8081`

2. **데이터베이스 연결 오류**
    - DATABASE MYSQL 콘솔 확인: `jdbc:mysql://localhost:3306/books?characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Seoul`
    - JDBC URL: `com.mysql.cj.jdbc.Driver`

3. **빌드 오류**
    - Java 버전 확인: `java -version`
    - Maven 버전 확인: `./mvnw -version`

## 📝 라이선스

MIT License

## 👨‍💻 개발자 정보

- **개발자**: [kangbj1026]
- **이메일**: [kangbj1026@gmail.com]
- **GitHub**: [https://github.com/kangbj1026/books]

---
