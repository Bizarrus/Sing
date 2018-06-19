package com.smule.android.facebook;

import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.smule.android.network.managers.UserManager;

class MagicFacebook$4 implements Runnable {
    final /* synthetic */ boolean f15973a;
    final /* synthetic */ MagicFacebook$FacebookDisconnectedListener f15974b;
    final /* synthetic */ MagicFacebook f15975c;

    class C35311 implements Callback {
        final /* synthetic */ MagicFacebook$4 f15972a;

        C35311(MagicFacebook$4 magicFacebook$4) {
            this.f15972a = magicFacebook$4;
        }

        public void onCompleted(GraphResponse graphResponse) {
        }
    }

    public void run() {
        boolean c;
        if (this.f15975c.d()) {
            c = UserManager.a().M().c();
        } else {
            c = false;
        }
        MagicFacebook.a(this.f15975c).getSharedPreferences("MagicFacebook", 0).edit().remove("access_token").remove("access_expires").apply();
        if (this.f15973a) {
            c &= GraphRequest.newDeleteObjectRequest(this.f15975c.b(), "me/permissions", new C35311(this)).executeAndWait().getError() == null ? 1 : 0;
        }
        if (c) {
            if (this.f15975c.d()) {
                LoginManager.getInstance().logOut();
            }
            this.f15975c.a(false);
            MagicFacebook.a(this.f15975c, null);
        }
        this.f15974b.m17760a(c);
    }
}
