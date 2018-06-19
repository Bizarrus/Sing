package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SearchAPI$GetUsersForTermRequest extends SnpRequest {
    public Integer limit;
    public Integer offset;
    public String term;

    public SearchAPI$GetUsersForTermRequest setTerm(String str) {
        this.term = str;
        return this;
    }

    public SearchAPI$GetUsersForTermRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public SearchAPI$GetUsersForTermRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }
}
