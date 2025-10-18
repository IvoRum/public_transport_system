package com.example.publictransportsystem.domain.response;

import com.example.publictransportsystem.domain.dto.TicketDTO;
import com.example.publictransportsystem.domain.status.TicketRequestStatus;

import javax.validation.Valid;

public final class ValidateTicketResponse {
    private final TicketDTO ticket;
    private final TicketRequestStatus status;

    public ValidateTicketResponse(@Valid final TicketDTO ticket, final TicketRequestStatus status) {
        this.ticket = ticket;
        this.status = status;
    }

    /**
     * Get the value of ticket. Necessary for json serialization.
     * @return ticket
     */
    public TicketDTO getTicket() {return ticket;}

    /**
     *  Get the value of status. Necessary for json serialization.
     * @return status
     */
    public TicketRequestStatus getStatus() {return status;}
}
