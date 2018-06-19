package com.smule.android.datasources;

import com.smule.android.annotations.NetworkThread;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponseCallback;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.PerformanceV2Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class OpenSeedsDataSource$1 implements PerformancesResponseCallback {
    final /* synthetic */ FetchDataCallback f6681a;
    final /* synthetic */ OpenSeedsDataSource f6682b;

    OpenSeedsDataSource$1(OpenSeedsDataSource openSeedsDataSource, FetchDataCallback fetchDataCallback) {
        this.f6682b = openSeedsDataSource;
        this.f6681a = fetchDataCallback;
    }

    @NetworkThread
    public void handleResponse(PerformancesResponse performancesResponse) {
        if (OpenSeedsDataSource.a(this.f6682b) != null) {
            OpenSeedsDataSource.a(this.f6682b).handleResponse(performancesResponse);
        }
        if (performancesResponse.m7677a()) {
            PerformanceV2 performanceV2;
            List<PerformanceV2> arrayList = new ArrayList();
            if (this.f6682b.a.r()) {
                Iterator it = performancesResponse.mPerformances.iterator();
                while (it.hasNext()) {
                    performanceV2 = (PerformanceV2) it.next();
                    if (performanceV2.m8280n()) {
                        arrayList.add(performanceV2);
                    }
                }
            } else {
                arrayList.addAll(performancesResponse.mPerformances);
            }
            List arrayList2 = new ArrayList();
            for (PerformanceV2 performanceV22 : arrayList) {
                if (!(PerformanceV2Util.a(performanceV22, OpenSeedsDataSource.b(this.f6682b)) == null || this.f6682b.b.contains(performanceV22.performanceKey))) {
                    this.f6682b.b.add(performanceV22.performanceKey);
                    arrayList2.add(performanceV22);
                }
            }
            this.f6681a.a(arrayList2, arrayList2.size() > 0 ? performancesResponse.mNext.intValue() : -1);
            return;
        }
        this.f6681a.a();
    }
}
