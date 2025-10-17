package com.example.publictransportsystem.intit;

import com.example.publictransportsystem.persitence.VehiclesEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DataInitializer {

    @PersistenceContext
    private EntityManager entityManager;


    @PostConstruct
    public void initData() {
        VehiclesEntity vehicle1 = new VehiclesEntity();
        vehicle1.setName("Bus A");

        entityManager.persist(vehicle1);
    }
}
