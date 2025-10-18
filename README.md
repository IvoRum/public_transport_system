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

## OpenAPI link

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
