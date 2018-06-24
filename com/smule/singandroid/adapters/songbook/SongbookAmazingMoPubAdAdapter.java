package com.smule.singandroid.adapters.songbook;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import com.foound.widget.AmazingAdapter$HasMorePagesListener;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubAdRenderer;
import com.mopub.nativeads.MoPubNative.SmuleNativeAdEventListenerInterface;
import com.mopub.nativeads.MoPubNativeAdLoadedListener;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.SmuleNativeAdEventListener;
import com.mopub.nativeads.VisibilityTracker;
import com.mopub.nativeads.VisibilityTracker.VisibilityTrackerListener;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.AdapterInterface;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.DataRefreshListener;
import com.smule.singandroid.customviews.SongbookSortSelector.SortMenuType;
import com.smule.singandroid.customviews.SongbookSortSelector.SortType;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.WeakHashMap;

public class SongbookAmazingMoPubAdAdapter extends SongbookAmazingAdapter implements SmuleNativeAdEventListenerInterface {
    @NonNull
    private final WeakHashMap<View, Integer> f20569f = new WeakHashMap();
    @NonNull
    private final SongbookAmazingAdapter f20570g;
    @NonNull
    private final MoPubStreamAdPlacer f20571h;
    @NonNull
    private final VisibilityTracker f20572i;
    @Nullable
    private WeakReference<MoPubNativeAdLoadedListener> f20573j;

    class C42181 implements VisibilityTrackerListener {
        final /* synthetic */ SongbookAmazingMoPubAdAdapter f20560a;

        C42181(SongbookAmazingMoPubAdAdapter songbookAmazingMoPubAdAdapter) {
            this.f20560a = songbookAmazingMoPubAdAdapter;
        }

        public void onVisibilityChanged(@NonNull List<View> list, List<View> list2) {
            this.f20560a.m22141a((List) list);
        }
    }

    class C42192 extends DataSetObserver {
        final /* synthetic */ SongbookAmazingMoPubAdAdapter f20561a;

        C42192(SongbookAmazingMoPubAdAdapter songbookAmazingMoPubAdAdapter) {
            this.f20561a = songbookAmazingMoPubAdAdapter;
        }

        public void onChanged() {
            this.f20561a.f20571h.setItemCount(this.f20561a.f20570g.getCount());
            this.f20561a.notifyDataSetChanged();
        }

        public void onInvalidated() {
            this.f20561a.notifyDataSetInvalidated();
        }
    }

    class C42203 implements MoPubNativeAdLoadedListener {
        final /* synthetic */ SongbookAmazingMoPubAdAdapter f20562a;

        C42203(SongbookAmazingMoPubAdAdapter songbookAmazingMoPubAdAdapter) {
            this.f20562a = songbookAmazingMoPubAdAdapter;
        }

        public void onAdLoaded(int i) {
            this.f20562a.m22153d(i);
        }

        public void onAdRemoved(int i) {
            this.f20562a.m22155e(i);
        }

        public void onAdChanged() {
            this.f20562a.notifyDataSetChanged();
        }
    }

