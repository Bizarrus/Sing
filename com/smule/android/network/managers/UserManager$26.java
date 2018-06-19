package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;
import java.util.LinkedList;
import java.util.List;

class UserManager$26 implements Runnable {
    final /* synthetic */ List f17265a;
    final /* synthetic */ UserManager$BlockUsersResponseCallback f17266b;
    final /* synthetic */ UserManager f17267c;

    UserManager$26(UserManager userManager, List list, UserManager$BlockUsersResponseCallback userManager$BlockUsersResponseCallback) {
        this.f17267c = userManager;
        this.f17265a = list;
        this.f17266b = userManager$BlockUsersResponseCallback;
    }

    public void run() {
        CoreUtil.m18079a(this.f17266b, this.f17267c.a(this.f17265a, new LinkedList()));
    }
}
