# Coronavirus-COVID19-API

API for current cases and more information about COVID-19

## Overview

- The API accesses data on COVID19 through an easy API for free
- Data is indexed and queried using Elasticsearch in a Spring application through Spring Data Elasticsearch

## Getting Started

These instructions will get you a copy of the projects up and running on your local machine for development and testing purposes

### Running the application locally

The application can be started locally using the following commands:

```
Use a docker image for the Elasticsearch instance:
docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
```

```
Run the Spring Boot application:
mvn spring-boot:run
```

### Testing

You can test the application using covid-19.postman_collection.json into Postman

## Requirements

- The source API has some bugs searching cases by dates, so the API tries to avoid them controlling some business exceptions
- The application uses H2 database to run as in-memory database and store the countries affected by the virus
- Swagger has been enabled to visualize and interact with the API’s resources
