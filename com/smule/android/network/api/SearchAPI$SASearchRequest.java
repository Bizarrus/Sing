package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SearchAPI$SASearchRequest extends SnpRequest {
    public Integer limit;
    public Integer offset;
    public String resultType;
    public String sort;
    public String term;

    public SearchAPI$SASearchRequest setTerm(String str) {
        this.term = str;
        return this;
    }

    public SearchAPI$SASearchRequest setResultType(String str) {
        this.resultType = str;
        return this;
    }

    public SearchAPI$SASearchRequest setOffset(Integer num) {
        this.offset = num;
        return this;
    }

    public SearchAPI$SASearchRequest setLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public SearchAPI$SASearchRequest setSort(String str) {
        this.sort = str;
        return this;
    }
}
