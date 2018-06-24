/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.database.DataSetObserver
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.AdapterView$OnItemLongClickListener
 *  android.widget.AdapterView$OnItemSelectedListener
 *  com.foound.widget.AmazingAdapter
 *  com.foound.widget.AmazingAdapter$HasMoreCursorsListener
 *  com.foound.widget.AmazingAdapter$HasMorePagesListener
 *  com.mopub.common.Preconditions
 *  com.mopub.common.Preconditions$NoThrow
 *  com.mopub.common.VisibleForTesting
 *  com.mopub.nativeads.MoPubAdRenderer
 *  com.mopub.nativeads.MoPubNative
 *  com.mopub.nativeads.MoPubNative$SmuleNativeAdEventListenerInterface
 *  com.mopub.nativeads.MoPubNativeAdLoadedListener
 *  com.mopub.nativeads.MoPubStreamAdPlacer
 *  com.mopub.nativeads.RequestParameters
 *  com.mopub.nativeads.SmuleNativeAdEventListener
 *  com.mopub.nativeads.VisibilityTracker
 *  com.mopub.nativeads.VisibilityTracker$VisibilityTrackerListener
 */
package com.smule.singandroid.adapters.songbook;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.foound.widget.AmazingAdapter;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubAdRenderer;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubNativeAdLoadedListener;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.SmuleNativeAdEventListener;
import com.mopub.nativeads.VisibilityTracker;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

