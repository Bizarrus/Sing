package com.smule.singandroid.task;

import android.os.AsyncTask;
import com.facebook.accountkit.AccountKitLoginResult;
import com.smule.android.network.managers.UserManager;

public class PhoneRegisterTask extends AsyncTask<AccountKitLoginResult, Void, Void> {
    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25661a((AccountKitLoginResult[]) objArr);
    }

    protected Void m25661a(AccountKitLoginResult... accountKitLoginResultArr) {
        AccountKitLoginResult accountKitLoginResult = accountKitLoginResultArr[0];
        UserManager.a().b(false);
        return null;
    }
}
