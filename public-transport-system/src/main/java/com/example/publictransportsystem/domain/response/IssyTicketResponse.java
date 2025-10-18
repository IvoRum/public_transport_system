package com.example.publictransportsystem.domain.response;

import com.example.publictransportsystem.domain.dto.TicketDTO;
import com.example.publictransportsystem.domain.status.TicketRequestStatus;

import javax.validation.Valid;

public class IssyTicketResponse {
    private TicketDTO ticket;
    private TicketRequestStatus status;

    public IssyTicketResponse(@Valid final TicketDTO ticket,final TicketRequestStatus status) {
        this.ticket = ticket;
        this.status = status;
    }

    public TicketDTO getTicket() {return ticket;}

    public TicketRequestStatus getStatus() {return status;}
}
