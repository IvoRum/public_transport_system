package com.example.publictransportsystem.persitence;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class VehicleTypeEntity {
    //@Id
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
