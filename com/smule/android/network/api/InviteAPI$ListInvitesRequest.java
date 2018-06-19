package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class InviteAPI$ListInvitesRequest extends SnpRequest {
    public String app;
    public Integer limit = Integer.valueOf(25);
    public Integer offset = Integer.valueOf(0);

    public InviteAPI$ListInvitesRequest setApp(String str) {
        this.app = str;
        return this;
    }

    public InviteAPI$ListInvitesRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public InviteAPI$ListInvitesRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }
}
