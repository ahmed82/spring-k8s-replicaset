FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/spring-k8s-1.0.war
ADD ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]