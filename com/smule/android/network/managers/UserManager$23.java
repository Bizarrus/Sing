package com.smule.android.network.managers;

import com.smule.android.network.api.UserAPI$UserProfileRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.UserManager.AccountIconResponse;

class UserManager$23 implements Runnable {
    final /* synthetic */ long f17257a;
    final /* synthetic */ UserManager$AccountIconResponseCallback f17258b;
    final /* synthetic */ UserManager f17259c;

    UserManager$23(UserManager userManager, long j, UserManager$AccountIconResponseCallback userManager$AccountIconResponseCallback) {
        this.f17259c = userManager;
        this.f17257a = j;
        this.f17258b = userManager$AccountIconResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f17258b, AccountIconResponse.a(NetworkUtils.m18104a(this.f17259c.b.userProfile(new UserAPI$UserProfileRequest().setAccountId(Long.valueOf(this.f17257a))))));
    }
}
