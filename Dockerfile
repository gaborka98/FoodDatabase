FROM maven:latest as build
WORKDIR /app

COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests


FROM openjdk:17-jdk-alpine

COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
