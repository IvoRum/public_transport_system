package com.example.publictransportsystem.controller;

import com.example.publictransportsystem.domain.request.IssyTicketRequest;
import com.example.publictransportsystem.domain.response.GetAllTicketsForVehiclesResponse;
import com.example.publictransportsystem.domain.response.IssyTicketResponse;
import com.example.publictransportsystem.domain.response.ValidateTicketResponse;
import com.example.publictransportsystem.domain.status.TicketRequestStatus;
import com.example.publictransportsystem.exeptions.EntityNotFoundException;
import com.example.publictransportsystem.exeptions.TicketWasValidatedException;
import com.example.publictransportsystem.interceptor.TransactionLogged;
import com.example.publictransportsystem.service.TicketService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/ticket")
@Tag(name = "Ticket", description = "Creating and validation tickets")
@ApplicationScoped
@TransactionLogged
public class TicketController {

    @Inject
    private TicketService ticketService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Issue a new ticket",
            description = "Returns a the newly issued vehicle. With a unique ticket code")
    @APIResponse(responseCode = "200", description = "List of vehicles")
    public IssyTicketResponse issyTicket(@Valid @RequestBody IssyTicketRequest request){
        try{
            return new IssyTicketResponse(ticketService.issyTicket(request), TicketRequestStatus.SUCCESS);
        } catch (EntityNotFoundException e){
            return new IssyTicketResponse(null, TicketRequestStatus.INVALID_TICKET_DATA);
        }
    }

    @PATCH
    @Path("/{ticketId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Validates issued ticket",
            description = "Returns the ticket details if the ticket code is valid")
    @APIResponse(responseCode = "200", description = "List of vehicles")
    public ValidateTicketResponse validateTicket(@PathParam("ticketId") @NotNull @NotEmpty String ticketCode){
        try{
            return new ValidateTicketResponse(ticketService.validateTicket(ticketCode), TicketRequestStatus.SUCCESS);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return new ValidateTicketResponse(null, TicketRequestStatus.INVALID_TICKET_DATA);
        }catch (TicketWasValidatedException e){
            return new ValidateTicketResponse(null, TicketRequestStatus.TICKET_REGISTERED_ALREADY);
        }
    }

    @GET
    @Path("/{registrationNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Validates issued ticket",
            description = "Returns the ticket details if the ticket code is valid")
    @APIResponse(responseCode = "200", description = "List of vehicles")
    public GetAllTicketsForVehiclesResponse getTicketsForVihivle(@PathParam("registrationNumber") @NotNull @NotEmpty String registrationNumber){
        try{
            return new GetAllTicketsForVehiclesResponse(ticketService.getTicketsForVehicle(registrationNumber), TicketRequestStatus.SUCCESS);
        } catch (IllegalArgumentException e){
            return new GetAllTicketsForVehiclesResponse(null, TicketRequestStatus.FAILED);
        }
    }
}
