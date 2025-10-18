package com.example.publictransportsystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public final class VehicleDTO {
    @JsonProperty("registration_number")
    private String registrationNumber;
    @JsonProperty("passenger_capacity")
    private int passengerCapacity;
    @JsonProperty
    private String type;

    public VehicleDTO(final String registrationNumber,final int passengerCapacity,final String type) {
        this.registrationNumber = registrationNumber;
        this.passengerCapacity = passengerCapacity;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public String getType() {
        return type;
    }
}
