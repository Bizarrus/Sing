package com.smule.singandroid;

import android.util.Pair;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.MiscUtils;

class BaseActivity$11 implements Runnable {
    final /* synthetic */ int f18415a;
    final /* synthetic */ boolean f18416b;
    final /* synthetic */ Runnable f18417c;
    final /* synthetic */ Runnable f18418d;
    final /* synthetic */ BaseActivity f18419e;

    BaseActivity$11(BaseActivity baseActivity, int i, boolean z, Runnable runnable, Runnable runnable2) {
        this.f18419e = baseActivity;
        this.f18415a = i;
        this.f18416b = z;
        this.f18417c = runnable;
        this.f18418d = runnable2;
    }

    public void run() {
        if (BaseActivity.c(this.f18419e) == null || !BaseActivity.c(this.f18419e).isShowing()) {
            Pair a = MiscUtils.m25880a(this.f18415a, Boolean.valueOf(this.f18416b));
            TextAlertDialog textAlertDialog = new TextAlertDialog(this.f18419e, (String) a.first, (CharSequence) a.second, this.f18417c != null, true);
            textAlertDialog.m19806a(this.f18417c != null ? this.f18419e.getString(C1947R.string.hide_cover) : null, this.f18419e.getString(C1947R.string.core_ok));
            if (this.f18418d != null) {
                textAlertDialog.m19809b(this.f18418d);
            }
            if (this.f18417c != null) {
                textAlertDialog.m19804a(this.f18417c);
            }
            textAlertDialog.show();
        }
    }
}
