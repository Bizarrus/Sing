package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class ArrangementAPI$SendArrangementPlayedRequest extends SnpRequest {
    public String arrKey;

    public ArrangementAPI$SendArrangementPlayedRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }
}
