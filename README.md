# Articles
API for Article data

## URLs

API

http://localhost:8080/swagger-ui.html


GET 

http://localhost:8080/articles

http://localhost:8080/articles/1

http://localhost:8080/tags/health/20180813


POST

http://localhost:8080/articles


## Technologies

- Java 8
- Spring: MVC, Boot, Data, Test
- Jackson
- Maven
- H2 database
- Lombok
- Swagger

## Running Locally

From an IDE run the ArticlesApplication class. 


### Run from command line

java -jar articles.jar from project root

or run the code directly using mvn springboot:run from project root

If you have docker installed please see below about running as a docker image.


## Testing

I have used the FireFox plugin RESTer extensively during development to help ensure the request and response bodies meet the specification.

Unit tests can be run using:

mvn test

## Docker Image

There is a Docker file in src/main/docker that can be used to build a Docker image of the application.

### To Build The Docker Image

Command line  - "mvn package docker:build"
IDE - make a maven config that runs "package docker:build"

### To Run The Docker Image

Command line -  "docker run -p 8080:8080 article/articles", then go to your browser and go to http://localhost:8080/swagger-ui.html.

### Stop Docker Image

"docker ps" will give you the number of the Docker container.

"docker stop 'container number'" will stop the application.

### Sharing A Built Docker Image Manually

Docker supports exporting and saving.

To export a copy of the current instance use
'docker export' to save and 'docker import' to load.

To save a copy of the docker image use 'docker save' to save and 'docker load' to load.

