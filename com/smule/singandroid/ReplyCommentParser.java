package com.smule.singandroid;

import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager$AccountIconResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconResponse;

public class ReplyCommentParser {
    private static final String f19568a = ReplyCommentParser.class.getName();

    static class AccountResponseListener implements UserManager$AccountIconResponseCallback {
        String f19564a;
        String f19565b;
        Runnable f19566c;
        Runnable f19567d;

        public void handleResponse(AccountIconResponse accountIconResponse) {
            if (accountIconResponse.a != null && accountIconResponse.a.c()) {
                if (PerformanceManager.a().a(this.f19565b, this.f19564a, accountIconResponse.mAccount.latitude, accountIconResponse.mAccount.longitude, Long.valueOf(accountIconResponse.mAccount.accountId)).c()) {
                    this.f19566c.run();
                } else {
                    this.f19567d.run();
                }
            }
        }
    }
}
