# Stage 1: Build only bootJar
FROM gradle:8.5.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew bootJar

# Stage 2: Run jar
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
