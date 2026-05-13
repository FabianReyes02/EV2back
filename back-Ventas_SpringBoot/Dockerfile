# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build

# Al estar el contexto de Actions en la raíz del microservicio,
# copiamos directamente los archivos que están en esa ubicación.
COPY Springboot-API-REST/pom.xml .
COPY Springboot-API-REST/src ./src

# Compilar y empaquetar la aplicación
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Usamos el comodín *.jar para que no falle si cambia la versión en el pom.xml
COPY --from=builder /build/target/*.jar app.jar

# Variables de entorno por defecto
ENV SERVER_PORT=8080
ENV DB_ENDPOINT=localhost
ENV DB_PORT=3306
ENV DB_NAME=db_ventas
ENV DB_USERNAME=admin
ENV DB_PASSWORD=root123

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
