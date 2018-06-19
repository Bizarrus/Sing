package com.smule.singandroid;

import android.content.Intent;
import com.smule.android.logging.Log;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyDialog.BusyDialogListener;
import com.smule.singandroid.utils.NavigationUtils;

class BaseActivity$9 implements Runnable {
    final /* synthetic */ BaseActivity f18444a;

    class C37731 implements Runnable {
        final /* synthetic */ BaseActivity$9 f18441a;

        C37731(BaseActivity$9 baseActivity$9) {
            this.f18441a = baseActivity$9;
        }

        public void run() {
            BaseActivity.b(false);
            Log.b(BaseActivity.a, "AfterLoginIntent is : " + BaseActivity.p());
            if (BaseActivity.p() != null) {
                Intent p = BaseActivity.p();
                BaseActivity.a(null);
                this.f18441a.f18444a.startActivity(p);
            }
        }
    }

    class C37752 implements Runnable {
        final /* synthetic */ BaseActivity$9 f18443a;

        class C37741 implements BusyDialogListener {
            final /* synthetic */ C37752 f18442a;

            C37741(C37752 c37752) {
                this.f18442a = c37752;
            }

            public void mo6373a() {
                BaseActivity.b(false);
                BaseActivity.a(this.f18442a.f18443a.f18444a, null);
            }
        }

        C37752(BaseActivity$9 baseActivity$9) {
            this.f18443a = baseActivity$9;
        }

        public void run() {
            Log.b(BaseActivity.a, "initiateRegistration: device lookup failed");
            BusyDialog busyDialog = new BusyDialog(this.f18443a.f18444a, this.f18443a.f18444a.getString(C1947R.string.login_checking));
            busyDialog.m23579a(new C37741(this));
            busyDialog.m23576a(2, this.f18443a.f18444a.getString(C1947R.string.login_cannot_connect_to_smule), true);
            busyDialog.show();
        }
    }

    BaseActivity$9(BaseActivity baseActivity) {
        this.f18444a = baseActivity;
    }

    public void run() {
        Log.c(BaseActivity.a, "Calling initiateRegistration");
        NavigationUtils.m25908a(this.f18444a, new C37731(this), new C37752(this));
    }
}
