/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Bundle
 *  android.os.Handler
 *  com.google.android.gms.ads.AdListener
 *  com.google.android.gms.ads.doubleclick.AppEventListener
 *  com.google.android.gms.ads.doubleclick.PublisherAdRequest
 *  com.google.android.gms.ads.doubleclick.PublisherAdRequest$Builder
 *  com.google.android.gms.ads.doubleclick.PublisherInterstitialAd
 *  com.google.android.gms.ads.mediation.NetworkExtras
 *  com.google.android.gms.ads.mediation.admob.AdMobExtras
 */
package com.smule.singandroid.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.smule.android.ads.AdsSettingsManager;
import com.smule.android.ads.dfp.AppLovinDFPInterstitial;
import com.smule.android.ads.dfp.DFPCustomEventInterstitial;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.utils.ThreadUtils;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.ads.SingAdSettings;
import java.text.MessageFormat;
import java.util.Map;

public class FullScreenAd {
    private static final String a = FullScreenAd.class.getName();
    private PublisherInterstitialAd b;
    private String c = SingApplication.g().getString(2131297707);
    private String d;
    private String e;
    private Runnable f;
    private SingAdSettings.DFPAdEventListener g;

    private void b(Activity activity, Runnable runnable, Map<String, String> map) {
        Log.b(a, "Entering startAdPreload " + this.e);
        this.f = runnable;
        this.b = new PublisherInterstitialAd((Context)activity);
        this.b.setAdUnitId(MessageFormat.format("/{0}/{1}", this.c, this.d));
        activity = new Bundle();
        activity.putString("ZONE_ID_BUNDLE_KEY", this.e);
        activity = new PublisherAdRequest.Builder().addNetworkExtras((NetworkExtras)new AdMobExtras(SingAdSettings.a(map))).addCustomEventExtrasBundle(AppLovinDFPInterstitial.class, (Bundle)activity).build();
        Log.b(a, "Loading DFP for ad unit " + this.d);
        DFPCustomEventInterstitial.setLastDFPInterstitialVendor(Analytics.c);
        this.b.loadAd((PublisherAdRequest)activity);
        this.b.setAdListener((AdListener)new DFPAdListener());
        this.g = new SingAdSettings.DFPAdEventListener();
        this.b.setAppEventListener((AppEventListener)this.g);
    }

    private void c(Activity activity, Runnable runnable, Map<String, String> map) {
        Log.b(a, "Entering preloadAds " + this.e);
        if (AppSettingsManager.a().e()) {
            this.b(activity, runnable, map);
        }
    }

    private void e() {
        com.smule.android.ads.AdsSettingsManager.a().c(this.e);
        this.b.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void f() {
        String string2 = this.g.a();
        if (string2 == null || string2.isEmpty()) {
            if (this.b.getMediationAdapterClassName() == null) {
                DFPCustomEventInterstitial.setLastDFPInterstitialVendor(Analytics.c);
            }
            string2 = DFPCustomEventInterstitial.getLastDFPInterstitialVendor().toString();
        }
        com.smule.android.logging.Analytics.c(this.b.getAdUnitId(), string2);
    }

    public void a(Activity activity, Runnable runnable, Map<String, String> map) {
        Log.b(a, "Entering loadAd " + this.e);
        if (!com.smule.android.ads.AdsSettingsManager.a().b(this.e)) {
            Log.b(a, "Ad frequency not met");
            return;
        }
        if (!this.a((Context)activity)) {
            Log.b(a, "Not ready to load and show ads");
            return;
        }
        try {
            this.c(activity, runnable, map);
            return;
        }
        catch (RuntimeException runtimeException) {
            MagicCrittercism.a(new DroidSing7437Exception("DroidSing7437Exception:runtime exception").initCause(runtimeException));
            return;
        }
    }

    protected void a(String string2) {
        this.e = string2;
    }

    protected boolean a() {
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean a(Activity object) {
        block5 : {
            block4 : {
                if (!this.a((Context)object) || !this.a()) break block4;
                if (this.b() && this.b != null) {
                    Log.b(a, "Showing DFP " + this.d);
                    this.e();
                    return true;
                }
                object = a;
                StringBuilder stringBuilder = new StringBuilder().append("is mDFPInterstitial null ? ");
                boolean bl = this.b == null;
                Log.b((String)object, stringBuilder.append(bl).toString());
                if (this.b != null) break block5;
            }
            return false;
        }
        ((DFPAdListener)this.b.getAdListener()).a(true);
        return false;
    }

    protected boolean a(Context context) {
        return SingAdSettings.g(context);
    }

    public void b(String string2) {
        this.d = string2;
    }

    public boolean b() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.a()) {
            bl2 = bl;
            if (this.b != null) {
                bl2 = this.b.isLoaded();
            }
        }
        Log.c(a, "Ad " + this.e + " ready: " + bl2);
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void c() {
        int n = 1000;
        int n2 = com.smule.android.ads.AdsSettingsManager.a().d(this.e).a();
        if (n2 >= 1000) {
            n = n2;
        }
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                if (FullScreenAd.this.b != null) {
                    FullScreenAd.this.b.setAdListener(null);
                }
            }
        }, (long)n);
    }

    public class DFPAdListener
    extends AdListener {
        private boolean b;

        public void a(boolean bl) {
            this.b = bl;
        }

        public void onAdClosed() {
            super.onAdClosed();
            Log.b(a, "onAdClosed " + FullScreenAd.this.e);
            if (FullScreenAd.this.f != null) {
                ThreadUtils.a(FullScreenAd.this.f);
            }
        }

        public void onAdFailedToLoad(int n) {
            super.onAdFailedToLoad(n);
            Log.b(a, "onAdFailedToLoad " + n);
        }

        public void onAdLeftApplication() {
            super.onAdLeftApplication();
            Log.b(a, "onAdLeftApplication");
        }

        public void onAdLoaded() {
            super.onAdLoaded();
            Log.b(a, "onAdLoaded " + FullScreenAd.this.e);
            if (this.b) {
                FullScreenAd.this.e();
            }
        }

        public void onAdOpened() {
            super.onAdOpened();
            Log.b(a, "onAdOpened " + FullScreenAd.this.e);
            FullScreenAd.this.f();
        }
    }

    public static class DroidSing7437Exception
    extends Throwable {
        public DroidSing7437Exception(String string2) {
            super(string2);
        }
    }

}

