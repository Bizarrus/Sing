package com.smule.singandroid.purchases;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.BillingListener;
import com.smule.android.purchases.Consts.ResponseCode;
import com.smule.android.purchases.SkuDetails;

class V3BillingHelper$SubscriptionBillingListener implements BillingListener {
    boolean f23818a;
    final /* synthetic */ V3BillingHelper f23819b;

    private V3BillingHelper$SubscriptionBillingListener(V3BillingHelper v3BillingHelper) {
        this.f23819b = v3BillingHelper;
        this.f23818a = false;
    }

    public void mo6272a(ResponseCode responseCode, String str) {
    }

    public void mo6273a(boolean z) {
        if (!this.f23818a) {
            this.f23818a = true;
            if (z) {
                this.f23819b.d();
                this.f23819b.A = true;
                return;
            }
            Log.d(V3BillingHelper.g(), "Subscription purchasing not supported.");
            if (this.f23819b.t != null) {
                this.f23819b.t.setVisibility(8);
                if (this.f23819b.s != null) {
                    this.f23819b.s.mo6537a();
                }
            }
        }
    }

    public void mo6271a(ResponseCode responseCode) {
        if (responseCode == ResponseCode.RESULT_OK) {
            Log.c(V3BillingHelper.g(), "purchase request was successfully sent to server");
        } else if (responseCode == ResponseCode.RESULT_USER_CANCELED) {
            Log.c(V3BillingHelper.g(), "user canceled purchase");
        } else {
            Log.c(V3BillingHelper.g(), "purchase request failed!");
        }
        V3BillingHelper.a(this.f23819b);
    }

    public void mo6274a(boolean z, String str) {
        if (z) {
            Log.b(V3BillingHelper.g(), "purchase state success for " + str);
            Log.b(V3BillingHelper.g(), "purchaseDidSucceed called; finishing this activity to return to previous activity");
            return;
        }
        Log.e(V3BillingHelper.g(), "unexpected purchase state for " + str);
    }

    public void mo6275b(ResponseCode responseCode) {
        Log.b(V3BillingHelper.g(), "doRestorePurchases returned with responseCode: " + responseCode.name());
        V3BillingHelper.a(this.f23819b);
        if (V3BillingHelper.b(this.f23819b) != null) {
            for (SubscriptionPack subscriptionPack : SubscriptionManager.a().e()) {
                SkuDetails skuDetails = V3BillingHelper.h() == null ? null : (SkuDetails) V3BillingHelper.h().get(subscriptionPack.sku);
                if ((V3BillingHelper.b(this.f23819b).equals("weekly") && subscriptionPack.period.equals("1w")) || ((V3BillingHelper.b(this.f23819b).equals("monthly") && subscriptionPack.period.equals("1m")) || (V3BillingHelper.b(this.f23819b).equals("yearly") && subscriptionPack.period.equals("1y")))) {
                    V3BillingHelper.a(this.f23819b, subscriptionPack, skuDetails);
                }
            }
        }
    }
}
