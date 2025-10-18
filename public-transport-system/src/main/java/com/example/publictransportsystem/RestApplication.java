package com.example.publictransportsystem;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")

// MicroProfile OpenAPI Annotation to define global API metadata
@OpenAPIDefinition(
        info = @Info(
                title = "WildFly 18 Service API",
                version = "1.0.0",
                description = "Documentation for the Java EE 8 Application running on WildFly 18, generated using SmallRye/MicroProfile OpenAPI.",
                license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT"),
                contact = @Contact(name = "Support", email = "support@example.com")
        ),
        servers = {
                @Server(url = "/wildfly18-openapi-app/api", description = "Local WildFly Server")
        }
)
public class RestApplication extends Application {
}
