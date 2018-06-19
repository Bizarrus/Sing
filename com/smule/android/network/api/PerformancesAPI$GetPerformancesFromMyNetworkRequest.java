package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$GetPerformancesFromMyNetworkRequest extends SnpRequest {
    public String fillStatus;
    public Integer limit;
    public Integer offset;

    public PerformancesAPI$GetPerformancesFromMyNetworkRequest setFillStatus(PerformancesAPI$FillStatus performancesAPI$FillStatus) {
        this.fillStatus = performancesAPI$FillStatus.toString();
        return this;
    }

    public PerformancesAPI$GetPerformancesFromMyNetworkRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public PerformancesAPI$GetPerformancesFromMyNetworkRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
