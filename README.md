# Flight Interconnections

This app uses _Spring Boot_, _Java 8_, and  _Maven_ for build the artifact. 

The micro-service is implemented using [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) in order to avoid mixing between business and technological decisions.
## Build & Run
### Create war
To build the war execute the next command:
```
mvnw clean install
```
### Start micro-service using Spring Boot
To use Spring Boot Maven Plugin to run the app, execute the next command:
```
mvnw spring-boot:run
```
### Example request
```
curl -X GET \
  http://localhost:8080/interconnections?departure=DUB&arrival=WRO&departureDateTime=2018-04-02T07:00&arrivalDateTime=2018-04-03T21:00
```
### Observations
[Lombok Library](https://projectlombok.org/) is used in this project, then it's required to install the plugin to build the project using an IDE.