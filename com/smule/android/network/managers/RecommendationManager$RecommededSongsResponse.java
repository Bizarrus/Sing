package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendationManager$RecommededSongsResponse extends ParsedResponse {
    @JsonProperty("songIds")
    public List<String> mSongs = new ArrayList();

    static RecommendationManager$RecommededSongsResponse m8048a(NetworkResponse networkResponse) {
        return (RecommendationManager$RecommededSongsResponse) ParsedResponse.m7676a(networkResponse, RecommendationManager$RecommededSongsResponse.class);
    }

    public String toString() {
        return "RecommedationResponse[songs=" + this.mSongs + "]";
    }
}
