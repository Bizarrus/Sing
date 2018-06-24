package com.smule.singandroid.purchases;

import com.smule.singandroid.C1947R;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

class V3BillingHelper$2 implements Observer {
    final /* synthetic */ V3BillingHelper f23809a;

    V3BillingHelper$2(V3BillingHelper v3BillingHelper) {
        this.f23809a = v3BillingHelper;
    }

    public void update(Observable observable, final Object obj) {
        this.f23809a.z.post(new Runnable(this) {
            final /* synthetic */ V3BillingHelper$2 f23808b;

            public void run() {
                boolean booleanValue = ((Boolean) ((Map) obj).get("result")).booleanValue();
                if (this.f23808b.f23809a.w != null) {
                    if (booleanValue) {
                        this.f23808b.f23809a.w.dismiss();
                    } else {
                        this.f23808b.f23809a.w.m23576a(2, this.f23808b.f23809a.r.getResources().getString(C1947R.string.subscription_purchase_error), true);
                    }
                    this.f23808b.f23809a.w = null;
                }
                if (this.f23808b.f23809a.a != null) {
                    this.f23808b.f23809a.a.mo6536a(booleanValue);
                }
            }
        });
    }
}
