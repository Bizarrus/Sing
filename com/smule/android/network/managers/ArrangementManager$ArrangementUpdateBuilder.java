package com.smule.android.network.managers;

import com.smule.android.network.api.ArrangementAPI$ArrangementUpdateRequest;
import java.util.List;

public class ArrangementManager$ArrangementUpdateBuilder {
    public String f16546a;
    public String f16547b;
    public String f16548c;
    public List<String> f16549d;

    private ArrangementAPI$ArrangementUpdateRequest m18126a() {
        ArrangementAPI$ArrangementUpdateRequest arrangementAPI$ArrangementUpdateRequest = new ArrangementAPI$ArrangementUpdateRequest();
        arrangementAPI$ArrangementUpdateRequest.arrKey = this.f16546a;
        arrangementAPI$ArrangementUpdateRequest.name = this.f16547b;
        arrangementAPI$ArrangementUpdateRequest.artist = this.f16548c;
        arrangementAPI$ArrangementUpdateRequest.tags = this.f16549d;
        return arrangementAPI$ArrangementUpdateRequest;
    }
}
