package com.smule.singandroid.ads;

import android.app.Activity;
import com.facebook.appevents.AppEventsConstants;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import java.util.HashMap;
import java.util.Map;

public class PostPerformanceReviewAd extends FullScreenAd {
    private static final String f20638a = PostPerformanceReviewAd.class.getName();
    private static final String f20639b = SingApplication.f().getString(C1947R.string.dfp_post_performance_save_ad_unit);

    public PostPerformanceReviewAd() {
        m22256b(f20639b);
        m22252a("postperformance");
    }

    public boolean m22260a(Activity activity, String str, String str2, String str3, Boolean bool) {
        if (activity != null) {
            Log.b(f20638a, "Pre-loading ad");
            Map hashMap = new HashMap();
            if (str != null) {
                hashMap.put("uid", str);
            }
            if (!(str2 == null || str2.equals("-1"))) {
                hashMap.put("promo_id", str2);
            }
            if (str3 != null) {
                hashMap.put("seed_id", str3);
            }
            if (bool != null) {
                hashMap.put("is_join", bool.booleanValue() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            try {
                m22251a(activity, null, hashMap);
            } catch (Throwable e) {
                Log.d(f20638a, "showPostPerformanceReviewAd: failed to load ad: ", e);
            }
        }
        return false;
    }
}
