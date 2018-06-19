package com.smule.android.ads.networks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.interstitials.InterstitialAdCloseReason;
import com.fyber.requesters.OfferWallRequester;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;
import com.fyber.utils.FyberLogger;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.networks.AdVendor.AdType;
import com.smule.android.ads.networks.AdVendor.ShowAdCallbackInterface;
import com.smule.android.logging.Analytics.EarnVCVendor;
import com.smule.android.network.managers.UserManager;

public class FyberAdVendor extends AdVendor {
    private static final String f15670c = FyberAdVendor.class.getName();
    String f15671a;
    String f15672b;

    public FyberAdVendor(String str, String str2) {
        this.f15671a = str;
        this.f15672b = str2;
    }

    public EarnVCVendor mo6224a() {
        return EarnVCVendor.FYBER;
    }

    public boolean mo6227a(AdType adType) {
        switch (adType) {
            case OFFER_WALL:
                return true;
            default:
                return false;
        }
    }

    public void mo6225a(Activity activity) {
        Fyber.a(this.f15671a, activity).a(String.valueOf(UserManager.a().g())).b(this.f15672b).b();
    }

    public void mo6228b(Activity activity) {
    }

    protected void mo6229a(final Activity activity, final ShowAdCallbackInterface showAdCallbackInterface) {
        OfferWallRequester.m9446a(new RequestCallback(this) {
            final /* synthetic */ FyberAdVendor f15668c;

            public void mo4151a(Intent intent) {
                showAdCallbackInterface.mo6207a(this.f15668c);
                activity.startActivityForResult(intent, 2114);
            }

            public void mo4152a(AdFormat adFormat) {
                showAdCallbackInterface.mo6209c(this.f15668c);
            }

            public void mo4153a(RequestError requestError) {
                showAdCallbackInterface.mo6210d(this.f15668c);
            }
        }).m9447a(true).m9448a(activity.getApplicationContext());
    }

    public boolean mo6230a(Context context, int i, int i2, Intent intent) {
        if (i != 2114) {
            return false;
        }
        if (i2 == -1 && intent != null) {
            InterstitialAdCloseReason interstitialAdCloseReason = (InterstitialAdCloseReason) intent.getSerializableExtra("AD_STATUS");
            FyberLogger.m9475b(f15670c, "SPInterstitial closed with status - " + interstitialAdCloseReason);
            if (interstitialAdCloseReason.equals(InterstitialAdCloseReason.ReasonError)) {
                FyberLogger.m9475b(f15670c, "SPInterstitial closed and error - " + intent.getStringExtra("ERROR_MESSAGE"));
            } else {
                AdVendorManager.m17399a().m17410a(context);
            }
        }
        return true;
    }
}
