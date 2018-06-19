package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.Collection;

public class InviteAPI$SendInvitesRequest extends SnpRequest {
    public Collection<Long> accountIds;
    public String performanceKey;

    public InviteAPI$SendInvitesRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public InviteAPI$SendInvitesRequest setAccountIds(Collection<Long> collection) {
        this.accountIds = collection;
        return this;
    }
}
