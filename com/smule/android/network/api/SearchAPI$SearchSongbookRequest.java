package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SearchAPI$SearchSongbookRequest extends SnpRequest {
    public Boolean inclArr;
    public Integer limit = Integer.valueOf(20);
    public Integer offset = Integer.valueOf(0);
    public String term;

    public SearchAPI$SearchSongbookRequest setTerm(String str) {
        this.term = str;
        return this;
    }

    public SearchAPI$SearchSongbookRequest setOffset(Integer num) {
        if (num != null) {
            this.offset = num;
        }
        return this;
    }

    public SearchAPI$SearchSongbookRequest setLimit(Integer num) {
        if (num != null) {
            this.limit = num;
        }
        return this;
    }

    public SearchAPI$SearchSongbookRequest setInclArr(Boolean bool) {
        this.inclArr = bool;
        return this;
    }
}
