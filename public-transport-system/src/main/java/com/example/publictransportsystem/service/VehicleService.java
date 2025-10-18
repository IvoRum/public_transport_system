package com.example.publictransportsystem.service;

import com.example.publictransportsystem.domain.dto.VehicleDTO;
import com.example.publictransportsystem.domain.request.RegisterVehicleRequest;
import com.example.publictransportsystem.exeptions.EntityNotFoundException;
import com.example.publictransportsystem.exeptions.VehicleRegisteredException;
import com.example.publictransportsystem.persitence.VehicleEntity;
import com.example.publictransportsystem.persitence.VehicleTypeEntity;
import com.example.publictransportsystem.repository.VehicleRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing vehicles in the public transport system.
 */
@Dependent
public class VehicleService {

    @Inject
    private VehicleRepository vehicleRepository;

    /**
     * Retrieve all registered vehicles in the system.
     *
     * @return The list of all registered vehicles in the system.
     */
    @Transactional
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAllVehicles().stream()
                .map(VehicleEntity::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Register a new vehicle in the system.
     *
     * @param request Object containing vehicle details to be registered. Must not be null.
     * @return VehicleDTO of the newly registered vehicle.
     * @throws EntityNotFoundException if the vehicle type specified in the request does not exist.
     * @throws VehicleRegisteredException if a vehicle with the same registration number is already registered.
     */
    @Transactional
    public VehicleDTO registerVehicle(final RegisterVehicleRequest request)
            throws EntityNotFoundException, VehicleRegisteredException {
        assert request != null: "Request must not be null";

        final VehicleEntity vehicleEntity = VehicleEntity.fromDTO(request.getVehicles());
        vehicleEntity.setType(vehicleRepository.findVehicleTypeByName(request.getVehicles().getType())
                .orElseThrow(() -> new EntityNotFoundException(VehicleTypeEntity.class.getName())));

        final VehicleEntity insertedVehicle= vehicleRepository.insertIfNotPresent(vehicleEntity)
                .orElseThrow(() -> new VehicleRegisteredException(VehicleEntity.class.getName()));
        return insertedVehicle.toDTO();
    }
}
