package com.example.publictransportsystem.service;

import com.example.publictransportsystem.domain.dto.TicketDTO;
import com.example.publictransportsystem.domain.request.IssyTicketRequest;
import com.example.publictransportsystem.exeptions.EntityNotFoundException;
import com.example.publictransportsystem.persitence.PassengerEntity;
import com.example.publictransportsystem.persitence.TicketEntity;
import com.example.publictransportsystem.persitence.VehicleEntity;
import com.example.publictransportsystem.repository.PassengerRepository;
import com.example.publictransportsystem.repository.TicketRepository;
import com.example.publictransportsystem.repository.VehicleRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;

@Dependent
public class TicketService {

    @Inject
    private TicketRepository ticketRepository;
    @Inject
    private VehicleRepository vehicleRepository;
    @Inject
    private PassengerRepository passengerRepository;

    /**
     * Issue a new ticket based on the provided request.
     *
     * @param request Object containing ticket issuance details. Must not be null.
     * @return TicketDTO of the newly issued ticket.
     * @throws EntityNotFoundException if the vehicle or passenger specified in the request does not exist.
     */
    @Transactional
    public TicketDTO issyTicket(final IssyTicketRequest request) {
        assert request != null;

        final VehicleEntity foundVehicle = vehicleRepository.findVehicle(request.getVehicleNumber())
                .orElseThrow(() -> new EntityNotFoundException(VehicleEntity.class.getName()));

        final PassengerEntity foundPassenger = passengerRepository.findPassengerByName(request.getPersonName())
                .orElseThrow(() -> new EntityNotFoundException(PassengerEntity.class.getName()));

        final TicketEntity ticketToInsert = new TicketEntity();
        ticketToInsert.setCreatedOn(Timestamp.from(Instant.now()));
        ticketToInsert.setVehicle(foundVehicle);
        ticketToInsert.setPassenger(foundPassenger);
        ticketToInsert.generateTicketCode();

        final TicketEntity insertedTicket = ticketRepository.insertTicket(ticketToInsert)
                .orElseThrow(() -> new EntityNotFoundException(TicketEntity.class.getName()));
        return insertedTicket.toDTO();
    }
}
