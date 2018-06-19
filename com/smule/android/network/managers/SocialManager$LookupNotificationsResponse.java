package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.Notification;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialManager$LookupNotificationsResponse extends ParsedResponse {
    @JsonProperty("notifications")
    public List<Notification> notifications;

    static SocialManager$LookupNotificationsResponse m8061a(NetworkResponse networkResponse) {
        return (SocialManager$LookupNotificationsResponse) ParsedResponse.m7676a(networkResponse, SocialManager$LookupNotificationsResponse.class);
    }

    public String toString() {
        return "LookupNotificationsResponse [mResponse=" + this.a + ", notificationItems=" + this.notifications + "]";
    }
}
