package com.smule.singandroid.purchases;

import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;
import java.util.Observable;
import java.util.Observer;

class V3BillingHelper$1 implements Observer {
    final /* synthetic */ V3BillingHelper f23806a;

    class C48131 implements Runnable {
        final /* synthetic */ V3BillingHelper$1 f23805a;

        C48131(V3BillingHelper$1 v3BillingHelper$1) {
            this.f23805a = v3BillingHelper$1;
        }

        public void run() {
            if (this.f23805a.f23806a.a != null) {
                this.f23805a.f23806a.a.mo6535a();
            }
            if (this.f23805a.f23806a.r != null && !this.f23805a.f23806a.r.isFinishing()) {
                if (this.f23805a.f23806a.w != null) {
                    this.f23805a.f23806a.w.dismiss();
                }
                this.f23805a.f23806a.w = new BusyDialog(this.f23805a.f23806a.r, this.f23805a.f23806a.r.getResources().getString(C1947R.string.subscriptions_sending_subscription));
                this.f23805a.f23806a.w.m23580a(false);
            }
        }
    }

    V3BillingHelper$1(V3BillingHelper v3BillingHelper) {
        this.f23806a = v3BillingHelper;
    }

    public void update(Observable observable, Object obj) {
        this.f23806a.z.post(new C48131(this));
    }
}
