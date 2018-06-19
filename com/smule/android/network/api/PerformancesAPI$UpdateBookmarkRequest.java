package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.ArrayList;

public class PerformancesAPI$UpdateBookmarkRequest extends SnpRequest {
    public ArrayList<String> add;
    public ArrayList<String> remove;

    public PerformancesAPI$UpdateBookmarkRequest setAdd(ArrayList<String> arrayList) {
        this.add = arrayList;
        return this;
    }

    public PerformancesAPI$UpdateBookmarkRequest setRemove(ArrayList<String> arrayList) {
        this.remove = arrayList;
        return this;
    }
}
