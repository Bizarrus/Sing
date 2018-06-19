package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SearchAPI$SearchSongbookAutoCompleteRequest extends SnpRequest {
    public boolean inclArr;
    public String term;

    public SearchAPI$SearchSongbookAutoCompleteRequest setTerm(String str) {
        this.term = str;
        return this;
    }

    public SearchAPI$SearchSongbookAutoCompleteRequest setInclArr(boolean z) {
        this.inclArr = z;
        return this;
    }
}
