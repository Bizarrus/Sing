package com.smule.singandroid.task;

import android.app.Activity;
import android.os.AsyncTask;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$LoginResponse;

public class LoginTask extends AsyncTask<Void, Void, UserManager$LoginResponse> {
    private static final String f24447a = LoginTask.class.getName();
    private String f24448b;
    private String f24449c;
    private LoginTaskListener f24450d;

    public interface LoginTaskListener {
        void mo6916a(UserManager$LoginResponse userManager$LoginResponse);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25659a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25660a((UserManager$LoginResponse) obj);
    }

    public LoginTask(Activity activity, LoginTaskListener loginTaskListener, String str, String str2) {
        this.f24450d = loginTaskListener;
        this.f24448b = str;
        this.f24449c = str2;
    }

    protected void onPreExecute() {
    }

    protected UserManager$LoginResponse m25659a(Void... voidArr) {
        return UserManager.a().b(this.f24448b, this.f24449c);
    }

    protected void m25660a(UserManager$LoginResponse userManager$LoginResponse) {
        if (this.f24450d != null) {
            this.f24450d.mo6916a(userManager$LoginResponse);
        }
    }
}
