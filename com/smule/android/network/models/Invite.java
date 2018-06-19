package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Invite {
    @JsonProperty("invitedAt")
    public long invitedAt;
    @JsonProperty("inviter")
    public AccountIcon inviter;
    @JsonProperty("performanceIcon")
    public PerformanceV2 performance;

    public String toString() {
        return "Invite{inviter=" + this.inviter + ", performance=" + this.performance + ", invitedAt=" + this.invitedAt + '}';
    }
}
