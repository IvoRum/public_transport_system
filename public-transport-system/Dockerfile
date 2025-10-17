FROM jboss/wildfly:18.0.1.Final

WORKDIR /opt/jboss/wildfly/standalone/deployments/

COPY target/public-transport-system-1.0-SNAPSHOT.war ./

#COPY standalone.xml /opt/jboss/wildfly/standalone/configuration/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
