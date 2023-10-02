FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} server.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/server.jar"]