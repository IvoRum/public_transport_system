package com.example.publictransportsystem.repository;

import com.example.publictransportsystem.persitence.TicketEntity;

import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Dependent
public class TicketRepository extends BaseRepositoryJPA{

    /**
     * Inserts a new ticket into the database.
     *
     * @param ticketToInsert the ticket entity to be inserted. Must not be null.
     * @return Optional containing the inserted TicketEntity if successful, or Optional.empty() if insertion failed.
     */
    public Optional<TicketEntity> persist(TicketEntity ticketToInsert) {
        assert ticketToInsert != null : "Ticket to insert must not be null";

        try{
            entityManager.persist(ticketToInsert);
            return Optional.of(ticketToInsert);
        } catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * Finds a ticket by its unique ticket code.
     *
     * @param ticketCode the unique code of the ticket. Must not be null or empty.
     * @return Optional containing the TicketEntity if found, or Optional.empty() if not found.
     */
    public Optional<TicketEntity> findTicketByCode(@NotNull @NotEmpty String ticketCode) {
        assert ticketCode != null : "Ticket code must not be null";

        try {
            TicketEntity ticket = entityManager.createQuery(
                    "SELECT t FROM TicketEntity t WHERE t.code = :code", TicketEntity.class)
                    .setParameter("code", ticketCode)
                    .getSingleResult();
            return Optional.of(ticket);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Finds all tickets associated with a vehicle's registration number.
     *
     * @param registrationNumber the registration number of the vehicle. Must not be null.
     * @return List of TicketEntity associated with the vehicle. The list will be {@code empty} if no tickets are found.
     */
    public List<TicketEntity> findTicketsByVehicleRegNr(final String registrationNumber) {
        assert registrationNumber != null : "Registration number must not be null";

        return entityManager.createQuery(
                "SELECT t FROM TicketEntity t WHERE t.vehicle.registrationNumber = :regNr", TicketEntity.class)
                .setParameter("regNr", registrationNumber)
                .getResultList();
    }
}
