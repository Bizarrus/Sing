package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendationManager$RecommedationSelectResponse extends ParsedResponse {
    static RecommendationManager$RecommedationSelectResponse m8047a(NetworkResponse networkResponse) {
        return (RecommendationManager$RecommedationSelectResponse) ParsedResponse.m7676a(networkResponse, RecommendationManager$RecommedationSelectResponse.class);
    }

    public String toString() {
        return "RecommedationResponse{}";
    }
}
