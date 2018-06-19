package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.Collection;

public class SocialAPI$GetFolloweesRequest extends SnpRequest {
    public Long accountId;
    public Collection<String> apps;

    public SocialAPI$GetFolloweesRequest setApps(Collection<String> collection) {
        if (collection != null && collection.size() > 0) {
            this.apps = collection;
        }
        return this;
    }

    public SocialAPI$GetFolloweesRequest setAccountId(Long l) {
        this.accountId = l;
        return this;
    }
}
