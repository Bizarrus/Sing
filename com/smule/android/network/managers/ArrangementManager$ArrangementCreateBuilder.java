package com.smule.android.network.managers;

import com.smule.android.network.api.ArrangementAPI$ArrangementCreateRequest;
import java.util.List;

public class ArrangementManager$ArrangementCreateBuilder {
    private String f16538a;
    private String f16539b;
    private String f16540c;
    private String f16541d;
    private String f16542e;
    private String f16543f;
    private String f16544g;
    private List<String> f16545h;

    private ArrangementAPI$ArrangementCreateRequest m18122a() {
        ArrangementAPI$ArrangementCreateRequest arrangementAPI$ArrangementCreateRequest = new ArrangementAPI$ArrangementCreateRequest();
        arrangementAPI$ArrangementCreateRequest.name = this.f16538a;
        arrangementAPI$ArrangementCreateRequest.artist = this.f16539b;
        arrangementAPI$ArrangementCreateRequest.langId = this.f16540c;
        arrangementAPI$ArrangementCreateRequest.songId = this.f16541d;
        arrangementAPI$ArrangementCreateRequest.primeCompType = this.f16542e;
        arrangementAPI$ArrangementCreateRequest.primeSongId = this.f16543f;
        arrangementAPI$ArrangementCreateRequest.primeArrKey = this.f16544g;
        arrangementAPI$ArrangementCreateRequest.tags = this.f16545h;
        return arrangementAPI$ArrangementCreateRequest;
    }
}
