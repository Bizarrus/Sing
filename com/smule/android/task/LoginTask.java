package com.smule.android.task;

import android.os.AsyncTask;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$LoginResponse;

public class LoginTask extends AsyncTask<Void, Void, UserManager$LoginResponse> {
    private String f17656a;
    private String f17657b;
    private LoginTaskListener f17658c;

    public interface LoginTaskListener {
        void m18825a(UserManager$LoginResponse userManager$LoginResponse);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m18826a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m18827a((UserManager$LoginResponse) obj);
    }

    protected void onPreExecute() {
    }

    protected UserManager$LoginResponse m18826a(Void... voidArr) {
        return UserManager.a().b(this.f17656a, this.f17657b);
    }

    protected void m18827a(UserManager$LoginResponse userManager$LoginResponse) {
        if (this.f17658c != null) {
            this.f17658c.m18825a(userManager$LoginResponse);
        }
    }
}
