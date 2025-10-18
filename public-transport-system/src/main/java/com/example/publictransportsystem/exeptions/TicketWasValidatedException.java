package com.example.publictransportsystem.exeptions;

public class TicketWasValidatedException extends RuntimeException {
    public TicketWasValidatedException(String message) {
        super(message);
    }
}
