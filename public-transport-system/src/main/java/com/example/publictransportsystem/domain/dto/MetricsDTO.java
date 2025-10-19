package com.example.publictransportsystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class MetricsDTO {
    private final Long totalVehicles;
    private final Long totalTicketsIssued;
    private final Long allValidatedTickets;

    @JsonCreator
    public MetricsDTO(
            @JsonProperty("total_vehicles") final Long totalVehicles,
            @JsonProperty("total_tickets") final Long totalTicketsIssued,
            @JsonProperty("tickets_validated") final Long allValidatedTickets) {
        this.totalVehicles = totalVehicles;
        this.totalTicketsIssued = totalTicketsIssued;
        this.allValidatedTickets = allValidatedTickets;
    }
    /**
     * Necessary for json serialization.
     */
    public Long getTotalVehicles() {
        return totalVehicles;
    }

    /**
     * Necessary for json serialization.
     */
    public Long getTotalTicketsIssued() {
        return totalTicketsIssued;
    }

    /**
     * Necessary for json serialization.
     */
    public Long getAllValidatedTickets() {
        return allValidatedTickets;
    }
}
