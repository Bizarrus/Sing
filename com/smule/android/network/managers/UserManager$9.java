package com.smule.android.network.managers;

import com.smule.android.facebook.MagicFacebook$FacebookUserInfo;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;

class UserManager$9 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f17299a;
    final /* synthetic */ MagicFacebook$FacebookUserInfo f17300b;
    final /* synthetic */ UserManager f17301c;

    public void run() {
        CoreUtil.m18079a(this.f17299a, this.f17301c.a(this.f17300b));
    }
}
