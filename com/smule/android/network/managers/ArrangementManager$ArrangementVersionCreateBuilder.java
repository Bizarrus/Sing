package com.smule.android.network.managers;

import com.smule.android.network.api.ArrangementAPI$ArrangementVersionCreateRequest;
import com.smule.android.network.core.SnpRequest;
import java.util.ArrayList;
import java.util.List;

public class ArrangementManager$ArrangementVersionCreateBuilder extends SnpRequest {
    public String f16552a;
    public boolean f16553b;
    public boolean f16554c;
    public boolean f16555d;
    public final List<Resource> f16556e = new ArrayList();

    public static class Resource {
        public String f16550a;
        public Long f16551b;
    }

    private ArrangementAPI$ArrangementVersionCreateRequest m18128a() {
        ArrangementAPI$ArrangementVersionCreateRequest arrangementAPI$ArrangementVersionCreateRequest = new ArrangementAPI$ArrangementVersionCreateRequest();
        arrangementAPI$ArrangementVersionCreateRequest.arrKey = this.f16552a;
        arrangementAPI$ArrangementVersionCreateRequest.lyrics = this.f16553b;
        arrangementAPI$ArrangementVersionCreateRequest.multipart = this.f16554c;
        arrangementAPI$ArrangementVersionCreateRequest.groupParts = this.f16555d;
        arrangementAPI$ArrangementVersionCreateRequest.origResources = new ArrayList(this.f16556e.size());
        for (Resource resource : this.f16556e) {
            arrangementAPI$ArrangementVersionCreateRequest.origResources.add(new com.smule.android.network.api.ArrangementAPI$ArrangementVersionCreateRequest.Resource(resource.f16550a, resource.f16551b));
        }
        return arrangementAPI$ArrangementVersionCreateRequest;
    }
}
