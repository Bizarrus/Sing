package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$RenderPerformanceRequest extends SnpRequest {
    public String performanceKey;
    public String renderType;

    public PerformancesAPI$RenderPerformanceRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$RenderPerformanceRequest setRenderType(PerformancesAPI$RenderType performancesAPI$RenderType) {
        if (performancesAPI$RenderType != null) {
            this.renderType = performancesAPI$RenderType.toString();
        }
        return this;
    }
}
