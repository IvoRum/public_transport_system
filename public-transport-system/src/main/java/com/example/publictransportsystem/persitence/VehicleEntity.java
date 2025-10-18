package com.example.publictransportsystem.persitence;

import com.example.publictransportsystem.domain.dto.VehicleDTO;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "registration_number", nullable = false, length = 8)
    private String registrationNumber;

    @Column(name = "passenger_capacity", nullable = false)
    private int passengerCapacity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private VehicleTypeEntity type;

    public static VehicleEntity fromDTO(final VehicleDTO vehicleDTO) {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        vehicleEntity.setPassengerCapacity(vehicleDTO.getPassengerCapacity());
        return vehicleEntity;
    }

    public long getId() {return id;}

    public void setId(final long id) {this.id = id;}

    public String getRegistrationNumber() {return registrationNumber;}

    public void setRegistrationNumber(String registrationNumber) {this.registrationNumber = registrationNumber;}

    public int getPassengerCapacity() {return passengerCapacity;}

    public void setPassengerCapacity(final int passengerCapacity) {this.passengerCapacity = passengerCapacity;}

    public VehicleTypeEntity getType() {return type;}

    public void setType(final VehicleTypeEntity type) {this.type = type;}

    public VehicleDTO toDTO() {
        return new VehicleDTO(
                this.registrationNumber,
                this.passengerCapacity,
                this.type != null ? this.type.getName() : null
        );
    }
}
