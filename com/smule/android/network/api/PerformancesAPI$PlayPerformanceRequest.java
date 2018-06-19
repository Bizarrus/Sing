package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$PlayPerformanceRequest extends SnpRequest {
    public String performanceKey;

    public PerformancesAPI$PlayPerformanceRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }
}
