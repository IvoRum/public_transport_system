package com.example.publictransportsystem.controller;

import com.example.publictransportsystem.domain.request.IssyTicketRequest;
import com.example.publictransportsystem.domain.response.IssyTicketResponse;
import com.example.publictransportsystem.domain.status.TicketRequestStatus;
import com.example.publictransportsystem.service.TicketService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ticket")
@Tag(name = "Ticket", description = "Creating and validation tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TicketController {

    @Inject
    private TicketService ticketService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Register a new vehicle", description = "Returns a the newly registered vehicle")
    @APIResponse(responseCode = "200", description = "List of vehicles")
    public IssyTicketResponse issyTicket(@Valid @RequestBody IssyTicketRequest request){
        return new IssyTicketResponse(ticketService.issyTicket(request), TicketRequestStatus.SUCCESS);
    }
}
