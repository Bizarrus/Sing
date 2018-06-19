package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.ArrayList;

public class PerformancesAPI$ReportAbusesRequest extends SnpRequest {
    public String performanceKey;
    public ArrayList<String> postKeys;

    public PerformancesAPI$ReportAbusesRequest setPerformanceKey(String str) {
        this.performanceKey = str;
        return this;
    }

    public PerformancesAPI$ReportAbusesRequest setPostKeys(ArrayList<String> arrayList) {
        this.postKeys = arrayList;
        return this;
    }

    public PerformancesAPI$ReportAbusesRequest setPostKey(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return setPostKeys(arrayList);
    }
}
