package com.smule.android.network.managers;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendationManager$RecommendedTrendingsResponse extends ParsedResponse {
    @JsonProperty("recTrendingSearches")
    public List<RecTrendingSearch> mTrendingSearches = new ArrayList();

    public static class RecTrendingSearch extends ParsedResponse {
        @JsonProperty("recId")
        public String mRecId;
        @JsonProperty("trendingSearch")
        public String mTrendingTerm;

        public String toString() {
            return "RecTrendingSearch[recId=" + this.mRecId + ",mTrendingTerm=" + this.mTrendingTerm + "]";
        }

        public int hashCode() {
            if (TextUtils.isEmpty(this.mTrendingTerm)) {
                return super.hashCode();
            }
            return this.mTrendingTerm.hashCode();
        }
    }

    static RecommendationManager$RecommendedTrendingsResponse m8051a(NetworkResponse networkResponse) {
        return (RecommendationManager$RecommendedTrendingsResponse) ParsedResponse.m7676a(networkResponse, RecommendationManager$RecommendedTrendingsResponse.class);
    }
}
