FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/ageservice
ADD transport-service-0.0.1-SNAPSHOT.jar /usr/local/transportservice/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
