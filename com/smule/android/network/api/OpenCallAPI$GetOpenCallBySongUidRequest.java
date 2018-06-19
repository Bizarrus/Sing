package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class OpenCallAPI$GetOpenCallBySongUidRequest extends SnpRequest {
    public String songUid;

    public OpenCallAPI$GetOpenCallBySongUidRequest setSongUid(String str) {
        this.songUid = str;
        return this;
    }
}
