package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.NotificationListItem;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialManager$ListNotificationsResponse extends ParsedResponse {
    @JsonProperty("next")
    public Integer mNext;
    @JsonProperty("notificationItems")
    public List<NotificationListItem> notificationListItems;

    static SocialManager$ListNotificationsResponse m8060a(NetworkResponse networkResponse) {
        return (SocialManager$ListNotificationsResponse) ParsedResponse.m7676a(networkResponse, SocialManager$ListNotificationsResponse.class);
    }

    public String toString() {
        return "LookupNotificationsResponse [mResponse=" + this.a + ", next=" + this.mNext + ", notificationItems=" + this.notificationListItems + "]";
    }
}
