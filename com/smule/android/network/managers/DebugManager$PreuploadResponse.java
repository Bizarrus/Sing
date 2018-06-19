package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DebugManager$PreuploadResponse extends ParsedResponse {
    @JsonProperty("resourceId")
    public long resourceId;

    static DebugManager$PreuploadResponse m7937a(NetworkResponse networkResponse) {
        return (DebugManager$PreuploadResponse) ParsedResponse.m7676a(networkResponse, DebugManager$PreuploadResponse.class);
    }

    public String toString() {
        return "PreuploadResponse{resourceId=" + this.resourceId + '}';
    }
}
