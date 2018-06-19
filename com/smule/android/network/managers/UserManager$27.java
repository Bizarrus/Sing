package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import java.util.LinkedList;
import java.util.List;

class UserManager$27 implements Runnable {
    final /* synthetic */ List f17268a;
    final /* synthetic */ UserManager$BlockUsersResponseCallback f17269b;
    final /* synthetic */ UserManager f17270c;

    UserManager$27(UserManager userManager, List list, UserManager$BlockUsersResponseCallback userManager$BlockUsersResponseCallback) {
        this.f17270c = userManager;
        this.f17268a = list;
        this.f17269b = userManager$BlockUsersResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f17269b, this.f17270c.a(new LinkedList(), this.f17268a));
    }
}