    class C42214 implements OnItemClickListener {
        final /* synthetic */ OnItemClickListener f20563a;
        final /* synthetic */ SongbookAmazingMoPubAdAdapter f20564b;

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!this.f20564b.f20571h.isAd(i)) {
                this.f20563a.onItemClick(adapterView, view, this.f20564b.f20571h.getOriginalPosition(i), j);
            }
        }
    }

    class C42225 implements OnItemLongClickListener {
        final /* synthetic */ OnItemLongClickListener f20565a;
        final /* synthetic */ SongbookAmazingMoPubAdAdapter f20566b;

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!this.f20566b.m22156f(i)) {
                if (!this.f20565a.onItemLongClick(adapterView, view, this.f20566b.f20571h.getOriginalPosition(i), j)) {
                    return false;
                }
            }
            return true;
        }
    }

    class C42236 implements OnItemSelectedListener {
        final /* synthetic */ OnItemSelectedListener f20567a;
        final /* synthetic */ SongbookAmazingMoPubAdAdapter f20568b;

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (!this.f20568b.m22156f(i)) {
                this.f20567a.onItemSelected(adapterView, view, this.f20568b.f20571h.getOriginalPosition(i), j);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            this.f20567a.onNothingSelected(adapterView);
        }
    }

    @VisibleForTesting
    public SongbookAmazingMoPubAdAdapter(@NonNull MoPubStreamAdPlacer moPubStreamAdPlacer, @NonNull SongbookAmazingAdapter songbookAmazingAdapter, @NonNull VisibilityTracker visibilityTracker) {
        this.f20570g = songbookAmazingAdapter;
        this.f20571h = moPubStreamAdPlacer;
        this.f20572i = visibilityTracker;
        this.f20572i.setVisibilityTrackerListener(new C42181(this));
        this.f20570g.registerDataSetObserver(new C42192(this));
        this.f20571h.setAdLoadedListener(new C42203(this));
        this.f20571h.setItemCount(this.f20570g.getCount());
    }

    @VisibleForTesting
    void m22153d(int i) {
        if (this.f20573j != null) {
            MoPubNativeAdLoadedListener moPubNativeAdLoadedListener = (MoPubNativeAdLoadedListener) this.f20573j.get();
            if (moPubNativeAdLoadedListener != null) {
                moPubNativeAdLoadedListener.onAdLoaded(i);
            }
        }
        notifyDataSetChanged();
    }

    @VisibleForTesting
    void m22155e(int i) {
        if (this.f20573j != null) {
            MoPubNativeAdLoadedListener moPubNativeAdLoadedListener = (MoPubNativeAdLoadedListener) this.f20573j.get();
            if (moPubNativeAdLoadedListener != null) {
                moPubNativeAdLoadedListener.onAdRemoved(i);
            }
        }
        notifyDataSetChanged();
    }

    public final void m22146a(@NonNull MoPubAdRenderer moPubAdRenderer) {
        if (NoThrow.checkNotNull(moPubAdRenderer, "Tried to set a null ad renderer on the placer.")) {
            this.f20571h.registerAdRenderer(moPubAdRenderer);
        }
    }

    public void m22149a(@NonNull String str, @Nullable RequestParameters requestParameters) {
        this.f20571h.loadAds(str, requestParameters);
    }

    public boolean m22156f(int i) {
        return this.f20571h.isAd(i);
    }

    public void m22168r() {
        this.f20571h.destroy();
        this.f20572i.destroy();
    }

    public boolean areAllItemsEnabled() {
        return (this.f20570g instanceof SongbookAmazingAdapter) && this.f20570g.areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return m22156f(i) || ((this.f20570g instanceof SongbookAmazingAdapter) && this.f20570g.isEnabled(this.f20571h.getOriginalPosition(i)));
    }

    public int getCount() {
        return this.f20571h.getAdjustedCount(this.f20570g.getCount());
    }

    @Nullable
    public Object getItem(int i) {
        Object adData = this.f20571h.getAdData(i);
        return adData != null ? adData : this.f20570g.getItem(this.f20571h.getOriginalPosition(i));
    }

    public long getItemId(int i) {
        Object adData = this.f20571h.getAdData(i);
        if (adData != null) {
            return (long) (-System.identityHashCode(adData));
        }
        return this.f20570g.getItemId(this.f20571h.getOriginalPosition(i));
    }

    public boolean hasStableIds() {
        return this.f20570g.hasStableIds();
    }

    public int getItemViewType(int i) {
        int adViewType = this.f20571h.getAdViewType(i);
        if (adViewType != 0) {
            return (adViewType + this.f20570g.getViewTypeCount()) - 1;
        }
        return this.f20570g.getItemViewType(this.f20571h.getOriginalPosition(i));
    }

    public int getViewTypeCount() {
        return this.f20570g.getViewTypeCount() + this.f20571h.getAdViewTypeCount();
    }

    public boolean isEmpty() {
        return this.f20570g.isEmpty() && this.f20571h.getAdjustedCount(0) == 0;
    }

    private void m22141a(@NonNull List<View> list) {
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        for (View view : list) {
            Integer num = (Integer) this.f20569f.get(view);
            if (num != null) {
                i = Math.min(num.intValue(), i);
                i2 = Math.max(num.intValue(), i2);
            }
        }
        this.f20571h.placeAdsInRange(i, i2 + 1);
    }

    public void m22152c(int i) {
        this.f20570g.c(i);
    }

    public void mo6694a(SortType sortType) {
        this.f20570g.mo6694a(sortType);
    }

    public boolean mo6697i() {
        return this.f20570g.mo6697i();
    }

    public void mo6698j() {
        this.f20570g.mo6698j();
    }

    public boolean mo6699k() {
        return this.f20570g.mo6699k();
    }

    public void mo6693a(DataRefreshListener dataRefreshListener) {
        this.f20570g.mo6693a(dataRefreshListener);
    }

    public AdapterInterface mo6700l() {
        return this.f20570g.mo6700l();
    }

    public SongbookSection mo6701m() {
        return this.f20570g.mo6701m();
    }

    public void mo6702n() {
        this.f20570g.mo6702n();
    }

    public SongbookListViewState mo6703o() {
        return this.f20570g.mo6703o();
    }

    public SortMenuType mo6704p() {
        return this.f20570g.mo6704p();
    }

    public Object[] getSections() {
        return this.f20570g.getSections();
    }

    public int getPositionForSection(int i) {
        return this.f20571h.getAdjustedPosition(this.f20570g.getPositionForSection(i));
    }

    public int getSectionForPosition(int i) {
        int count = getCount();
        int i2 = i;
        while (m22156f(i2) && i2 < count - 1) {
            i2++;
        }
        if (m22156f(i2) && i2 > 0) {
            i2 = i - 1;
            while (m22156f(i2) && i2 >= 0) {
                i2--;
            }
        }
        if (m22156f(i2)) {
            return 0;
        }
        return this.f20570g.getSectionForPosition(this.f20571h.getOriginalPosition(i2));
    }

    protected void mo6692a(View view, int i, int i2, boolean z) {
        if (!m22156f(i2)) {
            this.f20570g.mo6692a(view, i, this.f20571h.getOriginalPosition(i2), z);
        }
    }

    @Nullable
    public View mo6691a(int i, View view, ViewGroup viewGroup, boolean z) {
        View adView = this.f20571h.getAdView(i, view, viewGroup);
        if (adView == null) {
            adView = this.f20570g.mo6691a(this.f20571h.getOriginalPosition(i), view, viewGroup, z);
        }
        this.f20569f.put(adView, Integer.valueOf(i));
        this.f20572i.addView(adView, 0);
        return adView;
    }

    public void m22145a(AmazingAdapter$HasMorePagesListener amazingAdapter$HasMorePagesListener) {
        this.f20570g.a(amazingAdapter$HasMorePagesListener);
    }

    public AmazingAdapter$HasMorePagesListener m22157g() {
        return this.f20570g.g();
    }

    public boolean m22158h() {
        return this.f20570g.h();
    }

    public void m22151b(boolean z) {
        this.f20570g.b(z);
    }

    public void m22150a(boolean z) {
        this.f20570g.a(z);
    }

    public boolean m22154d() {
        return this.f20570g.d();
    }

    public void setSmuleNativeAdEventListener(@NonNull SmuleNativeAdEventListener smuleNativeAdEventListener) {
        this.f20571h.setSmuleNativeAdEventListener(smuleNativeAdEventListener);
    }

    public boolean mo6705q() {
        return this.f20570g.mo6705q();
    }
}
