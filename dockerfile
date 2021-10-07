#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
From openjdk:8
copy ./target/student-manager-0.0.1-SNAPSHOT.jar student-manager-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","student-manager-0.0.1-SNAPSHOT.jar"]
