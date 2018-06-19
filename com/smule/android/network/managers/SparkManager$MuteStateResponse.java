package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.SNPChat;
import java.util.List;

public class SparkManager$MuteStateResponse extends ParsedResponse {
    @JsonProperty("mute")
    public List<SNPChat> muted;
    @JsonProperty("notMute")
    public List<SNPChat> notMuted;

    static SparkManager$MuteStateResponse m8063a(NetworkResponse networkResponse) {
        return (SparkManager$MuteStateResponse) ParsedResponse.m7676a(networkResponse, SparkManager$MuteStateResponse.class);
    }
}
