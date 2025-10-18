package com.example.publictransportsystem.exeptions.mapper;

import com.example.publictransportsystem.domain.response.ValidateTicketResponse;
import com.example.publictransportsystem.domain.status.TicketRequestStatus;
import com.example.publictransportsystem.exeptions.EntityNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TicketWasValidatedExceptionMapper implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException exception) {
        return Response.status(Response.Status.NOT_MODIFIED)
                .entity(new ValidateTicketResponse(null, TicketRequestStatus.FAILED))
                .build();
    }
}
