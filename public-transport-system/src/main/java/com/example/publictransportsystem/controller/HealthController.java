package com.example.publictransportsystem.controller;

import com.example.publictransportsystem.domain.response.HealthCheckResponse;
import com.example.publictransportsystem.service.MetricsService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/health")
@ApplicationScoped
public class HealthController {

    @Inject
    private MetricsService metricsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Health Check",
            description = "Checks the health status of the application and its dependencies")
    @APIResponse(responseCode = "200", description = "If the application is healthy")
    public HealthCheckResponse healthCheck() {
        return metricsService.healthCheck();
    }
}
