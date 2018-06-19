package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {
    @JsonProperty("id")
    public long id;
    @JsonProperty("message")
    public String message;
    @JsonProperty("name")
    public String name;

    public String toString() {
        return "Playlist [id=" + this.id + ", name=" + this.name + ", message=" + this.message + "]";
    }
}
