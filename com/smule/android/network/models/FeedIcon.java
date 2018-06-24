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
public class FeedIcon {
    @JsonProperty(value="id")
    public String id;
    @JsonProperty(value="name")
    public String name;
    @JsonProperty(value="url")
    public String url;

    public String toString() {
        return "FeedIcon [name=" + this.name + ", url=" + this.url + ", id=" + this.id + "]";
    }
}

