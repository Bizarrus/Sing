package com.smule.android.datasources;

import com.smule.android.annotations.NetworkThread;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponseCallback;

class LeaderboardDataSource$1 implements PerformancesResponseCallback {
    final /* synthetic */ FetchDataCallback f6679a;
    final /* synthetic */ LeaderboardDataSource f6680b;

    LeaderboardDataSource$1(LeaderboardDataSource leaderboardDataSource, FetchDataCallback fetchDataCallback) {
        this.f6680b = leaderboardDataSource;
        this.f6679a = fetchDataCallback;
    }

    @NetworkThread
    public void handleResponse(PerformancesResponse performancesResponse) {
        if (performancesResponse.m7677a()) {
            this.f6679a.a(performancesResponse.mPerformances, performancesResponse.mNext.intValue());
        } else {
            this.f6679a.a();
        }
    }
}
