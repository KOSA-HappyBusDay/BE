FROM adoptopenjdk/openjdk11:alpine-jre
COPY BE/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]