package com.smule.singandroid.purchases;

import android.view.View;
import android.view.View.OnClickListener;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.SkuDetails;

class V3BillingHelper$3 implements OnClickListener {
    final /* synthetic */ SubscriptionPack f23810a;
    final /* synthetic */ SkuDetails f23811b;
    final /* synthetic */ V3BillingHelper f23812c;

    V3BillingHelper$3(V3BillingHelper v3BillingHelper, SubscriptionPack subscriptionPack, SkuDetails skuDetails) {
        this.f23812c = v3BillingHelper;
        this.f23810a = subscriptionPack;
        this.f23811b = skuDetails;
    }

    public void onClick(View view) {
        V3BillingHelper.a(this.f23812c, this.f23810a, this.f23811b);
    }
}
