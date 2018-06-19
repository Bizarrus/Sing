package com.smule.android.network.managers;

import com.smule.android.facebook.MagicFacebook$FacebookUserInfo;
import com.smule.android.network.core.CoreUtil;

class UserManager$8 implements Runnable {
    final /* synthetic */ UserManager$LoginResponseCallback f17294a;
    final /* synthetic */ MagicFacebook$FacebookUserInfo f17295b;
    final /* synthetic */ String f17296c;
    final /* synthetic */ boolean f17297d;
    final /* synthetic */ UserManager f17298e;

    public void run() {
        CoreUtil.m18079a(this.f17294a, this.f17298e.a(this.f17295b, this.f17296c, this.f17297d));
    }
}
