package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CompositionLite;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendationManager$RecommendedCompsResponse extends ParsedResponse {
    @JsonProperty("recCompositionLites")
    public List<RecCompositionLite> mComps = new ArrayList();
    @JsonProperty("next")
    public Integer mNext;

    public static class RecCompositionLite extends ParsedResponse {
        @JsonProperty("compositionLite")
        public CompositionLite mComp;
        @JsonProperty("recId")
        public String mRecId;

        public String toString() {
            return "RecCompositionLite[recId=" + this.mRecId + ",song=" + this.mComp + "]";
        }
    }

    static RecommendationManager$RecommendedCompsResponse m8049a(NetworkResponse networkResponse) {
        return (RecommendationManager$RecommendedCompsResponse) ParsedResponse.m7676a(networkResponse, RecommendationManager$RecommendedCompsResponse.class);
    }

    public String toString() {
        return "RecommedationResponse[songs=" + this.mComps + "]";
    }

    public List<ArrangementVersionLite> getArrangementLites() {
        List<ArrangementVersionLite> arrayList = new ArrayList();
        if (this.mComps != null) {
            for (RecCompositionLite recCompositionLite : this.mComps) {
                if (recCompositionLite.mComp.mArrangementVersionLite != null) {
                    arrayList.add(recCompositionLite.mComp.mArrangementVersionLite);
                }
            }
        }
        return arrayList;
    }
}
