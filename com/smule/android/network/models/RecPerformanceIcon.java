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
import com.smule.android.network.models.PerformanceV2;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RecPerformanceIcon {
    @JsonProperty(value="performanceIcon")
    public PerformanceV2 performanceIcon;
    @JsonProperty(value="recId")
    public String recId;

    public String toString() {
        return "RecPerformanceIcon [recId=" + this.recId + ", performanceIcon=" + this.performanceIcon + "]";
    }
}

