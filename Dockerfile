FROM openjdk:21-jdk
ARG JAR_FILE=build/libs/*.war
COPY ${JAR_FILE} app.war
CMD ["java","-jar","app.war"]