package com.example.publictransportsystem.service;

import com.example.publictransportsystem.persitence.VehicleEntity;
import com.example.publictransportsystem.repository.VehicleRepository;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class VehicleService {

    @Inject
    private VehicleRepository vehicleRepository;

    public List<VehicleEntity> getAllVehicles() {
        return vehicleRepository.findAllVehicles();
    }
}
