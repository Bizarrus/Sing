package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class ArrangementAPI$GetArrangementListRequest extends SnpRequest {
    public Integer limit = Integer.valueOf(10);
    public Integer offset = Integer.valueOf(0);
    public String sort = ArrangementAPI$ListSortOrder.RECENT.toString();

    public ArrangementAPI$GetArrangementListRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public ArrangementAPI$GetArrangementListRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }

    public ArrangementAPI$GetArrangementListRequest setSort(ArrangementAPI$ListSortOrder arrangementAPI$ListSortOrder) {
        if (arrangementAPI$ListSortOrder != null) {
            this.sort = arrangementAPI$ListSortOrder.toString();
        }
        return this;
    }
}
