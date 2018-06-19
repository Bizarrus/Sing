package com.smule.android.purchases;

import com.smule.android.logging.Log;
import com.smule.android.purchases.IabHelper.OnConsumeFinishedListener;

class GoogleV3Billing$4 implements OnConsumeFinishedListener {
    final /* synthetic */ GoogleV3Billing f17517a;

    GoogleV3Billing$4(GoogleV3Billing googleV3Billing) {
        this.f17517a = googleV3Billing;
    }

    public void mo6284a(Purchase purchase, IabResult iabResult) {
        if (GoogleV3Billing.b(this.f17517a) != null && !GoogleV3Billing.e(this.f17517a).isFinishing()) {
            Log.b(GoogleV3Billing.e(), "Consume finished: " + iabResult + ", purchase: " + purchase);
            GoogleV3Billing.b(this.f17517a).mo6274a(iabResult.m18660c(), purchase.m18668c());
        }
    }
}
