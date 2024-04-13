FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY /build/libs/sqs-0.0.1-SNAPSHOT.jar sqs.jar
EXPOSE 8081
CMD ["java", "-jar", "sqs.jar"]