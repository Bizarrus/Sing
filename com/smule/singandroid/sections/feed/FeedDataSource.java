/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.sections.feed;

import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.SocialManager;
import com.smule.android.network.models.FeedListItem;
import com.smule.android.network.models.FeedListItemObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class FeedDataSource
extends MagicDataSource<com.smule.android.network.models.FeedListItem, MagicDataSource.OffsetPaginationTracker> {
    public static boolean a;
    private static final String b;
    private final FeedType l;

    static {
        b = FeedDataSource.class.getName();
        a = false;
    }

    public FeedDataSource(FeedType feedType) {
        super(FeedDataSource.class.getSimpleName(), new MagicDataSource.OffsetPaginationTracker());
        this.l = feedType;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean a(com.smule.android.network.models.FeedListItem feedListItem) {
        boolean bl = true;
        if (!feedListItem.object.a()) {
            return false;
        }
        if (feedListItem.b() == null) return false;
        switch (feedListItem.b()) {
            default: {
                if (feedListItem.a() == null) return true;
                if (feedListItem.a() == FeedListItem.c) return false;
                if (feedListItem.a() == FeedListItem.g) return false;
                return true;
            }
            case a: 
        }
        if (feedListItem.a() == null) return false;
        if (feedListItem.a() == FeedListItem.c) return false;
        if (feedListItem.a() == FeedListItem.g) return false;
        return bl;
    }

    private Future<?> b(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<com.smule.android.network.models.FeedListItem, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        return com.smule.android.network.managers.SocialManager.a().a(offsetPaginationTracker.a(), (Integer)n, new SocialManager.ListFeedResponseCallback(){

            @Override
            public void handleResponse(SocialManager listFeedResponse) {
                if (!listFeedResponse.a()) {
                    fetchDataCallback.a();
                    return;
                }
                ArrayList<com.smule.android.network.models.FeedListItem> arrayList = new ArrayList<com.smule.android.network.models.FeedListItem>();
                Log.c(b, "handleResponse : [" + listFeedResponse.feedListItems.size() + "]");
                for (com.smule.android.network.models.FeedListItem feedListItem : listFeedResponse.feedListItems) {
                    if (!FeedDataSource.this.a(feedListItem)) continue;
                    arrayList.add(feedListItem);
                }
                fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(listFeedResponse.mNext));
            }
        });
    }

    private Future<?> c(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<com.smule.android.network.models.FeedListItem, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        return com.smule.android.network.managers.SocialManager.a().b(offsetPaginationTracker.a(), n, new SocialManager.ListFeedResponseCallback(){

            @Override
            public void handleResponse(SocialManager listFeedResponse) {
                if (!listFeedResponse.a()) {
                    fetchDataCallback.a();
                    return;
                }
                ArrayList<com.smule.android.network.models.FeedListItem> arrayList = new ArrayList<com.smule.android.network.models.FeedListItem>();
                Log.c(b, "handleResponse : [" + listFeedResponse.feedListItems.size() + "]");
                for (com.smule.android.network.models.FeedListItem feedListItem : listFeedResponse.feedListItems) {
                    if (!FeedDataSource.this.a(feedListItem)) continue;
                    arrayList.add(feedListItem);
                }
                fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(listFeedResponse.mNext));
            }
        });
    }

    @Override
    public int a() {
        return 10;
    }

    @Override
    public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, MagicDataSource.FetchDataCallback<com.smule.android.network.models.FeedListItem, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
        if (this.l == FeedType.a) {
            return this.b(offsetPaginationTracker, n, fetchDataCallback);
        }
        return this.c(offsetPaginationTracker, n, fetchDataCallback);
    }

    public static enum FeedType {
        a,
        b;
        

        private FeedType() {
        }
    }

}

