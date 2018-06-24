/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 */
package com.smule.android.task;

import android.os.AsyncTask;
import com.smule.android.network.managers.UserManager;

public class LoginTask
extends AsyncTask<Void, Void, UserManager> {
    private String a;
    private String b;
    private LoginTaskListener c;

    protected /* varargs */ UserManager a(Void ... arrvoid) {
        return com.smule.android.network.managers.UserManager.a().b(this.a, this.b);
    }

    protected void a(UserManager loginResponse) {
        if (this.c != null) {
            this.c.a(loginResponse);
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] arrobject) {
        return this.a((Void[])arrobject);
    }

    protected /* synthetic */ void onPostExecute(Object object) {
        this.a(object);
    }

    protected void onPreExecute() {
    }

    public static interface LoginTaskListener {
        public void a(UserManager var1);
    }

}

