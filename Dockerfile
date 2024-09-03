FROM gradle:jdk17-alpine as builder

WORKDIR /app

COPY build.gradle /app
COPY build.gradle /app
COPY src/ /app/src

RUN gradle build --no-daemon
RUN ls -l /app/build/libs/

FROM openjdk:17-alpine

ENV LANGUAGE='en_US:en'

WORKDIR /app

COPY --from=builder /app/build/libs/app-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]