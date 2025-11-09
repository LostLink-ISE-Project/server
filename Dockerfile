FROM eclipse-temurin:17-jdk-alpine

WORKDIR /server

COPY build/libs/lost-link-server-0.0.1-SNAPSHOT.jar lostlink.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "lostlink.jar"]
