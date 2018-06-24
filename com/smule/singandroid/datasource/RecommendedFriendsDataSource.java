/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.datasource;

import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.models.CursorModel;
import java.util.List;
import java.util.concurrent.Future;

public class RecommendedFriendsDataSource
extends MagicDataSource<RecommendationManager.RecommendedSingersResponse.RecAccountIcon, MagicDataSource.CursorPaginationTracker> {
    public static final String b = RecommendedFriendsDataSource.class.getName();
    protected int l = 20;

    public RecommendedFriendsDataSource(int n) {
        super(RecommendedFriendsDataSource.class.getSimpleName(), new MagicDataSource.CursorPaginationTracker());
        this.l = n;
    }

    public RecommendedFriendsDataSource(String string2, MagicDataSource.CursorPaginationTracker cursorPaginationTracker) {
        super(string2, cursorPaginationTracker);
    }

    @Override
    public Future<?> a(MagicDataSource.CursorPaginationTracker object, int n, final MagicDataSource.FetchDataCallback<RecommendationManager.RecommendedSingersResponse.RecAccountIcon, MagicDataSource.CursorPaginationTracker> fetchDataCallback) {
        object = object.a();
        return com.smule.android.network.managers.RecommendationManager.a().a(object.next, this.l, new RecommendationManager(){

            @Override
            public void handleResponse(RecommendationManager.RecommendedSingersResponse recommendedSingersResponse) {
                if (recommendedSingersResponse.a()) {
                    fetchDataCallback.a(recommendedSingersResponse.mRecAccountIcons, new MagicDataSource.CursorPaginationTracker(recommendedSingersResponse.mCursor));
                    return;
                }
                fetchDataCallback.a();
            }
        });
    }

    @Override
    public void b(String string2) {
        this.f = null;
    }

    @Override
    protected long d() {
        return 0;
    }

}

