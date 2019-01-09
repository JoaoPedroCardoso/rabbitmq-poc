# @name@

## Welcome to rabbitmq poc!

This is a simple example of how to use rabbitmq with Spring/Java features.

##### Technologies Used::
 - Rabbitmq.
 - Java.
 - H2.
 - Docker.
 - Spring Boot.

## Dependencies
- [Git](https://www.atlassian.com/git/tutorials/install-git)
- [Java](https://www.java.com/en/download/help/download_options.xml)
- [Docker](https://docs.docker.com/engine/installation/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/install.html)
- Maven Alternative Installation Options: 
  - http://sdkman.io: `$ sdk install maven`
  - https://brew.sh: `$ brew install maven`

### Executing the Project
- Clone the [Project](https://github.com/JoaoPedroCardoso/rabbitmq-poc).
- Open terminal and execute: 
```
$ docker-compose up
```
- After execute 
```
$ mvn clean install.
```
- Execute the RabbitmqApplication in project.

### API Documentation
- After run the application:

	> http://localhost:8080/swagger-ui.html