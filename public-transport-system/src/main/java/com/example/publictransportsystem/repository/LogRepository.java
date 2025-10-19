package com.example.publictransportsystem.repository;

import com.example.publictransportsystem.persitence.TransactionLogEntity;
import com.example.publictransportsystem.persitence.VehicleEntity;

import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
public class LogRepository extends BaseRepositoryJPA {

    @Transactional
    public void logMessage(TransactionLogEntity log) {
        entityManager.persist(log);
    }

    public List<TransactionLogEntity> getAllLogs() {
        return entityManager.createQuery("SELECT l FROM TransactionLogEntity l", TransactionLogEntity.class).getResultList();
    }
}
