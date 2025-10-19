package com.example.publictransportsystem.domain.response;

import com.example.publictransportsystem.domain.dto.TicketDTO;
import com.example.publictransportsystem.domain.status.TicketRequestStatus;

import java.util.List;

public final class GetAllTicketsForVehiclesResponse {
    private final List<TicketDTO> tickets;
    private final TicketRequestStatus status;

    public GetAllTicketsForVehiclesResponse(final List<TicketDTO> tickets,final TicketRequestStatus status) {
        this.tickets = tickets;
        this.status = status;
    }

    /**
     * Necessary for json serialization.
     */
    public List<TicketDTO> getTickets() {
        return tickets;
    }

    /**
     * Necessary for json serialization.
     */
    public TicketRequestStatus getStatus() {
        return status;
    }
}
