package com.smule.android.purchases;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.PurchasesManager;
import com.smule.android.purchases.Consts.ResponseCode;
import com.smule.android.purchases.IabHelper.OnIabPurchaseFinishedListener;
import com.smule.android.utils.NotificationCenter;

class GoogleV3Billing$3 implements OnIabPurchaseFinishedListener {
    final /* synthetic */ GoogleV3Billing f17516a;

    GoogleV3Billing$3(GoogleV3Billing googleV3Billing) {
        this.f17516a = googleV3Billing;
    }

    public void mo6283a(IabResult iabResult, Purchase purchase) {
        Log.b(GoogleV3Billing.e(), "Purchase finished: " + iabResult + ", purchase: " + purchase);
        GoogleV3Billing.a(this.f17516a, iabResult, purchase, false);
        if (iabResult.m18660c()) {
            if (PurchasesManager.m18267a().m18273a(purchase.m18668c()).booleanValue()) {
                GoogleV3Billing.a(this.f17516a).m18646a(purchase, this.f17516a.b);
            } else {
                NotificationCenter.m19011a().m19017b("SUBSCRIPTION_PURCHASED", new Object[0]);
                purchase.m18671f();
                GoogleV3Billing.b(this.f17516a).mo6274a(true, purchase.m18668c());
            }
        } else if (!(GoogleV3Billing.b(this.f17516a) == null || GoogleV3Billing.d(this.f17516a) == null)) {
            GoogleV3Billing.b(this.f17516a).mo6272a(ResponseCode.m18619a(iabResult.m18658a()), GoogleV3Billing.d(this.f17516a));
            GoogleV3Billing.a(this.f17516a, null);
        }
        GoogleV3Billing.b(this.f17516a).mo6271a(ResponseCode.m18619a(iabResult.m18658a()));
    }
}
