package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SearchAPI$GetAccountsRequest extends SnpRequest {
    public Boolean doHandles;
    public Boolean doTags;
    public String term;

    public SearchAPI$GetAccountsRequest setTerm(String str) {
        this.term = str;
        return this;
    }

    public SearchAPI$GetAccountsRequest setDoHandles(Boolean bool) {
        this.doHandles = bool;
        return this;
    }

    public SearchAPI$GetAccountsRequest setDoTags(Boolean bool) {
        this.doTags = bool;
        return this;
    }
}
