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

## Design Notes

The development lifecycle is an agile one. I have tried not to over engineer the first draft solution with the view that details can be improved following stakeholder feedback.

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


## Testing

I have used the FireFox plugin RESTer extensively during development to help ensure the request and response bodies meet the specification.

Unit tests can be run using:

mvn test

## Docker

