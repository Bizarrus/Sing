package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.Arrays;
import java.util.List;

public class PerformancesAPI$GetFavoritesRequest extends SnpRequest {
    public Long accountId;
    public List<String> forApps = Arrays.asList(new String[]{MagicNetwork.d().getAppUID()});
    public Integer limit;
    public Integer offset;

    public PerformancesAPI$GetFavoritesRequest setAccountId(Long l) {
        if (!(l == null || l.longValue() == 0)) {
            this.accountId = l;
        }
        return this;
    }

    public PerformancesAPI$GetFavoritesRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public PerformancesAPI$GetFavoritesRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
