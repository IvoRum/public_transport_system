# public_transport_system

A lightweight backend system for managing ticketing in a public transport compan

# Dev notes

## Run WildFlt 18

1- Go to the bin where the application server is located and run:

```
standalone.bat
```

URL for endpoint

2- Build the package

```
nvm clean package
```

3- Coppy to directory

```
wildfly-18.0.1.Final\standalone\deployments
```

Wildfly will aothomatecly detect and deply the war file

4- Test if the server is reachable

```
http://localhost:8080/public-transport-system-1.0-SNAPSHOT
```

## Package instal and run with Docker comands

Docker run Wildfly whit the last build version

```
docker run -d -p 8080:8080 wildfly-public-transport
```

Docker steps for upload

Test if the server is reachable

```
http://localhost:8080/public-transport-system-1.0-SNAPSHOT
```

## Publish docker version

```
docker login
docker build -t ivorum/wildfly-public-transport:latest .
docker push ivorum/wildfly-public-transport:latest
```

## Docker run

```
docker run -it --rm -p 8080:8080 -p 9990:9990 ivorum/wildfly-public-transport:latest

```

port `9990` is for wildFly admin acces to configure whe web server.

Here is the url to the Docker repository:
https://hub.docker.com/repository/docker/ivorum/wildfly-public-transport/general

## Posgre set up
