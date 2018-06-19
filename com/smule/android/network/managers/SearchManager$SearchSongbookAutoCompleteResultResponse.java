package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchManager$SearchSongbookAutoCompleteResultResponse extends ParsedResponse {
    @JsonProperty("results")
    public ArrayList<String> mResults;

    public static SearchManager$SearchSongbookAutoCompleteResultResponse m8057a(NetworkResponse networkResponse) {
        return (SearchManager$SearchSongbookAutoCompleteResultResponse) ParsedResponse.m7676a(networkResponse, SearchManager$SearchSongbookAutoCompleteResultResponse.class);
    }

    public String toString() {
        return "SearchSongbookAutoCompleteResultResponse [mResponse=" + this.a + ", mResults=" + this.mResults + "]";
    }
}
