package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.managers.RecommendationManager$RecommendedCompsResponse.RecCompositionLite;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicsManager$SubmitChosenTopicsResponse extends ParsedResponse {
    @JsonProperty("freeCompositions")
    public List<RecCompositionLite> mComps = new ArrayList();

    static TopicsManager$SubmitChosenTopicsResponse m8096a(NetworkResponse networkResponse) {
        return (TopicsManager$SubmitChosenTopicsResponse) ParsedResponse.m7676a(networkResponse, TopicsManager$SubmitChosenTopicsResponse.class);
    }

    public String toString() {
        return "SubmitChosenTopicsResponse[comps=" + this.mComps + "]";
    }
}
