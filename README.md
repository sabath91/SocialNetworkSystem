# SocialNetworkSystem

This project uses Camel and Spring-boot to expose REST API for simple social media like application. 

#### Dependencies

    camel = "2.21.0"
    spring-boot = "1.5.12"
    activemq = "5.14.0"

#### System requirements

* Java 8
* Maven


### Environment Setup

1. Make sure Java 8 SDK is installed and configured and databases (mongoDb and neo4j) are installed and its 
services are started. Expected neo4j password is 'password'.
In case of custom password, please change file application.properties.

2. Download dependencies:
    * Download the latest version of [Neo4j](https://neo4j.com/download/)
    * Download the latest version of [MongoDB](https://docs.mongodb.com/manual/installation/) 
    
#### Running
Please make sure that both databases services are running before executing command below.
```bash
$ mvn clean spring-boot:run
``` 

#### Resources

##### [Camel Documentation](http://camel.apache.org/)

##### [ActiveMQ Documentation](http://activemq.apache.org/)

##### [Java Documentation](https://docs.oracle.com/javase/7/docs/api/)