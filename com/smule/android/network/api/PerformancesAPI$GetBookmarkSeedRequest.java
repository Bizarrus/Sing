package com.smule.android.network.api;

import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.SnpRequest;
import java.util.Arrays;
import java.util.List;

public class PerformancesAPI$GetBookmarkSeedRequest extends SnpRequest {
    public Long accountId;
    public List<String> forApps = Arrays.asList(new String[]{MagicNetwork.d().getAppUID()});
    public Integer limit;
    public Integer offset;

    public PerformancesAPI$GetBookmarkSeedRequest setAccountId(Long l) {
        if (!(l == null || l.longValue() == 0)) {
            this.accountId = l;
        }
        return this;
    }

    public PerformancesAPI$GetBookmarkSeedRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public PerformancesAPI$GetBookmarkSeedRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
