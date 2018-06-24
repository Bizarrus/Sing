/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Analytics;
import com.smule.android.network.api.BoostAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.BoostManager;
import com.smule.android.utils.NotificationCenter;
import java.util.Observer;
import java.util.concurrent.Future;
import retrofit2.Call;

public class BoostManager {
    private static BoostManager d;
    public Context a;
    private String b;
    private String c;
    private Long e;
    private com.smule.android.network.api.BoostAPI f = MagicNetwork.a().a(com.smule.android.network.api.BoostAPI.class);

    private BoostManager() {
    }

    private BoostAvailabilityResponse a(BoostAvailabilityResponse boostAvailabilityResponse) {
        if (boostAvailabilityResponse.a()) {
            this.e = boostAvailabilityResponse.nextVipBoostAt;
        }
        return boostAvailabilityResponse;
    }

    public static BoostManager a() {
        synchronized (BoostManager.class) {
            if (d == null) {
                d = new BoostManager();
            }
            BoostManager boostManager = d;
            return boostManager;
        }
    }

    static /* synthetic */ void a(BoostManager boostManager) {
        boostManager.f();
    }

    private void f() {
        this.b = "promote.test.sku.2017.05";
    }

    public  a(String string2, Analytics boostType, String string3) {
        return .a(NetworkUtils.a(this.f.boostPerformance(new SnpRequest(){
            public String boostType;
            public String performanceKey;
            public String receipt;

            public BoostAPI setBoostType(String string2) {
                if (string2 != null) {
                    this.boostType = string2;
                }
                return this;
            }

            public BoostAPI setPerformanceKey(String string2) {
                if (string2 != null) {
                    this.performanceKey = string2;
                }
                return this;
            }

            public BoostAPI setReceipt(String string2) {
                if (string2 != null) {
                    this.receipt = string2;
                }
                return this;
            }
        }.setPerformanceKey(string2).setBoostType(boostType.b()).setReceipt(string3))));
    }

    public Future<?> a( boostAvailabilityResponseCallback) {
        return MagicNetwork.a(new Runnable(this, boostAvailabilityResponseCallback){
            final /* synthetic */  a;
            final /* synthetic */ BoostManager b;
            {
                this.b = boostManager;
                this.a = boostAvailabilityResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.b.d());
            }
        });
    }

    public Future<?> a(String string2, Analytics boostType, String string3,  boostPerformanceCallback) {
        return MagicNetwork.a(new Runnable(this, boostPerformanceCallback, string2, boostType, string3){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ Analytics c;
            final /* synthetic */ String d;
            final /* synthetic */ BoostManager e;
            {
                this.e = boostManager;
                this.a = boostPerformanceCallback;
                this.b = string2;
                this.c = boostType;
                this.d = string3;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.e.a(this.b, this.c, this.d));
            }
        });
    }

    public void a(Context context) {
        this.a = context.getApplicationContext();
        NotificationCenter.a().a("APP_SETTINGS_LOADED_EVENT", new Observer(this){
            final /* synthetic */ BoostManager a;
            {
                this.a = boostManager;
            }

            public void update(java.util.Observable observable, Object object) {
                BoostManager.a(this.a);
            }
        });
    }

    public void a(@NonNull String string2) {
        this.c = string2;
        this.a.getSharedPreferences("BOOST_PREFERENCES", 0).edit().putString("PERF_TO_BOOST", string2).apply();
    }

    public boolean b() {
        if (this.c() <= 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public long c() {
        long l;
        if (this.e == null) {
            return -1;
        }
        long l2 = l = this.e * 1000 - System.currentTimeMillis();
        if (l > 0) return l2;
        return 0;
    }

    public BoostAvailabilityResponse d() {
        return this.a(BoostAvailabilityResponse.a(NetworkUtils.a(this.f.getBoostAvailability(new SnpRequest()))));
    }

    public String e() {
        if (!TextUtils.isEmpty((CharSequence)this.c)) {
            return this.c;
        }
        return this.a.getSharedPreferences("BOOST_PREFERENCES", 0).getString("PERF_TO_BOOST", null);
    }

    public static class BoostAvailabilityResponse
    extends ParsedResponse {
        @JsonProperty
        public Long nextVipBoostAt;

        public static BoostAvailabilityResponse a(NetworkResponse networkResponse) {
            return BoostAvailabilityResponse.a(networkResponse, BoostAvailabilityResponse.class);
        }

        public String toString() {
            return "BoostAvailabilityResponse [nextVipBoostAt=" + this.nextVipBoostAt + "]";
        }
    }

}

