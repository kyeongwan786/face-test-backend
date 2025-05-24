# 1. 베이스 이미지
FROM eclipse-temurin:17-jdk-alpine

# 2. 작업 디렉토리
WORKDIR /app

# 3. target에 생성된 JAR 파일 복사 (파일명 정확히 맞춰야 됨)
COPY target/face-test-backend2-0.0.1-SNAPSHOT.jar app.jar

# 4. JAR 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
