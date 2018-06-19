package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.utils.LocationUtils;

public class PerformancesAPI$LoveRequest extends SnpRequest {
    public Float latitude;
    public Float longitude;
    public String performanceKey;

    public PerformancesAPI$LoveRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$LoveRequest setLatAndLong(Float f, Float f2) {
        if (LocationUtils.m19001a(f.floatValue(), f2.floatValue())) {
            this.latitude = f;
            this.longitude = f2;
        }
        return this;
    }
}
