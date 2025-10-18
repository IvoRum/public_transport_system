package com.example.publictransportsystem.repository;


import com.example.publictransportsystem.persitence.VehicleEntity;
import com.example.publictransportsystem.persitence.VehicleTypeEntity;

import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing VehicleEntity and VehicleTypeEntity.
 */
@Dependent
public final class VehicleRepository extends BaseRepositoryJPA{

    public List<VehicleEntity> findAllVehicles() {
        return entityManager.createQuery("SELECT v FROM VehicleEntity v JOIN FETCH v.type", VehicleEntity.class).getResultList();
    }

    /**
     * Find vehicle type by its name.
     *
     * @param typeName The name of the vehicle type. Must not be null.
     * @return Optional containing the VehicleTypeEntity if found, or Optional.empty() if not found.
     */
    public Optional<VehicleTypeEntity> findVehicleTypeByName(final String typeName) {
        assert typeName != null : "Type name must not be null";

        List<VehicleTypeEntity> result = entityManager.createQuery(
                "SELECT vt FROM VehicleTypeEntity vt WHERE vt.name = :name", VehicleTypeEntity.class)
                .setParameter("name", typeName)
                .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    /**
     * Will insert the vehicleEntity if there is no other entity with the same registration number.
     *
     * @param vehicleEntity The vehicle entity to insert. Must not be null. Registration number must not be null.
     * @return Optional.empty() if the entity was inserted, or Optional.of(existingEntity) if an entity with the same
     */
    public Optional<VehicleEntity> insertIfNotPresent(final VehicleEntity vehicleEntity) {
        assert vehicleEntity != null : "VehicleEntity must not be null";
        assert vehicleEntity.getRegistrationNumber() != null : "Registration number must not be null";

        List<VehicleEntity> result = entityManager.createQuery(
                "SELECT v FROM VehicleEntity v WHERE v.registrationNumber = :regNum", VehicleEntity.class)
                .setParameter("regNum", vehicleEntity.getRegistrationNumber())
                .getResultList();
        if (result.isEmpty()) {
            entityManager.persist(vehicleEntity);
            return Optional.of(vehicleEntity);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Find vehicle by its registration number.
     *
     * @param vehicleNumber The registration number of the vehicle. Must not be null.
     * @return Optional containing the VehicleEntity if found, or Optional.empty() if not found.
     */
    public Optional<VehicleEntity> findVehicle(final String vehicleNumber){
        assert vehicleNumber != null : "Vehicle number must not be null";

        try {
            return Optional.of(entityManager.createQuery(
                            "SELECT v FROM VehicleEntity v WHERE v.registrationNumber = :regNum", VehicleEntity.class)
                    .setParameter("regNum", vehicleNumber).getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }
}
