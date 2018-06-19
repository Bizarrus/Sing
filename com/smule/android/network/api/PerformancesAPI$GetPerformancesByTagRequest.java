package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.HashSet;

public class PerformancesAPI$GetPerformancesByTagRequest extends SnpRequest {
    public HashSet<String> apps = MagicNetwork.d().getAppsInFamily();
    public Integer limit;
    public Integer offset;
    public String term;

    public PerformancesAPI$GetPerformancesByTagRequest setTerm(String str) {
        this.term = str;
        return this;
    }

    public PerformancesAPI$GetPerformancesByTagRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public PerformancesAPI$GetPerformancesByTagRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
