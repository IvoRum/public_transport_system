package com.example.publictransportsystem.service;

import com.example.publictransportsystem.persitence.VehicleEntity;
import com.example.publictransportsystem.repository.VehicleRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class VehicleService {

    @Inject
    private VehicleRepository vehicleRepository;

    public List<VehicleEntity> getAllVehicles() {
        return vehicleRepository.findAllVehicles();
    }
}
