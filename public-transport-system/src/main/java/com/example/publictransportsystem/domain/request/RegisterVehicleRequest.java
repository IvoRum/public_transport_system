package com.example.publictransportsystem.domain.request;

import com.example.publictransportsystem.domain.dto.VehicleDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public final class RegisterVehicleRequest {
    private final VehicleDTO vehicles;

    @JsonCreator
    public RegisterVehicleRequest(@JsonProperty("vehicle") VehicleDTO vehicles) {
        this.vehicles = vehicles;
    }

    public VehicleDTO getVehicles() {
        return vehicles;
    }
}
