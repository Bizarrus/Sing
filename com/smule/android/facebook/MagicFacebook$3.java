package com.smule.android.facebook;

import android.os.Handler;
import com.smule.android.logging.Log;

class MagicFacebook$3 implements Runnable {
    final /* synthetic */ MagicFacebook$FacebookUserInfoListener f15969a;
    final /* synthetic */ Handler f15970b;
    final /* synthetic */ MagicFacebook f15971c;

    MagicFacebook$3(MagicFacebook magicFacebook, MagicFacebook$FacebookUserInfoListener magicFacebook$FacebookUserInfoListener, Handler handler) {
        this.f15971c = magicFacebook;
        this.f15969a = magicFacebook$FacebookUserInfoListener;
        this.f15970b = handler;
    }

    public void run() {
        final MagicFacebook$FacebookUserInfo a = this.f15971c.a(null, true);
        if (this.f15969a != null) {
            this.f15970b.post(new Runnable(this) {
                final /* synthetic */ MagicFacebook$3 f15968b;

                public void run() {
                    if (a == null || !a.m17763a()) {
                        Log.e(MagicFacebook.i(), "user info not valid");
                        this.f15968b.f15969a.mo6411b(a);
                        return;
                    }
                    this.f15968b.f15969a.mo6410a(a);
                }
            });
        }
    }
}
