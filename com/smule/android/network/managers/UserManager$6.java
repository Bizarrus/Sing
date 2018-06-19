package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class UserManager$6 implements Runnable {
    final /* synthetic */ UserManager$LoginResponseCallback f17288a;
    final /* synthetic */ UserManager f17289b;

    UserManager$6(UserManager userManager, UserManager$LoginResponseCallback userManager$LoginResponseCallback) {
        this.f17289b = userManager;
        this.f17288a = userManager$LoginResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f17288a, this.f17289b.L());
    }
}
