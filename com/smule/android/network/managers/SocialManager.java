package com.smule.android.network.managers;

import android.content.SharedPreferences;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.api.SocialAPI;
import com.smule.android.network.api.SocialAPI$GetActivityFeedRequest;
import com.smule.android.network.api.SocialAPI$GetFacebookUsersRequest;
import com.smule.android.network.api.SocialAPI$ListActivityNotificationsRequest;
import com.smule.android.network.api.SocialAPI$ListFeedRequest;
import com.smule.android.network.api.SocialAPI$LookupNotificationsRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.models.FeedItem;
import com.smule.android.utils.JsonUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class SocialManager {
    protected static SocialManager f17078a = null;
    private static final String f17079b = SocialManager.class.getName();
    private SocialAPI f17080c = ((SocialAPI) MagicNetwork.a().a(SocialAPI.class));

    public interface FeedItemsCallback extends ResponseInterface<FeedItemsResponse> {
        void handleResponse(FeedItemsResponse feedItemsResponse);
    }

    class C36071 implements FeedItemsCallback {
        final /* synthetic */ SharedPreferences f17061a;

        public void handleResponse(FeedItemsResponse feedItemsResponse) {
            if (feedItemsResponse.a.c()) {
                this.f17061a.edit().putBoolean("newsfeedActivated", true).apply();
            }
        }
    }

    class C36082 implements Runnable {
        final /* synthetic */ FeedItemsCallback f17062a;
        final /* synthetic */ SocialManager f17063b;

        public void run() {
            CoreUtil.m18079a(this.f17062a, this.f17063b.m18352b());
        }
    }

    class C36093 implements Runnable {
        final /* synthetic */ FeedItemsCallback f17064a;
        final /* synthetic */ SocialManager f17065b;

        public void run() {
            CoreUtil.m18079a(this.f17064a, this.f17065b.m18354c());
        }
    }

    public static class FeedItemsResponse extends ParsedResponse {
        public List<FeedItem> f17077b;

        public String toString() {
            return "FeedItemsResponse [mResponse=" + this.a + ", items=" + this.f17077b + "]";
        }
    }

    public interface ListFeedResponseCallback extends ResponseInterface<ListFeedResponse> {
        void handleResponse(ListFeedResponse listFeedResponse);
    }

    public interface ListNotificationsResponseCallback extends ResponseInterface<ListNotificationsResponse> {
        void handleResponse(ListNotificationsResponse listNotificationsResponse);
    }

    public interface LookupNotificationsResponseCallback extends ResponseInterface<LookupNotificationsResponse> {
        void handleResponse(LookupNotificationsResponse lookupNotificationsResponse);
    }

    private SocialManager() {
    }

    public static SocialManager m18345a() {
        if (f17078a == null) {
            f17078a = new SocialManager();
        }
        return f17078a;
    }

    public FeedItemsResponse m18352b() {
        return m18344a(NetworkUtils.m18104a(this.f17080c.getActivityFeed(new SocialAPI$GetActivityFeedRequest())));
    }

    public FeedItemsResponse m18354c() {
        return m18352b();
    }

    private FeedItemsResponse m18344a(NetworkResponse networkResponse) {
        FeedItemsResponse feedItemsResponse = new FeedItemsResponse();
        feedItemsResponse.a = networkResponse;
        if (!networkResponse.c()) {
            return feedItemsResponse;
        }
        List supportedVerbTypes = MagicNetwork.d().getSupportedVerbTypes();
        feedItemsResponse.f17077b = new ArrayList();
        Iterator it = networkResponse.l.get("feed").iterator();
        while (it.hasNext()) {
            FeedItem feedItem = (FeedItem) JsonUtils.m18985a((JsonNode) it.next(), FeedItem.class);
            if (feedItem.a(supportedVerbTypes)) {
                feedItemsResponse.f17077b.add(feedItem);
            } else {
                Log.e(f17079b, "Invalid feed item received!");
            }
        }
        for (FeedItem feedItem2 : feedItemsResponse.f17077b) {
            feedItem2.a(UserManager.a().f(), UserManager.a().g());
        }
        return feedItemsResponse;
    }

    public NetworkResponse m18346a(List<String> list, boolean z) {
        return NetworkUtils.m18104a(this.f17080c.getFacebookUsers(new SocialAPI$GetFacebookUsersRequest().setAfbIds(list).setThisAppOnly(false).setAutoFollow(Boolean.valueOf(z))));
    }

    public LookupNotificationsResponse m18348a(List<String> list) {
        return LookupNotificationsResponse.a(NetworkUtils.m18104a(this.f17080c.lookupNotifications(new SocialAPI$LookupNotificationsRequest().setNotificationKeys(list))));
    }

    public Future<?> m18351a(final List<String> list, final LookupNotificationsResponseCallback lookupNotificationsResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SocialManager f17068c;

            public void run() {
                CoreUtil.m18079a(lookupNotificationsResponseCallback, this.f17068c.m18348a(list));
            }
        });
    }

    public ListNotificationsResponse m18347a(Integer num, Integer num2) {
        return ListNotificationsResponse.a(NetworkUtils.m18104a(this.f17080c.listActivityNotifications(new SocialAPI$ListActivityNotificationsRequest().setOffset(num).setLimit(num2))));
    }

    public Future<?> m18350a(final Integer num, final Integer num2, final ListNotificationsResponseCallback listNotificationsResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SocialManager f17072d;

            public void run() {
                CoreUtil.m18079a(listNotificationsResponseCallback, this.f17072d.m18347a(num, num2));
            }
        });
    }

    public ListFeedResponse m18353b(Integer num, Integer num2) {
        return ListFeedResponse.a(NetworkUtils.m18104a(this.f17080c.listFeed(new SocialAPI$ListFeedRequest().setLimit(num2).setOffset(num))));
    }

    public Future<?> m18349a(final Integer num, final Integer num2, final ListFeedResponseCallback listFeedResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SocialManager f17076d;

            public void run() {
                CoreUtil.m18079a(listFeedResponseCallback, this.f17076d.m18353b(num, num2));
            }
        });
    }
}
