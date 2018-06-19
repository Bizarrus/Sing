package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;

public interface PerformanceManager$PerformanceResponseCallback extends ResponseInterface<PerformanceResponse> {
    void handleResponse(PerformanceResponse performanceResponse);
}
