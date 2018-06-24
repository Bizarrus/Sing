/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.singandroid.datasource;

import android.support.annotation.NonNull;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.managers.TopicsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.SongbookEntryId;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class OnboardingSongsDataSource
extends MagicDataSource<SongbookEntry, MagicDataSource.OffsetPaginationTracker> {
    private static final String l = OnboardingSongsDataSource.class.getName();
    ArrayList<Integer> a;
    ArrayList<SongbookEntryId> b;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public OnboardingSongsDataSource(@NonNull ArrayList<Integer> object) {
        String string2 = null;
        super(OnboardingSongsDataSource.class.getSimpleName() + object.toString(), new MagicDataSource.OffsetPaginationTracker());
        this.a = object;
        UserManager deferredLaunchParam = com.smule.android.network.managers.UserManager.a().w();
        if (deferredLaunchParam != null) {
            try {
                boolean bl = deferredLaunchParam.b == UserManager.Type.b;
                String string3 = deferredLaunchParam.b.name();
                object = bl ? null : deferredLaunchParam.a;
                if (bl) {
                    string2 = deferredLaunchParam.a;
                }
                object = new SongbookEntryId(string3, (String)object, string2);
                this.b = new ArrayList();
                this.b.add((SongbookEntryId)object);
                return;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                Log.d(l, "Error creating deferred launch song ID", illegalArgumentException);
            }
        }
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<SongbookEntry, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        com.smule.android.network.managers.TopicsManager.a().a(this.a, true, this.b, new TopicsManager.SubmitChosenTopicsResponseListener(){

            @Override
            public void handleResponse(TopicsManager object) {
                NotificationCenter.a().b("ONBOARDING_FINISHED", new Object[0]);
                if (object.a()) {
                    ArrayList<SongbookEntry> arrayList = new ArrayList<SongbookEntry>();
                    object = object.mComps.iterator();
                    while (object.hasNext()) {
                        CompositionLite compositionLite = ((RecommendationManager.RecommendedCompsResponse.RecCompositionLite)object.next()).mComp;
                        if (compositionLite.mType != CompositionLite.Type.b || compositionLite.mArrangementVersionLite == null) continue;
                        arrayList.add(SongbookEntry.a(compositionLite.mArrangementVersionLite));
                    }
                    fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(-1));
                    return;
                }
                fetchDataCallback.a();
            }
        });
        return null;
    }

    @Override
    protected long d() {
        return 30;
    }

}

