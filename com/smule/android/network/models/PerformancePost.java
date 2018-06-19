package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PerformancePost {
    @JsonProperty("accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("app")
    public String app;
    @JsonProperty("latitude")
    public float latitude;
    @JsonProperty("longitude")
    public float longitude;
    @JsonProperty("message")
    public String message;
    @JsonProperty("postKey")
    public String postKey;
    @JsonProperty("time")
    public long time;

    @JsonProperty("time")
    public void setTime(long j) {
        this.time = 1000 * j;
    }

    public String toString() {
        return "PerformancePost [postKey=" + this.postKey + ", app=" + this.app + ", time=" + this.time + ", accountIcon=" + this.accountIcon + ", message=" + this.message + ", latitude=" + this.latitude + ", longitude=" + this.longitude + "]";
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (!(obj instanceof PerformancePost)) {
            return false;
        }
        try {
            PerformancePost performancePost = (PerformancePost) obj;
            boolean z2 = (this.app != null && this.app.equals(performancePost.app)) || (this.app == null && performancePost.app == null);
            boolean z3;
            if (Math.abs(this.time - performancePost.time) < 5000) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!(this.postKey.equals(performancePost.postKey) && z2 && r2 && this.accountIcon.accountId == performancePost.accountIcon.accountId && this.message.equals(performancePost.message))) {
                z = false;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }
}
