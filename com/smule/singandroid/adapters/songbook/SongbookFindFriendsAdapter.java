/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.DataSetObserver
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.View
 *  android.view.ViewGroup
 *  com.foound.widget.AmazingAdapter
 *  com.foound.widget.AmazingAdapter$HasMoreCursorsListener
 *  com.foound.widget.AmazingAdapter$HasMorePagesListener
 *  com.mopub.common.VisibleForTesting
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$SingModulePlacement
 */
package com.smule.singandroid.adapters.songbook;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.foound.widget.AmazingAdapter;
import com.mopub.common.VisibleForTesting;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsModule;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.adapters.songbook.SongbookSongsAdapter;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.datasource.RecommendedFriendsCachedDataSource;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.HashMap;
import java.util.List;

public class SongbookFindFriendsAdapter
extends SongbookAmazingAdapter {
    public static final String i = SongbookFindFriendsAdapter.class.getName();
    protected RecommendedFriendsCachedDataSource j;
    protected InitialLoaderDataSourceObserver k;
    protected boolean l = false;
    @NonNull
    private final SongbookSongsAdapter m;
    @NonNull
    private final FindFriendsModule.FindFriendsModulePlacer n;
    @NonNull
    private final BaseFragment o;
    @NonNull
    private final Context p;
    @NonNull
    private final SingAnalytics.SingModulePlacement q;
    private long r = -1000;
    private int s = 0;
    private final Long t;

    @VisibleForTesting
    public SongbookFindFriendsAdapter(@NonNull Long l, @NonNull Context context, @NonNull FindFriendsModule.FindFriendsModulePlacer findFriendsModulePlacer, @NonNull SongbookSongsAdapter songbookSongsAdapter, @NonNull BaseFragment baseFragment, @NonNull SingAnalytics.SingModulePlacement singModulePlacement, int n) {
        this.t = l;
        this.n = findFriendsModulePlacer;
        this.m = songbookSongsAdapter;
        this.o = baseFragment;
        this.p = context;
        this.q = singModulePlacement;
        this.s = n;
        this.m.registerDataSetObserver(new DataSetObserver(){

            public void onChanged() {
                SongbookFindFriendsAdapter.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                SongbookFindFriendsAdapter.this.notifyDataSetInvalidated();
            }
        });
        findFriendsModulePlacer.b();
        this.j = new RecommendedFriendsCachedDataSource(20);
        this.k = new InitialLoaderDataSourceObserver();
        this.j.a(this.k);
        if (this.j.i() == MagicDataSource.DataState.b && this.j.k() > 0) {
            this.n.a();
            this.l = true;
            this.notifyDataSetChanged();
            return;
        }
        this.j.o();
    }

    @Nullable
    @Override
    public View a(int n, View object, ViewGroup viewGroup, boolean bl) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        if (this.d(n)) {
            object = FindFriendsModule.a(this.p, this.o, this.q, this.s);
            hashMap.put("position_adjusted_for_find_friends_module", -1);
            object.setTag(hashMap);
            return object;
        }
        object = this.m.a(this.n.b(n), (View)object, viewGroup, bl);
        hashMap.put("position_adjusted_for_find_friends_module", n);
        object.setTag(hashMap);
        return object;
    }

    @Override
    protected void a(View view, int n, int n2, boolean bl) {
        if (this.d(n2)) {
            return;
        }
        this.m.a(view, n, this.n.b(n2), bl);
    }

    public void a(AmazingAdapter.HasMoreCursorsListener hasMoreCursorsListener) {
        this.m.a(hasMoreCursorsListener);
    }

    public void a(AmazingAdapter.HasMorePagesListener hasMorePagesListener) {
        this.m.a(hasMorePagesListener);
    }

    @Override
    public void a(SongbookAmazingAdapter.DataRefreshListener dataRefreshListener) {
        this.m.a(dataRefreshListener);
    }

    @Override
    public void a(SongbookSortSelector.SortType sortType) {
        this.m.a(sortType);
    }

    public void a(boolean bl) {
        this.m.a(bl);
    }

    public boolean areAllItemsEnabled() {
        return this.m.areAllItemsEnabled();
    }

    public void b(String string2) {
        this.m.b(string2);
    }

    public void b(boolean bl) {
        this.m.b(bl);
    }

    public void c(int n) {
        this.m.c(n);
    }

    public boolean d() {
        return this.m.d();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean d(int n) {
        boolean bl = false;
        boolean bl2 = this.n.a(n);
        n = this.m.z() && this.n.c() && n == this.getCount() - 1 ? 1 : 0;
        if (bl2) return true;
        if (n == 0) return bl;
        return true;
    }

    public int getCount() {
        if (this.m.z() && this.n.c()) {
            return this.n.c(this.m.getCount()) + 1;
        }
        if (this.n.d()) {
            return this.n.c(this.m.getCount());
        }
        return this.m.getCount();
    }

    @Nullable
    public Object getItem(int n) {
        if (this.d(n)) {
            return FindFriendsModule.a(this.p, this.o, this.q, this.s);
        }
        return this.m.getItem(this.n.b(n));
    }

    public long getItemId(int n) {
        if (this.d(n)) {
            --this.r;
            return this.r;
        }
        return this.m.getItemId(this.n.b(n));
    }

    public int getItemViewType(int n) {
        if (this.d(n)) {
            return this.m.getViewTypeCount();
        }
        return this.m.getItemViewType(this.n.b(n));
    }

    @Override
    public int getPositionForSection(int n) {
        n = this.m.getPositionForSection(n);
        return this.n.b(n);
    }

    @Override
    public int getSectionForPosition(int n) {
        int n2;
        int n3 = this.getCount();
        for (n2 = n; this.d(n2) && n2 < n3 - 1; ++n2) {
        }
        n3 = n2;
        if (this.d(n2)) {
            n3 = n2;
            if (n2 > 0) {
                --n;
                do {
                    n3 = n;
                    if (!this.d(n)) break;
                    n3 = n;
                    if (n < 0) break;
                    --n;
                } while (true);
            }
        }
        if (this.d(n3)) {
            return 0;
        }
        n = this.n.b(n3);
        return this.m.getSectionForPosition(n);
    }

    public Object[] getSections() {
        return this.m.getSections();
    }

    public int getViewTypeCount() {
        return this.m.getViewTypeCount() + 1;
    }

    public boolean hasStableIds() {
        return this.m.hasStableIds();
    }

    public AmazingAdapter.HasMorePagesListener i() {
        return this.m.i();
    }

    public boolean isEmpty() {
        if (this.m.isEmpty() && !this.n.d()) {
            return true;
        }
        return false;
    }

    public boolean isEnabled(int n) {
        if (this.d(n) || this.m.isEnabled(this.n.b(n))) {
            return true;
        }
        return false;
    }

    public AmazingAdapter.HasMoreCursorsListener j() {
        return this.m.j();
    }

    public boolean k() {
        return this.m.k();
    }

    @Override
    public boolean l() {
        return this.m.l();
    }

    @Override
    public void m() {
        this.m.m();
    }

    @Override
    public SongbookAmazingAdapter.AdapterInterface n() {
        return this.m.n();
    }

    @Override
    public SongbookSection o() {
        return this.m.o();
    }

    @Override
    public SongbookListViewState p() {
        return this.m.p();
    }

    @Override
    public SongbookSortSelector.SortMenuType q() {
        return this.m.q();
    }

    @Override
    public boolean r() {
        return this.m.r();
    }

    @Override
    public Long s() {
        return this.t;
    }

    protected class InitialLoaderDataSourceObserver
    implements MagicDataSource.DataSourceObserver {
        InitialLoaderDataSourceObserver() {
        }

        @Override
        public void a(MagicDataSource magicDataSource) {
        }

        @Override
        public void a(MagicDataSource magicDataSource, List<Object> list) {
        }

        @Override
        public void b(MagicDataSource magicDataSource) {
        }

        @Override
        public void c(MagicDataSource magicDataSource) {
            MagicDataSource.DataState dataState = magicDataSource.i();
            if (!SongbookFindFriendsAdapter.this.l && dataState == MagicDataSource.DataState.b && magicDataSource.k() > 0) {
                SongbookFindFriendsAdapter.this.n.a();
                SongbookFindFriendsAdapter.this.l = true;
                SongbookFindFriendsAdapter.this.notifyDataSetChanged();
            }
        }

        @Override
        public void d(MagicDataSource magicDataSource) {
        }
    }

}

