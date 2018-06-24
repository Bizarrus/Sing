/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.ads;

import com.smule.singandroid.SingApplication;
import com.smule.singandroid.ads.FullScreenAd;

public class OnLaunchAd
extends FullScreenAd {
    private static final String a = OnLaunchAd.class.getName();
    private static final String b = SingApplication.g().getString(2131297708);

    public OnLaunchAd() {
        this.b(b);
        this.a("launch");
    }
}

