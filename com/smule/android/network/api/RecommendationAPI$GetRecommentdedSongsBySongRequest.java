package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class RecommendationAPI$GetRecommentdedSongsBySongRequest extends SnpRequest {
    public String songId;

    public RecommendationAPI$GetRecommentdedSongsBySongRequest setSongId(String str) {
        this.songId = str;
        return this;
    }
}
