package com.smule.android.purchases;

import com.smule.android.logging.Log;
import com.smule.android.purchases.IabHelper.OnIabSetupFinishedListener;

class GoogleV3Billing$1 implements OnIabSetupFinishedListener {
    final /* synthetic */ GoogleV3Billing f17512a;

    GoogleV3Billing$1(GoogleV3Billing googleV3Billing) {
        this.f17512a = googleV3Billing;
    }

    public void mo6281a(IabResult iabResult) {
        if (!iabResult.m18660c()) {
            Log.e(GoogleV3Billing.e(), "Problem setting up In-app Billing: " + iabResult);
        }
        Log.b(GoogleV3Billing.e(), "Setup complete");
        if (GoogleV3Billing.a(this.f17512a) != null && GoogleV3Billing.b(this.f17512a) != null) {
            GoogleV3Billing googleV3Billing = this.f17512a;
            boolean z = iabResult.m18660c() && GoogleV3Billing.a(this.f17512a).m18653b();
            GoogleV3Billing.a(googleV3Billing, z);
            GoogleV3Billing.b(this.f17512a).mo6273a(GoogleV3Billing.c(this.f17512a));
        }
    }
}
