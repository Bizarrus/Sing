package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class RecommendationAPI$GetRecommentedSingersRequest extends SnpRequest {
    public String type;

    public RecommendationAPI$GetRecommentedSingersRequest setType(String str) {
        this.type = str;
        return this;
    }
}
