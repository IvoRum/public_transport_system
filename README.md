# public_transport_system

A lightweight backend system for managing ticketing in a public transport compan.

This project is a `Java`-based web application built using `Maven`, leveraging the Java EE 8 API for enterprise features such as servlets, `JAX-RS`, and transactions. It uses `PostgreSQL` as the database and integrates `OpenAPI` for API documentation via `MicroProfile` and `SmallRye`. The application is packaged as a WAR file and deployed on `WildFly 18`, a robust Java EE application server. For deployment, the WAR is containerized using Docker, allowing easy and consistent deployment across environments.

# Build and run the app

```
cd public-transport-system
mvn clean package
docker-compose build
```

# Vehicle API Endpoints

### Get All Vehicles

URL: `/vehicles`

Method: `GET`

Description: Returns a list of all vehicles in the system.

Response:
200 OK — AllVehiclesResponse (list of vehicles and status)

Example:
`api/vehicles`

Result:

```Json
{
    "status": "SUCCESS",
    "vehicles": [
        {
            "type": "BUS",
            "registrationNumber": "H7872BT",
            "passengerCapacity": 40
        }
    ]
}
```

<hr></hr>

### Register a New Vehicle

URL: `/vehicles`

Method: `POST`

Description: Registers a new vehicle and returns the newly registered vehicle.

Request Body:

JSON object matching RegisterVehicleRequest

Response:
200 OK — RegisterVehicleResponse (registered vehicle and status)
If the vehicle type is not found: status VEHICLE_TYPE_NOT_FOUND
If the vehicle is already registered: status VEHICLE_ALREADY_REGISTERED

Example:

```json
{
  "vehicle": {
    "registration_number": "CB1234AB",
    "passenger_capacity": 60,
    "type": "BUS"
  }
}
```

Result:

```json
{
  "status": "SUCCESS",
  "vehicleDTO": {
    "type": "BUS",
    "registrationNumber": "CB1234AB",
    "passengerCapacity": 60
  }
}
```

<hr></hr>

# Ticket API Endpoints

### Issue a New Ticket

URL: `/api/ticket`

Method: `POST`

Request Body: JSON object matching IssyTicketRequest

Response: 200 OK with IssyTicketResponse (ticket details and status)

Example:

```json
{
  "vehicle_number": "H7872BT",
  "passenger_name": "Ivan"
}
```

Returns:

```json
{
  "ticket": {
    "vehicle": {
      "type": "BUS",
      "registrationNumber": "H7872BT",
      "passengerCapacity": 40
    },
    "code": "PT-C8828D03",
    "passenger": "Ivan",
    "validated": false,
    "createdOn": "2025-10-19T07:29:25.744Z",
    "validatedOn": null
  },
  "status": "SUCCESS"
}
```

<hr></hr>

### Validate Issued Ticket

URL: `/api/ticket/{ticketId}`

Method: `PATCH`

Path Parameter: ticketId (string, required) — The unique ticket code
Response: 200 OK with ValidateTicketResponse (ticket details and status)

Example: `/api/ticket/PT-C8828D03`

Return:

```Json
{
    "ticket": {
        "vehicle": {
            "type": "BUS",
            "registrationNumber": "H7872BT",
            "passengerCapacity": 40
        },
        "code": "PT-C8828D03",
        "passenger": "Ivan",
        "validated": true,
        "createdOn": "2025-10-19T07:29:25.744Z",
        "validatedOn": "2025-10-19T07:43:51.667Z"
    },
    "status": "SUCCESS"
}
```

<hr></hr>

### Get All Tickets for a Vehicle

URL: `/api/ticket/{registrationNumber}`

Method: `GET`

Path Parameter: registrationNumber (string, required) — The vehicle registration number
Response: 200 OK with GetAllTicketsForVehiclesResponse (list of tickets and status)

Example: `/api/ticket/H7872BT`

Result:

