package com.smule.singandroid;

import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import java.util.Observable;
import java.util.Observer;

class BaseActivity$6 implements Observer {
    final /* synthetic */ BaseActivity f18436a;

    BaseActivity$6(BaseActivity baseActivity) {
        this.f18436a = baseActivity;
    }

    public void update(Observable observable, final Object obj) {
        this.f18436a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ BaseActivity$6 f18435b;

            public void run() {
                NetworkResponse networkResponse;
                if (obj instanceof NetworkResponse) {
                    networkResponse = (NetworkResponse) obj;
                } else {
                    networkResponse = null;
                }
                if (this.f18435b.f18436a.f()) {
                    BaseActivity.a(this.f18435b.f18436a, networkResponse);
                    return;
                }
                Log.b(BaseActivity.a, "AUTO_LOGIN_FAILED:" + this.f18435b.f18436a.getClass().getName() + ":setting static");
                BaseActivity.c = true;
                if (networkResponse != null) {
                    BaseActivity.d = networkResponse;
                }
            }
        });
    }
}
