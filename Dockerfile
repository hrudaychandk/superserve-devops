FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/superserve-devops-1.0-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]