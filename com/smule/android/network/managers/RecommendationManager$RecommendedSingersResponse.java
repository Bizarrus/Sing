package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountIcon;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendationManager$RecommendedSingersResponse extends ParsedResponse {
    @JsonProperty("recAccountIcons")
    public List<RecAccountIcon> mRecAccountIcons = new ArrayList();

    public static class RecAccountIcon extends ParsedResponse {
        @JsonProperty("accountIcon")
        public AccountIcon mAccountIcon;
        @JsonProperty("recId")
        public String mRecId;
        @JsonIgnore
        public String mRecommendationType = null;

        public String toString() {
            return "RecAccountIcon[recId=" + this.mRecId + ",singer=" + this.mAccountIcon + "]";
        }

        public int hashCode() {
            if (this.mAccountIcon == null) {
                return super.hashCode();
            }
            return this.mAccountIcon.hashCode();
        }
    }

    static RecommendationManager$RecommendedSingersResponse m8050a(NetworkResponse networkResponse) {
        return (RecommendationManager$RecommendedSingersResponse) ParsedResponse.m7676a(networkResponse, RecommendationManager$RecommendedSingersResponse.class);
    }

    public String toString() {
        return "RecommedationResponse[singers=" + this.mRecAccountIcons + "]";
    }
}
