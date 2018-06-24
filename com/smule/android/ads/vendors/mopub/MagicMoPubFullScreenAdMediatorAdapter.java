/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.location.Location
 *  android.os.Handler
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.text.TextUtils
 *  com.chartboost.sdk.Chartboost
 *  com.mopub.common.MediationSettings
 *  com.mopub.common.MoPub
 *  com.mopub.common.MoPubReward
 *  com.mopub.mobileads.ChartboostRewardedVideo
 *  com.mopub.mobileads.ChartboostRewardedVideo$ChartboostMediationSettings
 *  com.mopub.mobileads.MoPubAnalyticsData
 *  com.mopub.mobileads.MoPubErrorCode
 *  com.mopub.mobileads.MoPubRewardedVideoListener
 *  com.mopub.mobileads.MoPubRewardedVideoManager
 *  com.mopub.mobileads.MoPubRewardedVideoManager$RequestParameters
 *  com.mopub.mobileads.MoPubRewardedVideos
 *  com.mopub.rewardedvideos.AppLovinRewardedAdapter
 *  com.mopub.rewardedvideos.AppLovinRewardedAdapter$AppLovinMediationSettings
 */
package com.smule.android.ads.vendors.mopub;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.chartboost.sdk.Chartboost;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.ChartboostRewardedVideo;
import com.mopub.mobileads.MoPubAnalyticsData;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import com.mopub.mobileads.MoPubRewardedVideos;
import com.mopub.rewardedvideos.AppLovinRewardedAdapter;
import com.smule.android.R;
import com.smule.android.ads.MagicAd;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter;
import com.smule.android.ads.vendors.mopub.MagicMoPubFullScreenAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.utils.LocationUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MagicMoPubFullScreenAdMediatorAdapter
extends com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter
implements MoPubRewardedVideoListener {
    private static final String APP_LOVIN_PLACEMENT_ID_LOOKUP = "placement_id.fullscreen_ads.rewarded.phone.singing.mopub.applovin";
    private static final String CHARTBOOST_PLACEMENT_ID_LOOKUP = "placement_id.fullscreen_ads.rewarded.phone.singing.mopub.chartboost";
    public static final String FILE_NAME = "fullscreen_ads_prefs";
    public static final String TAG = MagicMoPubFullScreenAdMediatorAdapter.class.getSimpleName();
    private static MagicAd sFullScreenAd;
    private static boolean sRewardedVideoInitialized;
    private boolean mIsAdBeingDisplayed;
    private final SharedPreferences mSharedPreferences;
    private Handler mUXTimeoutHandler;
    private boolean mUserEarnedReward;
    private final ArrayList<MediationSettings> mVendorParamters;

    /*
     * Enabled aggressive block sorting
     */
    public MagicMoPubFullScreenAdMediatorAdapter(@NonNull Activity activity) {
        super(activity);
        Context context = activity.getApplicationContext();
        this.configureSpecificVendors();
        this.mVendorParamters = new ArrayList();
        this.mVendorParamters.add(this.getAppLovinMediationSettings(context));
        this.mVendorParamters.add(this.getChartboostMediationSettings(context));
        this.mSharedPreferences = context.getSharedPreferences("fullscreen_ads_prefs", 0);
        if (sRewardedVideoInitialized) {
            Log.b(TAG, "####### FULLSCREEN_AD: update activity");
            MoPubRewardedVideoManager.updateActivity((Activity)activity);
        } else {
            sRewardedVideoInitialized = true;
            Log.b(TAG, "######## FULLSCREEN_AD: initialize with activity");
            MoPubRewardedVideos.initializeRewardedVideo((Activity)activity, (MediationSettings[])new MediationSettings[0]);
        }
        MoPubRewardedVideos.setRewardedVideoListener((MoPubRewardedVideoListener)this);
    }

    static /* synthetic */ void access$000(MagicMoPubFullScreenAdMediatorAdapter magicMoPubFullScreenAdMediatorAdapter) {
        magicMoPubFullScreenAdMediatorAdapter.onUXTimeout();
    }

    private void configureSpecificVendors() {
        Chartboost.setShouldPrefetchVideoContent((boolean)false);
    }

    private static String formatKeywords(HashMap<String, String> hashMap) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : hashMap.keySet()) {
            String string3 = hashMap.get(string2);
            arrayList.add(string2 + ":" + string3);
        }
        return TextUtils.join((CharSequence)",", arrayList);
    }

    private MediationSettings getAppLovinMediationSettings(Context context) {
        return new AppLovinRewardedAdapter.AppLovinMediationSettings(this.getPlacementId(context, "placement_id.fullscreen_ads.rewarded.phone.singing.mopub.applovin"));
    }

    private MediationSettings getChartboostMediationSettings(Context context) {
        return new ChartboostRewardedVideo.ChartboostMediationSettings(this.getPlacementId(context, "placement_id.fullscreen_ads.rewarded.phone.singing.mopub.chartboost"));
    }

    @NonNull
    private String getPlacementId(@NonNull Context context, @NonNull String string2) {
        int n = context.getResources().getIdentifier(string2, "string", context.getPackageName());
        if (n == 0) {
            Log.e(TAG, "no resource ID found for: " + string2);
            return "unknown_placement_id";
        }
        return context.getResources().getString(n);
    }

    private boolean isAdCurrent(MagicAd magicAd) {
        if (sFullScreenAd == null) {
            return false;
        }
        return magicAd.b().equals(sFullScreenAd.b());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onUXTimeout() {
        Log.b(TAG, "FULLSCREEN_AD: UX timeout reached");
        this.stopUIXTimeout();
        if (sFullScreenAd == null) {
            Log.e(TAG, "sFullScreenAd is null on timeout");
            return;
        } else {
            if (sFullScreenAd.f() != MagicAd.AdStatus.b) return;
            {
                Log.b(TAG, "    FULLSCREEN_AD: - ad is still being fetched, so move user along in UX flow");
                sFullScreenAd.a(false);
                String string2 = MoPubRewardedVideos.getAdRequestId((String)sFullScreenAd.b());
                long l = System.currentTimeMillis();
                long l2 = sFullScreenAd.h();
                com.smule.android.logging.Analytics.b(Analytics.d, sFullScreenAd.a(), Analytics.a, null, Analytics.a, null, string2, l - l2);
                this.mEventObservable.a(MagicFullScreenAdMediatorAdapter.b, null);
                return;
            }
        }
    }

    private void onUserBackgroundedAppDuringUXTimeout() {
        this.stopUIXTimeout();
        if (sFullScreenAd.f() == MagicAd.AdStatus.b) {
            Log.b(TAG, "    FULLSCREEN_AD: - user backgrounded app during UX timeout");
            sFullScreenAd.a(false);
            this.mEventObservable.a(MagicFullScreenAdMediatorAdapter.c, null);
        }
    }

    public static void registerWithFactory(Context object) {
        object = object.getResources().getString(R.string.MagicMoPubMediatorSettingsKey);
        MagicAdAdapterFactory.a().b(MagicMoPubFullScreenAdMediatorAdapter.class, (String)object);
    }

    private void startUXTimeout(int n) {
        Log.b(TAG, "FULLSCREEN_AD: startUXTimeout: length *" + n + "* seconds");
        long l = TimeUnit.MILLISECONDS.convert(n, TimeUnit.SECONDS);
        this.stopUIXTimeout();
        this.mUXTimeoutHandler = new Handler();
        Runnable runnable = new Runnable(this){
            final /* synthetic */ MagicMoPubFullScreenAdMediatorAdapter a;
            {
                this.a = magicMoPubFullScreenAdMediatorAdapter;
            }

            public void run() {
                MagicMoPubFullScreenAdMediatorAdapter.access$000(this.a);
            }
        };
        Log.b(TAG, "   FULLSCREEN_AD: UX timeout starting now...");
        this.mUXTimeoutHandler.postDelayed(runnable, l);
    }

    private void stopUIXTimeout() {
        Log.b(TAG, "FULLSCREEN_AD: stopUXTimeout");
        if (this.mUXTimeoutHandler != null) {
            this.mUXTimeoutHandler.removeCallbacksAndMessages((Object)null);
        }
        this.mUXTimeoutHandler = null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected boolean actuallyShowAd() {
        if (sFullScreenAd == null) {
            Log.e(TAG, "Error: sFullScreenAd is null");
            return false;
        }
        Log.b(TAG, "FULLSCREEN_AD: actuallyShowAd(): adPlacement: '" + sFullScreenAd.a().a() + "', entryPoint: '" + sFullScreenAd.c() + "', adUnitId: '" + sFullScreenAd.b() + "'");
        boolean bl = MoPubRewardedVideos.hasRewardedVideo((String)sFullScreenAd.b());
        if (bl) {
            Log.b(TAG, "   FULLSCREEN_AD: - according to MoPub, ad is ready");
            this.mIsAdBeingDisplayed = true;
            this.mUserEarnedReward = false;
            Log.b(TAG, "   FULLSCREEN_AD: - SHOWING AD!!!");
            MoPubRewardedVideos.showRewardedVideo((String)sFullScreenAd.b());
            do {
                return bl;
                break;
            } while (true);
        }
        Log.b(TAG, "   FULLSCREEN_AD: - couldn't show it, according to MoPub, ad wasn't loaded");
        return bl;
    }

    @Override
    public boolean cancelUserWaitForAd(@NonNull MagicAd object) {
        Log.b(TAG, "FULLSCREEN_AD: cancelUserWaitForAd() adPlacement: " + object.a() + " entryPoint: " + object.c());
        if (object == null) {
            Log.e(TAG, "null ad passed to cancelUserWaitForAd");
            return false;
        }
        if (sFullScreenAd == null) {
            Log.d(TAG, "    FULLSCREEN_AD: tried to cancel a null ad! passed-in ad placement: " + object.a());
            return true;
        }
        if (this.mIsAdBeingDisplayed) {
            Log.b(TAG, "    FULLSCREEN_AD: - user hit cancel a little too late, you're watching an ad, buddy!");
            return false;
        }
        Log.b(TAG, "    FULLSCREEN_AD: - user cancelled, so ad should be left in cache when it arrives");
        this.stopUIXTimeout();
        sFullScreenAd.a(false);
        object = MoPubRewardedVideos.getAdRequestId((String)object.b());
        long l = System.currentTimeMillis();
        long l2 = sFullScreenAd.h();
        com.smule.android.logging.Analytics.a(Analytics.d, sFullScreenAd.a(), Analytics.a, null, Analytics.a, null, (String)object, l - l2);
        return true;
    }

    @Override
    public void destroy() {
        Log.b(TAG, "FULLSCREEN_AD: destroy");
        this.stopUIXTimeout();
        this.mEventObservable.unregisterAll();
    }

    @Override
    public boolean isAdBeingDisplayed() {
        return this.mIsAdBeingDisplayed;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean isAdLoaded(@NonNull MagicAd magicAd) {
        if (!this.isAdCurrent(magicAd) || sFullScreenAd.f() != MagicAd.AdStatus.c) {
            return false;
        }
        if (MoPubRewardedVideos.hasRewardedVideo((String)sFullScreenAd.b())) {
            return true;
        }
        Log.c(TAG, "FULLSCREN_AD: ad has evidently exipred");
        sFullScreenAd.a(MagicAd.AdStatus.e);
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean isAdLoading(@NonNull MagicAd magicAd) {
        if (!this.isAdCurrent(magicAd) || sFullScreenAd.f() != MagicAd.AdStatus.b) {
            return false;
        }
        return true;
    }

    protected void loadAdWhileUserWaits() {
        Log.b(TAG, "FULLSCREEN_AD: loadAdWhileUserWaits()");
        sFullScreenAd.a(true);
        this.preloadAd(sFullScreenAd);
        this.startUXTimeout(sFullScreenAd.d());
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void loadAndShowAdWhileUserWaits(@NonNull MagicAd magicAd) {
        if (magicAd == null) {
            Log.e(TAG, "FULLSCREEN_AD: Error: null ad passed to loadAndShowAdWhileUserWaits");
            return;
        } else {
            Log.b(TAG, "============================ FULLSCREEN_AD: loadAndShowAdWhileUserWaits(): adPlacement: '" + magicAd.a() + "', entryPoint: '" + magicAd.c() + "', adUnitId: '" + magicAd.b() + "'");
            if (this.isAdCurrent(magicAd)) {
                sFullScreenAd.a(magicAd);
            } else {
                sFullScreenAd = magicAd;
            }
            if (!this.isAdLoaded(sFullScreenAd)) {
                this.loadAdWhileUserWaits();
                return;
            }
            Log.b(TAG, "     FULLSCREEN_AD: - ad should be downloaded and ready");
            if (this.actuallyShowAd()) return;
            {
                this.loadAdWhileUserWaits();
                return;
            }
        }
    }

    @Override
    public void logAdRewardClick() {
        com.smule.android.logging.Analytics.a(Analytics.d);
    }

    @Override
    public void onActivityCreate(Activity activity) {
        MoPub.onCreate((Activity)activity);
    }

    @Override
    public void onActivityDestroy(Activity activity) {
        MoPub.onDestroy((Activity)activity);
    }

    @Override
    public void onActivityPause(Activity activity) {
        MoPub.onPause((Activity)activity);
    }

    @Override
    public void onActivityRestart(Activity activity) {
        MoPub.onRestart((Activity)activity);
    }

    @Override
    public void onActivityResume(Activity activity) {
        MoPub.onResume((Activity)activity);
    }

    @Override
    public void onActivityStart(Activity activity) {
        MoPub.onStart((Activity)activity);
    }

    @Override
    public void onActivityStop(Activity activity) {
        MoPub.onStop((Activity)activity);
        if (this.mUXTimeoutHandler != null) {
            this.onUserBackgroundedAppDuringUXTimeout();
        }
    }

    @Override
    public void onBackPressed(Activity activity) {
        MoPub.onBackPressed((Activity)activity);
    }

    public void onRewardedVideoClicked(@NonNull String string2, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        if (sFullScreenAd == null) {
            Log.e(TAG, "FULLSCREEN_AD: onRewardedVideoClicked Error: sFullScreenAd is null");
            return;
        }
        Log.b(TAG, "FULLSCREEN_AD: onRewardedVideoClicked: adPlacement: '" + sFullScreenAd.a().a() + "', entryPoint: '" + sFullScreenAd.c() + "', network: '" + moPubAnalyticsData.getAdNetworkName() + "', adUnitId: '" + string2 + "', request id: '" + moPubAnalyticsData.getRequestId() + "'");
        if (!string2.equals(sFullScreenAd.b())) {
            Log.b(TAG, "adUnits do not match:" + sFullScreenAd.b() + " vs. " + string2);
            return;
        }
        com.smule.android.logging.Analytics.b(Analytics.d, sFullScreenAd.a(), Analytics.a, moPubAnalyticsData.getAdNetworkName(), Analytics.a, null, moPubAnalyticsData.getRequestId());
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onRewardedVideoClosed(@NonNull String string2, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        if (sFullScreenAd == null) {
            Log.e(TAG, "FULLSCREEN_AD: onRewardedVideoClosed Error: sFullScreenAd is null");
            return;
        }
        Log.b(TAG, "FULLSCREEN_AD: onRewardedVideoClosed: adPlacement: '" + sFullScreenAd.a().a() + "', entryPoint: '" + sFullScreenAd.c() + "', network: '" + moPubAnalyticsData.getAdNetworkName() + "', adUnitId: '" + string2 + "', request id: '" + moPubAnalyticsData.getRequestId() + "'");
        if (!string2.equals(sFullScreenAd.b())) {
            Log.b(TAG, "adUnits do not match:" + sFullScreenAd.b() + " vs. " + string2);
            return;
        }
        if (!this.mIsAdBeingDisplayed) {
            Log.b(TAG, "  FULLSCREEN_AD: - duplicate call to onRewardedVideoClosed, exiting");
            return;
        }
        this.mIsAdBeingDisplayed = false;
        if (this.mSharedPreferences != null) {
            this.mSharedPreferences.edit().putBoolean(MagicMoPubFullScreenAdMediatorAdapter.class.getSimpleName(), this.mUserEarnedReward).apply();
        }
        if (this.mUserEarnedReward) {
            long l = System.currentTimeMillis();
            long l2 = sFullScreenAd.i();
            com.smule.android.logging.Analytics.c(Analytics.d, sFullScreenAd.a(), Analytics.a, moPubAnalyticsData.getAdNetworkName(), Analytics.a, null, moPubAnalyticsData.getRequestId(), l - l2);
            this.mEventObservable.a(MagicFullScreenAdMediatorAdapter.a);
        } else {
            this.mEventObservable.a(MagicFullScreenAdMediatorAdapter.b);
        }
        sFullScreenAd.a(MagicAd.AdStatus.i);
    }

    public void onRewardedVideoCompleted(@NonNull Set<String> set, @NonNull MoPubReward moPubReward, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        if (sFullScreenAd == null) {
            Log.e(TAG, "FULLSCREEN_AD: onRewardedVideoCompleted Error: sFullScreenAd is null");
            return;
        }
        Log.b(TAG, "FULLSCREEN_AD: onRewardedVideoCompleted: success? '" + moPubReward.isSuccessful() + "', adPlacement: '" + sFullScreenAd.a().a() + "', entryPoint: '" + sFullScreenAd.c() + "', network: '" + moPubAnalyticsData.getAdNetworkName() + "', adUnitId: '" + set + "', request id: '" + moPubAnalyticsData.getRequestId() + "'");
        if (!set.contains(sFullScreenAd.b())) {
            Log.b(TAG, "adUnits do not match:" + sFullScreenAd.b() + " vs. " + set);
            return;
        }
        this.mUserEarnedReward = moPubReward.isSuccessful();
        sFullScreenAd.a(MagicAd.AdStatus.h);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onRewardedVideoLoadFailure(@NonNull String string2, @NonNull MoPubErrorCode moPubErrorCode, @Nullable MoPubAnalyticsData moPubAnalyticsData) {
        if (sFullScreenAd == null) {
            Log.e(TAG, "FULLSCREEN_AD: onRewardedVideoLoadFailure Error: sFullScreenAd is null");
            return;
        } else {
            String string3 = moPubAnalyticsData != null ? moPubAnalyticsData.getAdNetworkName() : "";
            String string4 = moPubAnalyticsData != null ? moPubAnalyticsData.getRequestId() : "";
            Log.b(TAG, "^^^^^^^^^^^^^^^^ FULLSCREEN_AD: onRewardedVideoLoad FAILURE!!!: adPlacement: '" + sFullScreenAd.a().a() + "', entryPoint: '" + sFullScreenAd.c() + "', network: '" + moPubAnalyticsData.getAdNetworkName() + "', errorCode: '" + moPubErrorCode.toString() + "', adUnitId: '" + string2 + "', request id: '" + moPubAnalyticsData.getRequestId() + "'");
            if (!string2.equals(sFullScreenAd.b())) return;
            {
                this.stopUIXTimeout();
                sFullScreenAd.a(MagicAd.AdStatus.d);
                long l = System.currentTimeMillis();
                long l2 = sFullScreenAd.h();
                com.smule.android.logging.Analytics.a(Analytics.d, sFullScreenAd.a(), false, Analytics.a, string3, Analytics.a, null, string4, moPubErrorCode.name(), (Long)(l - l2));
                this.mEventObservable.a(MagicFullScreenAdMediatorAdapter.d, moPubErrorCode);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onRewardedVideoLoadSuccess(@NonNull String string2, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        if (sFullScreenAd == null) {
            Log.e(TAG, "FULLSCREEN_AD: onRewardedVideoLoadSuccess Error: sFullScreenAd is null");
            return;
        } else {
            Log.b(TAG, "^^^^^^^^^^^^^^^^ FULLSCREEN_AD: onRewardedVideoLoad SUCCESS: adPlacement: '" + sFullScreenAd.a().a() + "', entryPoint: '" + sFullScreenAd.c() + "', network: '" + moPubAnalyticsData.getAdNetworkName() + "', adUnitId: '" + string2 + "', request id: '" + moPubAnalyticsData.getRequestId() + "'");
            boolean bl = MoPubRewardedVideos.hasRewardedVideo((String)string2);
            Log.b(TAG, "   FULLSCREEN_AD:- does MoPubRewardedVideos report it has freshly loaded ad? " + bl);
            if (!bl) {
                Log.e(TAG, "         FULLSCREEN_AD: - NO AD! MoPub issue!!!!");
            }
            if (!string2.equals(sFullScreenAd.b())) return;
            {
                this.stopUIXTimeout();
                sFullScreenAd.a(MagicAd.AdStatus.c);
                long l = System.currentTimeMillis();
                long l2 = sFullScreenAd.h();
                com.smule.android.logging.Analytics.a(Analytics.d, sFullScreenAd.a(), true, Analytics.a, moPubAnalyticsData.getAdNetworkName(), Analytics.a, null, moPubAnalyticsData.getRequestId(), null, (Long)(l - l2));
                this.mEventObservable.a();
                if (!sFullScreenAd.g()) {
                    Log.b(TAG, "        FULLSCREEN_AD: - ad should NOT be played immediately, will be in cache for next time");
                    return;
                }
                Log.b(TAG, "        FULLSCREEN_AD: - ad should be played immediately, so going to show ad");
                sFullScreenAd.a(false);
                if (this.actuallyShowAd()) return;
                {
                    this.onRewardedVideoLoadFailure(sFullScreenAd.b(), MoPubErrorCode.VIDEO_NOT_AVAILABLE, moPubAnalyticsData);
                    return;
                }
            }
        }
    }

    public void onRewardedVideoPlaybackError(@NonNull String string2, @NonNull MoPubErrorCode moPubErrorCode, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        if (sFullScreenAd == null) {
            Log.e(TAG, "FULLSCREEN_AD: onRewardedVideoPlaybackError Error: sFullScreenAd is null");
            return;
        }
        Log.b(TAG, "FULLSCREEN_AD: onRewardedVideoPlaybackError: errorCode: '" + (Object)moPubErrorCode + "', adPlacement: '" + sFullScreenAd.a().a() + "', entryPoint: '" + sFullScreenAd.c() + "', network: '" + moPubAnalyticsData.getAdNetworkName() + "', adUnitId: '" + string2 + "', request id: '" + moPubAnalyticsData.getRequestId() + "'");
        if (!string2.equals(sFullScreenAd.b())) {
            Log.b(TAG, "adUnits do not match:" + sFullScreenAd.b() + " vs. " + string2);
            return;
        }
        sFullScreenAd.a(MagicAd.AdStatus.g);
        this.mEventObservable.a(MagicFullScreenAdMediatorAdapter.d, moPubErrorCode);
    }

    public void onRewardedVideoStarted(@NonNull String string2, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        if (sFullScreenAd == null) {
            Log.e(TAG, "FULLSCREEN_AD: onRewardedVideoStarted Error: sFullScreenAd is null");
            return;
        }
        Log.b(TAG, "FULLSCREEN_AD: onRewardedVideoStarted: adPlacement: '" + sFullScreenAd.a().a() + "', entryPoint: '" + sFullScreenAd.c() + "', network: '" + moPubAnalyticsData.getAdNetworkName() + "', adUnitId: '" + string2 + "', request id: '" + moPubAnalyticsData.getRequestId() + "'");
        if (!string2.equals(sFullScreenAd.b())) {
            Log.b(TAG, "adUnits do not match:" + sFullScreenAd.b() + " vs. " + string2);
            return;
        }
        com.smule.android.logging.Analytics.a(Analytics.d, sFullScreenAd.a(), Analytics.a, moPubAnalyticsData.getAdNetworkName(), Analytics.a, null, moPubAnalyticsData.getRequestId());
        sFullScreenAd.a(MagicAd.AdStatus.f);
        sFullScreenAd.b(System.currentTimeMillis());
        this.mEventObservable.a(MagicFullScreenAdMediatorAdapter.a, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void preloadAd(@NonNull MagicAd hashMap) {
        if (hashMap == null) {
            Log.e(TAG, "Error: null ad passed to preloadAd");
            return;
        }
        Log.b(TAG, "FULLSCREEN_AD: preloadAd(): adPlacement: '" + hashMap.a() + "', entryPoint: '" + hashMap.c() + "', adUnitId: '" + hashMap.b() + "'");
        if (this.isAdCurrent((MagicAd)((Object)hashMap))) {
            sFullScreenAd.a((MagicAd)((Object)hashMap));
        } else {
            sFullScreenAd = hashMap;
        }
        if (sFullScreenAd.f() == MagicAd.AdStatus.b) {
            Log.b(TAG, "    FULLSCREEN_AD: previous ad load request is still running, so ignoring this load attempt");
            return;
        }
        if (this.isAdLoaded(sFullScreenAd)) {
            Log.b(TAG, "    FULLSCREEN_AD: this ad is already loaded, so ignoring this load attempt");
            return;
        }
        hashMap = sFullScreenAd.e();
        hashMap = hashMap != null ? MagicMoPubFullScreenAdMediatorAdapter.formatKeywords(hashMap) : null;
        hashMap = new MoPubRewardedVideoManager.RequestParameters((String)((Object)hashMap), LocationUtils.a(), null);
        sFullScreenAd.a(MagicAd.AdStatus.b);
        Log.b(TAG, ">>>>>>>>>>>>>> FULLSCREEN_AD: actually loading the ad now...calling to MoPub");
        MoPubRewardedVideos.loadRewardedVideo((String)sFullScreenAd.b(), (MoPubRewardedVideoManager.RequestParameters)hashMap, (MediationSettings[])this.mVendorParamters.toArray((T[])new MediationSettings[this.mVendorParamters.size()]));
        sFullScreenAd.a(System.currentTimeMillis());
    }
}

