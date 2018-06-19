package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$GetPerformanceDetailsRequest extends SnpRequest {
    public String performanceKey;

    public PerformancesAPI$GetPerformanceDetailsRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }
}
