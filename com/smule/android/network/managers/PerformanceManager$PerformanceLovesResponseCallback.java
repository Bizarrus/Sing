package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PerformanceManager.PerformanceLovesResponse;

public interface PerformanceManager$PerformanceLovesResponseCallback extends ResponseInterface<PerformanceLovesResponse> {
    void handleResponse(PerformanceLovesResponse performanceLovesResponse);
}
