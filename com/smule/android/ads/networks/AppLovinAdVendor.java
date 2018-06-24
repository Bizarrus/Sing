/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  com.applovin.adview.AppLovinIncentivizedInterstitial
 *  com.applovin.sdk.AppLovinAd
 *  com.applovin.sdk.AppLovinAdDisplayListener
 *  com.applovin.sdk.AppLovinAdLoadListener
 *  com.applovin.sdk.AppLovinAdRewardListener
 *  com.applovin.sdk.AppLovinAdSize
 *  com.applovin.sdk.AppLovinAdType
 *  com.applovin.sdk.AppLovinAdVideoPlaybackListener
 *  com.applovin.sdk.AppLovinSdk
 *  com.applovin.sdk.AppLovinSdkSettings
 */
package com.smule.android.ads.networks;

import android.app.Activity;
import android.content.Context;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.WeakListener;

public class AppLovinAdVendor
extends AdVendor {
    private static final String b = AppLovinAdVendor.class.getName();
    AppLovinAdLoadListener a;
    private AppLovinIncentivizedInterstitial c;

    public AppLovinAdVendor() {
        this.a = new AppLovinAdLoadListener(){

            public void adReceived(AppLovinAd appLovinAd) {
                Log.b(b, "AppLovin.adReceived");
                AppLovinAdVendor.this.d();
            }

            public void failedToReceiveAd(int n) {
                Log.b(b, "AppLovin.failedToReceiveAd code: " + n);
                AppLovinAdVendor.this.c = null;
                AppLovinAdVendor.this.e();
            }
        };
    }

    public AppLovinSdk a(Context context) {
        AppLovinSdkSettings appLovinSdkSettings = new AppLovinSdkSettings();
        appLovinSdkSettings.a(AppLovinAdSize.c.c());
        appLovinSdkSettings.b(AppLovinAdType.a.a());
        return AppLovinSdk.a((AppLovinSdkSettings)appLovinSdkSettings, (Context)context.getApplicationContext());
    }

    @Override
    public Analytics a() {
        return Analytics.c;
    }

    @Override
    protected void a(Activity activity) {
        this.a((Context)activity).n();
    }

    @Override
    protected void a(final Activity activity, Object object, final AdVendor.ShowAdCallbackInterface showAdCallbackInterface) {
        if (this.c == null) {
            throw new RuntimeException("Video ad should be pre-loading before we get to AppLovinAdVendor.showRewardVideo");
        }
        if (!this.c.a()) {
            Log.b(b, "ad was not ready for display");
            showAdCallbackInterface.c(this);
            return;
        }
        this.c.a("" + UserManager.a().g());
        this.c.a(activity, null, null, (AppLovinAdDisplayListener)new WeakAppLovinAdDisplayListener(object, new AppLovinAdDisplayListener(){

            public void adDisplayed(AppLovinAd appLovinAd) {
                showAdCallbackInterface.a(AppLovinAdVendor.this);
            }

            public void adHidden(AppLovinAd appLovinAd) {
                AppLovinAdVendor.this.d(activity);
                showAdCallbackInterface.b(AppLovinAdVendor.this);
            }
        }));
    }

    @Override
    public boolean a(AdVendor.AdType adType) {
        switch (.a[adType.ordinal()]) {
            default: {
                return false;
            }
            case 2: 
        }
        return true;
    }

    @Override
    public void b(Activity activity) {
        if (this.c == null) {
            this.c = AppLovinIncentivizedInterstitial.a((Context)activity);
        }
        Log.b(b, "AppLovinIncentivizedInterstitial.preload");
        this.c.a(this.a);
    }

    public static class WeakAppLovinAdDisplayListener
    extends WeakListener.WeakListenerTemplate<AppLovinAdDisplayListener>
    implements AppLovinAdDisplayListener {
        public WeakAppLovinAdDisplayListener(Object object, AppLovinAdDisplayListener appLovinAdDisplayListener) {
            super(object, appLovinAdDisplayListener);
        }

        public void adDisplayed(AppLovinAd appLovinAd) {
            AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener)this.a();
            if (appLovinAdDisplayListener != null) {
                appLovinAdDisplayListener.adDisplayed(appLovinAd);
            }
        }

        public void adHidden(AppLovinAd appLovinAd) {
            AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener)this.a();
            if (appLovinAdDisplayListener != null) {
                appLovinAdDisplayListener.adHidden(appLovinAd);
            }
        }
    }

}

