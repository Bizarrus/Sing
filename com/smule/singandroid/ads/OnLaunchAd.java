package com.smule.singandroid.ads;

import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;

public class OnLaunchAd extends FullScreenAd {
    private static final String f20634a = OnLaunchAd.class.getName();
    private static final String f20635b = SingApplication.f().getString(C1947R.string.dfp_launch_ad_unit);

    public OnLaunchAd() {
        m22256b(f20635b);
        m22252a("launch");
    }
}
