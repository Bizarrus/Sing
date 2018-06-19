package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class UserManager$3 implements Runnable {
    final /* synthetic */ UserManager$AccountResponseCallback f17279a;
    final /* synthetic */ UserManager f17280b;

    UserManager$3(UserManager userManager, UserManager$AccountResponseCallback userManager$AccountResponseCallback) {
        this.f17280b = userManager;
        this.f17279a = userManager$AccountResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f17279a, this.f17280b.H());
    }
}
