# Microservices-music-bands Application

## Backend
This repository is a simple Microservices application example built with **Spring Boot 2.4.0**

**Spring Cloud Netflix Eureka**: Service Discovery Pattern.

**Spring Cloud Gateway**: Microservices routing.

**Okta OAuth 2.0**: API Authentication. Based on the examples explained in https://dzone.com/articles/how-to-use-spring-cloud-gateway-with-oauth-20-patt

**Apache Kafka**: Producer and Consumer working with Kafka cluster created in local, cosuming band tweets from twitter and inserting them into a **Cloud Elasticsearch search engine (https://bonsai.io/)**. 

Other projects and libraries used: Lombok, H2 embedded RDBMS, Swagger 3.0.0.


**Class Diagram:**
![Alt text](/class_diagram.jpeg?raw=true "Class diagram")
