
# Start with a base image containing Java runtime
FROM openjdk:8
#RUN apt-get update && apt-get install -y maven
# Add Maintainer Info
LABEL maintainer="victorpazrdgz@gmail.com"

## Add a volume pointing to /tmp
#VOLUME /tmp
COPY target/psicologia-0.0.1-SNAPSHOT.jar appPsicologia.jar
# Make port 8080 available to the world outside this container
EXPOSE 8080
## The application's jar file
#ARG JAR_FILE=target/*.jar
#
## Add the application's jar to the container
#
#ADD ${JAR_FILE}  app.jar
#COPY . /AppPsicologia
#RUN  cd /AppPsicologia && mvn package
# Run the jar file
#ENTRYPOINT ["java", "-Dspring.data.mongodb.host=172.18.0.2","-Dspring.data.mongodb.port=37017","-Dspring.data.mongodb.database= appPsicologia","-Djava.security.egd=file:/dev/./urandom","-jar","/appPsicologia.jar"]
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://172.20.0.2:27017/appPsicologia","-jar","/appPsicologia.jar"]