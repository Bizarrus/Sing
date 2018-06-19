package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PerformanceManager.PerformancesByPerformerResponse;

public interface PerformanceManager$PerformancesByPerformerResponseCallback extends ResponseInterface<PerformancesByPerformerResponse> {
    void handleResponse(PerformancesByPerformerResponse performancesByPerformerResponse);
}
