package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class UserManager$21 implements Runnable {
    final /* synthetic */ UserManager$AccountIconResponseCallback f17251a;
    final /* synthetic */ String f17252b;
    final /* synthetic */ UserManager f17253c;

    UserManager$21(UserManager userManager, UserManager$AccountIconResponseCallback userManager$AccountIconResponseCallback, String str) {
        this.f17253c = userManager;
        this.f17251a = userManager$AccountIconResponseCallback;
        this.f17252b = str;
    }

    public void run() {
        CoreUtil.m18079a(this.f17251a, this.f17253c.f(this.f17252b));
    }
}
