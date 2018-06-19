package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.CompositionLite;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicsManager$SelectTopicsResponse extends ParsedResponse {
    @JsonProperty("freeCompositions")
    public List<CompositionLite> mComps = new ArrayList();

    static TopicsManager$SelectTopicsResponse m8095a(NetworkResponse networkResponse) {
        return (TopicsManager$SelectTopicsResponse) ParsedResponse.m7676a(networkResponse, TopicsManager$SelectTopicsResponse.class);
    }

    public String toString() {
        return "SelectTopicsResponse[comps=" + this.mComps + "]";
    }
}
