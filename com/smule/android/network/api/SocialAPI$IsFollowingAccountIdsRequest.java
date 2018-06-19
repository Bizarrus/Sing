package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SocialAPI$IsFollowingAccountIdsRequest extends SnpRequest {
    public Collection<Long> accountIds;
    public List<String> apps = Collections.singletonList(MagicNetwork.b());

    public SocialAPI$IsFollowingAccountIdsRequest setAccountIds(Collection<Long> collection) {
        this.accountIds = collection;
        return this;
    }

    public SocialAPI$IsFollowingAccountIdsRequest setApps(List<String> list) {
        this.apps = list;
        return this;
    }
}
