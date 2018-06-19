package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class StoreAPI$GetSoundfontRequest extends SnpRequest {
    public String soundfontId;

    public StoreAPI$GetSoundfontRequest setSoundfontId(String str) {
        this.soundfontId = str;
        return this;
    }
}
