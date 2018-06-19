package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecPerformanceIcon {
    @JsonProperty("performanceIcon")
    public PerformanceV2 performanceIcon;
    @JsonProperty("recId")
    public String recId;

    public String toString() {
        return "RecPerformanceIcon [recId=" + this.recId + ", performanceIcon=" + this.performanceIcon + "]";
    }
}
