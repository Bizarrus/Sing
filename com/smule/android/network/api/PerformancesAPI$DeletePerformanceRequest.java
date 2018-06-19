package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$DeletePerformanceRequest extends SnpRequest {
    public String performanceKey;

    public PerformancesAPI$DeletePerformanceRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }
}
