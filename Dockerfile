# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy all project files
COPY . .

# Build the application
# We don't need a separate dependency resolution step because it fails for multi-module projects
# without their source code. Docker will still cache the layers above this if files don't change.
RUN mvn clean package -DskipTests -B

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/api/target/api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
