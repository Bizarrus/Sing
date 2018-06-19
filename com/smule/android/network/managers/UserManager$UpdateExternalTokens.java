package com.smule.android.network.managers;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.smule.android.logging.Log;

class UserManager$UpdateExternalTokens implements Runnable {
    final /* synthetic */ UserManager f17352a;
    private final UserManager$LoginType f17353b;

    class C36331 implements AccountKitCallback<Account> {
        final /* synthetic */ UserManager$UpdateExternalTokens f17351a;

        C36331(UserManager$UpdateExternalTokens userManager$UpdateExternalTokens) {
            this.f17351a = userManager$UpdateExternalTokens;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            m18483a((Account) obj);
        }

        public void m18483a(Account account) {
            Log.a(UserManager.R(), "Updated credentials for PHONE login: " + account.getPhoneNumber());
        }

        public void onError(AccountKitError accountKitError) {
            Log.d(UserManager.R(), "Failed to update credentials for PHONE login: " + accountKitError);
        }
    }

    public UserManager$UpdateExternalTokens(UserManager userManager, UserManager$LoginType userManager$LoginType) {
        this.f17352a = userManager;
        this.f17353b = userManager$LoginType;
    }

    public void run() {
        Log.a(UserManager.R(), "Update external token: " + this.f17353b.name());
        switch (UserManager$30.f17278a[this.f17353b.ordinal()]) {
            case 5:
                AccountKit.getCurrentAccount(new C36331(this));
                return;
            default:
                return;
        }
    }
}
