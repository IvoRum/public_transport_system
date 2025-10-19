package com.example.publictransportsystem.service;

import com.example.publictransportsystem.domain.response.HealthCheckResponse;
import com.example.publictransportsystem.repository.BaseRepositoryJPA;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Dependent
public class MetricsService {
    @Inject
    @Named("baseRepositoryJPA")
    private BaseRepositoryJPA baseRepository;

    /**
     * Perform a health check on the database connection.
     *
     * @return HealthCheckResponse indicating the status of the database connection.
     */
    @Transactional
    public HealthCheckResponse healthCheck() {
        try{
            baseRepository.isDBConnectionHealthy();
            return HealthCheckResponse.up("Database connection is healthy");
        } catch (Exception e) {
            return HealthCheckResponse.down("Database connection is unhealthy: " + e.getMessage());
        }
    }
}
