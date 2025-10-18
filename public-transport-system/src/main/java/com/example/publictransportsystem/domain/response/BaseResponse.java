package com.example.publictransportsystem.domain.response;

import com.example.publictransportsystem.domain.status.VehicleRequestStatus;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class BaseResponse {
    private final VehicleRequestStatus status;

    public BaseResponse(final VehicleRequestStatus status) {
        this.status = status;
    }

    public VehicleRequestStatus getStatus() {
        return status;
    }
}
