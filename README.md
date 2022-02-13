# Reading is Good Aplication
This application developed with Java and SpringBoot FrameWork

## Api Documantation:
- Swagger Api documantation is used
- Api documantation located at …/swagger-ui/ url. (authentication not needed)

##Security
- Api’s secured by bearer token
- Token can be taken by http://localhost:8080/user service

##Database
- MySql rdbms is used
- Liquibase used for db scripts.

##Containerization
- Application containerized with docker
- To build a docker image, run this maven command "spring-boot:build-image -DskipTests"
Note: DockerFile not used. Maven springboot build plugin is used for creating docker image.



