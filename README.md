# bicycle-lockers-service

As part of FRAME, bicycle lockers service provides for customers the option to reserve a bicycle locker, while it also provides services to manage the customers and bicycle lockers.

## Requirements

For building and running the application you need:

- [JDK 17](https://jdk.java.net/17/)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com/products/docker-desktop) (Optional)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `nl.ns.frame.bicyclelockersservice.BicycleLockersServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

You can also pull this project from the docker hub like so:

```shell
docker pull mbt4nw1r/bicycle-lockers-service
docker run -p 1111:1111 mbt4nw1r/bicycle-lockers-service
```

## Open API
Once the application is running, you can see a list of overview of which endpoints are available with the given description by visiting:
http://localhost:1111/swagger-ui/index.html
