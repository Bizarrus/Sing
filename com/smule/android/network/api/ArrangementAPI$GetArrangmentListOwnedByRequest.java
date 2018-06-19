package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class ArrangementAPI$GetArrangmentListOwnedByRequest extends SnpRequest {
    public Integer limit = Integer.valueOf(10);
    public Integer offset = Integer.valueOf(0);
    public Long ownerAccountId;

    public ArrangementAPI$GetArrangmentListOwnedByRequest setOwnerAccountId(Long l) {
        this.ownerAccountId = l;
        return this;
    }

    public ArrangementAPI$GetArrangmentListOwnedByRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public ArrangementAPI$GetArrangmentListOwnedByRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }
}
