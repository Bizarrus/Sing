package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$ReportAbusePerformanceRequest extends SnpRequest {
    public String performanceKey;

    public PerformancesAPI$ReportAbusePerformanceRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }
}
