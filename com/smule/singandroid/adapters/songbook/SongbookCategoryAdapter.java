/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.widget.SectionIndexer
 */
package com.smule.singandroid.adapters.songbook;

import android.widget.SectionIndexer;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SongbookManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.RecArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SongbookFragment;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.adapters.songbook.SongbookPaginatedAdapter;
import com.smule.singandroid.models.SongbookSection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class SongbookCategoryAdapter
extends SongbookPaginatedAdapter {
    public static final String i = SongbookCategoryAdapter.class.getSimpleName();
    private SongbookFragment.InitialLoadRecommendedCallback s;
    private AtomicBoolean t = new AtomicBoolean(false);
    private volatile String u;
    private Long v;
    private AtomicBoolean w;

    public SongbookCategoryAdapter(Long l, SongbookAmazingAdapter.AdapterInterface adapterInterface, SongbookFragment.InitialLoadRecommendedCallback initialLoadRecommendedCallback) {
        super(adapterInterface);
        this.v = l;
        this.s = initialLoadRecommendedCallback;
    }

    private void F() {
        this.b();
        this.j = false;
        this.k.a();
        if (this.m != null) {
            if (this.m != this.k.e) {
                Log.e(i, "adapter data should be the same as mSongbookSection data");
            }
            if (this.m.size() != 0) {
                Log.e(i, "adapter contained data that was not saved to mSongbookSection");
                this.m.clear();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean G() {
        long l = this.k.h;
        SongbookSection songbookSection = this.k;
        if (l + 300000 > System.currentTimeMillis()) {
            if (this.k.e != null && this.k.f != null) {
                if (this.k.e == this.m) {
                    Log.c(i, "faking data refresh, keep already fetched data");
                } else if (this.m != null) {
                    Log.e(i, "adapter already contains data that was not saved to mSongbookSection");
                }
                this.m = this.k.e;
                this.a(this.k.f);
                this.j = this.k.g;
                return true;
            }
            Log.e(i, "mSongbookSection data should have been saved -- cursor=" + this.k.f);
        }
        return false;
    }

    @Override
    public void b(String string2) {
        if (!"end".equals(this.c())) {
            super.b(this.c());
        }
    }

    @Override
    public void c(int n) {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void c(boolean bl) {
        String string2;
        AtomicBoolean atomicBoolean;
        block14 : {
            block15 : {
                block16 : {
                    block13 : {
                        Log.b(i, "mCategoryId = " + this.v + " - loadMoreData(" + bl + ")");
                        if (!SingApplication.g || this.t.get() && this.c() != null && this.c().equals(this.u)) break block13;
                        if (com.smule.android.network.managers.SongbookManager.b().b(this.v)) {
                            this.F();
                            com.smule.android.network.managers.SongbookManager.b().a(SongbookSection.d, false);
                            this.notifyDataSetChanged();
                        }
                        if (!bl) break block14;
                        if (!this.G()) break block15;
                        if ("start".equals(this.c())) {
                            this.s.a();
                        }
                        if (this.j) {
                            this.f();
                        } else {
                            this.h();
                        }
                        this.notifyDataSetChanged();
                        if (this.m == null) {
                            Log.e(i, "Adapter data should have been assigned");
                            this.x();
                            return;
                        }
                        if (this.m.size() == 0) {
                            this.x();
                            return;
                        }
                        if (!"start".equals(this.c())) break block16;
                    }
                    return;
                }
                this.y();
                return;
            }
            this.F();
            this.notifyDataSetChanged();
            this.w();
        }
        if (this.w != null) {
            atomicBoolean = this.w;
            synchronized (atomicBoolean) {
                this.w.set(true);
            }
        }
        this.w = atomicBoolean = new AtomicBoolean(false);
        this.t.set(true);
        this.u = string2 = this.c();
        com.smule.android.network.managers.SongbookManager.b().a(this.v, string2, 10, new SongbookManager.GetCategorySongsCallback(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void handleResponse(SongbookManager categorySongsResponse) {
                AtomicBoolean atomicBoolean2 = atomicBoolean;
                synchronized (atomicBoolean2) {
                    SongbookCategoryAdapter.this.t.set(false);
                    if (atomicBoolean.get()) {
                        return;
                    }
                    if (!string2.equals(SongbookCategoryAdapter.this.c())) {
                        Log.d(SongbookCategoryAdapter.i, "getCategorySongs - handleResponse: query is outdated", new IllegalStateException());
                        SongbookCategoryAdapter.this.y();
                        return;
                    }
                    if (!categorySongsResponse.a()) {
                        SongbookCategoryAdapter.this.x();
                        return;
                    }
                    if (SongbookCategoryAdapter.this.m == null) {
                        if (!"start".equals(string2)) {
                            Log.e(SongbookCategoryAdapter.i, "getCategorySongs - handleResponse: data should not be null for cursor=" + string2);
                        }
                        SongbookCategoryAdapter.this.m = new ArrayList();
                    }
                    for (SongbookManager recArrangementVersionLite : categorySongsResponse.mSongs) {
                        SongbookCategoryAdapter.this.m.add(new RecArrangementVersionLiteEntry(recArrangementVersionLite.mArrVersionLite, recArrangementVersionLite.mRecId, false));
                    }
                    if (!categorySongsResponse.mCursor.mHasNext.booleanValue()) {
                        SongbookCategoryAdapter.this.j = true;
                        SongbookCategoryAdapter.this.k.g = true;
                    }
                    if ("start".equals(string2)) {
                        SongbookCategoryAdapter.this.k.h = System.currentTimeMillis();
                        SongbookCategoryAdapter.this.s.a();
                    }
                    SongbookCategoryAdapter.this.k.e = SongbookCategoryAdapter.this.m;
                    if (categorySongsResponse.mCursor.mNext == null) {
                        SongbookCategoryAdapter.this.a("end");
                    } else {
                        SongbookCategoryAdapter.this.a(categorySongsResponse.mCursor.mNext);
                    }
                    SongbookCategoryAdapter.this.k.f = SongbookCategoryAdapter.this.c();
                    if (SongbookCategoryAdapter.this.j) {
                        SongbookCategoryAdapter.this.f();
                    } else {
                        SongbookCategoryAdapter.this.h();
                    }
                    SongbookCategoryAdapter.this.notifyDataSetChanged();
                    if (SongbookCategoryAdapter.this.m.size() == 0) {
                        SongbookCategoryAdapter.this.x();
                    } else {
                        SongbookCategoryAdapter.this.y();
                    }
                    return;
                }
            }
        });
    }

    @Override
    public boolean r() {
        if (this.v != (long)SongbookSection.a) {
            return true;
        }
        return false;
    }

    @Override
    public Long s() {
        return this.v;
    }

    @Override
    public boolean t() {
        return true;
    }

    @Override
    protected void u() {
        super.u();
        this.k.e = new ArrayList<SongbookEntry>();
        this.h = null;
    }

}

