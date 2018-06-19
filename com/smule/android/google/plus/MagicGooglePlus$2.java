package com.smule.android.google.plus;

import android.os.AsyncTask;

class MagicGooglePlus$2 extends AsyncTask<Object, Object, Boolean> {
    final /* synthetic */ MagicGooglePlus f16000a;

    MagicGooglePlus$2(MagicGooglePlus magicGooglePlus) {
        this.f16000a = magicGooglePlus;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m17772a(objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m17773a((Boolean) obj);
    }

    protected Boolean m17772a(Object[] objArr) {
        return Boolean.valueOf(MagicGooglePlus.b(this.f16000a));
    }

    protected void m17773a(Boolean bool) {
        if (bool.booleanValue()) {
            MagicGooglePlus.c(this.f16000a);
        }
    }
}
