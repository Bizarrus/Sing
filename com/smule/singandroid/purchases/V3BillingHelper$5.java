package com.smule.singandroid.purchases;

import com.smule.android.logging.Log;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.GoogleV3Billing$SkuDetailsListener;
import com.smule.android.purchases.SkuDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class V3BillingHelper$5 implements GoogleV3Billing$SkuDetailsListener {
    final /* synthetic */ List f23814a;
    final /* synthetic */ String f23815b;
    final /* synthetic */ V3BillingHelper f23816c;

    V3BillingHelper$5(V3BillingHelper v3BillingHelper, List list, String str) {
        this.f23816c = v3BillingHelper;
        this.f23814a = list;
        this.f23815b = str;
    }

    public void mo6672a(Map<String, SkuDetails> map) {
        V3BillingHelper.a(this.f23816c);
        List arrayList = new ArrayList();
        for (SubscriptionPack subscriptionPack : this.f23814a) {
            if (map == null || map.get(subscriptionPack.sku) == null) {
                Log.e(V3BillingHelper.g(), "sku: " + subscriptionPack.sku + " did not have a corresponding Google Play sku");
            } else {
                arrayList.add(subscriptionPack);
            }
        }
        V3BillingHelper.a(this.f23816c, arrayList, map);
        if (map != null && map.size() != 0) {
            V3BillingHelper.a(map);
            V3BillingHelper.b(this.f23815b);
            V3BillingHelper.a(Long.valueOf(System.currentTimeMillis()));
        }
    }
}
