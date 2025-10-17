package com.example.publictransportsystem.persitence;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public final class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "passenger_capacity", nullable = false)
    private int passengerCapacity;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private VehicleTypeEntity type;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(final int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public VehicleTypeEntity getType() {
        return type;
    }

    public void setType(final VehicleTypeEntity type) {
        this.type = type;
    }
}
