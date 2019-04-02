FROM openjdk:8-jdk-alpine

EXPOSE 8080
COPY build/libs/challenge1-1.0-SNAPSHOT-all.jar service.jar

CMD java -jar service.jar

