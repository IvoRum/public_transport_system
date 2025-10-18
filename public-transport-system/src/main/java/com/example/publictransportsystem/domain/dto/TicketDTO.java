package com.example.publictransportsystem.domain.dto;

import com.example.publictransportsystem.persitence.PassengerEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public final class TicketDTO {
    private final VehicleDTO vehicle;
    private final String code;
    private final Timestamp createdOn;
    private final Timestamp validatedOn;
    private final PassengerEntity passengerEntity;
    private final boolean validated;

    @JsonCreator
    public TicketDTO( @JsonProperty("vehicle") final VehicleDTO vehicle,
                      @JsonProperty("code") final String code,
                      @JsonProperty("created_on") final Timestamp createdOn,
                      @JsonProperty("validated_on") final Timestamp validatedOn,
                      @JsonProperty("passenger_entity") final PassengerEntity passengerEntity,
                      @JsonProperty("validated") final boolean validated) {
        this.vehicle = vehicle;
        this.code = code;
        this.createdOn = createdOn;
        this.validatedOn = validatedOn;
        this.passengerEntity = passengerEntity;
        this.validated = validated;
    }

    public VehicleDTO getVehicle() {return vehicle;}

    public String getCode() {return code;}

    public Timestamp getCreatedOn() {return createdOn;}

    public Timestamp getValidatedOn() {return validatedOn;}

    public PassengerEntity getPassengerEntity() {return passengerEntity;}

    public boolean isValidated() {return validated;}
}