public class SongbookAmazingMoPubAdAdapter
extends SongbookAmazingAdapter
implements MoPubNative.SmuleNativeAdEventListenerInterface {
    @NonNull
    private final WeakHashMap<View, Integer> i;
    @NonNull
    private final SongbookAmazingAdapter j;
    @NonNull
    private final MoPubStreamAdPlacer k;
    @NonNull
    private final VisibilityTracker l;
    @Nullable
    private WeakReference<MoPubNativeAdLoadedListener> m;

    @VisibleForTesting
    public SongbookAmazingMoPubAdAdapter(@NonNull MoPubStreamAdPlacer moPubStreamAdPlacer, @NonNull SongbookAmazingAdapter songbookAmazingAdapter, @NonNull VisibilityTracker visibilityTracker) {
        this.j = songbookAmazingAdapter;
        this.k = moPubStreamAdPlacer;
        this.i = new WeakHashMap();
        this.l = visibilityTracker;
        this.l.setVisibilityTrackerListener(new VisibilityTracker.VisibilityTrackerListener(){

            public void onVisibilityChanged(@NonNull List<View> list, List<View> list2) {
                SongbookAmazingMoPubAdAdapter.this.a(list);
            }
        });
        this.j.registerDataSetObserver(new DataSetObserver(){

            public void onChanged() {
                SongbookAmazingMoPubAdAdapter.this.k.setItemCount(SongbookAmazingMoPubAdAdapter.this.j.getCount());
                SongbookAmazingMoPubAdAdapter.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                SongbookAmazingMoPubAdAdapter.this.notifyDataSetInvalidated();
            }
        });
        this.k.setAdLoadedListener(new MoPubNativeAdLoadedListener(){

            public void onAdChanged() {
                SongbookAmazingMoPubAdAdapter.this.notifyDataSetChanged();
            }

            public void onAdLoaded(int n) {
                SongbookAmazingMoPubAdAdapter.this.d(n);
            }

            public void onAdRemoved(int n) {
                SongbookAmazingMoPubAdAdapter.this.e(n);
            }
        });
        this.k.setItemCount(this.j.getCount());
    }

    private void a(@NonNull List<View> object) {
        object = object.iterator();
        int n = Integer.MAX_VALUE;
        int n2 = 0;
        while (object.hasNext()) {
            Object object2 = (View)object.next();
            if ((object2 = this.i.get(object2)) == null) continue;
            n = Math.min(object2.intValue(), n);
            n2 = Math.max(object2.intValue(), n2);
        }
        this.k.placeAdsInRange(n, n2 + 1);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Nullable
    @Override
    public View a(int n, View view, ViewGroup viewGroup, boolean bl) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        View view2 = this.k.getAdView(n, view, viewGroup);
        if (view2 != null) {
            hashMap.put("position_adjusted_for_native_ads", -1);
            view = view2;
        } else {
            view = this.j.a(this.k.getOriginalPosition(n), view, viewGroup, bl);
            hashMap.put("position_adjusted_for_native_ads", n);
        }
        view.setTag(hashMap);
        this.i.put(view, n);
        this.l.addView(view, 0);
        return view;
    }

    @Override
    protected void a(View view, int n, int n2, boolean bl) {
        if (this.f(n2)) {
            return;
        }
        this.j.a(view, n, this.k.getOriginalPosition(n2), bl);
    }

    public void a(AmazingAdapter.HasMoreCursorsListener hasMoreCursorsListener) {
        this.j.a(hasMoreCursorsListener);
    }

    public void a(AmazingAdapter.HasMorePagesListener hasMorePagesListener) {
        this.j.a(hasMorePagesListener);
    }

    public final void a(@NonNull MoPubAdRenderer moPubAdRenderer) {
        if (!Preconditions.NoThrow.checkNotNull((Object)moPubAdRenderer, (String)"Tried to set a null ad renderer on the placer.")) {
            return;
        }
        this.k.registerAdRenderer(moPubAdRenderer);
    }

    @Override
    public void a(SongbookAmazingAdapter.DataRefreshListener dataRefreshListener) {
        this.j.a(dataRefreshListener);
    }

    @Override
    public void a(SongbookSortSelector.SortType sortType) {
        this.j.a(sortType);
    }

    public void a(@NonNull String string2, @Nullable RequestParameters requestParameters) {
        this.k.loadAds(string2, requestParameters);
    }

    public void a(boolean bl) {
        this.j.a(bl);
    }

    public boolean areAllItemsEnabled() {
        if (this.j instanceof SongbookAmazingAdapter && this.j.areAllItemsEnabled()) {
            return true;
        }
        return false;
    }

    public void b(String string2) {
        this.j.b(string2);
    }

    public void b(boolean bl) {
        this.j.b(bl);
    }

    public void c(int n) {
        this.j.c(n);
    }

    @VisibleForTesting
    void d(int n) {
        MoPubNativeAdLoadedListener moPubNativeAdLoadedListener;
        if (this.m != null && (moPubNativeAdLoadedListener = this.m.get()) != null) {
            moPubNativeAdLoadedListener.onAdLoaded(n);
        }
        this.notifyDataSetChanged();
    }

    public boolean d() {
        return this.j.d();
    }

    @VisibleForTesting
    void e(int n) {
        MoPubNativeAdLoadedListener moPubNativeAdLoadedListener;
        if (this.m != null && (moPubNativeAdLoadedListener = this.m.get()) != null) {
            moPubNativeAdLoadedListener.onAdRemoved(n);
        }
        this.notifyDataSetChanged();
    }

    public boolean f(int n) {
        return this.k.isAd(n);
    }

    public int getCount() {
        return this.k.getAdjustedCount(this.j.getCount());
    }

    @Nullable
    public Object getItem(int n) {
        Object object = this.k.getAdData(n);
        if (object != null) {
            return object;
        }
        return this.j.getItem(this.k.getOriginalPosition(n));
    }

    public long getItemId(int n) {
        Object object = this.k.getAdData(n);
        if (object != null) {
            return - System.identityHashCode(object);
        }
        return this.j.getItemId(this.k.getOriginalPosition(n));
    }

    public int getItemViewType(int n) {
        int n2 = this.k.getAdViewType(n);
        if (n2 != 0) {
            return n2 + this.j.getViewTypeCount() - 1;
        }
        return this.j.getItemViewType(this.k.getOriginalPosition(n));
    }

    @Override
    public int getPositionForSection(int n) {
        n = this.j.getPositionForSection(n);
        return this.k.getAdjustedPosition(n);
    }

    @Override
    public int getSectionForPosition(int n) {
        int n2;
        int n3 = this.getCount();
        for (n2 = n; this.f(n2) && n2 < n3 - 1; ++n2) {
        }
        n3 = n2;
        if (this.f(n2)) {
            n3 = n2;
            if (n2 > 0) {
                --n;
                do {
                    n3 = n;
                    if (!this.f(n)) break;
                    n3 = n;
                    if (n < 0) break;
                    --n;
                } while (true);
            }
        }
        if (this.f(n3)) {
            return 0;
        }
        n = this.k.getOriginalPosition(n3);
        return this.j.getSectionForPosition(n);
    }

    public Object[] getSections() {
        return this.j.getSections();
    }

    public int getViewTypeCount() {
        return this.j.getViewTypeCount() + this.k.getAdViewTypeCount();
    }

    public boolean hasStableIds() {
        return this.j.hasStableIds();
    }

    public AmazingAdapter.HasMorePagesListener i() {
        return this.j.i();
    }

    public boolean isEmpty() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.j.isEmpty()) {
            bl2 = bl;
            if (this.k.getAdjustedCount(0) == 0) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public boolean isEnabled(int n) {
        if (this.f(n) || this.j instanceof SongbookAmazingAdapter && this.j.isEnabled(this.k.getOriginalPosition(n))) {
            return true;
        }
        return false;
    }

    public AmazingAdapter.HasMoreCursorsListener j() {
        return this.j.j();
    }

    public boolean k() {
        return this.j.k();
    }

    @Override
    public boolean l() {
        return this.j.l();
    }

    @Override
    public void m() {
        this.j.m();
    }

    @Override
    public SongbookAmazingAdapter.AdapterInterface n() {
        return this.j.n();
    }

    @Override
    public SongbookSection o() {
        return this.j.o();
    }

    @Override
    public SongbookListViewState p() {
        return this.j.p();
    }

    @Override
    public SongbookSortSelector.SortMenuType q() {
        return this.j.q();
    }

    @Override
    public boolean r() {
        return this.j.r();
    }

    @Override
    public Long s() {
        return this.j.s();
    }

    public void setSmuleNativeAdEventListener(@NonNull SmuleNativeAdEventListener smuleNativeAdEventListener) {
        this.k.setSmuleNativeAdEventListener(smuleNativeAdEventListener);
    }

    public void t() {
        this.k.destroy();
        this.l.destroy();
    }

}

