# 1. Java 17 기반 이미지
FROM eclipse-temurin:17-jdk-alpine

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. Maven 빌드용 jar 복사
COPY target/*.jar app.jar

# 4. 앱 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
