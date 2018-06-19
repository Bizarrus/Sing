package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class RecommendationAPI$GetRecommendedCompsBySongRequest extends SnpRequest {
    public String arrKey;
    public String compType;
    public String songId;

    public RecommendationAPI$GetRecommendedCompsBySongRequest setCompType(String str) {
        this.compType = str;
        return this;
    }

    public RecommendationAPI$GetRecommendedCompsBySongRequest setSongId(String str) {
        this.songId = str;
        return this;
    }

    public RecommendationAPI$GetRecommendedCompsBySongRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }
}
