package com.smule.android.network.managers;

import com.smule.android.network.api.UserAPI$GetPreferencesRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.UserManager.AccountPreferencesResponse;
import java.util.List;

class UserManager$28 implements Runnable {
    final /* synthetic */ List f17271a;
    final /* synthetic */ UserManager$AccountPreferencesResponseCallback f17272b;
    final /* synthetic */ UserManager f17273c;

    UserManager$28(UserManager userManager, List list, UserManager$AccountPreferencesResponseCallback userManager$AccountPreferencesResponseCallback) {
        this.f17273c = userManager;
        this.f17271a = list;
        this.f17272b = userManager$AccountPreferencesResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f17272b, AccountPreferencesResponse.a(NetworkUtils.m18104a(this.f17273c.b.getPreferences(new UserAPI$GetPreferencesRequest().setNames(this.f17271a)))));
    }
}
