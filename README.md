# Microservices-music-bands Application

## Backend
This repository is a simple Microservices application example built with **Spring Boot 2.4.0**

### Tech Stack 

| Name | Version | Description |
| --- | --- | --- |
| **Spring Boot 2** | 2.3.5 | Service Discovery Pattern. |
| **Spring Cloud Netflix Eureka** | 1.10.7 |
| **Spring Cloud Gateway** | spring-cloud-gateway-dependencies 3.0.0-SNAPSHOT | Microservices routing. |
| **Spring Boot Okta** | 1.5.0 | API Authentication. Based on the examples explained in https://dzone.com/articles/how-to-use-spring-cloud-gateway-with-oauth-20-patt |
| **Apache Kafka** | 2.6.0 | Producer and Consumer working with Kafka cluster created in local, cosuming band tweets from twitter and inserting them into a Eslasticsearch |
| **Elasticsearch-rest-high-level** | 7.9.3 | [Bonsai Hosted Elasticsearch search engine](https://bonsai.io/). |
| **Lombok** | 1.18.16 | java Library: Getters & Setters, Equals method, Logging, Constructors, etc using annotations. | 
| **H2** | 1.4.200 |  in-memory RDBMS. |
| **Swagger** | 3.0.0 | REST API documentation. |

**Class Diagram:**
![Alt text](/class_diagram.jpeg?raw=true "Class diagram")
