FROM adoptopenjdk/openjdk11 as builder

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src


RUN ./mvnw package -DskipTests

FROM adoptopenjdk/openjdk11

COPY --from=builder target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
