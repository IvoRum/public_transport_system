package com.example.publictransportsystem.repository;

import com.example.publictransportsystem.domain.dto.MetricsDTO;
import com.example.publictransportsystem.persitence.TicketEntity;

import javax.enterprise.context.Dependent;

@Dependent
public class MetricsRepository extends BaseRepositoryJPA {

    public MetricsDTO getAllMetrics() {
        final String sql = "SELECT " +
                "(SELECT COUNT(*) FROM vehicle) AS totalVehicles, " +
                "(SELECT COUNT(*) FROM ticket) AS totalTicketsIssued, " +
                "(SELECT COUNT(*) FROM ticket WHERE validated = TRUE) AS allValidatedTickets";

        Object[] result = (Object[]) entityManager.createNativeQuery(sql).getSingleResult();
        return new MetricsDTO(
                ((Number) result[0]).longValue(),
                ((Number) result[1]).longValue(),
                ((Number) result[2]).longValue()
        );
    }
}
