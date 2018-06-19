package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class ArrangementAPI$ArrangementVersionCreateRequest extends SnpRequest {
    public String arrKey;
    public boolean groupParts;
    public boolean lyrics;
    public boolean multipart;
    public List<Resource> origResources;

    public static class Resource {
        public Long resourceId;
        public String role;

        public Resource(String str, Long l) {
            this.role = str;
            this.resourceId = l;
        }
    }
}
