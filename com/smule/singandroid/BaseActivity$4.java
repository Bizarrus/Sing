package com.smule.singandroid;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Observable;
import java.util.Observer;

class BaseActivity$4 implements Observer {
    final /* synthetic */ BaseActivity f18432a;

    class C37701 implements Runnable {
        final /* synthetic */ BaseActivity$4 f18431a;

        class C37691 implements OnClickListener {
            final /* synthetic */ C37701 f18430a;

            C37691(C37701 c37701) {
                this.f18430a = c37701;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                BaseActivity.b(this.f18430a.f18431a.f18432a).dismiss();
                BaseActivity.a(this.f18430a.f18431a.f18432a, false);
                this.f18430a.f18431a.f18432a.l();
            }
        }

        C37701(BaseActivity$4 baseActivity$4) {
            this.f18431a = baseActivity$4;
        }

        public void run() {
            if (BaseActivity.b(this.f18431a.f18432a) == null) {
                Builder builder = new Builder(this.f18431a.f18432a);
                builder.setMessage(C1947R.string.network_service);
                builder.setCancelable(false);
                builder.setPositiveButton(C1947R.string.okay, new C37691(this));
                BaseActivity.a(this.f18431a.f18432a, builder.create());
            }
            if (!BaseActivity.b(this.f18431a.f18432a).isShowing()) {
                BaseActivity.b(this.f18431a.f18432a).show();
                SingAnalytics.m26152p();
                BaseActivity.a(this.f18431a.f18432a, true);
            }
        }
    }

    BaseActivity$4(BaseActivity baseActivity) {
        this.f18432a = baseActivity;
    }

    public void update(Observable observable, Object obj) {
        this.f18432a.runOnUiThread(new C37701(this));
    }
}
