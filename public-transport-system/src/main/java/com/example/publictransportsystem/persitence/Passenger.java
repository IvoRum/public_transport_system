package com.example.publictransportsystem.persitence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    private long id;
    @Column(nullable = false)
    private String name;

    public void setId(long id) {this.id = id;}

    public long getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
