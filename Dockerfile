# syntax=docker/dockerfile:1

FROM maven:3.9.9-eclipse-temurin-17 AS build
ARG MODULE_DIR
WORKDIR /workspace
COPY . .
RUN mvn -f ${MODULE_DIR}/pom.xml -DskipTests package

FROM eclipse-temurin:17-jre
ARG MODULE_DIR
WORKDIR /app
COPY --from=build /workspace/${MODULE_DIR}/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
