package com.smule.singandroid;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager$AccountIconResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconResponse;
import com.smule.android.network.models.AccountIcon;

class MasterActivity$11 implements UserManager$AccountIconResponseCallback {
    final /* synthetic */ long f18844a;
    final /* synthetic */ MasterActivity f18845b;

    MasterActivity$11(MasterActivity masterActivity, long j) {
        this.f18845b = masterActivity;
        this.f18844a = j;
    }

    public void handleResponse(AccountIconResponse accountIconResponse) {
        if (accountIconResponse == null || !accountIconResponse.a()) {
            Log.d(MasterActivity.g, "showUserProfile for id " + this.f18844a + " failed; " + (accountIconResponse == null ? "NULL reply" : "error in call"));
            return;
        }
        final AccountIcon accountIcon = accountIconResponse.mAccount;
        this.f18845b.a(new Runnable(this) {
            final /* synthetic */ MasterActivity$11 f18843b;

            public void run() {
                BaseFragment a = ProfileFragment.m21036a(accountIcon);
                this.f18843b.f18845b.a(a, a.mo6514z());
            }
        });
    }
}
