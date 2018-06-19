package com.smule.android.facebook;

import android.content.SharedPreferences;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;

class MagicFacebook$2 implements Runnable {
    final /* synthetic */ SharedPreferences f15965a;
    final /* synthetic */ MagicFacebook f15966b;

    MagicFacebook$2(MagicFacebook magicFacebook, SharedPreferences sharedPreferences) {
        this.f15966b = magicFacebook;
        this.f15965a = sharedPreferences;
    }

    public void run() {
        NetworkResponse networkResponse = null;
        MagicFacebook$FacebookUserInfo a = this.f15966b.a(null, false);
        if (a != null && a.m17763a()) {
            networkResponse = UserManager.a().a(a);
        }
        if (networkResponse != null && networkResponse.c()) {
            Log.b(MagicFacebook.i(), "on connectFacebookToSmuleAccountSync - Facebook account successfully connected");
            this.f15965a.edit().putBoolean("DID_CONNECT_FACEBOOK", true).apply();
        }
        MagicFacebook.a(this.f15966b, false);
    }
}
