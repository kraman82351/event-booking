# Step 1: Build the application
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Step 2: Run with a lightweight JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/irctc_core/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]