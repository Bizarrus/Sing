package com.smule.android.network.managers;

import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PerformanceManager.IsBookmarkSeedResponse;

public interface PerformanceManager$IsBookmarkSeedResponseCallback extends ResponseInterface<IsBookmarkSeedResponse> {
    void handleResponse(IsBookmarkSeedResponse isBookmarkSeedResponse);
}
