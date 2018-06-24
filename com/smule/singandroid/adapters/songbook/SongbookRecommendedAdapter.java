package com.smule.singandroid.adapters.songbook;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.managers.RecommendationManager.CacheDuration;
import com.smule.android.network.managers.RecommendationManager.GetRecommendedCompsCallback;
import com.smule.android.network.managers.RecommendationManager.RecommendedCompsResponse;
import com.smule.android.network.managers.RecommendationManager.RecommendedCompsResponse.RecCompositionLite;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.CompositionLite$Type;
import com.smule.android.network.models.ListingV2;
import com.smule.android.songbook.RecArrangementVersionLiteEntry;
import com.smule.android.songbook.RecListingEntry;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SongbookFragment.InitialLoadRecommendedCallback;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.AdapterInterface;
import java.util.ArrayList;
import java.util.List;

public class SongbookRecommendedAdapter extends SongbookPaginatedAdapter {
    private InitialLoadRecommendedCallback f20604f;
    private boolean f20605g;

    class C42251 implements GetRecommendedCompsCallback {
        final /* synthetic */ SongbookRecommendedAdapter f20603a;

        C42251(SongbookRecommendedAdapter songbookRecommendedAdapter) {
            this.f20603a = songbookRecommendedAdapter;
        }

        public void handleResponse(RecommendedCompsResponse recommendedCompsResponse) {
            if (recommendedCompsResponse.a()) {
                this.f20603a.m22205a(recommendedCompsResponse.mComps);
                this.f20603a.m22200v();
                return;
            }
            this.f20603a.m22199u();
        }
    }

    public SongbookRecommendedAdapter(AdapterInterface adapterInterface, InitialLoadRecommendedCallback initialLoadRecommendedCallback, boolean z) {
        super(adapterInterface);
        this.f20604f = initialLoadRecommendedCallback;
        this.f20605g = z;
    }

    public boolean mo6705q() {
        return true;
    }

    public boolean mo6712w() {
        return false;
    }

    public boolean mo6697i() {
        return this.l == null || this.l.size() == 0;
    }

    protected void mo6708c(boolean z) {
        if (SingApplication.f) {
            if (this.l == null) {
                this.l = new ArrayList();
            }
            List d = RecommendationManager.m18285a().m18307d();
            if (d == null) {
                if (z || this.l.isEmpty()) {
                    m22198t();
                }
                RecommendationManager.m18285a().m18295a(CacheDuration.SHORT, new C42251(this));
                return;
            }
            m22205a(d);
            m22200v();
        }
    }

    void m22205a(List<RecCompositionLite> list) {
        List arrayList = new ArrayList();
        for (RecCompositionLite recCompositionLite : list) {
            CompositionLite compositionLite = recCompositionLite.mComp;
            if (compositionLite.mType == CompositionLite$Type.ARR && compositionLite.mArrangementVersionLite != null) {
                arrayList.add(new RecArrangementVersionLiteEntry(recCompositionLite.mComp.mArrangementVersionLite, recCompositionLite.mRecId, this.f20605g));
            } else if (compositionLite.mType == CompositionLite$Type.f17391a && compositionLite.mSongLite != null) {
                ListingV2 e = StoreManager.m18378a().m18428e(compositionLite.mSongLite.songId);
                if (e == null) {
                    Log.d(a, "Could not find listing for: " + compositionLite.mSongLite.songId);
                } else {
                    arrayList.add(new RecListingEntry(e, recCompositionLite.mRecId, this.f20605g));
                }
            }
        }
        boolean z = getCount() == 0;
        this.j.f23512a = arrayList;
        this.l = arrayList;
        this.i = true;
        e();
        mo6702n();
        if (z) {
            this.f20604f.mo6652a();
        }
    }
}
