FROM openjdk:14
EXPOSE 8082
ADD target/parking-tariffservice.jar parking-tariffservice.jar
ENTRYPOINT ["java","-jar","/parking-tariffservice.jar"]