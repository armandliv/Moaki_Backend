FROM maven:3.9.6-eclipse-temurin-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM 23-ea-7-jdk-oraclelinux8
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]