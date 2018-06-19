package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class SongAPI$SendSongPlayedRequest extends SnpRequest {
    public String songId;

    public SongAPI$SendSongPlayedRequest setSongId(String str) {
        this.songId = str;
        return this;
    }
}
