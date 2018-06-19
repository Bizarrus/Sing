package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PerformanceManager.PerformancePostsResponse;

public interface PerformanceManager$PerformancePostsResponseCallback extends ResponseInterface<PerformancePostsResponse> {
    void handleResponse(PerformancePostsResponse performancePostsResponse);
}
