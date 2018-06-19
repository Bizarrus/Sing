package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class PerformancesAPI$IsBookmarkSeedRequest extends SnpRequest {
    public String performanceKey;

    public PerformancesAPI$IsBookmarkSeedRequest setPerfKey(String str) {
        this.performanceKey = str;
        return this;
    }
}
