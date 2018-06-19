package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class SocialAPI$GetFollowersRequest extends SnpRequest {
    public Long accountId;
    public List<String> apps;

    public SocialAPI$GetFollowersRequest setAccountId(Long l) {
        this.accountId = l;
        return this;
    }

    public SocialAPI$GetFollowersRequest setApps(List<String> list) {
        if (list != null && list.size() > 0) {
            this.apps = list;
        }
        return this;
    }
}
