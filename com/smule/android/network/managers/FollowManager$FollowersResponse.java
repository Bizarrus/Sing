package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountApps;
import com.smule.android.network.models.AccountIcon;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FollowManager$FollowersResponse extends ParsedResponse {
    @JsonProperty("accountApps")
    public List<AccountApps> mAccountApps;
    @JsonProperty("followers")
    public List<AccountIcon> mFollowers;
    @JsonProperty("totalFollowers")
    public int mTotalFollowers;

    static FollowManager$FollowersResponse m7940a(NetworkResponse networkResponse) {
        return (FollowManager$FollowersResponse) ParsedResponse.m7676a(networkResponse, FollowManager$FollowersResponse.class);
    }

    public String toString() {
        return "FollowersResponse [mResponse=" + this.a + ", mFollowers=" + this.mFollowers + ", mAccountApps=" + this.mAccountApps + ", mTotalFollowers=" + this.mTotalFollowers + "]";
    }
}
