package com.example.publictransportsystem.domain.response;

import com.example.publictransportsystem.domain.dto.VehicleDTO;
import com.example.publictransportsystem.domain.status.VehicleRequestStatus;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.List;

public final class AllVehiclesResponse extends BaseResponse {
    private final List<VehicleDTO> vehicles;

    public AllVehiclesResponse(List<VehicleDTO> vehicles, VehicleRequestStatus status) {
        super(status);
        this.vehicles = vehicles;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }
}
