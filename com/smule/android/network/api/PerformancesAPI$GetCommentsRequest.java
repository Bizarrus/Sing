package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$GetCommentsRequest extends SnpRequest {
    public Integer limit;
    public Integer offset;
    public String performanceKey;

    public PerformancesAPI$GetCommentsRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$GetCommentsRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public PerformancesAPI$GetCommentsRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
