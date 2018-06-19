package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class InviteAPI$SendInvitesToAllFollowersRequest extends SnpRequest {
    public String performanceKey;

    public InviteAPI$SendInvitesToAllFollowersRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }
}
