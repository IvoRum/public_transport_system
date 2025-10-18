package com.example.publictransportsystem.intit;

import com.example.publictransportsystem.persitence.PassengerEntity;
import com.example.publictransportsystem.persitence.VehicleEntity;
import com.example.publictransportsystem.persitence.VehicleTypeEntity;
import com.example.publictransportsystem.repository.VehicleRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DataInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void initData() {
        final VehicleTypeEntity vType = new VehicleTypeEntity();
        vType.setName("BUS");
        entityManager.persist(vType);

        VehicleEntity vehicle1 = new VehicleEntity();
        vehicle1.setRegistrationNumber("H7872BT");
        vehicle1.setType(vType);
        vehicle1.setPassengerCapacity(40);

        entityManager.persist(vehicle1);

        PassengerEntity passenger = new PassengerEntity();
        passenger.setName("Ivan");
        entityManager.persist(passenger);
    }
}
