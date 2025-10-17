package com.example.publictransportsystem.controller;
import com.example.publictransportsystem.persitence.VehiclesEntity;
import com.example.publictransportsystem.service.VehicleService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vehicles")
public class VihicleController {

    @Inject
    private VehicleService vehicleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VehiclesEntity> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
}
