package com.example.publictransportsystem.domain.response;

public final class HealthCheckResponse {
    private final boolean up;
    private final String message;

    public HealthCheckResponse(boolean up, String message) {
        this.up = up;
        this.message = message;
    }

    public static HealthCheckResponse up(String massage) {
        return new HealthCheckResponse(true, massage);
    }


    public static HealthCheckResponse down(String massage) {
        return new HealthCheckResponse( false, massage);
    }

    /**
     * Necessary for json serialization.
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Necessary for json serialization.
     */
    public String getMessage() {
        return message;
    }
}
