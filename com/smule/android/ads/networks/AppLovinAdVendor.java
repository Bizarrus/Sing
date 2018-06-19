package com.smule.android.ads.networks;

import android.app.Activity;
import android.content.Context;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.smule.android.ads.networks.AdVendor.AdType;
import com.smule.android.ads.networks.AdVendor.ShowAdCallbackInterface;
import com.smule.android.logging.Analytics.EarnVCVendor;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.WeakListener.WeakListenerTemplate;

public class AppLovinAdVendor extends AdVendor {
    private static final String f15663b = AppLovinAdVendor.class.getName();
    AppLovinAdLoadListener f15664a = new C35031(this);
    private AppLovinIncentivizedInterstitial f15665c;

    class C35031 implements AppLovinAdLoadListener {
        final /* synthetic */ AppLovinAdVendor f15657a;

        C35031(AppLovinAdVendor appLovinAdVendor) {
            this.f15657a = appLovinAdVendor;
        }

        public void adReceived(AppLovinAd appLovinAd) {
            Log.b(AppLovinAdVendor.f15663b, "AppLovin.adReceived");
            this.f15657a.m17468d();
        }

        public void failedToReceiveAd(int i) {
            Log.b(AppLovinAdVendor.f15663b, "AppLovin.failedToReceiveAd code: " + i);
            this.f15657a.f15665c = null;
            this.f15657a.m17470e();
        }
    }

    public static class WeakAppLovinAdDisplayListener extends WeakListenerTemplate<AppLovinAdDisplayListener> implements AppLovinAdDisplayListener {
        public WeakAppLovinAdDisplayListener(Object obj, AppLovinAdDisplayListener appLovinAdDisplayListener) {
            super(obj, appLovinAdDisplayListener);
        }

        public void adDisplayed(AppLovinAd appLovinAd) {
            AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener) m17483a();
            if (appLovinAdDisplayListener != null) {
                appLovinAdDisplayListener.adDisplayed(appLovinAd);
            }
        }

        public void adHidden(AppLovinAd appLovinAd) {
            AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener) m17483a();
            if (appLovinAdDisplayListener != null) {
                appLovinAdDisplayListener.adHidden(appLovinAd);
            }
        }
    }

    public EarnVCVendor mo6224a() {
        return EarnVCVendor.APPLOVIN;
    }

    public boolean mo6227a(AdType adType) {
        switch (adType) {
            case VIDEO_REWARD:
                return true;
            default:
                return false;
        }
    }

    protected void mo6225a(Activity activity) {
        m17486a((Context) activity).n();
    }

    public AppLovinSdk m17486a(Context context) {
        AppLovinSdkSettings appLovinSdkSettings = new AppLovinSdkSettings();
        appLovinSdkSettings.a(AppLovinAdSize.c.c());
        appLovinSdkSettings.b(AppLovinAdType.a.a());
        return AppLovinSdk.a(appLovinSdkSettings, context.getApplicationContext());
    }

    public void mo6228b(Activity activity) {
        if (this.f15665c == null) {
            this.f15665c = AppLovinIncentivizedInterstitial.a(activity);
        }
        Log.b(f15663b, "AppLovinIncentivizedInterstitial.preload");
        this.f15665c.a(this.f15664a);
    }

    protected void mo6226a(final Activity activity, Object obj, final ShowAdCallbackInterface showAdCallbackInterface) {
        if (this.f15665c == null) {
            throw new RuntimeException("Video ad should be pre-loading before we get to AppLovinAdVendor.showRewardVideo");
        } else if (this.f15665c.a()) {
            this.f15665c.a("" + UserManager.a().g());
            this.f15665c.a(activity, null, null, new WeakAppLovinAdDisplayListener(obj, new AppLovinAdDisplayListener(this) {
                final /* synthetic */ AppLovinAdVendor f15660c;

                public void adDisplayed(AppLovinAd appLovinAd) {
                    showAdCallbackInterface.mo6207a(this.f15660c);
                }

                public void adHidden(AppLovinAd appLovinAd) {
                    this.f15660c.m17469d(activity);
                    showAdCallbackInterface.mo6208b(this.f15660c);
                }
            }));
        } else {
            Log.b(f15663b, "ad was not ready for display");
            showAdCallbackInterface.mo6209c(this);
        }
    }
}
