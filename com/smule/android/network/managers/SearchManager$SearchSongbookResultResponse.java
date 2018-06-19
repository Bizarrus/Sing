package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.CompositionLite;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchManager$SearchSongbookResultResponse extends ParsedResponse {
    @JsonProperty("results")
    public ArrayList<CompositionLite> mResults;
    @JsonProperty("next")
    public int next;

    public static SearchManager$SearchSongbookResultResponse m8058a(NetworkResponse networkResponse) {
        return (SearchManager$SearchSongbookResultResponse) ParsedResponse.m7676a(networkResponse, SearchManager$SearchSongbookResultResponse.class);
    }

    public String toString() {
        return "PerformancePostsResponse [mResponse=" + this.a + ", mResults=" + this.mResults + "]";
    }
}
