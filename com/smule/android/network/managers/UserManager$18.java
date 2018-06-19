package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.managers.UserManager.UserBlurbResponse;

class UserManager$18 implements Runnable {
    final /* synthetic */ UserManager$GetUserBlurbResponseCallback f17241a;
    final /* synthetic */ long f17242b;
    final /* synthetic */ UserManager f17243c;

    UserManager$18(UserManager userManager, UserManager$GetUserBlurbResponseCallback userManager$GetUserBlurbResponseCallback, long j) {
        this.f17243c = userManager;
        this.f17241a = userManager$GetUserBlurbResponseCallback;
        this.f17242b = j;
    }

    public void run() {
        CoreUtil.m18079a(this.f17241a, UserBlurbResponse.a(this.f17243c.a(this.f17242b)));
    }
}
