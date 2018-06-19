package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedIcon {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("url")
    public String url;

    public String toString() {
        return "FeedIcon [name=" + this.name + ", url=" + this.url + ", id=" + this.id + "]";
    }
}
