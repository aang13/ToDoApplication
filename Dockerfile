FROM amazoncorretto:11-alpine-jdk
MAINTAINER baeldung.com
COPY target/TODO-0.0.1-SNAPSHOT.jar todo-1.0.0.jar
ENTRYPOINT ["java","-jar","/todo-1.0.0.jar"]