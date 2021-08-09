FROM openjdk:16-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar"]
