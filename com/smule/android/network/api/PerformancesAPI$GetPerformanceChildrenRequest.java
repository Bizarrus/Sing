package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$GetPerformanceChildrenRequest extends SnpRequest {
    public Integer limit = Integer.valueOf(25);
    public Integer offset = Integer.valueOf(0);
    public String performanceKey;

    public PerformancesAPI$GetPerformanceChildrenRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$GetPerformanceChildrenRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public PerformancesAPI$GetPerformanceChildrenRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }
}
