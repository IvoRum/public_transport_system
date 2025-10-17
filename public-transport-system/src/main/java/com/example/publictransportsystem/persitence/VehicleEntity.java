package com.example.publictransportsystem.persitence;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "passenger_capacity", nullable = false)
    private int passengerCapacity;

    @Column(name = "name", nullable = false)
    private String name;

//    @Column
//    @OneToMany
//    private VehicleTypeEntity type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
