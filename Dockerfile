FROM adoptopenjdk/openjdk11:alpine-jre
COPY ../BACKEND/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]