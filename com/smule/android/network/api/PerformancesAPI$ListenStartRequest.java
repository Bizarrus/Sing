package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$ListenStartRequest extends SnpRequest {
    public String performanceKey;

    public PerformancesAPI$ListenStartRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }
}
