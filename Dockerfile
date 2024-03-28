FROM openjdk:17-jdk

EXPOSE 5500

ADD build/cloudStorage_test-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
