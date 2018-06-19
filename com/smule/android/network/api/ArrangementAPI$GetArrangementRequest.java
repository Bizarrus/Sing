package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class ArrangementAPI$GetArrangementRequest extends SnpRequest {
    public String arrKey;
    public Integer baseVer;

    public ArrangementAPI$GetArrangementRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }

    public ArrangementAPI$GetArrangementRequest setBaseVer(Integer num) {
        this.baseVer = num;
        return this;
    }
}
