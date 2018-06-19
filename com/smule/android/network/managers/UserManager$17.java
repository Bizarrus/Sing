package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;

class UserManager$17 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f17238a;
    final /* synthetic */ String f17239b;
    final /* synthetic */ UserManager f17240c;

    UserManager$17(UserManager userManager, NetworkResponseCallback networkResponseCallback, String str) {
        this.f17240c = userManager;
        this.f17238a = networkResponseCallback;
        this.f17239b = str;
    }

    public void run() {
        CoreUtil.m18079a(this.f17238a, this.f17240c.c(this.f17239b));
    }
}
