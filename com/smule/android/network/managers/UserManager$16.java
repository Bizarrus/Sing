package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.utils.EmailOptIn;

class UserManager$16 implements Runnable {
    final /* synthetic */ NetworkResponseCallback f17232a;
    final /* synthetic */ String f17233b;
    final /* synthetic */ String f17234c;
    final /* synthetic */ String f17235d;
    final /* synthetic */ EmailOptIn f17236e;
    final /* synthetic */ UserManager f17237f;

    public void run() {
        CoreUtil.m18079a(this.f17232a, this.f17237f.a(this.f17233b, this.f17234c, this.f17235d, this.f17236e));
    }
}
