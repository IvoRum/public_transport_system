package com.example.publictransportsystem.service;

import com.example.publictransportsystem.persitence.VehiclesEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VehicleService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<VehiclesEntity> getAllVehicles() {
        return entityManager.createQuery("SELECT v FROM VehiclesEntity v", VehiclesEntity.class).getResultList();
    }
}
