package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.SNPChat;
import java.util.List;

public class SparkManager$ActiveChatsResponse extends ParsedResponse {
    @JsonProperty("chats")
    public List<SNPChat> chats;
    @JsonProperty("inbox")
    public List<SNPChat> inbox;
    @JsonProperty("other")
    public List<SNPChat> other;

    static SparkManager$ActiveChatsResponse m8062a(NetworkResponse networkResponse) {
        return (SparkManager$ActiveChatsResponse) ParsedResponse.m7676a(networkResponse, SparkManager$ActiveChatsResponse.class);
    }
}
