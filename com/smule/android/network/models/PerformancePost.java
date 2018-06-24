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

@JsonIgnoreProperties(ignoreUnknown=true)
public class PerformancePost {
    @JsonProperty(value="accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty(value="app")
    public String app;
    @JsonProperty(value="latitude")
    public float latitude;
    @JsonProperty(value="longitude")
    public float longitude;
    @JsonProperty(value="message")
    public String message;
    @JsonProperty(value="postKey")
    public String postKey;
    @JsonProperty(value="time")
    public long time;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean equals(Object object) {
        boolean bl;
        boolean bl2;
        block4 : {
            block3 : {
                bl = true;
                if (!(object instanceof PerformancePost)) {
                    return false;
                }
                try {
                    object = (PerformancePost)object;
                    if (this.app != null && this.app.equals(object.app) || this.app == null && object.app == null) break block3;
                    bl2 = false;
                    break block4;
                }
                catch (Exception exception) {
                    return false;
                }
            }
            bl2 = true;
        }
        boolean bl3 = Math.abs(this.time - object.time) < 5000;
        if (!this.postKey.equals(object.postKey)) return false;
        if (!bl2) return false;
        if (!bl3) return false;
        if (this.accountIcon.accountId != object.accountIcon.accountId) return false;
        boolean bl4 = this.message.equals(object.message);
        if (!bl4) return false;
        return bl;
    }

    @JsonProperty(value="time")
    public void setTime(long l) {
        this.time = 1000 * l;
    }

    public String toString() {
        return "PerformancePost [postKey=" + this.postKey + ", app=" + this.app + ", time=" + this.time + ", accountIcon=" + this.accountIcon + ", message=" + this.message + ", latitude=" + this.latitude + ", longitude=" + this.longitude + "]";
    }
}

