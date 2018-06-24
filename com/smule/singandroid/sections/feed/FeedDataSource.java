package com.smule.singandroid.sections.feed;

import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.SocialManager;
import com.smule.android.network.managers.SocialManager.ListFeedResponse;
import com.smule.android.network.managers.SocialManager.ListFeedResponseCallback;
import com.smule.android.network.models.FeedListItem;
import com.smule.android.network.models.FeedListItem$ActionType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class FeedDataSource extends MagicDataSource<FeedListItem> {
    private static final String f23955a = FeedDataSource.class.getName();

    public FeedDataSource() {
        super(FeedDataSource.class.getSimpleName());
    }

    public int mo6242a() {
        return 10;
    }

    public Future<?> mo6243a(int i, int i2, final FetchDataCallback<FeedListItem> fetchDataCallback) {
        return SocialManager.m18345a().m18349a(Integer.valueOf(i), Integer.valueOf(i2), new ListFeedResponseCallback(this) {
            final /* synthetic */ FeedDataSource f23953b;

            public void handleResponse(ListFeedResponse listFeedResponse) {
                if (listFeedResponse.a()) {
                    List arrayList = new ArrayList();
                    Log.c(FeedDataSource.f23955a, "handleResponse : [" + listFeedResponse.feedListItems.size() + "]");
                    for (FeedListItem feedListItem : listFeedResponse.feedListItems) {
                        if (this.f23953b.m25197a(feedListItem)) {
                            arrayList.add(feedListItem);
                        }
                    }
                    fetchDataCallback.mo6257a(arrayList, listFeedResponse.mNext.intValue());
                    return;
                }
                fetchDataCallback.mo6256a();
            }
        });
    }

    private boolean m25197a(FeedListItem feedListItem) {
        boolean z = true;
        if (!feedListItem.object.a() || feedListItem.b() == null) {
            return false;
        }
        switch (feedListItem.b()) {
            case MYNW:
                if (feedListItem.a() == null || feedListItem.a() == FeedListItem$ActionType.FOLLOW || feedListItem.a() == FeedListItem$ActionType.INVITE) {
                    z = false;
                }
                return z;
            default:
                if (feedListItem.a() == null || (feedListItem.a() != FeedListItem$ActionType.FOLLOW && feedListItem.a() != FeedListItem$ActionType.INVITE)) {
                    return true;
                }
                return false;
        }
    }
}
