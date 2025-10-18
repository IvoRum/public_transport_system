package com.example.publictransportsystem.domain.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.validation.constraints.NotNull;

@Immutable
public final class IssyTicketRequest {
    @NotNull
    private final String vehicleNumber;
    @NotNull
    private final String personName;

    @JsonCreator
    public IssyTicketRequest(
            @JsonProperty("vehicle_number") final String vehicleNumber,
            @JsonProperty("passenger_name") final String personName) {
        this.vehicleNumber = vehicleNumber;
        this.personName = personName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getPersonName() {
        return personName;
    }
}
