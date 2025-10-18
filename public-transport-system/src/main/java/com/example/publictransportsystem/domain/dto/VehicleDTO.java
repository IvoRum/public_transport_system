package com.example.publictransportsystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public final class VehicleDTO {
    @NotBlank(message = "Registration number cannot be empty")
    @Size(max = 20, message = "Registration number cannot exceed 20 characters")
    private final String registrationNumber;
    @Min(value = 1, message = "Passenger capacity must be at least 1")
    @Max(value = 200, message = "Passenger capacity cannot exceed 200")
    private final int passengerCapacity;
    @NotBlank(message = "Vehicle type cannot be empty")
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

    /**
     * Get the value of registrationNumber. Necessary for json serialization.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Get the value of passengerCapacity. Necessary for json serialization.
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    /**
     * Get the value of type. Necessary for json serialization.
     */
    public String getType() {
        return type;
    }
}
