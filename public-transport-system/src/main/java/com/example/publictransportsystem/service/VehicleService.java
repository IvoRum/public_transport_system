package com.example.publictransportsystem.service;

import com.example.publictransportsystem.persitence.VehicleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VehicleService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<VehicleEntity> getAllVehicles() {
        return entityManager.createQuery("SELECT v FROM VehiclesEntity v", VehicleEntity.class).getResultList();
    }
}
