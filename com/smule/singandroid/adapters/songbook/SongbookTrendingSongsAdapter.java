package com.smule.singandroid.adapters.songbook;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.managers.RecommendationManager.CacheDuration;
import com.smule.android.network.managers.RecommendationManager.GetRecommendedCompsByLocaleCallback;
import com.smule.android.network.managers.RecommendationManager.RecContext;
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

public class SongbookTrendingSongsAdapter extends SongbookPaginatedAdapter {
    private InitialLoadRecommendedCallback f20615f;

    class C42271 implements GetRecommendedCompsByLocaleCallback {
        final /* synthetic */ SongbookTrendingSongsAdapter f20614a;

        C42271(SongbookTrendingSongsAdapter songbookTrendingSongsAdapter) {
            this.f20614a = songbookTrendingSongsAdapter;
        }

        public void handleResponse(RecommendedCompsResponse recommendedCompsResponse) {
            if (recommendedCompsResponse.a()) {
                this.f20614a.m22212a(recommendedCompsResponse.mComps);
                this.f20614a.m22200v();
                return;
            }
            this.f20614a.m22199u();
        }
    }

    public SongbookTrendingSongsAdapter(AdapterInterface adapterInterface, InitialLoadRecommendedCallback initialLoadRecommendedCallback) {
        super(adapterInterface);
        this.f20615f = initialLoadRecommendedCallback;
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
            List b = RecommendationManager.m18285a().m18303b(RecContext.songbook);
            if (b == null) {
                if (z || this.l.isEmpty()) {
                    m22198t();
                }
                RecommendationManager.m18285a().m18296a(CacheDuration.SHORT, RecContext.songbook, new C42271(this));
                return;
            }
            m22212a(b);
            m22200v();
        }
    }

    void m22212a(List<RecCompositionLite> list) {
        List arrayList = new ArrayList();
        for (RecCompositionLite recCompositionLite : list) {
            CompositionLite compositionLite = recCompositionLite.mComp;
            if (compositionLite.mType == CompositionLite$Type.ARR && compositionLite.mArrangementVersionLite != null) {
                arrayList.add(new RecArrangementVersionLiteEntry(recCompositionLite.mComp.mArrangementVersionLite, recCompositionLite.mRecId, false));
            } else if (compositionLite.mType == CompositionLite$Type.f17391a && compositionLite.mSongLite != null) {
                ListingV2 e = StoreManager.m18378a().m18428e(compositionLite.mSongLite.songId);
                if (e == null) {
                    Log.d(a, "Could not find listing for: " + compositionLite.mSongLite.songId);
                } else {
                    arrayList.add(new RecListingEntry(e, recCompositionLite.mRecId, false));
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
            this.f20615f.mo6652a();
        }
    }
}
