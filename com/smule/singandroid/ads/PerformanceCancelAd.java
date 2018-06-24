package com.smule.singandroid.ads;

import android.app.Activity;
import com.facebook.appevents.AppEventsConstants;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingBundle;
import java.util.HashMap;
import java.util.Map;

public class PerformanceCancelAd extends FullScreenAd {
    private static final String f20636a = PostPerformanceReviewAd.class.getName();
    private static final String f20637b = SingApplication.f().getString(C1947R.string.dfp_performance_cancel_ad_unit);

    public PerformanceCancelAd() {
        m22256b(f20637b);
        m22252a("cancel");
    }

    public void m22259a(Activity activity, SingBundle singBundle) {
        SongbookEntry songbookEntry = singBundle.f20139d;
        Map hashMap = new HashMap();
        if (!(songbookEntry == null || songbookEntry.mo6290d() == null)) {
            hashMap.put("uid", songbookEntry.mo6290d());
        }
        hashMap.put("is_join", singBundle.f20146k ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        m22251a(activity, null, hashMap);
    }
}
