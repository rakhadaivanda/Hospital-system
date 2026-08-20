# Menggunakan image base Java 17 (Ringan)
FROM eclipse-temurin:17-jre-alpine

# Mengatur direktori kerja di dalam container
WORKDIR /app

# Menyalin file JAR hasil kompilasi ke dalam container
COPY target/system-0.0.1-SNAPSHOT.jar app.jar

# Membuka port 8080 (Render biasanya menggunakan port ini)
EXPOSE 8080

# Menjalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]
