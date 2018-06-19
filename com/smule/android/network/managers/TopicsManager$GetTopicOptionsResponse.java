package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.Topic;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicsManager$GetTopicOptionsResponse extends ParsedResponse {
    @JsonProperty("next")
    public int next;
    @JsonProperty("options")
    public ArrayList<Topic> options;

    static TopicsManager$GetTopicOptionsResponse m8094a(NetworkResponse networkResponse) {
        return (TopicsManager$GetTopicOptionsResponse) ParsedResponse.m7676a(networkResponse, TopicsManager$GetTopicOptionsResponse.class);
    }

    public String toString() {
        return "GetTopicOptionsResponse{options=" + this.options + ", next=" + this.next + '}';
    }
}
