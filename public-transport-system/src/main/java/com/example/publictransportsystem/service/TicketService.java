package com.example.publictransportsystem.service;

import com.example.publictransportsystem.domain.dto.TicketDTO;
import com.example.publictransportsystem.domain.request.IssyTicketRequest;
import com.example.publictransportsystem.exeptions.EntityNotFoundException;
import com.example.publictransportsystem.exeptions.TicketWasValidatedException;
import com.example.publictransportsystem.persitence.PassengerEntity;
import com.example.publictransportsystem.persitence.TicketEntity;
import com.example.publictransportsystem.persitence.VehicleEntity;
import com.example.publictransportsystem.repository.PassengerRepository;
import com.example.publictransportsystem.repository.TicketRepository;
import com.example.publictransportsystem.repository.VehicleRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing tickets in the public transport system.
 */
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

        final TicketEntity insertedTicket = ticketRepository.persist(ticketToInsert)
                .orElseThrow(() -> new EntityNotFoundException(TicketEntity.class.getName()));
        return insertedTicket.toDTO();
    }

    /**
     * Validate a ticket using its unique ticket code.
     *
     * @param ticketCode the unique code of the ticket to be validated. Must not be null or empty.
     * @return TicketDTO of the validated ticket.
     * @throws EntityNotFoundException if the ticket with the specified code does not exist.
     * @throws TicketWasValidatedException if the ticket has already been validated.
     */
    @Transactional
    public TicketDTO validateTicket(@NotNull @NotEmpty final String ticketCode) {
        assert ticketCode != null : "Ticket code must not be null";

        final TicketEntity foundTicket = ticketRepository.findTicketByCode(ticketCode)
                .orElseThrow(() -> new EntityNotFoundException(TicketEntity.class.getName()));

        System.out.println("Found ticket: " + foundTicket.getCode() + ", validated: " + foundTicket.isValidated());
        if(foundTicket.isValidated()){
            throw new TicketWasValidatedException(ticketCode);
        }
        foundTicket.setValidated(true);
        foundTicket.setValidatedOn(Timestamp.from(Instant.now()));

        final TicketEntity persistedTicket = ticketRepository.persist(foundTicket)
                .orElseThrow(() -> new EntityNotFoundException(TicketEntity.class.getName()));
        return persistedTicket.toDTO();
    }

    /**
     * Retrieve all tickets associated with a specific vehicle registration number.
     *
     * @param registrationNumber the registration number of the vehicle. Must not be null or empty.
     * @return List of TicketDTOs associated with the specified vehicle.
     */
    public List<TicketDTO> getTicketsForVehicle(@NotNull @NotEmpty final String registrationNumber) {
        assert registrationNumber != null : "Registration number must not be null";

        List<TicketEntity> tickets = ticketRepository.findTicketsByVehicleRegNr(registrationNumber);

        return tickets.stream()
                .map(TicketEntity::toDTO)
                .collect(Collectors.toList());
    }
}
