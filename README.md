# Ista Todo List Assessment Backend

Minimal Spring Boot application with Spring Starter Web and Spring Start Test enabled. This is ment to be a skeleton application for the Ista coding assessment.

This is a simple application Spring Boot to build a ws (Webservice) where we use persistence methods (GET,POST,UPDATE,DELETE) better known as CRUD Rest.
 1. To persiste data we use H2 Database
 2. the project has been divided into several layers to be more efficient and robust.
 3. Of course we can improve this microservices adding a Spring Boot Security and more things
 4.
 
## Requirements
For building and running the application you need:

* [JDK 11](https://openjdk.java.net/projects/jdk/11/)

## Run locally

`./mvnw spring-boot:run`

We changed server port to 8888 just to add some security and customized our project
Service listens on http://localhost:8888

Method Get all users: http://localhost:8888/users/
Method Get Specifyc User: http://localhost:8888/users/{id}
Method Post (create User) : http://localhost:8888/users/
	Example Json Body : 
	{
	"firstName" : "bbbbbb",
	"lastName" : "RYETYEY",
	"email" : "bbbbbb@gmail.com"
	}
Method Put (update specifyc User) http://localhost:8888/users/{id}
Method Delete (Delete specifyc User) http://localhost:8888/users/{id}

## H2 - Console

Service listens on http://localhost:8888/h2-console

you can see in the Aplication properties the data to connect to the h2-console


## Build JAR

`./mvnw clean package`

Resulting JAR package can be found in `./target`.
