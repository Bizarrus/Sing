package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.ArrayList;

public class PerformancesAPI$DeleteCommentsRequest extends SnpRequest {
    public String performanceKey;
    public ArrayList<String> postKeys;

    public PerformancesAPI$DeleteCommentsRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$DeleteCommentsRequest setPostKeys(ArrayList<String> arrayList) {
        this.postKeys = arrayList;
        return this;
    }

    public PerformancesAPI$DeleteCommentsRequest setPostKey(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return setPostKeys(arrayList);
    }
}
