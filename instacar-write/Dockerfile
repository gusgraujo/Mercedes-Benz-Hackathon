# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight base image with Java installed
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/instacar-write-0.0.1-SNAPSHOT.jar .

# Expose the port on which the application will run (change if needed)
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "instacar-write-0.0.1-SNAPSHOT.jar"]
