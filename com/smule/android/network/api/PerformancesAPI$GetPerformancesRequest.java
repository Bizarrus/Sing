package com.smule.android.network.api;

import android.text.TextUtils;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.HashSet;

public class PerformancesAPI$GetPerformancesRequest extends SnpRequest {
    public Long accountId;
    public String app = MagicNetwork.b();
    public HashSet<String> forApps = MagicNetwork.d().getAppsInFamily();
    public Long playerId;

    public PerformancesAPI$GetPerformancesRequest setPlayerId(Long l) {
        this.playerId = l;
        return this;
    }

    public PerformancesAPI$GetPerformancesRequest setAccountId(Long l) {
        this.accountId = l;
        return this;
    }

    public PerformancesAPI$GetPerformancesRequest setApp(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.app = str;
        }
        return this;
    }
}
