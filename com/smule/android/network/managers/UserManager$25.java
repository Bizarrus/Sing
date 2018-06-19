package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.UserManager.BlockedUsersResponse;

class UserManager$25 implements Runnable {
    final /* synthetic */ UserManager$BlockedUsersResponseCallback f17263a;
    final /* synthetic */ UserManager f17264b;

    UserManager$25(UserManager userManager, UserManager$BlockedUsersResponseCallback userManager$BlockedUsersResponseCallback) {
        this.f17264b = userManager;
        this.f17263a = userManager$BlockedUsersResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f17263a, BlockedUsersResponse.a(NetworkUtils.m18104a(this.f17264b.b.getBlockedUsers(new SnpRequest()))));
    }
}
