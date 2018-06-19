package com.smule.singandroid;

import com.smule.android.logging.Log;
import java.util.Observable;
import java.util.Observer;

class BaseActivity$5 implements Observer {
    final /* synthetic */ BaseActivity f18433a;

    BaseActivity$5(BaseActivity baseActivity) {
        this.f18433a = baseActivity;
    }

    public void update(Observable observable, Object obj) {
        if (obj == null) {
            Log.e(BaseActivity.a, "No object dispatched with UPGRADE_REQUIRED_EVENT notification");
            return;
        }
        String str = (String) obj;
        if (str.isEmpty()) {
            Log.e(BaseActivity.a, "Malformed string sent with UPGRADE_REQUIRED_EVENT notification");
            return;
        }
        Log.c(BaseActivity.a, "Force upgrade notification received! Url: " + str);
        BaseActivity.b(str);
        BaseActivity.a(this.f18433a, str);
    }
}
