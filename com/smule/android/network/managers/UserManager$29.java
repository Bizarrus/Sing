package com.smule.android.network.managers;

import com.smule.android.network.api.UserAPI$UpdatePreferencesRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.UserManager.UpdateAccountPreferencesResponse;
import java.util.List;

class UserManager$29 implements Runnable {
    final /* synthetic */ List f17274a;
    final /* synthetic */ UserManager$UpdateAccountPreferencesResponseCallback f17275b;
    final /* synthetic */ UserManager f17276c;

    UserManager$29(UserManager userManager, List list, UserManager$UpdateAccountPreferencesResponseCallback userManager$UpdateAccountPreferencesResponseCallback) {
        this.f17276c = userManager;
        this.f17274a = list;
        this.f17275b = userManager$UpdateAccountPreferencesResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f17275b, UpdateAccountPreferencesResponse.a(NetworkUtils.m18104a(this.f17276c.b.updatePreferences(new UserAPI$UpdatePreferencesRequest().setPreferences(this.f17274a))), UpdateAccountPreferencesResponse.class));
    }
}
