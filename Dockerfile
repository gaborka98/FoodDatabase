FROM maven:latest as build
WORKDIR /app

COPY .mvn .mvn
COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


FROM openjdk:17-jdk-alpine

ARG DEPENDENCY=/app/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "me.agronaut.foodDatabase.FoodDatabaseApplication"]
