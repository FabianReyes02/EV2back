# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build

# Copiamos el contenido DESDE la subcarpeta al nivel raíz de /build
COPY Springboot-API-REST/pom.xml .
COPY Springboot-API-REST/src ./src

# Ahora Maven encontrará el pom.xml en /build/pom.xml
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# El JAR se genera en /build/target/
COPY --from=builder /build/target/*.jar app.jar

ENV SERVER_PORT=8080
ENV DB_ENDPOINT=localhost
ENV DB_PORT=3306
ENV DB_NAME=db_ventas
ENV DB_USERNAME=admin
ENV DB_PASSWORD=root123

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
