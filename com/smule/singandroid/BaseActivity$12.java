package com.smule.singandroid;

import android.app.Activity;

class BaseActivity$12 implements Runnable {
    final /* synthetic */ Runnable f18420a;
    final /* synthetic */ Runnable f18421b;
    final /* synthetic */ BaseActivity f18422c;

    BaseActivity$12(BaseActivity baseActivity, Runnable runnable, Runnable runnable2) {
        this.f18422c = baseActivity;
        this.f18420a = runnable;
        this.f18421b = runnable2;
    }

    public void run() {
        String string;
        String str = null;
        boolean z = true;
        Activity activity = this.f18422c;
        String string2 = this.f18422c.getString(C1947R.string.performance_upload_network_error_title);
        String string3 = this.f18422c.getString(C1947R.string.performance_upload_network_error_desc);
        boolean z2 = this.f18420a != null;
        if (this.f18421b == null) {
            z = false;
        }
        BaseActivity$AlwaysTryAgainDialog baseActivity$AlwaysTryAgainDialog = new BaseActivity$AlwaysTryAgainDialog(activity, string2, string3, z2, z);
        if (this.f18420a != null) {
            string = this.f18422c.getString(C1947R.string.invite_connect_fail_retry);
        } else {
            string = null;
        }
        if (this.f18421b != null) {
            str = this.f18422c.getString(C1947R.string.delete_performance);
        }
        baseActivity$AlwaysTryAgainDialog.m19806a(string, str);
        if (this.f18420a != null) {
            baseActivity$AlwaysTryAgainDialog.m19804a(this.f18420a);
        }
        if (this.f18421b != null) {
            baseActivity$AlwaysTryAgainDialog.m19809b(this.f18421b);
        }
        baseActivity$AlwaysTryAgainDialog.show();
    }
}
