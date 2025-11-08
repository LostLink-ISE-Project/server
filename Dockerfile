# Use JDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and necessary files
ENV GRADLE_USER_HOME=/home/gradle/.gradle

COPY gradlew .
COPY gradle gradle

# Copy build files
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Grant execute permission to gradlew
RUN chmod +x gradlew

# Build the app
RUN ./gradlew build -x test

# Expose the application port
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "build/libs/lost-link-server-0.0.1-SNAPSHOT.jar"]
