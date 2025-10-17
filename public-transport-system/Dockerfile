FROM jboss/wildfly:18.0.1.Final

# Set up db config
USER root
WORKDIR /opt/jboss/wildfly
RUN mkdir -p modules/org/postgresql/main
COPY drivers/postgresql-42.7.8.jar modules/org/postgresql/main/
COPY drivers/module.xml modules/org/postgresql/main/

# Copy the war file
WORKDIR /opt/jboss/wildfly/standalone/deployments/
COPY target/public-transport-system-1.0-SNAPSHOT.war ./
COPY standalone.xml /opt/jboss/wildfly/standalone/configuration/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
