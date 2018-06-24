/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.SharedPreferences;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.PromotionAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.Banner;
import com.smule.android.utils.NotificationCenter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;

public class PromotionManager {
    public static final long a = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
    private static PromotionManager b;
    private com.smule.android.network.api.PromotionAPI c = MagicNetwork.a().a(com.smule.android.network.api.PromotionAPI.class);
    private final AtomicBoolean d = new AtomicBoolean();

    private PromotionManager() {
        NotificationCenter.a().a("SUBSCRIPTION_UPDATED_NOTIFICATION", new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                if (SubscriptionManager.a().b()) {
                    MagicNetwork.e().edit().putLong("BANNERS_CACHE_TIMESTAMP", 0).apply();
                }
            }
        });
    }

    public static PromotionManager a() {
        if (b == null) {
            b = new PromotionManager();
        }
        return b;
    }

    public PerformanceManager.PerformancesResponse a(long l, String string2, int n, int n2) {
        return PerformanceManager.PerformancesResponse.a(NetworkUtils.a(this.c.getLeaderboard(new SnpRequest(){
            public int limit;
            public int offset;
            public String orderBy;
            public Long promoId;

            public PromotionAPI setLimit(int n) {
                this.limit = n;
                return this;
            }

            public PromotionAPI setOffset(int n) {
                this.offset = n;
                return this;
            }

            public PromotionAPI setOrderBy(String string2) {
                this.orderBy = string2;
                return this;
            }

            public PromotionAPI setPromoId(Long l) {
                this.promoId = l;
                return this;
            }
        }.setPromoId(l).setOrderBy(string2).setOffset(n).setLimit(n2))));
    }

    public  a(String string2) {
        return .a(NetworkUtils.a(this.c.getBanners(new SnpRequest(){
            public String screenType;

            public PromotionAPI setScreenType(String string2) {
                this.screenType = string2;
                return this;
            }
        }.setScreenType(string2))));
    }

    public  a(long l) {
        return .a(NetworkUtils.a(this.c.getPromotion(new SnpRequest(){
            public Long promoId;

            public PromotionAPI setPromoId(Long l) {
                this.promoId = l;
                return this;
            }
        }.setPromoId(l))));
    }

    public Future<?> a(final long l, final PromotionCallback promotionCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                if (promotionCallback != null) {
                    CoreUtil.a(promotionCallback, PromotionManager.this.a(l));
                }
            }
        });
    }

    public Future<?> a(final long l, final String string2, final int n, final int n2, final PerformanceManager performancesResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                if (performancesResponseCallback != null) {
                    CoreUtil.a(performancesResponseCallback, PromotionManager.this.a(l, string2, n, n2));
                }
            }
        });
    }

    public Future<?> a(final String string2, final BannersCallback bannersCallback) {
        this.d.set(true);
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                long l = MagicNetwork.e().getLong("BANNERS_CACHE_TIMESTAMP", 0);
                Object object = MagicNetwork.d().getAppVersion();
                Object object2 = MagicNetwork.e().getString("BANNERS_CACHE_APP_VERSION", (String)object);
                if ((l + PromotionManager.a < System.currentTimeMillis() || !object2.equals(object)) && (object2 = PromotionManager.this.a(string2)) != null && object2.a()) {
                    MagicNetwork.e().edit().putString("BANNERS_CACHE", object2.a.j).putLong("BANNERS_CACHE_TIMESTAMP", System.currentTimeMillis()).putString("BANNERS_CACHE_APP_VERSION", (String)object).apply();
                    PromotionManager.this.d.set(false);
                    object2.b = false;
                    CoreUtil.a(bannersCallback, object2);
                    return;
                }
                object = new com.smule.android.network.core.NetworkResponse(MagicNetwork.e().getString("BANNERS_CACHE", null));
                object.a = NetworkResponse.a;
                object = ParsedResponse.a((com.smule.android.network.core.NetworkResponse)((Object)object), .class);
                object.b = true;
                PromotionManager.this.d.set(false);
                CoreUtil.a(bannersCallback, object);
            }
        });
    }

    public boolean b() {
        return this.d.get();
    }

    public static interface BannersCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface PromotionCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

}

