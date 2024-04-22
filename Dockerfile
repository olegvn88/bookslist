#FROM maven:3.8.4-openjdk-17 AS builder
#WORKDIR /app
#COPY . /app/.
#RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true
#
#
#FROM openjdk:17-alpine
#WORKDIR /app
#COPY --from=builder /app/target/*.jar /app/*.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app/*.jar"]

FROM alpine:3.19

RUN apk add openjdk17 && apk cache clean
COPY target/books-0.0.1.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]