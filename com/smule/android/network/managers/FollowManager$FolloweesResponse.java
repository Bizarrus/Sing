package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountApps;
import com.smule.android.network.models.AccountIcon;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FollowManager$FolloweesResponse extends ParsedResponse {
    @JsonProperty("accountApps")
    public List<AccountApps> mAccountApps;
    @JsonProperty("followees")
    public List<AccountIcon> mFollowees;
    @JsonProperty("totalFollowees")
    public int mTotalFollowees;

    static FollowManager$FolloweesResponse m7939a(NetworkResponse networkResponse) {
        return (FollowManager$FolloweesResponse) ParsedResponse.m7676a(networkResponse, FollowManager$FolloweesResponse.class);
    }

    public String toString() {
        return "FolloweesResponse [mResponse=" + this.a + ", mFollowees=" + this.mFollowees + ", mAccountApps=" + this.mAccountApps + ", mTotalFollowees=" + this.mTotalFollowees + "]";
    }
}
