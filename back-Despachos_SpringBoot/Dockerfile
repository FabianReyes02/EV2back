# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build

# Al estar el contexto en la raíz del microservicio, 
# el pom.xml y src están directamente disponibles.
COPY Springboot-API-REST-DESPACHO/pom.xml .
COPY Springboot-API-REST-DESPACHO/src ./src

# Compilar y empaquetar la aplicación
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar JAR desde el stage anterior
# Nota: Verifica que el nombre del JAR generado en la carpeta target 
# sea exactamente este.
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
