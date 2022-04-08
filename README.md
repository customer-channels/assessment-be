# Ista Todo List Assessment Backend Resolved

Hi, I am Fernando Moreno Ruiz and I have resolved the Assessemnt. I have created serveral layers.

* **Controller**: it stores classes that control the request in the server
* **Model**: it stores entity classes that represent a model in Database
* **Repository**: it stores repository classes that allow you to interact with database
* **Service**: it stores classes with the business logic. I have created 2 packages for that, one of them store the interfaces and the other implements that interfaces.

In other hand I have created unit test to check the correct functionality.

## API functions
### createItem
* **url**: "/api/items"
* **method**: "POST"
* **requestBody**: JSON Item object.
* **response**: JSON Item object (with the id updated).

### updateItem
* **url**: "/api/items/{id}"
* **method**: "PUT"
* **pathVariables**: *long* id
* **requestBody**: JSON Item class object with new information.
* **response**: JSON Item object updated retrieved from database.

### getItem
* **url**: "/api/items/{id}"
* **method**: "GET"
* **pathVariables**: *long* id
* **response**: JSON Item object retrieved from database.

### listItems
* **url**: "/api/items"
* **method**: "GET"
* **response**: JSON List of All Item objects retrieved from database.

## Requirements
For building and running the application you need:

* [JDK 11](https://openjdk.java.net/projects/jdk/11/)

### Dependencies

	* H2 Database configuration 
	* lombok
	* spring-boot-starter-data-jpa

## Run locally

`./mvnw spring-boot:run`

Service listens on http://localhost:8080

## Build JAR

`./mvnw clean package`

Resulting JAR package can be found in `./target`.
