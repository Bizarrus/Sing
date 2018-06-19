package com.smule.android.ads.vendors.mopub;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.ChartboostRewardedVideo.ChartboostMediationSettings;
import com.mopub.mobileads.MoPubAnalyticsData;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import com.mopub.mobileads.MoPubRewardedVideoManager.RequestParameters;
import com.mopub.mobileads.MoPubRewardedVideos;
import com.mopub.rewardedvideos.AppLovinRewardedAdapter.AppLovinMediationSettings;
import com.smule.android.R;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter.AdImpressionResult;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter.RewardResult;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.AdCategory;
import com.smule.android.logging.Analytics.AdMediator;
import com.smule.android.logging.Analytics.AdType;
import com.smule.android.logging.Analytics.FullScreenAdPlacementType;
import com.smule.android.logging.Log;
import com.smule.android.utils.LocationUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MagicMoPubFullScreenAdMediatorAdapter extends MagicFullScreenAdMediatorAdapter implements MoPubRewardedVideoListener {
    private static final String APP_LOVIN_PLACEMENT_ID_LOOKUP = "placement_id.fullscreen_ads.rewarded.phone.singing.mopub.applovin";
    private static final String CHARTBOOST_PLACEMENT_ID_LOOKUP = "placement_id.fullscreen_ads.rewarded.phone.singing.mopub.chartboost";
    public static final String FILE_NAME = "fullscreen_ads_prefs";
    private static final String TAG = MagicMoPubFullScreenAdMediatorAdapter.class.getName();
    private static boolean sInitialAdPreloaded;
    private static boolean sRewardedVideoInitialized;
    private long mAdImpressionStartTime = 0;
    private long mAdLoadStartTime = 0;
    private boolean mFetchingAd;
    private boolean mIsAdBeingDisplayed;
    private FullScreenAdPlacementType mPlacementType;
    private boolean mPlayAdImmediatelyUponLoad;
    private String mRewwardedVideoAdUnit;
    private SharedPreferences mSharedPreferences;
    private boolean mUXTimeOutReached;
    private boolean mUserEarnedReward;
    private ArrayList<MediationSettings> mVendorParamters;

    public static void registerWithFactory(Context context) {
        MagicAdAdapterFactory.a().b(MagicMoPubFullScreenAdMediatorAdapter.class, context.getResources().getString(R.string.MagicMoPubMediatorSettingsKey));
    }

    public MagicMoPubFullScreenAdMediatorAdapter(Activity activity, FullScreenAdPlacementType fullScreenAdPlacementType, HashMap<String, String> hashMap, Runnable runnable) {
        super(activity, fullScreenAdPlacementType, hashMap, runnable);
        this.mPlacementType = fullScreenAdPlacementType;
        this.mRewwardedVideoAdUnit = MagicAdSettings.a(activity, this.mPlacementType);
        Context applicationContext = activity.getApplicationContext();
        this.mVendorParamters = new ArrayList();
        this.mVendorParamters.add(getAppLovinMediationSettings(applicationContext));
        this.mVendorParamters.add(getChartboostMediationSettings(applicationContext));
        this.mSharedPreferences = applicationContext.getSharedPreferences(FILE_NAME, 0);
        if (sRewardedVideoInitialized) {
            MoPubRewardedVideoManager.updateActivity(activity);
        } else {
            sRewardedVideoInitialized = true;
            MoPubRewardedVideos.initializeRewardedVideo(activity, new MediationSettings[0]);
        }
        MoPubRewardedVideos.setRewardedVideoListener(this);
    }

    public void preloadInitialAd(HashMap<String, String> hashMap) {
        Log.m7770b(TAG, "preloadInitialAd loadAd()");
        if (!sInitialAdPreloaded) {
            sInitialAdPreloaded = true;
            loadAd(hashMap);
        }
    }

    public void loadAd(HashMap<String, String> hashMap) {
        String formatKeywords;
        Log.m7770b(TAG, "fulllscreen loadAd(): " + this.mRewwardedVideoAdUnit);
        this.mFetchingAd = true;
        if (hashMap != null) {
            formatKeywords = formatKeywords(hashMap);
        } else {
            formatKeywords = null;
        }
        MoPubRewardedVideos.loadRewardedVideo(this.mRewwardedVideoAdUnit, new RequestParameters(formatKeywords, LocationUtils.a(), null), (MediationSettings[]) this.mVendorParamters.toArray(new MediationSettings[this.mVendorParamters.size()]));
        this.mAdLoadStartTime = System.currentTimeMillis();
    }

    public boolean showAd() {
        Log.m7770b(TAG, "fulllscreen showAd()");
        boolean hasRewardedVideo = MoPubRewardedVideos.hasRewardedVideo(this.mRewwardedVideoAdUnit);
        if (hasRewardedVideo) {
            this.mIsAdBeingDisplayed = true;
            this.mUserEarnedReward = false;
            MoPubRewardedVideos.showRewardedVideo(this.mRewwardedVideoAdUnit);
        }
        return hasRewardedVideo;
    }

    public void loadAndShowAd(HashMap<String, String> hashMap) {
        Log.m7770b(TAG, "fulllscreen loadAndShowAd()");
        if (!showAd()) {
            this.mPlayAdImmediatelyUponLoad = true;
            this.mUXTimeOutReached = false;
            loadAd(hashMap);
            startUXTimeout();
        }
    }

    public void cancelAd() {
        this.mPlayAdImmediatelyUponLoad = false;
        Analytics.a(AdType.d, this.mPlacementType, AdMediator.a, null, AdCategory.a, null, MoPubRewardedVideos.getAdRequestId(this.mRewwardedVideoAdUnit), System.currentTimeMillis() - this.mAdLoadStartTime);
    }

    public void destroy() {
        this.mEventObservable.unregisterAll();
    }

    public void logAdRewardClick() {
        Analytics.a(AdType.d);
    }

    private void startUXTimeout() {
        Log.m7770b(TAG, "fullscreen ad - starting UX timeout");
        new Handler().postDelayed(new 1(this), TimeUnit.MILLISECONDS.convert((long) MagicAdSettings.c(), TimeUnit.SECONDS));
    }

    private void onUXTimeout() {
        Log.m7770b(TAG, "fullscreen ad UX timeout reached, is ad still being fetched? " + this.mFetchingAd);
        if (this.mFetchingAd) {
            this.mUXTimeOutReached = true;
            Analytics.b(AdType.d, this.mPlacementType, AdMediator.a, null, AdCategory.a, null, MoPubRewardedVideos.getAdRequestId(this.mRewwardedVideoAdUnit), System.currentTimeMillis() - this.mAdLoadStartTime);
            this.mEventObservable.a(AdImpressionResult.b, null);
            return;
        }
        this.mUXTimeOutReached = false;
    }

    public boolean isAdBeingDisplayed() {
        return this.mIsAdBeingDisplayed;
    }

    public void onRewardedVideoLoadSuccess(@NonNull String str, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        Log.m7770b(TAG, "fullscreen ad - onRewardedVideoLoadSuccess: " + str + ", network: " + moPubAnalyticsData.getAdNetworkName() + ", request id: " + moPubAnalyticsData.getRequestId());
        if (str.equals(this.mRewwardedVideoAdUnit)) {
            this.mFetchingAd = false;
            if (!this.mUXTimeOutReached && this.mPlayAdImmediatelyUponLoad) {
                showAd();
                this.mPlayAdImmediatelyUponLoad = false;
            }
            this.mEventObservable.a();
            long currentTimeMillis = System.currentTimeMillis() - this.mAdLoadStartTime;
            Analytics.a(AdType.d, this.mPlacementType, true, AdMediator.a, moPubAnalyticsData.getAdNetworkName(), AdCategory.a, null, moPubAnalyticsData.getRequestId(), null, Long.valueOf(currentTimeMillis));
        }
    }

    public void onRewardedVideoLoadFailure(@NonNull String str, @NonNull MoPubErrorCode moPubErrorCode, MoPubAnalyticsData moPubAnalyticsData) {
        String adNetworkName;
        String requestId;
        if (moPubAnalyticsData != null) {
            adNetworkName = moPubAnalyticsData.getAdNetworkName();
            requestId = moPubAnalyticsData.getRequestId();
            Log.m7770b(TAG, "fullscreen ad - onRewardedVideoLoadFailure: " + str + ", errorCode: " + moPubErrorCode + ", network: " + adNetworkName + ", request id: " + requestId);
        } else {
            adNetworkName = "";
            requestId = "";
            Log.m7770b(TAG, "fullscreen ad - onRewardedVideoLoadFailure: " + str + ", errorCode: " + moPubErrorCode);
        }
        this.mFetchingAd = false;
        this.mPlayAdImmediatelyUponLoad = false;
        Analytics.a(AdType.d, this.mPlacementType, false, AdMediator.a, adNetworkName, AdCategory.a, null, requestId, moPubErrorCode.name(), Long.valueOf(System.currentTimeMillis() - this.mAdLoadStartTime));
        this.mEventObservable.a(AdImpressionResult.c, moPubErrorCode);
    }

    public void onRewardedVideoStarted(@NonNull String str, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        Log.m7770b(TAG, "fullscreen ad - onRewardedVideoStarted: " + str + ", network: " + moPubAnalyticsData.getAdNetworkName() + ", request id: " + moPubAnalyticsData.getRequestId());
        Analytics.a(AdType.d, this.mPlacementType, AdMediator.a, moPubAnalyticsData.getAdNetworkName(), AdCategory.a, null, moPubAnalyticsData.getRequestId());
        this.mAdImpressionStartTime = System.currentTimeMillis();
        this.mEventObservable.a(AdImpressionResult.a, null);
    }

    public void onRewardedVideoPlaybackError(@NonNull String str, @NonNull MoPubErrorCode moPubErrorCode, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        Log.m7770b(TAG, "fullscreen ad - onRewardedVideoPlaybackError: " + str + ", errorCode: " + moPubErrorCode + ", network: " + moPubAnalyticsData.getAdNetworkName() + ", request id: " + moPubAnalyticsData.getRequestId());
        this.mEventObservable.a(AdImpressionResult.c, moPubErrorCode);
    }

    public void onRewardedVideoClosed(@NonNull String str, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        Log.m7770b(TAG, "fullscreen ad - onRewardedVideoClosed: " + str + ", network: " + moPubAnalyticsData.getAdNetworkName() + ", request id: " + moPubAnalyticsData.getRequestId());
        if (this.mIsAdBeingDisplayed) {
            this.mIsAdBeingDisplayed = false;
            if (this.mSharedPreferences != null) {
                this.mSharedPreferences.edit().putBoolean(MagicMoPubFullScreenAdMediatorAdapter.class.getSimpleName(), this.mUserEarnedReward).apply();
            }
            if (this.mUserEarnedReward) {
                Analytics.c(AdType.d, this.mPlacementType, AdMediator.a, moPubAnalyticsData.getAdNetworkName(), AdCategory.a, null, moPubAnalyticsData.getRequestId(), System.currentTimeMillis() - this.mAdImpressionStartTime);
                this.mEventObservable.a(RewardResult.a);
                return;
            }
            this.mEventObservable.a(RewardResult.b);
            return;
        }
        Log.m7770b(TAG, "  duplicate call to onRewardedVideoClosed, exiting");
    }

    public void onRewardedVideoCompleted(@NonNull Set<String> set, @NonNull MoPubReward moPubReward, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        Log.m7770b(TAG, "fullscreen ad - onRewardedVideoCompleted: " + set + ", reward success?  " + moPubReward.isSuccessful() + ", network: " + moPubAnalyticsData.getAdNetworkName() + ", request id: " + moPubAnalyticsData.getRequestId());
        this.mUserEarnedReward = moPubReward.isSuccessful();
    }

    public void onRewardedVideoClicked(@NonNull String str, @NonNull MoPubAnalyticsData moPubAnalyticsData) {
        Log.m7770b(TAG, "fullscreen ad - onRewardedVideoClicked: " + str + ", network: " + moPubAnalyticsData.getAdNetworkName() + ", request id: " + moPubAnalyticsData.getRequestId());
        Analytics.b(AdType.d, this.mPlacementType, AdMediator.a, moPubAnalyticsData.getAdNetworkName(), AdCategory.a, null, moPubAnalyticsData.getRequestId());
    }

    public void onActivityCreate(Activity activity) {
        MoPub.onCreate(activity);
    }

    public void onActivityStart(Activity activity) {
        MoPub.onStart(activity);
    }

    public void onActivityRestart(Activity activity) {
        MoPub.onRestart(activity);
    }

    public void onActivityResume(Activity activity) {
        MoPub.onResume(activity);
    }

    public void onActivityPause(Activity activity) {
        MoPub.onPause(activity);
    }

    public void onActivityStop(Activity activity) {
        MoPub.onStop(activity);
    }

    public void onActivityDestroy(Activity activity) {
        MoPub.onDestroy(activity);
    }

    public void onBackPressed(Activity activity) {
        MoPub.onBackPressed(activity);
    }

    @NonNull
    private String getPlacementId(@NonNull Context context, @NonNull String str) {
        int identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        if (identifier != 0) {
            return context.getResources().getString(identifier);
        }
        Log.m7776e(TAG, "no resource ID found for: " + str);
        return "unknown_placement_id";
    }

    private MediationSettings getAppLovinMediationSettings(Context context) {
        return new AppLovinMediationSettings(getPlacementId(context, APP_LOVIN_PLACEMENT_ID_LOOKUP));
    }

    private MediationSettings getChartboostMediationSettings(Context context) {
        return new ChartboostMediationSettings(getPlacementId(context, CHARTBOOST_PLACEMENT_ID_LOOKUP));
    }

    private static String formatKeywords(HashMap<String, String> hashMap) {
        Iterable arrayList = new ArrayList();
        for (String str : hashMap.keySet()) {
            arrayList.add(str + ":" + ((String) hashMap.get(str)));
        }
        return TextUtils.join(",", arrayList);
    }
}