```Json
{
    "tickets": [
        {
            "vehicle": {
                "type": "BUS",
                "registrationNumber": "H7872BT",
                "passengerCapacity": 40
            },
            "code": "PT-9E8D3810",
            "passenger": "Ivan",
            "validated": false,
            "createdOn": "2025-10-19T07:29:00.982Z",
            "validatedOn": null
        },
        {
            "vehicle": {
                "type": "BUS",
                "registrationNumber": "H7872BT",
                "passengerCapacity": 40
            },
            "code": "PT-F3A9CE1B",
            "passenger": "Ivan",
            "validated": false,
            "createdOn": "2025-10-19T07:29:01.818Z",
            "validatedOn": null
        }
    ],
    "status": "SUCCESS"
}
```

<hr></hr>

# Metrics API

### Health check

URL: `/metrics/health`

Method: `GET`

Checks the health status of the application and its dependencies.

Response: 200 OK — Returns a HealthCheckResponse object containing health status.

<hr></hr>

### Get all Application logs

URL: `/metrics/logs`

MEthod: `GET`

Response:
200 OK — Returns a list of log entries as plain text.
If an error occurs, returns an error message.

<hr></hr>

### Get all application metrics

URL: `/metrics`

Method: `GET`

Retrieves aggregated application metrics, including:
Number of validated tickets
Total number of tickets
Number of registered vehicles

Response:
200 OK — Returns an AllMetricsResponse object with metrics and request status.
If an error occurs, returns a failed status.

<hr></hr>

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

## Docker compose build and push to repository

Buidl and prep

```
docker-compose build
docker tag public-transport-system ivorum/wildfly-postgre-public-transport:latest
docker login
docker push ivorum/wildfly-postgre-public-transport:latest

```

push db

```
docker tag public-transport-system-postgres-db-1 ivorum/public-transport-system-postgres-db:latest
docker login
docker push ivorum/wildfly-postgre-public-transport:latest
```

## Posgre set up

For tesp perposes I first run a clean container of posgres and then. after I see that wildfly is connecting properly to the db I`l create a new dicker file and after that I'l create a docker compose file.

```
docker build -f Dockerfile_db -t public_transport_db:latest .
```

```
docker run -d --name public_transport_db -p 5432:5432 -v pgdata:/var/lib/postgresql/data public_transport_db:latest
```

# Persisntace configs

Това е за да се интециализа схема с която апликацията ти ще се свърже към дб, и по-конкретно посгрес.

```xml

  <?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">
  <persistence-unit name="default" transaction-type="JTA">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <!--<jta-data-source>java:/jdbc/PublicTransportDS</jta-data-source>-->
    <properties>
      <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
      <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://postgres-db:5432/public_transport_db"/>
      <property name="javax.persistence.jdbc.user" value="myuser"/>
      <property name="javax.persistence.jdbc.password" value="mysecretpassword"/>

      <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQL95Dialect"/>
      <property name="hibernate.hbm2ddl.auto" value="update"/>
      <property name="hibernate.show_sql" value="true"/>
      <property name="hibernate.format_sql" value="true"/>
    </properties>
  </persistence-unit>

```

jboss-deployment-structure.xml

```xml
<jboss-deployment-structure>
    <deployment>
        <dependencies>
            <module name="org.eclipse.microprofile.openapi"/>
        </dependencies>
    </deployment>
</jboss-deployment-structure>
```

## Anotaions

@Dependent - Е за когато искаме нова иснтанция за всяко инйектиране. Тази анотация е по подразбиране и е с най-къс живот.

@RequestScoped - Е един път за всяко исвикване по HTTP

@ApplicationScoped - Единпът се инициализира за цялото проложение. Нещо като singoltun

## OpenAPI link

None of them work dont try

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/openapi

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/openapi.json

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/api/openapi

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/api

http://localhost:8080/openapi

http://localhost:8080/swagger

http://localhost:8080/openapi/swagger

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/swagger

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/api/swagger

new

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/api/openapi

final

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/openapi

http://localhost:8080/public-transport-system-1.0-SNAPSHOT/openapi/ui
