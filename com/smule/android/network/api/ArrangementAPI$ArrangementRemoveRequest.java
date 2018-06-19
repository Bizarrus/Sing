package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class ArrangementAPI$ArrangementRemoveRequest extends SnpRequest {
    public String arrKey;
    public boolean deletePerfs;

    public ArrangementAPI$ArrangementRemoveRequest setArrKey(String str) {
        this.arrKey = str;
        return this;
    }

    public ArrangementAPI$ArrangementRemoveRequest setDeletePerfs(boolean z) {
        this.deletePerfs = z;
        return this;
    }
}
