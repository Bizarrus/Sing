package com.smule.singandroid.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.view.PointerIconCompat;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager.RegistrationResponse;

public class RegisterTask extends AsyncTask<Void, Void, RegistrationResponse> {
    private static final String f24453a = RegisterTask.class.getName();
    private String f24454b;
    private String f24455c;
    private RegisterTaskListener f24456d;

    public interface RegisterTaskListener {
        void mo6917a(RegistrationResponse registrationResponse);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25664a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25665a((RegistrationResponse) obj);
    }

    public RegisterTask(Activity activity, String str, String str2, RegisterTaskListener registerTaskListener) {
        this.f24454b = str;
        this.f24455c = str2;
        this.f24456d = registerTaskListener;
    }

    protected void onPreExecute() {
    }

    protected RegistrationResponse m25664a(Void... voidArr) {
        UserManager a = UserManager.a();
        if (this.f24455c == null || this.f24455c.isEmpty()) {
            return a.b(this.f24454b);
        }
        RegistrationResponse a2 = a.a(this.f24454b, this.f24455c);
        if (a2.a.a != NetworkResponse$Status.OK || a2.a.b != PointerIconCompat.TYPE_TEXT) {
            return a2;
        }
        Log.c(f24453a, "Password set by user was NOT accepted by server. Generating a new one.");
        return a.b(this.f24454b);
    }

    protected void m25665a(RegistrationResponse registrationResponse) {
        if (this.f24456d != null) {
            this.f24456d.mo6917a(registrationResponse);
        }
    }
}
