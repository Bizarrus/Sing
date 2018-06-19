package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SearchAPI$SATermSearchRequest extends SnpRequest {
    public String term;

    public SearchAPI$SATermSearchRequest setTerm(String str) {
        this.term = str;
        return this;
    }
}
