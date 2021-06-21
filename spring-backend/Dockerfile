FROM gradle:7.0.2-jdk16 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon # exclude tests, because spring wants to check db connection

FROM openjdk:16
EXPOSE 8444

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-backend-dueton.jar
ENTRYPOINT ["java", "-jar", "/app/spring-backend-dueton.jar"]
