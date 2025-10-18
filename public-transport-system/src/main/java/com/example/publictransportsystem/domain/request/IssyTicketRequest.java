package com.example.publictransportsystem.domain.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request to issue a ticket. Will contain vehicle number and passenger name. Will map to json request body.
 */
@Immutable
public final class IssyTicketRequest {
    @NotNull
    @NotEmpty
    @Size(max = 20, message = "Registration number cannot exceed 20 characters")
    private final String vehicleNumber;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
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
