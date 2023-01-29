FROM gradle:jdk11 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle --stacktrace build

FROM openjdk:11-jre-slim
COPY --from=gradleimage home/gradle/source/build/libs/dashboard-backend-0.0.1-SNAPSHOT.war /app/
EXPOSE 8080
WORKDIR /app
ENTRYPOINT ["java", "-jar", "dashboard-backend-0.0.1-SNAPSHOT.war"]