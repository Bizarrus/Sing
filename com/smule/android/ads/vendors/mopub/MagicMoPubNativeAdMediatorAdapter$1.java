package com.smule.android.ads.vendors.mopub;

import com.mopub.nativeads.BaseNativeAd;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.SmuleNativeAdEventListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.AdCategory;
import com.smule.android.logging.Analytics.AdMediator;
import com.smule.android.logging.Analytics.AdType;
import com.smule.android.logging.Analytics.NativeAdPlacementType;

class MagicMoPubNativeAdMediatorAdapter$1 implements SmuleNativeAdEventListener {
    final /* synthetic */ NativeAdPlacementType f15674a;
    final /* synthetic */ Runnable f15675b;

    MagicMoPubNativeAdMediatorAdapter$1(NativeAdPlacementType nativeAdPlacementType, Runnable runnable) {
        this.f15674a = nativeAdPlacementType;
        this.f15675b = runnable;
    }

    public void onAdRequestSuccess(BaseNativeAd baseNativeAd) {
        if (baseNativeAd != null) {
            Analytics.m17842a(AdType.NATIVE, this.f15674a, true, AdMediator.MOPUB, baseNativeAd.getAdNetworkName(), AdCategory.THIRD_PARTY, null, baseNativeAd.getRequestId(), null, Long.valueOf(baseNativeAd.getLoadTime()));
        }
    }

    public void onAdRequestFail(BaseNativeAd baseNativeAd, NativeErrorCode nativeErrorCode) {
        if (baseNativeAd != null) {
            Analytics.m17842a(AdType.NATIVE, this.f15674a, false, AdMediator.MOPUB, baseNativeAd.getAdNetworkName(), AdCategory.THIRD_PARTY, null, baseNativeAd.getRequestId(), nativeErrorCode.name(), null);
        }
    }

    public void onAdImpression(BaseNativeAd baseNativeAd) {
        if (baseNativeAd != null) {
            Analytics.m17841a(AdType.NATIVE, this.f15674a, Integer.valueOf(baseNativeAd.getPosition()), AdMediator.MOPUB, baseNativeAd.getAdNetworkName(), AdCategory.THIRD_PARTY, null, baseNativeAd.getRequestId());
        }
    }

    public void onAdClick(BaseNativeAd baseNativeAd) {
        if (baseNativeAd != null) {
            Analytics.m17886b(AdType.NATIVE, this.f15674a, Integer.valueOf(baseNativeAd.getPosition()), AdMediator.MOPUB, baseNativeAd.getAdNetworkName(), AdCategory.THIRD_PARTY, null, baseNativeAd.getRequestId());
            if (this.f15675b != null) {
                this.f15675b.run();
            }
        }
    }
}
