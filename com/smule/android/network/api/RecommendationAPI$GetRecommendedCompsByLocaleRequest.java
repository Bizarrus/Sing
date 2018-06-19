package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class RecommendationAPI$GetRecommendedCompsByLocaleRequest extends SnpRequest {
    public String ctxt;
    public Integer limit = Integer.valueOf(25);
    public Integer offset = Integer.valueOf(0);

    public RecommendationAPI$GetRecommendedCompsByLocaleRequest setCtxt(String str) {
        this.ctxt = str;
        return this;
    }

    public RecommendationAPI$GetRecommendedCompsByLocaleRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public RecommendationAPI$GetRecommendedCompsByLocaleRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }
}
