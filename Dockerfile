# Use a base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file to the container
COPY target/test-service-0.0.1-SNAPSHOT.jar test-service.jar

# Expose a port (optional, useful for mapping ports)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/test-service.jar"]
