package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.Collection;

public class PerformancesAPI$GetPerformanceDetailsByKeysRequest extends SnpRequest {
    public Collection<String> performanceKeys;

    public PerformancesAPI$GetPerformanceDetailsByKeysRequest setPerformanceKeys(Collection<String> collection) {
        this.performanceKeys = collection;
        return this;
    }
}
