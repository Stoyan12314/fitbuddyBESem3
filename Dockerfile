FROM azul/zulu-openjdk-alpine:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
["java","-jar","/app.jar"]



