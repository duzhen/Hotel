[![Build Status](https://travis-ci.org/duzhen/Hotel.svg?branch=master)](https://travis-ci.org/duzhen/Hotel)

# Dependency
* Spring Boot
* Java 8
* Jersey
* Mybatis
* H2 EmbeddedDB

# Environment Prepare
```
Java
Maven
Docker
```

# Test
```
mvn test
```

# Running
 ```
 mvn spring-boot:run
 ```
 
 # Build jar
 ```
  mvn package
 ```
 
 # Build docker
 ```
 mvn package docker:build
 docker run --network=host -p 8080:8080 -t hotel/hotel-search
 ```
 
 # Demo
 
 ## Search Hotel
 ```
curl --request GET \
  --url 'http://localhost:8080/rest/search?city=Buenos&country=Argentina'
 ```
 
 ## Create Hotel by GET method
 ```
curl --request GET \
  --url 'http://localhost:8080/rest/add?name=C%20Hotel&city=Buenos%20Aires&country=Argentina'
```

## Create Hotel by POST JSON
```
curl --request POST \
  --url http://localhost:8080/rest/add \
  --header 'Content-Type: application/json' \
  --data '{"name": "B Hotel", "city": "Buenos Bires", "country": "Argentina"}'
```

## Create Hotel by POST FORM
```
curl --request POST \
  --url http://localhost:8080/rest/add \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data 'name=B%20Hotel&city=Buenos%20Aires&country=Argentina'
```

## Create Hotel with invalid parameter
```
curl --request GET \
  --url 'http://localhost:8080/rest/add?name=12Hotel&city=Buenos%20Aires&country=Argentina'
```
```
curl --request GET \
  --url 'http://localhost:8080/rest/add?name=Hotel&city=Buenos-Aires&country=Argentina'
```
```
curl --request GET \
  --url 'http://localhost:8080/rest/add?name=Hotel&city=Buenos%20Aires&country=#%25^%28%29%2A&\\@$%21\\Argentina'
```

## Create Hotel with conflict country
```
curl --request GET \
  --url 'http://localhost:8080/rest/add?name=C%20Hotel&city=Buenos%20Aires&country=Country%20Argentina'
```

[API doc](https://documenter.getpostman.com/view/5494810/RzZ1rhy2)

# Feature Work

* Integrate Spring Annotation validation method
* Centralized Exception handling