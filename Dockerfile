## 베이스 이미지로 Java 17버전이 포함된 Docker 이미지를 사용
FROM bellsoft/liberica-openjdk-alpine:17

CMD ["./gradlew", "clean", "build"]
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]