package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountApps;
import com.smule.android.network.models.AccountIcon;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchManager$AccountSearchResponse extends ParsedResponse {
    @JsonProperty("accountApps")
    public List<AccountApps> accountAppsList;
    @JsonProperty("accounts")
    public List<AccountIcon> accountIcons;
    @JsonProperty("next")
    public int next;

    public static SearchManager$AccountSearchResponse m8052a(NetworkResponse networkResponse) {
        return (SearchManager$AccountSearchResponse) ParsedResponse.m7676a(networkResponse, SearchManager$AccountSearchResponse.class);
    }
}
