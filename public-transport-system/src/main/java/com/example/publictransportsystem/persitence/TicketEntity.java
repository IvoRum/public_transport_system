package com.example.publictransportsystem.persitence;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vehicle_id", referencedColumnName = "id")
    private VehicleEntity vehicle;

    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    private String code;

    @Column(name= "ts_created", nullable = false)
    private Timestamp createdOn;

    @Column(name= "ts_validated", nullable = false)
    private Timestamp validatedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    private Passenger passenger;

    @Column
    private boolean validated;

    @PrePersist
    public void generateTicketCode() {
        if (code == null || code.isEmpty()) {
            code = "PT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

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

    public String getCode() {return code;}

    public Passenger getPassenger() {return passenger;}

    public void setPassenger(Passenger passenger) {this.passenger = passenger;}

    public Timestamp getCreatedOn() {return createdOn;}

    public void setCreatedOn(Timestamp createdOn) {this.createdOn = createdOn;}

    public Timestamp getValidatedOn() {return validatedOn;}

    public void setValidatedOn(Timestamp validatedOn) {this.validatedOn = validatedOn;}
}
