# Stage 1: Build - Actualizado a JDK 25
FROM maven:3.9-eclipse-temurin-25-alpine AS builder
WORKDIR /build

# Copiamos desde la subcarpeta específica del proyecto de Despachos
COPY Springboot-API-REST-DESPACHO/pom.xml .
COPY Springboot-API-REST-DESPACHO/src ./src

# Compilar y empaquetar la aplicación
RUN mvn clean package -DskipTests

# Stage 2: Runtime - Actualizado a JRE 25 para soporte de Java 25
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Copiar el JAR generado (usamos comodín para mayor flexibilidad)
COPY --from=builder /build/target/*.jar app.jar

# Variables de entorno por defecto
ENV SERVER_PORT=8081
ENV DB_ENDPOINT=localhost
ENV DB_PORT=3306
ENV DB_NAME=db_despachos
ENV DB_USERNAME=admin
ENV DB_PASSWORD=root123

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
