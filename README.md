# Reading is Good Aplication
- This application developed with Java(11) and SpringBoot FrameWork
- Maven build automation tool is used.
- Lombok library is used for clean code
- Mockito is used for unit testing
- ModelMapper library is used for dto to entity mapping and vice versa
## Api Documantation:
- Swagger Api documantation is used
- Api documantation located at http://localhost:8080/swagger-ui/ url. (authentication not needed)

##Security
- Api’s secured by Bearer Token
- Token can be taken by http://localhost:8080/user service

##Database
- MySql rdbms is used
- Liquibase used for db scripts

## Test Coverage
- Class Coverage: 71%
- Method Coverage: 53%
- Line Coverage: 43%

##Containerization
- Application containerized with docker
- To build a docker image, run this maven command "spring-boot:build-image -DskipTests"
Note: DockerFile not used. Maven springboot build plugin is used for creating docker image.



