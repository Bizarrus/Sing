package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.PerformanceV2;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchManager$SASearchInstantResponse extends ParsedResponse {
    @JsonProperty("accts")
    public ArrayList<AccountIcon> mAccts;
    @JsonProperty("resultTypes")
    public ArrayList<String> mResultTypes;
    @JsonProperty("searchTerm")
    public String mSearchTerm;
    @JsonProperty("seeds")
    public ArrayList<PerformanceV2> mSeeds;
    @JsonProperty("songs")
    public ArrayList<CompositionLite> mSongs;

    public static SearchManager$SASearchInstantResponse m8055a(NetworkResponse networkResponse) {
        return (SearchManager$SASearchInstantResponse) ParsedResponse.m7676a(networkResponse, SearchManager$SASearchInstantResponse.class);
    }

    public String toString() {
        return "SAInstantSearchResponse [mResponse=" + this.a + ", mResultTypes=" + this.mResultTypes + ", mSongs=" + this.mSongs + ", mAccts=" + this.mAccts + ", mSeeds=" + this.mSeeds + ", mSearchTerm=" + this.mSearchTerm + "]";
    }
}
