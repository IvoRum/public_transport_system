package com.example.publictransportsystem.domain.request;

import com.example.publictransportsystem.domain.dto.VehicleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public final class RegisterVehicleRequest {
    @JsonProperty("vehicle")
    private VehicleDTO vehicleDTO;

    public RegisterVehicleRequest(final VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }

    public RegisterVehicleRequest() {
        // Required for Jackson deserialization
    }

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }
}
