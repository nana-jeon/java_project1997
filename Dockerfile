# ---- Build Stage ----
FROM gradle:8.7-jdk21 AS builder
WORKDIR /app

# Copy everything
COPY . .

# Build the project
RUN gradle clean bootJar --no-daemon

# ---- Run Stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
