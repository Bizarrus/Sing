package com.smule.android.purchases;

import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.purchases.IabHelper.QueryInventoryFinishedListener;
import java.util.Map.Entry;

class GoogleV3Billing$2 implements QueryInventoryFinishedListener {
    final /* synthetic */ GoogleV3Billing$SkuDetailsListener f17513a;
    final /* synthetic */ boolean f17514b;
    final /* synthetic */ GoogleV3Billing f17515c;

    GoogleV3Billing$2(GoogleV3Billing googleV3Billing, GoogleV3Billing$SkuDetailsListener googleV3Billing$SkuDetailsListener, boolean z) {
        this.f17515c = googleV3Billing;
        this.f17513a = googleV3Billing$SkuDetailsListener;
        this.f17514b = z;
    }

    public void mo6282a(IabResult iabResult, Inventory inventory) {
        GoogleV3Billing.a(this.f17515c, iabResult, inventory);
        if (iabResult.m18661d()) {
            this.f17513a.mo6672a(null);
            if (this.f17514b) {
                this.f17515c.c.mo6282a(iabResult, inventory);
                return;
            }
            return;
        }
        for (Entry entry : inventory.f17566a.entrySet()) {
            SkuDetails skuDetails = (SkuDetails) entry.getValue();
            SubscriptionManager.a().a((String) entry.getKey(), skuDetails.f17585e.intValue(), skuDetails.f17586f);
        }
        this.f17513a.mo6672a(inventory.f17566a);
        if (this.f17514b) {
            this.f17515c.c.mo6282a(iabResult, inventory);
        }
    }
}
