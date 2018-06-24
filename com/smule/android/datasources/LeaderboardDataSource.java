/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 */
package com.smule.android.datasources;

import android.content.Context;
import com.smule.android.datasources.LeaderboardDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PromotionManager;
import com.smule.android.network.models.PerformanceV2;
import java.util.concurrent.Future;

public class LeaderboardDataSource
extends MagicDataSource<PerformanceV2, MagicDataSource.OffsetPaginationTracker> {
    private long a;
    private String b;

    public LeaderboardDataSource(Context context, long l, String string2) {
        super(new MagicDataSource.OffsetPaginationTracker());
        this.a = l;
        this.b = string2;
    }

    @Override
    public int a() {
        return 25;
    }

    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, MagicDataSource.FetchDataCallback<PerformanceV2, MagicDataSource.OffsetPaginationTracker> object) {
        object = new PerformanceManager.PerformancesResponseCallback(this, (MagicDataSource.FetchDataCallback)object){
            final /* synthetic */ MagicDataSource.FetchDataCallback a;
            final /* synthetic */ LeaderboardDataSource b;
            {
                this.b = leaderboardDataSource;
                this.a = fetchDataCallback;
            }

            @com.smule.android.annotations.NetworkThread
            public void handleResponse(com.smule.android.network.managers.PerformanceManager$PerformancesResponse performancesResponse) {
                if (performancesResponse.a()) {
                    this.a.a(performancesResponse.mPerformances, new MagicDataSource.OffsetPaginationTracker(performancesResponse.mNext));
                    return;
                }
                this.a.a();
            }
        };
        return PromotionManager.a().a(this.a, this.b, offsetPaginationTracker.a(), n, object);
    }

    @Override
    public int b() {
        return 2;
    }
}

