package com.example.publictransportsystem.exeptions;

public class VehicleRegisteredException extends RuntimeException {
    public VehicleRegisteredException(String message) {
        super(message);
    }
}
