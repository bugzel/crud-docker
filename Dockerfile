FROM maven:3.5-jdk-8-slim AS build 
WORKDIR /project
COPY src /project/src  
COPY pom.xml /project
RUN mvn -f /project/pom.xml clean package

FROM openjdk:8-jre-slim
COPY --from=build /project/target/crud-0.0.1-SNAPSHOT.jar /crud.jar
EXPOSE 7070
ENTRYPOINT ["java","-jar","/crud.jar"]