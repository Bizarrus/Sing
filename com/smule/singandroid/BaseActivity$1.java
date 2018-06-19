package com.smule.singandroid;

import android.support.annotation.NonNull;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import java.util.Set;

class BaseActivity$1 implements ResultCallback {
    final /* synthetic */ ResultCallback f18423a;
    final /* synthetic */ BaseActivity f18424b;

    BaseActivity$1(BaseActivity baseActivity, ResultCallback resultCallback) {
        this.f18424b = baseActivity;
        this.f18423a = resultCallback;
    }

    public void mo6372a(boolean z, @NonNull Set<String> set) {
        BaseActivity.a(this.f18424b, null);
        if (this.f18423a != null) {
            this.f18423a.mo6372a(z, set);
        }
    }
}
