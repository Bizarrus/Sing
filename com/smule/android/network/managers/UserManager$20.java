package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class UserManager$20 implements Runnable {
    final /* synthetic */ UserManager$AccountIconResponseCallback f17248a;
    final /* synthetic */ String f17249b;
    final /* synthetic */ UserManager f17250c;

    UserManager$20(UserManager userManager, UserManager$AccountIconResponseCallback userManager$AccountIconResponseCallback, String str) {
        this.f17250c = userManager;
        this.f17248a = userManager$AccountIconResponseCallback;
        this.f17249b = str;
    }

    public void run() {
        CoreUtil.m18079a(this.f17248a, this.f17250c.e(this.f17249b));
    }
}
