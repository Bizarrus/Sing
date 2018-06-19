package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class ArrangementAPI$ArrangementUpdateRequest extends SnpRequest {
    public String arrKey;
    public String artist;
    public String name;
    public List<String> tags;

    public ArrangementAPI$ArrangementUpdateRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }

    public ArrangementAPI$ArrangementUpdateRequest setName(String str) {
        this.name = str;
        return this;
    }

    public ArrangementAPI$ArrangementUpdateRequest setArtist(String str) {
        this.artist = str;
        return this;
    }

    public ArrangementAPI$ArrangementUpdateRequest setTags(List<String> list) {
        this.tags = list;
        return this;
    }
}
