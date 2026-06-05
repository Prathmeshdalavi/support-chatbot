# ==========================================
# STAGE 1: Memory-Optimized Build
# ==========================================
FROM maven:3.8.5-openjdk-17 AS build
COPY . .

# MAVEN_OPTS limits JVM memory usage so Render's free tier doesn't crash.
ENV MAVEN_OPTS="-Xmx512m -XX:MaxMetaspaceSize=256m"
RUN mvn clean package -Dmaven.test.skip=true -DskipTests

# ==========================================
# STAGE 2: Lightweight Runtime Environment (Using Supported Eclipse Temurin)
# ==========================================
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080

# Run with controlled memory optimization flags in production
ENTRYPOINT ["java", "-Xmx300m", "-jar", "app.jar"]