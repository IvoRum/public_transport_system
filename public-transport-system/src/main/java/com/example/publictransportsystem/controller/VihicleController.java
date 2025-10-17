package com.example.publictransportsystem.controller;
import com.example.publictransportsystem.persitence.VehicleEntity;
import com.example.publictransportsystem.service.VehicleService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vehicles")
@Tag(name = "Vehicles", description = "Operations related to vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VihicleController {

    @Inject
    private VehicleService vehicleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all vehicles", description = "Returns a list of all vehicles in the system")
    @APIResponse(responseCode = "200", description = "List of vehicles")
    public List<VehicleEntity> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
}
