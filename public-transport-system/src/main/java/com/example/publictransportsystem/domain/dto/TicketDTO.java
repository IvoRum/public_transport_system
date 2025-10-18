package com.example.publictransportsystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public final class TicketDTO {
    private final VehicleDTO vehicle;
    private final String code;
    private final Timestamp createdOn;
    private final Timestamp validatedOn;
    private final String passenger;
    private final boolean validated;

    @JsonCreator
    public TicketDTO( @JsonProperty("vehicle") final VehicleDTO vehicle,
                      @JsonProperty("code") final String code,
                      @JsonProperty("created_on") final Timestamp createdOn,
                      @JsonProperty("validated_on") final Timestamp validatedOn,
                      @JsonProperty("passenger") final String passengerEntity,
                      @JsonProperty("validated") final boolean validated) {
        this.vehicle = vehicle;
        this.code = code;
        this.createdOn = createdOn;
        this.validatedOn = validatedOn;
        this.passenger = passengerEntity;
        this.validated = validated;
    }

    /**
     * Get the value of vehicle. Necessary for json serialization.
     */
    public VehicleDTO getVehicle() {return vehicle;}

    /**
     * Get the value of code. Necessary for json serialization.
     */
    public String getCode() {return code;}

    /**
     * Get the value of createdOn. Necessary for json serialization.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    public Timestamp getCreatedOn() {return createdOn;}

    /**
     * Get the value of validatedOn. Necessary for json serialization.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    public Timestamp getValidatedOn() {return validatedOn;}

    /**
     * Get the value of passenger. Necessary for json serialization.
     */
    public String getPassenger() {return passenger;}

    /**
     * Get the value of validated. Necessary for json serialization.
     */
    public boolean isValidated() {return validated;}
}
