/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.datasources;

import com.smule.android.datasources.OpenSeedsDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class OpenSeedsDataSource
extends MagicDataSource<PerformanceV2, MagicDataSource.OffsetPaginationTracker> {
    SongbookEntry a;
    Set<String> b = new HashSet<String>();
    private boolean l;
    private boolean m;
    private PerformanceManager n;

    public OpenSeedsDataSource(SongbookEntry songbookEntry, boolean bl, boolean bl2, PerformanceManager performancesResponseCallback) {
        super(OpenSeedsDataSource.class.getSimpleName() + ":" + songbookEntry.c() + ":" + bl + ":" + bl2, new MagicDataSource.OffsetPaginationTracker());
        this.a = songbookEntry;
        this.l = bl;
        this.m = bl2;
        this.n = performancesResponseCallback;
    }

    static /* synthetic */ PerformanceManager a(OpenSeedsDataSource openSeedsDataSource) {
        return openSeedsDataSource.n;
    }

    static /* synthetic */ boolean b(OpenSeedsDataSource openSeedsDataSource) {
        return openSeedsDataSource.m;
    }

    @Override
    public int a() {
        return 10;
    }

    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, MagicDataSource.FetchDataCallback<PerformanceV2, MagicDataSource.OffsetPaginationTracker> object) {
        object = new PerformanceManager(this, (MagicDataSource.FetchDataCallback)object){
            final /* synthetic */ MagicDataSource.FetchDataCallback a;
            final /* synthetic */ OpenSeedsDataSource b;
            {
                this.b = openSeedsDataSource;
                this.a = fetchDataCallback;
            }

            /*
             * Enabled aggressive block sorting
             */
            @com.smule.android.annotations.NetworkThread
            public void handleResponse(com.smule.android.network.managers.PerformanceManager$PerformancesResponse performancesResponse) {
                if (OpenSeedsDataSource.a(this.b) != null) {
                    OpenSeedsDataSource.a(this.b).handleResponse(performancesResponse);
                }
                if (!performancesResponse.a()) {
                    this.a.a();
                    return;
                }
                Object object = new java.util.ArrayList<PerformanceV2>();
                if (this.b.a.t()) {
                    for (PerformanceV2 performanceV2 : performancesResponse.mPerformances) {
                        if (!performanceV2.p()) continue;
                        object.add(performanceV2);
                    }
                } else {
                    object.addAll(performancesResponse.mPerformances);
                }
                java.util.ArrayList<E> arrayList = new java.util.ArrayList<E>();
                object = object.iterator();
                while (object.hasNext()) {
                    PerformanceV2 performanceV2;
                    performanceV2 = (PerformanceV2)object.next();
                    if (com.smule.android.songbook.PerformanceV2Util.a(performanceV2, OpenSeedsDataSource.b(this.b)) == null || this.b.b.contains(performanceV2.performanceKey)) continue;
                    this.b.b.add(performanceV2.performanceKey);
                    arrayList.add(performanceV2);
                }
                int n = arrayList.size() > 0 ? performancesResponse.mNext : -1;
                this.a.a(arrayList, new MagicDataSource.OffsetPaginationTracker(n));
            }
        };
        if (this.a.t()) {
            return com.smule.android.network.managers.PerformanceManager.a().b(this.a.c(), null, offsetPaginationTracker.a(), n, this.l, object);
        }
        return com.smule.android.network.managers.PerformanceManager.a().a(this.a.c(), null, offsetPaginationTracker.a(), n, (Boolean)this.l, object);
    }

    @Override
    public int b() {
        return 2;
    }

    @Override
    protected long d() {
        return TimeUnit.MINUTES.toSeconds(1);
    }
}

