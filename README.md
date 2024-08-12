# k-html Hackathon Project

## Stack

- **Java 17**
- **Spring Boot 3.3.2**
- **MySQL**
- **Docker**
- **Swagger**

### 로컬 DB (docker-compose.yaml)

```yaml
services:
  mysql:
    image: mysql:latest
    container_name: khtml-mysql
    environment:
      MYSQL_DATABASE: khtml
      MYSQL_ROOT_PASSWORD: password1234!
    ports:
      - "3306:3306"
```

### 공통 응답 처리

성공응답:

```json
{
  "result": "SUCCESS",
  "data": {
    "something": "응답 성공 예시"
  },
  "error": null
}
```

실패응답:

```json
{
  "result": "ERROR",
  "data": null,
  "error": {
    "code": "E404",
    "message": "Not Found",
    "data": "No endpoint GET /test."
  }
}
```

## 실행 방법

1. 리포지토리 클론
2. 의존성 설치: `./gradlew build`
3. MySQL 실행: `docker-compose up -d`
4. 애플리케이션 실행: `./gradlew bootRun`
