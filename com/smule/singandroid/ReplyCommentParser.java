/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid;

import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;

public class ReplyCommentParser {
    private static final String a = ReplyCommentParser.class.getName();

    static class AccountResponseListener
    implements UserManager {
        String a;
        String b;
        Runnable c;
        Runnable d;

        @Override
        public void handleResponse(UserManager.AccountIconResponse accountIconResponse) {
            block3 : {
                block2 : {
                    if (accountIconResponse.a == null || !accountIconResponse.a.c()) break block2;
                    long l = accountIconResponse.mAccount.accountId;
                    if (!PerformanceManager.a().a(this.b, this.a, accountIconResponse.mAccount.latitude, accountIconResponse.mAccount.longitude, l).c()) break block3;
                    this.c.run();
                }
                return;
            }
            this.d.run();
        }
    }

}

