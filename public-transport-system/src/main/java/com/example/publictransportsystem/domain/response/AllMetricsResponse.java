package com.example.publictransportsystem.domain.response;

import com.example.publictransportsystem.domain.dto.MetricsDTO;
import com.example.publictransportsystem.domain.status.MetricsRequestStatus;

public final class AllMetricsResponse {
    private final MetricsDTO metrics;
    private final MetricsRequestStatus status;

    public AllMetricsResponse(final MetricsDTO metrics, final MetricsRequestStatus status) {
        this.metrics = metrics;
        this.status = status;
    }

    /**
     * Necessary for json serialization.
     */
    public MetricsRequestStatus getStatus() {
        return status;
    }

    /**
     * Necessary for json serialization.
     */
    public MetricsDTO getMetrics() {
        return metrics;
    }
}
