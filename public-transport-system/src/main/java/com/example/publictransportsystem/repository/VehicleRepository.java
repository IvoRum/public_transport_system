package com.example.publictransportsystem.repository;


import com.example.publictransportsystem.persitence.VehicleEntity;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Dependent
public final class VehicleRepository extends BaseRepositoryJPA{
//    @PersistenceContext
//    private EntityManager entityManager;

    public List<VehicleEntity> findAllVehicles() {
        return entityManager.createQuery("SELECT v FROM VehicleEntity v", VehicleEntity.class).getResultList();
    }
}
