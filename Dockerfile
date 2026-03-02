# Step 1: Use Java 21 runtime
FROM eclipse-temurin:21-jdk-alpine

# Step 2: Set a working directory inside the container
WORKDIR /app

# Step 3: Copy the Maven build artifact (jar) into the container
# Make sure you have run 'mvn clean package' before building the image
COPY target/scoring_management_system-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your Spring Boot app runs on
EXPOSE 8080

# Step 5: Set environment variables (optional; can be overridden in docker-compose.yml)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/SM
ENV SPRING_DATASOURCE_USERNAME=SMUSER
ENV SPRING_DATASOURCE_PASSWORD=SMPW
ENV JWT_SECRET=your-super-secret-key-must-be-at-least-256-bits-long-for-hs256

# Step 6: Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]