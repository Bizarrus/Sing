package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;

public interface PerformanceManager$PerformancesResponseCallback extends ResponseInterface<PerformancesResponse> {
    void handleResponse(PerformancesResponse performancesResponse);
}
