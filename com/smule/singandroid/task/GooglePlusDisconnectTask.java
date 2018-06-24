package com.smule.singandroid.task;

import android.app.Activity;
import android.os.AsyncTask;
import com.smule.android.google.plus.MagicGooglePlus;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;

public class GooglePlusDisconnectTask extends AsyncTask<Void, Void, Boolean> {
    private static final String f24402a = GooglePlusDisconnectTask.class.getName();
    private Activity f24403b;
    private BusyDialog f24404c;
    private MagicGooglePlus f24405d;
    private GooglePlusDisconnectListener f24406e;

    public interface GooglePlusDisconnectListener {
        void m25614a();
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25615a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25616a((Boolean) obj);
    }

    protected void onPreExecute() {
        Log.b(f24402a, "onPreExecute - start disconnect task activity: " + this.f24403b);
        this.f24404c = new BusyDialog(this.f24403b, this.f24403b.getString(C1947R.string.disconnect_from_snp_google_plus));
        this.f24404c.setCancelable(false);
        this.f24404c.m23580a(false);
    }

    protected Boolean m25615a(Void... voidArr) {
        Log.b(f24402a, "doInBackground");
        return Boolean.valueOf(this.f24405d.b());
    }

    protected void m25616a(Boolean bool) {
        Analytics.m17902i();
        if (this.f24404c != null) {
            if (bool.booleanValue()) {
                this.f24404c.dismiss();
            } else {
                this.f24404c.m23576a(2, this.f24403b.getString(C1947R.string.failed_to_disconnect_from_google_plus), true);
            }
        }
        if (this.f24406e != null) {
            this.f24406e.m25614a();
        }
    }
}
