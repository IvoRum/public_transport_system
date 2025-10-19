package com.example.publictransportsystem.service;

import com.example.publictransportsystem.domain.response.HealthCheckResponse;
import com.example.publictransportsystem.repository.BaseRepositoryJPA;
import com.example.publictransportsystem.repository.LogRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for performing system metrics and health checks.
 */
@Dependent
public class MetricsService {

    @Inject
    @Named("baseRepositoryJPA")
    private BaseRepositoryJPA baseRepository;
    @Inject
    private LogRepository logRepository;

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

    /**
     * Retrieve all application logs from the log file.
     *
     * @return List of log entries as strings.
     * @throws Exception if there is an error reading the log file.
     */
    @Transactional
    public List<String> getAllLogs() throws Exception {
        return logRepository.getAllLogs().stream().map(log -> log.getTimestamp() + " " + log.getMessage())
        .collect(Collectors.toList());
    }
}
