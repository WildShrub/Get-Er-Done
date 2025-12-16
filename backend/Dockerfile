# Use official Java image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR
COPY target/get-er-done.jar app.jar

# Expose port
EXPOSE 8080

# Run the application using the profile from env variable
ENTRYPOINT ["sh", "-c", "java -jar app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]