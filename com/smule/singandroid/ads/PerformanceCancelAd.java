/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 */
package com.smule.singandroid.ads;

import android.app.Activity;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.ads.FullScreenAd;
import com.smule.singandroid.ads.PostPerformanceReviewAd;
import java.util.HashMap;
import java.util.Map;

public class PerformanceCancelAd
extends FullScreenAd {
    private static final String a = PostPerformanceReviewAd.class.getName();
    private static final String b = SingApplication.g().getString(2131297709);

    public PerformanceCancelAd() {
        this.b(b);
        this.a("cancel");
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Activity activity, SingBundle object) {
        SongbookEntry songbookEntry = object.d;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (songbookEntry != null && songbookEntry.d() != null) {
            hashMap.put("uid", songbookEntry.c());
        }
        object = object.k ? "1" : "0";
        hashMap.put("is_join", (String)object);
        this.a(activity, null, hashMap);
    }
}

