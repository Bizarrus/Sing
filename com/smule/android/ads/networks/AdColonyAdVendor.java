package com.smule.android.ads.networks;

import android.app.Activity;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdAvailabilityListener;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyV4VCAd;
import com.smule.android.ads.networks.AdVendor.AdType;
import com.smule.android.ads.networks.AdVendor.ShowAdCallbackInterface;
import com.smule.android.logging.Analytics.EarnVCVendor;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.MagicDevice;

public class AdColonyAdVendor extends AdVendor {
    private static final String f15633e = AdColonyAdVendor.class.getName();
    String f15634a;
    String f15635b;
    String f15636c;
    String[] f15637d;
    private volatile boolean f15638f = false;
    private AdColonyAdAvailabilityListener f15639g = new C34961(this);

    class C34961 implements AdColonyAdAvailabilityListener {
        final /* synthetic */ AdColonyAdVendor f15625a;

        C34961(AdColonyAdVendor adColonyAdVendor) {
            this.f15625a = adColonyAdVendor;
        }

        public void mo6223a(boolean z, String str) {
            Log.b(AdColonyAdVendor.f15633e, "onAdColonyAdAvailabilityChange zone: " + str + " available: " + z);
            if (this.f15625a.f15636c != null && this.f15625a.f15636c.equalsIgnoreCase(str)) {
                Log.b(AdColonyAdVendor.f15633e, "onAdColonyAdAvailabilityChange reward video available: " + z);
                if (z) {
                    this.f15625a.f15638f = false;
                }
                this.f15625a.m17468d();
            }
        }
    }

    public AdColonyAdVendor(String str, String str2, String str3, String[] strArr) {
        this.f15634a = str;
        this.f15635b = str2;
        this.f15636c = str3;
        this.f15637d = strArr;
    }

    public EarnVCVendor mo6224a() {
        return EarnVCVendor.ADCOLONY;
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
        Log.b(f15633e, "Initializing AdColony");
        AdColony.m14566a(activity, this.f15635b, this.f15634a, this.f15637d);
        long g = UserManager.a().g();
        if (g != 0) {
            AdColony.m14568a(String.valueOf(g));
        } else {
            Log.e(f15633e, "Player id is null when configuring AdColony");
        }
        AdColony.m14570b(MagicDevice.m19003a(activity));
        AdColony.m14567a(this.f15639g);
    }

    public void mo6228b(Activity activity) {
        Log.a(f15633e, "actuallyPreCacheRewardVideo");
        String e = AdColony.m14577e(this.f15636c);
        Object obj = -1;
        switch (e.hashCode()) {
            case -1422950650:
                if (e.equals("active")) {
                    obj = null;
                    break;
                }
                break;
            case -284840886:
                if (e.equals("unknown")) {
                    obj = 2;
                    break;
                }
                break;
            case 109935:
                if (e.equals("off")) {
                    obj = 4;
                    break;
                }
                break;
            case 336650556:
                if (e.equals("loading")) {
                    obj = 1;
                    break;
                }
                break;
            case 1959784951:
                if (e.equals("invalid")) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                m17468d();
                return;
            case 1:
                if (this.f15638f) {
                    m17468d();
                    return;
                }
                return;
            case 2:
                return;
            default:
                m17470e();
                return;
        }
    }

    protected void mo6226a(Activity activity, Object obj, final ShowAdCallbackInterface showAdCallbackInterface) {
        Log.a(f15633e, "showRewardVideo");
        new AdColonyV4VCAd(this.f15636c).m14617a(new AdColonyAdListener(this) {
            final /* synthetic */ AdColonyAdVendor f15627b;

            public void mo6213b(AdColonyAd adColonyAd) {
                Log.b(AdColonyAdVendor.f15633e, "AdColony video started");
                showAdCallbackInterface.mo6207a(this.f15627b);
            }

            public void mo6212a(AdColonyAd adColonyAd) {
                Log.b(AdColonyAdVendor.f15633e, "AdColony video finished");
                if (adColonyAd.m14578a()) {
                    showAdCallbackInterface.mo6208b(this.f15627b);
                } else if (adColonyAd.m14583d()) {
                    this.f15627b.f15638f = true;
                    showAdCallbackInterface.mo6209c(this.f15627b);
                } else if (adColonyAd.m14582c() || adColonyAd.m14584e()) {
                    showAdCallbackInterface.mo6211e(this.f15627b);
                } else {
                    showAdCallbackInterface.mo6210d(this.f15627b);
                }
            }
        }).m14628n();
    }
}
