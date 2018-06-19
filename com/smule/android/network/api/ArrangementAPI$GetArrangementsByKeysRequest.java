package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class ArrangementAPI$GetArrangementsByKeysRequest extends SnpRequest {
    public List<String> arrKeys;

    public ArrangementAPI$GetArrangementsByKeysRequest setArrKeys(List<String> list) {
        this.arrKeys = list;
        return this;
    }
}
