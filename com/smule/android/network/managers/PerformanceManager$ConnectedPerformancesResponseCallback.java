package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.response.GetConnectedPerformancesResponse;

public interface PerformanceManager$ConnectedPerformancesResponseCallback extends ResponseInterface<GetConnectedPerformancesResponse> {
    void handleResponse(GetConnectedPerformancesResponse getConnectedPerformancesResponse);
}
