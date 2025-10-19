package com.example.publictransportsystem.controller;
import com.example.publictransportsystem.domain.request.RegisterVehicleRequest;
import com.example.publictransportsystem.domain.response.RegisterVehicleResponse;
import com.example.publictransportsystem.domain.response.AllVehiclesResponse;
import com.example.publictransportsystem.domain.status.VehicleRequestStatus;
import com.example.publictransportsystem.exeptions.EntityNotFoundException;
import com.example.publictransportsystem.exeptions.VehicleRegisteredException;
import com.example.publictransportsystem.interceptor.TransactionLogged;
import com.example.publictransportsystem.service.VehicleService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/vehicles")
@Tag(name = "Vehicles", description = "Operations related to vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@TransactionLogged
public class VehicleController {

    @Inject
    private VehicleService vehicleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all vehicles", description = "Returns a list of all vehicles in the system")
    @APIResponse(responseCode = "200", description = "List of vehicles")
    public AllVehiclesResponse getAllVehicles() {
        return new AllVehiclesResponse(vehicleService.getAllVehicles(), VehicleRequestStatus.SUCCESS);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Register a new vehicle", description = "Returns a the newly registered vehicle")
    @APIResponse(responseCode = "200", description = "List of vehicles")
    public RegisterVehicleResponse registerVehicle(@Valid @RequestBody final RegisterVehicleRequest request) {
        try {
            return new RegisterVehicleResponse(vehicleService.registerVehicle(request), VehicleRequestStatus.SUCCESS);
        } catch (EntityNotFoundException e) {
            return new RegisterVehicleResponse(request.getVehicles(), VehicleRequestStatus.VEHICLE_TYPE_NOT_FOUND);
        } catch (VehicleRegisteredException e){
            return new RegisterVehicleResponse(request.getVehicles(), VehicleRequestStatus.VEHICLE_ALREADY_REGISTERED);
        }
    }
}
