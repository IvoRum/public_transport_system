package com.example.publictransportsystem.domain.response;

import com.example.publictransportsystem.domain.dto.VehicleDTO;
import com.example.publictransportsystem.domain.status.VehicleRequestStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class RegisterVehicleResponse extends BaseResponse {
    @JsonProperty("vehicle")
    private final VehicleDTO vehicleDTO;

    public RegisterVehicleResponse(final VehicleDTO vehicleDTO,final VehicleRequestStatus status) {
        super(status);
        this.vehicleDTO = vehicleDTO;
    }
    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }
}
