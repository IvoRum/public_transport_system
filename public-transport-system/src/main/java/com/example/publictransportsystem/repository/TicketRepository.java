package com.example.publictransportsystem.repository;

import com.example.publictransportsystem.persitence.TicketEntity;

import javax.enterprise.context.Dependent;
import java.util.Optional;

@Dependent
public final class TicketRepository extends BaseRepositoryJPA{

    /**
     * Inserts a new ticket into the database.
     *
     * @param ticketToInsert the ticket entity to be inserted. Must not be null.
     * @return Optional containing the inserted TicketEntity if successful, or Optional.empty() if insertion failed.
     */
    public Optional<TicketEntity> insertTicket(TicketEntity ticketToInsert) {
        assert ticketToInsert != null : "Ticket to insert must not be null";

        try{
            entityManager.persist(ticketToInsert);
            return Optional.of(ticketToInsert);
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
