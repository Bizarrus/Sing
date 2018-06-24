/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.databind.JsonNode
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.SharedPreferences;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.api.SocialAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.FeedItem;
import com.smule.android.network.models.FeedListItem;
import com.smule.android.network.models.Notification;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.utils.JsonUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import retrofit2.Call;

public class SocialManager {
    protected static SocialManager a;
    private static final String b;
    private com.smule.android.network.api.SocialAPI c = MagicNetwork.a().a(com.smule.android.network.api.SocialAPI.class);

    static {
        b = SocialManager.class.getName();
        a = null;
    }

    private SocialManager() {
    }

    private FeedItemsResponse a(NetworkResponse iterator) {
        FeedItemsResponse feedItemsResponse = new FeedItemsResponse();
        feedItemsResponse.a = iterator;
        if (!iterator.c()) {
            return feedItemsResponse;
        }
        List<String> list = MagicNetwork.d().getSupportedVerbTypes();
        feedItemsResponse.b = new ArrayList<FeedItem>();
        iterator = iterator.l.get("feed").iterator();
        while (iterator.hasNext()) {
            FeedItem feedItem = JsonUtils.a((JsonNode)iterator.next(), FeedItem.class);
            if (feedItem.a(list)) {
                feedItemsResponse.b.add(feedItem);
                continue;
            }
            Log.e(b, "Invalid feed item received!");
        }
        iterator = feedItemsResponse.b.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(UserManager.a().f(), UserManager.a().g());
        }
        return feedItemsResponse;
    }

    public static SocialManager a() {
        if (a == null) {
            a = new SocialManager();
        }
        return a;
    }

    public NetworkResponse a(List<String> list, boolean bl) {
        return NetworkUtils.a(this.c.getFacebookUsers(new SnpRequest(){
            public List<String> afbIds;
            public String app;
            public Boolean autoFollow;
            public String fbAppId = MagicNetwork.d().getFacebookAppId();

            public SocialAPI setAfbIds(List<String> list) {
                this.afbIds = list;
                return this;
            }

            public SocialAPI setAutoFollow(Boolean bl) {
                this.autoFollow = bl;
                return this;
            }

            public SocialAPI setThisAppOnly(boolean bl) {
                if (bl) {
                    this.app = MagicNetwork.d().getAppUID();
                }
                return this;
            }
        }.setAfbIds(list).setThisAppOnly(false).setAutoFollow(bl)));
    }

    public  a(Integer n, Integer n2) {
        return .a(NetworkUtils.a(this.c.listActivityNotifications(new SnpRequest(){
            public Integer limit = 10;
            public Integer offset = 0;

            public SocialAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public SocialAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }
        }.setOffset(n).setLimit(n2))));
    }

    public  a(List<String> list) {
        return .a(NetworkUtils.a(this.c.lookupNotifications(new SnpRequest(){
            public List<String> notificationKeys;

            public SocialAPI setNotificationKeys(List<String> list) {
                this.notificationKeys = list;
                return this;
            }
        }.setNotificationKeys(list))));
    }

    public Future<?> a(final Integer n, final Integer n2, final ListFeedResponseCallback listFeedResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(listFeedResponseCallback, SocialManager.this.b(n, n2));
            }
        });
    }

    public Future<?> a(final Integer n, final Integer n2, final ListNotificationsResponseCallback listNotificationsResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(listNotificationsResponseCallback, SocialManager.this.a(n, n2));
            }
        });
    }

    public Future<?> a(final List<String> list, final LookupNotificationsResponseCallback lookupNotificationsResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(lookupNotificationsResponseCallback, SocialManager.this.a(list));
            }
        });
    }

    public FeedItemsResponse b() {
        return this.a(NetworkUtils.a(this.c.getActivityFeed(new SnpRequest(){
            public List<String> apps = java.util.Collections.singletonList(MagicNetwork.d().getAppUID());
            public boolean useIcons = true;

            public SocialAPI setApp(String string2) {
                if (string2 != null) {
                    this.apps = java.util.Arrays.asList(string2);
                }
                return this;
            }

            public SocialAPI setApps(List<String> list) {
                this.apps = list;
                return this;
            }
        })));
    }

    public  b(Integer n, Integer n2) {
        return .a(NetworkUtils.a(this.c.listFeed(new SnpRequest(){
            public Integer limit = 10;
            public Integer offset = 0;

            public SocialAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public SocialAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }
        }.setLimit(n2).setOffset(n))));
    }

    public Future<?> b(final Integer n, final Integer n2, final ListFeedResponseCallback listFeedResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(listFeedResponseCallback, SocialManager.this.c(n, n2));
            }
        });
    }

    public FeedItemsResponse c() {
        return this.b();
    }

    public  c(Integer n, Integer n2) {
        return .a(NetworkUtils.a(this.c.listFeedSocialOnly(new .setLimit(n2).setOffset(n))));
    }

    public static interface FeedItemsCallback
    extends ResponseInterface<FeedItemsResponse> {
        @Override
        public void handleResponse(FeedItemsResponse var1);
    }

    public static class FeedItemsResponse
    extends ParsedResponse {
        public List<FeedItem> b;

        public String toString() {
            return "FeedItemsResponse [mResponse=" + (Object)((Object)this.a) + ", items=" + this.b + "]";
        }
    }

    public static interface ListFeedResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface ListNotificationsResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface LookupNotificationsResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

}

