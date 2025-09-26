FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy jar + wait-for-it
COPY --from=build /app/target/*.jar app.jar
COPY wait-for-it.sh .

# Make wait-for-it executable
RUN chmod +x wait-for-it.sh

EXPOSE 8082

# Wait for config-server and mongo before starting
ENTRYPOINT ["./wait-for-it.sh", "config-server:8888", "--", "./wait-for-it.sh", "mongo:27017", "--", "java", "-jar", "app.jar"]
