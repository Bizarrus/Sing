package com.smule.android.datasources;

import android.content.Context;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.PromotionManager;
import com.smule.android.network.models.PerformanceV2;
import java.util.concurrent.Future;

public class LeaderboardDataSource extends MagicDataSource<PerformanceV2> {
    private long f15882a;
    private String f15883b;

    public LeaderboardDataSource(Context context, long j, String str) {
        this.f15882a = j;
        this.f15883b = str;
    }

    public int mo6242a() {
        return 25;
    }

    public int l_() {
        return 2;
    }

    public Future<?> mo6243a(int i, int i2, FetchDataCallback<PerformanceV2> fetchDataCallback) {
        return PromotionManager.m18256a().m18262a(this.f15882a, this.f15883b, i, i2, new 1(this, fetchDataCallback));
    }
}
