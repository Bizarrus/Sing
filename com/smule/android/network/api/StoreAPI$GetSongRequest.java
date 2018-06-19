package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class StoreAPI$GetSongRequest extends SnpRequest {
    public String songId;

    public StoreAPI$GetSongRequest setSongId(String str) {
        this.songId = str;
        return this;
    }
}
