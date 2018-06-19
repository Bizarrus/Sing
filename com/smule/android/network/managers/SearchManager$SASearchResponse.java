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
public class SearchManager$SASearchResponse extends ParsedResponse {
    @JsonProperty("accts")
    public ArrayList<AccountIcon> mAccts;
    @JsonProperty("next")
    public Integer mNext;
    @JsonProperty("recs")
    public ArrayList<PerformanceV2> mRecs;
    @JsonProperty("seeds")
    public ArrayList<PerformanceV2> mSeeds;
    @JsonProperty("songs")
    public ArrayList<CompositionLite> mSongs;

    public static SearchManager$SASearchResponse m8056a(NetworkResponse networkResponse) {
        return (SearchManager$SASearchResponse) ParsedResponse.m7676a(networkResponse, SearchManager$SASearchResponse.class);
    }

    public String toString() {
        return "SASearchGlobalResponse [mResponse=" + this.a + ", mSongs=" + this.mSongs + ", mAccts=" + this.mAccts + ", mSeeds=" + this.mSeeds + ", mRecs=" + this.mRecs + ", mNext=" + this.mNext + "]";
    }
}
