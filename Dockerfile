FROM maven:latest as build
WORKDIR /app

COPY .mvn .mvn
COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests


FROM openjdk:17-jdk-alpine

ARG TARGET=/app/target/

COPY --from=build ${TARGET}/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
