package com.example.publictransportsystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class VehicleDTO {
    private final String registrationNumber;
    private final int passengerCapacity;
    private final String type;

    @JsonCreator
    public VehicleDTO(
            @JsonProperty("registration_number") String registrationNumber,
            @JsonProperty("passenger_capacity") int passengerCapacity,
            @JsonProperty("type") String type
    ) {
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
