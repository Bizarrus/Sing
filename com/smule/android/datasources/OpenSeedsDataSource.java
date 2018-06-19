package com.smule.android.datasources;

import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformancesResponseCallback;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class OpenSeedsDataSource extends MagicDataSource<PerformanceV2> {
    SongbookEntry f15884a;
    Set<String> f15885b = new HashSet();
    private boolean f15886c;
    private boolean f15887k;
    private PerformanceManager$PerformancesResponseCallback f15888l;

    public OpenSeedsDataSource(SongbookEntry songbookEntry, boolean z, boolean z2, PerformanceManager$PerformancesResponseCallback performanceManager$PerformancesResponseCallback) {
        super(OpenSeedsDataSource.class.getSimpleName() + ":" + songbookEntry.mo6289c() + ":" + z + ":" + z2);
        this.f15884a = songbookEntry;
        this.f15886c = z;
        this.f15887k = z2;
        this.f15888l = performanceManager$PerformancesResponseCallback;
    }

    protected long mo6245c() {
        return TimeUnit.MINUTES.toSeconds(1);
    }

    public int mo6242a() {
        return 10;
    }

    public int l_() {
        return 2;
    }

    public Future<?> mo6243a(int i, int i2, FetchDataCallback<PerformanceV2> fetchDataCallback) {
        PerformanceManager$PerformancesResponseCallback 1 = new 1(this, fetchDataCallback);
        if (this.f15884a.m18772r()) {
            return PerformanceManager.a().b(this.f15884a.mo6289c(), null, Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(this.f15886c), 1);
        }
        return PerformanceManager.a().a(this.f15884a.mo6289c(), null, Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(this.f15886c), 1);
    }
}
