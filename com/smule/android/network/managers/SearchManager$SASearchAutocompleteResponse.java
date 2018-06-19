package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.SAOption;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchManager$SASearchAutocompleteResponse extends ParsedResponse {
    @JsonProperty("options")
    public ArrayList<SAOption> mOptions;

    public static SearchManager$SASearchAutocompleteResponse m8053a(NetworkResponse networkResponse) {
        return (SearchManager$SASearchAutocompleteResponse) ParsedResponse.m7676a(networkResponse, SearchManager$SASearchAutocompleteResponse.class);
    }

    public String toString() {
        return "SearchAutocompleteResponse [mResponse=" + this.a + ", mOptions=" + this.mOptions + "]";
    }
}
