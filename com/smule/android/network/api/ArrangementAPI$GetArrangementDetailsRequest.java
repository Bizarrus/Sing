package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class ArrangementAPI$GetArrangementDetailsRequest extends SnpRequest {
    public String arrKey;

    public ArrangementAPI$GetArrangementDetailsRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }
}
