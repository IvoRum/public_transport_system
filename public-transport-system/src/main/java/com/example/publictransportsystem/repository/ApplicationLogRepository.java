package com.example.publictransportsystem.repository;

import com.example.publictransportsystem.persitence.ApplicationLogEntity;

import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;
import java.util.List;

@Dependent
public class ApplicationLogRepository extends BaseRepositoryJPA {

    @Transactional
    public void logMessage(ApplicationLogEntity log) {
        entityManager.persist(log);
    }

    public List<ApplicationLogEntity> getAllLogs() {
        return entityManager.createQuery("SELECT l FROM ApplicationLogEntity l", ApplicationLogEntity.class).getResultList();
    }
}
