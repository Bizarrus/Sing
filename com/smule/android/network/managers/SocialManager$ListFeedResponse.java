package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.FeedListItem;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialManager$ListFeedResponse extends ParsedResponse {
    @JsonProperty("feedItems")
    public List<FeedListItem> feedListItems;
    @JsonProperty("next")
    public Integer mNext;

    static SocialManager$ListFeedResponse m8059a(NetworkResponse networkResponse) {
        return (SocialManager$ListFeedResponse) ParsedResponse.m7676a(networkResponse, SocialManager$ListFeedResponse.class);
    }

    public String toString() {
        return "ListFeedResponse [mResponse=" + this.a + ", feedListItems=" + this.feedListItems + "]";
    }
}
