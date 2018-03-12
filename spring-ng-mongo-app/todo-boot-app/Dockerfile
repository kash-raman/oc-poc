FROM openjdk:8
WORKDIR /usr/local/todo-boot-app
COPY . .
RUN apt-get update && apt-get install -y  maven
RUN mvn install
EXPOSE 8080
CMD ["java", "-jar", "/usr/local/todo-boot-app/target/todo-boot-app-0.0.1-SNAPSHOT.jar"]

