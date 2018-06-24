package com.smule.singandroid.datasource;

import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.api.TopicsAPI$SubmitChosenTopicsRequest.SongBookEntryId;
import com.smule.android.network.managers.RecommendationManager.RecommendedCompsResponse.RecCompositionLite;
import com.smule.android.network.managers.TopicsManager;
import com.smule.android.network.managers.TopicsManager.SubmitChosenTopicsResponse;
import com.smule.android.network.managers.TopicsManager.SubmitChosenTopicsResponseListener;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$DeferredLaunchParam;
import com.smule.android.network.managers.UserManager$DeferredLaunchParam.Type;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.CompositionLite$Type;
import com.smule.android.network.models.ListingV2;
import com.smule.android.songbook.SongbookEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class OnboardingSongsDataSource extends MagicDataSource<SongbookEntry> {
    private static final String f22106c = OnboardingSongsDataSource.class.getName();
    ArrayList<Integer> f22107a;
    ArrayList<SongBookEntryId> f22108b;

    public OnboardingSongsDataSource(ArrayList<Integer> arrayList) {
        String str = null;
        super(OnboardingSongsDataSource.class.getSimpleName() + arrayList.toString());
        this.f22107a = arrayList;
        UserManager$DeferredLaunchParam x = UserManager.a().x();
        if (x != null) {
            try {
                Object obj = x.f17308b == Type.ARR ? 1 : null;
                String name = x.f17308b.name();
                String str2 = obj != null ? null : x.f17307a;
                if (obj != null) {
                    str = x.f17307a;
                }
                SongBookEntryId songBookEntryId = new SongBookEntryId(name, str2, str);
                this.f22108b = new ArrayList();
                this.f22108b.add(songBookEntryId);
            } catch (Throwable e) {
                Log.d(f22106c, "Error creating deferred launch song ID", e);
            }
        }
    }

    public int mo6242a() {
        return 0;
    }

    protected long mo6245c() {
        return 30;
    }

    public Future<?> mo6243a(int i, int i2, final FetchDataCallback<SongbookEntry> fetchDataCallback) {
        if (this.f22107a != null) {
            TopicsManager.m18458a().m18463a(this.f22107a, true, this.f22108b, new SubmitChosenTopicsResponseListener(this) {
                final /* synthetic */ OnboardingSongsDataSource f22105b;

                public void handleResponse(SubmitChosenTopicsResponse submitChosenTopicsResponse) {
                    if (submitChosenTopicsResponse.a()) {
                        List arrayList = new ArrayList();
                        for (RecCompositionLite recCompositionLite : submitChosenTopicsResponse.mComps) {
                            CompositionLite compositionLite = recCompositionLite.mComp;
                            if (compositionLite.mType == CompositionLite$Type.f17391a && compositionLite.mSongLite != null) {
                                arrayList.add(SongbookEntry.m18746a(new ListingV2(compositionLite.mSongLite)));
                            } else if (compositionLite.mType == CompositionLite$Type.ARR && compositionLite.mArrangementVersionLite != null) {
                                arrayList.add(SongbookEntry.m18744a(compositionLite.mArrangementVersionLite));
                            }
                        }
                        fetchDataCallback.mo6257a(arrayList, -1);
                        return;
                    }
                    fetchDataCallback.mo6256a();
                }
            });
        }
        return null;
    }
}
