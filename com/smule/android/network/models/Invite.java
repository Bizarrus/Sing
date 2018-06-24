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
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Invite {
    @JsonProperty(value="invitedAt")
    public long invitedAt;
    @JsonProperty(value="inviter")
    public AccountIcon inviter;
    @JsonProperty(value="performanceIcon")
    public PerformanceV2 performance;

    public String toString() {
        return "Invite{inviter=" + this.inviter + ", performance=" + this.performance + ", invitedAt=" + this.invitedAt + '}';
    }
}

