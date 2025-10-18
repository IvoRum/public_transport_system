package com.example.publictransportsystem.domain.request;

import com.example.publictransportsystem.domain.dto.VehicleDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Request to register a vehicle. Will contain vehicle details. Will map to json request body.
 */
@Immutable
public final class RegisterVehicleRequest {
    @Valid
    @NotNull
    private final VehicleDTO vehicles;

    @JsonCreator
    public RegisterVehicleRequest(@Valid @JsonProperty("vehicle") VehicleDTO vehicles) {
        this.vehicles = vehicles;
    }

    public VehicleDTO getVehicles() {
        return vehicles;
    }
}
