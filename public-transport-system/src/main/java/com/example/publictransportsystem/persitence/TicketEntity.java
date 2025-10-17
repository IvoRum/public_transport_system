package com.example.publictransportsystem.persitence;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vehicle_id", referencedColumnName = "id")
    private VehicleEntity vehicle;

    @Column
    private boolean validated;

    public void setId(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(final VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(final boolean validated) {
        this.validated = validated;
    }
}
