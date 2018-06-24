/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 */
package com.smule.singandroid.ads;

import android.app.Activity;
import com.smule.android.logging.Log;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.ads.FullScreenAd;
import java.util.HashMap;
import java.util.Map;

public class PostPerformanceReviewAd
extends FullScreenAd {
    private static final String a = PostPerformanceReviewAd.class.getName();
    private static final String b = SingApplication.g().getString(2131297710);

    public PostPerformanceReviewAd() {
        this.b(b);
        this.a("postperformance");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a(Activity activity, String string2, String string3, String string4, Boolean bl) {
        if (activity == null) {
            return false;
        }
        Log.b(a, "Pre-loading ad");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (string2 != null) {
            hashMap.put("uid", string2);
        }
        if (string3 != null && !string3.equals("-1")) {
            hashMap.put("promo_id", string3);
        }
        if (string4 != null) {
            hashMap.put("seed_id", string4);
        }
        if (bl != null) {
            string2 = bl != false ? "1" : "0";
            hashMap.put("is_join", string2);
        }
        try {
            this.a(activity, null, hashMap);
            return false;
        }
        catch (Exception exception) {
            Log.d(a, "showPostPerformanceReviewAd: failed to load ad: ", exception);
            return false;
        }
    }
}

