/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Playlist {
    @JsonProperty(value="id")
    public long id;
    @JsonProperty(value="message")
    public String message;
    @JsonProperty(value="name")
    public String name;

    public String toString() {
        return "Playlist [id=" + this.id + ", name=" + this.name + ", message=" + this.message + "]";
    }
}

