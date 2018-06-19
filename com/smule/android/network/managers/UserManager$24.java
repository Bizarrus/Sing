package com.smule.android.network.managers;

import com.smule.android.network.api.UserAPI$UserProfileRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.models.UserProfile;

class UserManager$24 implements Runnable {
    final /* synthetic */ long f17260a;
    final /* synthetic */ UserManager$UserProfileResponseCallback f17261b;
    final /* synthetic */ UserManager f17262c;

    UserManager$24(UserManager userManager, long j, UserManager$UserProfileResponseCallback userManager$UserProfileResponseCallback) {
        this.f17262c = userManager;
        this.f17260a = j;
        this.f17261b = userManager$UserProfileResponseCallback;
    }

    public void run() {
        UserProfile a = UserProfile.a(NetworkUtils.m18104a(this.f17262c.b.userProfile(new UserAPI$UserProfileRequest().setAccountId(Long.valueOf(this.f17260a)))));
        if (a.a() && a.b()) {
            UserManager.a(this.f17262c, new UserManagerBuilder().m18489a(a.getHandle()).m18493b(UserManager.s(this.f17262c)).m18485a(UserManager.r(this.f17262c)).m18494c(a.getPictureUrl()).m18492b(UserManager.q(this.f17262c)).m18495d(UserManager.p(this.f17262c)).m18496e(UserManager.o(this.f17262c)).m18497f(UserManager.n(this.f17262c)).m18498g(UserManager.m(this.f17262c)).m18499h(UserManager.l(this.f17262c)).m18500i(UserManager.k(this.f17262c)).m18501j(UserManager.j(this.f17262c)).m18486a(UserManager.i(this.f17262c)).m18488a(UserManager.h(this.f17262c)).m18484a(UserManager.g(this.f17262c)).m18504m(UserManager.f(this.f17262c)).m18502k(UserManager.e(this.f17262c)).m18503l(UserManager.d(this.f17262c)).m18487a(UserManager.c(this.f17262c)).m18505n(UserManager.b(this.f17262c)));
        }
        CoreUtil.m18079a(this.f17261b, a);
    }
}
