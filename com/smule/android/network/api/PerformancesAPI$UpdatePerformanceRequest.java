package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$UpdatePerformanceRequest extends SnpRequest {
    public Boolean close;
    public Boolean isPrivate;
    public String message;
    public String performanceKey;
    public String title;

    public PerformancesAPI$UpdatePerformanceRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$UpdatePerformanceRequest setTitle(String str) {
        this.title = str;
        return this;
    }

    public PerformancesAPI$UpdatePerformanceRequest setMessage(String str) {
        this.message = str;
        return this;
    }

    public PerformancesAPI$UpdatePerformanceRequest setIsPrivate(Boolean bool) {
        this.isPrivate = bool;
        return this;
    }

    public PerformancesAPI$UpdatePerformanceRequest setClose(Boolean bool) {
        this.close = bool;
        return this;
    }
}
