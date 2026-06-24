FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/sistema-editais.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]