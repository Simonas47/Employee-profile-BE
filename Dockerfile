FROM openjdk:17.0.1

COPY build/libs/employee-profile-api-*-SNAPSHOT.jar employee-profile-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/employee-profile-api.jar"]