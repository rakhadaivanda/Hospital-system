# Stage 1: Build aplikasi menggunakan Maven
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder
WORKDIR /app
# Copy file konfigurasi pom.xml dan kode sumber
COPY pom.xml .
COPY src ./src
# Compile aplikasi (membuat file JAR)
RUN mvn clean package -DskipTests

# Stage 2: Jalankan aplikasi menggunakan Java JRE yang sangat ringan
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy hasil file JAR dari Stage 1 ke Stage 2
COPY --from=builder /app/target/system-0.0.1-SNAPSHOT.jar app.jar

# Buka port 8080
EXPOSE 8080

# Jalankan aplikasinya
ENTRYPOINT ["java", "-jar", "app.jar"]
