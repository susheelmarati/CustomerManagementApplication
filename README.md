# Customer Management

Project contains Customer management crud operations.

[![Java v17]](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot v3]](https://jakarta.ee/specifications/platform/10/)

## Requirements

The list of tools required to build and run the project:

* Open JDK 17
* Apache Maven plugin 3.4
* Postgres database
* docker desktop
* minikube

Requirements for particular branch are provided in branch `README.md` file.

## Building

In order to build project use:

```bash
mvn clean package
```

```bash
JAVA_HOME=<path_to_jdk_home> mvn package
```

## Configuration

Configuration can be updated in `application.properties` or using environment variables.

## Running

In order to run using embedded Apache Tomcat server use:

```bash
java -jar target/customer-1.0.0-SNAPSHOT.jar
```

If your default `java` is not from JDK 17 or higher use:

```bash
<path_to_jdk_home>/bin/java -jar target/customer-1.0.0-SNAPSHOT.jar
```

In `customer`:

* `pom.xml` - project configuration with Spring dependencies

* `CustomerManagement` - Spring Boot application entry point, bean definition,
* `com/management/customer/entity` - entity classes for customer,

  #### public class Customer {
  #### private UUID id; //pk
  ####  private String firstName;  //not null
  ####  private String middleName; 
  ####  private String lastName;  //not null
  ####  private String emailId;  //not null
  ####  private String mobile;  //not null
#### }
* `com/management/customer/repository` - repository classes for customer,
* `com/management/customer/service` - service classes for customer,
* `com/management/customer/controller` - controller classes for customer,

installation kubectl and minikube
  
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-darwin-arm64
sudo install minikube-darwin-arm64 /usr/local/bin/minikube
 
# deploying in minikube
  minikube docker-env
  minikube start
  docker build . -t  customerapp
  minikube image load customerapp
  minikube image ls --format table
  kubectl run first-container --image=customerapp --image-pull-policy=Never
  kubectl logs first-container

