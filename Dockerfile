FROM openjdk:21

COPY build/libs/*.jar  app.jar
ENTRYPOINT ["java","-jar","/app.jar"]