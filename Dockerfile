FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bicycle-lockers-service.jar
ENTRYPOINT ["java","-jar","/bicycle-lockers-service.jar"]
