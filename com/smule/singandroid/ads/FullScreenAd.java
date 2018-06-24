package com.smule.singandroid.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.smule.android.ads.AdsSettingsManager;
import com.smule.android.ads.dfp.AppLovinDFPInterstitial;
import com.smule.android.ads.dfp.DFPCustomEventInterstitial;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.DFPVendor;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.utils.ThreadUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.ads.AdUtils.DFPAdEventListener;
import java.text.MessageFormat;
import java.util.Map;

public class FullScreenAd {
    private static final String f20627a = FullScreenAd.class.getName();
    private PublisherInterstitialAd f20628b;
    private String f20629c = SingApplication.f().getString(C1947R.string.dfp_id);
    private String f20630d;
    private String f20631e;
    private Runnable f20632f;
    private DFPAdEventListener f20633g;

    class C42301 implements Runnable {
        final /* synthetic */ FullScreenAd f20624a;

        C42301(FullScreenAd fullScreenAd) {
            this.f20624a = fullScreenAd;
        }

        public void run() {
            if (this.f20624a.f20628b != null) {
                this.f20624a.f20628b.setAdListener(null);
            }
        }
    }

    public class DFPAdListener extends AdListener {
        final /* synthetic */ FullScreenAd f20625a;
        private boolean f20626b;

        public DFPAdListener(FullScreenAd fullScreenAd) {
            this.f20625a = fullScreenAd;
        }

        public void m22240a(boolean z) {
            this.f20626b = z;
        }

        public void onAdClosed() {
            super.onAdClosed();
            Log.b(FullScreenAd.f20627a, "onAdClosed " + this.f20625a.f20631e);
            if (this.f20625a.f20632f != null) {
                ThreadUtils.m19058a(this.f20625a.f20632f);
            }
        }

        public void onAdFailedToLoad(int i) {
            super.onAdFailedToLoad(i);
            Log.b(FullScreenAd.f20627a, "onAdFailedToLoad " + i);
        }

        public void onAdLeftApplication() {
            super.onAdLeftApplication();
            Log.b(FullScreenAd.f20627a, "onAdLeftApplication");
        }

        public void onAdOpened() {
            super.onAdOpened();
            Log.b(FullScreenAd.f20627a, "onAdOpened " + this.f20625a.f20631e);
            this.f20625a.m22250f();
        }

        public void onAdLoaded() {
            super.onAdLoaded();
            Log.b(FullScreenAd.f20627a, "onAdLoaded " + this.f20625a.f20631e);
            if (this.f20626b) {
                this.f20625a.m22248e();
            }
        }
    }

    public static class DroidSing7437Exception extends Throwable {
        public DroidSing7437Exception(String str) {
            super(str);
        }
    }

    protected void m22252a(String str) {
        this.f20631e = str;
    }

    public void m22256b(String str) {
        this.f20630d = str;
    }

    protected boolean m22253a() {
        return true;
    }

    protected boolean m22255a(Context context) {
        return AdUtils.m22230d(context);
    }

    private void m22243b(Activity activity, Runnable runnable, Map<String, String> map) {
        Log.b(f20627a, "Entering startAdPreload " + this.f20631e);
        this.f20632f = runnable;
        this.f20628b = new PublisherInterstitialAd(activity);
        this.f20628b.setAdUnitId(MessageFormat.format("/{0}/{1}", new Object[]{this.f20629c, this.f20630d}));
        Bundle bundle = new Bundle();
        bundle.putString(DFPCustomEventInterstitial.ZONE_ID_BUNDLE_KEY, this.f20631e);
        PublisherAdRequest build = new Builder().addNetworkExtras(new AdMobExtras(AdUtils.m22218a((Map) map))).addCustomEventExtrasBundle(AppLovinDFPInterstitial.class, bundle).build();
        Log.b(f20627a, "Loading DFP for ad unit " + this.f20630d);
        DFPCustomEventInterstitial.setLastDFPInterstitialVendor(DFPVendor.ADMOB);
        this.f20628b.loadAd(build);
        this.f20628b.setAdListener(new DFPAdListener(this));
        this.f20633g = new DFPAdEventListener();
        this.f20628b.setAppEventListener(this.f20633g);
    }

    private void m22245c(Activity activity, Runnable runnable, Map<String, String> map) {
        Log.b(f20627a, "Entering preloadAds " + this.f20631e);
        if (AppSettingsManager.a().e()) {
            m22243b(activity, runnable, map);
        }
    }

    public void m22251a(Activity activity, Runnable runnable, Map<String, String> map) {
        Log.b(f20627a, "Entering loadAd " + this.f20631e);
        if (!AdsSettingsManager.m17419a().m17423b(this.f20631e)) {
            Log.b(f20627a, "Ad frequency not met");
        } else if (m22255a((Context) activity)) {
            try {
                m22245c(activity, runnable, map);
            } catch (Throwable e) {
                MagicCrittercism.a(new DroidSing7437Exception("DroidSing7437Exception:runtime exception").initCause(e));
            }
        } else {
            Log.b(f20627a, "Not ready to load and show ads");
        }
    }

    public boolean m22257b() {
        boolean z = false;
        if (m22253a() && this.f20628b != null) {
            z = this.f20628b.isLoaded();
        }
        Log.c(f20627a, "Ad " + this.f20631e + " ready: " + z);
        return z;
    }

    public boolean m22254a(Activity activity) {
        if (!m22255a((Context) activity) || !m22253a()) {
            return false;
        }
        if (!m22257b() || this.f20628b == null) {
            boolean z;
            String str = f20627a;
            StringBuilder append = new StringBuilder().append("is mDFPInterstitial null ? ");
            if (this.f20628b == null) {
                z = true;
            } else {
                z = false;
            }
            Log.b(str, append.append(z).toString());
            if (this.f20628b == null) {
                return false;
            }
            ((DFPAdListener) this.f20628b.getAdListener()).m22240a(true);
            return false;
        }
        Log.b(f20627a, "Showing DFP " + this.f20630d);
        m22248e();
        return true;
    }

    private void m22248e() {
        AdsSettingsManager.m17419a().m17424c(this.f20631e);
        this.f20628b.show();
    }

    private void m22250f() {
        String a = this.f20633g.m22217a();
        if (a == null || a.isEmpty()) {
            if (this.f20628b.getMediationAdapterClassName() == null) {
                DFPCustomEventInterstitial.setLastDFPInterstitialVendor(DFPVendor.ADMOB);
            }
            a = DFPCustomEventInterstitial.getLastDFPInterstitialVendor().toString();
        }
        Analytics.m17888b(this.f20628b.getAdUnitId(), a);
    }

    public void m22258c() {
        int i = 1000;
        int a = AdsSettingsManager.m17419a().m17425d(this.f20631e).a();
        if (a >= 1000) {
            i = a;
        }
        new Handler().postDelayed(new C42301(this), (long) i);
    }
}
