package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$GetLovesRequest extends SnpRequest {
    public String performanceKey;

    public PerformancesAPI$GetLovesRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }
}
