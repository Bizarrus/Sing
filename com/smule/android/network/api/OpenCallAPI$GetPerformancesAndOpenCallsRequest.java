package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class OpenCallAPI$GetPerformancesAndOpenCallsRequest extends SnpRequest {
    public Long accountId;
    public String app;
    public Boolean includeNonrendered;
    public Long playerId;

    public OpenCallAPI$GetPerformancesAndOpenCallsRequest setPlayerId(Long l) {
        this.playerId = l;
        return this;
    }

    public OpenCallAPI$GetPerformancesAndOpenCallsRequest setAccountId(Long l) {
        this.accountId = l;
        return this;
    }

    public OpenCallAPI$GetPerformancesAndOpenCallsRequest setApp(String str) {
        this.app = str;
        return this;
    }

    public OpenCallAPI$GetPerformancesAndOpenCallsRequest setIncludeNonrendered(Boolean bool) {
        this.includeNonrendered = bool;
        return this;
    }
}
