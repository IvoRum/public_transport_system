package com.example.publictransportsystem.persitence;

import javax.persistence.*;

@Entity
@Table(name = "passenger")
public class PassengerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    public void setId(long id) {this.id = id;}

    public long getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
