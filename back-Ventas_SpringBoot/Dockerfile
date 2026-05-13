# Stage 1: Build - Actualizado a JDK 25
FROM maven:3.9-eclipse-temurin-25-alpine AS builder
WORKDIR /build

# Copiamos desde la subcarpeta específica de tu proyecto
COPY Springboot-API-REST/pom.xml .
COPY Springboot-API-REST/src ./src

RUN mvn clean package -DskipTests

# Stage 2: Runtime - Actualizado a JRE 25
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

# Mantén tus variables de entorno igual...
ENV SERVER_PORT=8080
# ...
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
