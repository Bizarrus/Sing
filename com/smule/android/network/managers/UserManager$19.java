package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponse;

class UserManager$19 implements Runnable {
    final /* synthetic */ String f17244a;
    final /* synthetic */ UserManager$UpdateUserBlurbResponseCallback f17245b;
    final /* synthetic */ UserManager f17246c;

    UserManager$19(UserManager userManager, String str, UserManager$UpdateUserBlurbResponseCallback userManager$UpdateUserBlurbResponseCallback) {
        this.f17246c = userManager;
        this.f17244a = str;
        this.f17245b = userManager$UpdateUserBlurbResponseCallback;
    }

    public void run() {
        NetworkResponse d = this.f17246c.d(this.f17244a);
        if (d != null && d.c()) {
            this.f17246c.a(this.f17244a);
        }
        CoreUtil.m18079a(this.f17245b, d);
    }
}
