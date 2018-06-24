/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 */
package com.smule.android.network.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.purchases.BillingListener;
import com.smule.android.purchases.Consts;
import com.smule.android.purchases.GoogleV3Billing;
import com.smule.android.utils.Toaster;

public class SubscriptionsRestoreHelper {
    public static final String a = SubscriptionsRestoreHelper.class.getName();
    private static SubscriptionsRestoreHelper b;

    public SubscriptionsRestoreHelper() {
        this.b();
    }

    public static SubscriptionsRestoreHelper a() {
        if (b == null) {
            b = new SubscriptionsRestoreHelper();
        }
        return b;
    }

    public static void a(Boolean bl, Context context) {
        context.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putBoolean("DO_PURCHASE_RESTORE", bl.booleanValue()).apply();
    }

    private static int b(String string2) {
        return MagicNetwork.d().getApplicationContext().getResources().getIdentifier(string2, "string", MagicNetwork.d().getApplicationContext().getPackageName());
    }

    private void b() {
        if (SubscriptionsRestoreHelper.b("subscription_purchase_error") <= 0) {
            throw new IllegalStateException("No purchase error resource available.");
        }
    }

    public Boolean a(Context context) {
        return context.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getBoolean("DO_PURCHASE_RESTORE", true);
    }

    public void a(Activity activity, boolean bl) {
        Context context = activity.getApplicationContext();
        if (this.a(context).booleanValue() && NetworkUtils.a(context) && !SubscriptionManager.a().b()) {
            Log.b(a, "restorePurchases - attempting purchase restore");
            new RestorePurchase(activity).a();
        }
    }

    private static class RestorePurchase
    implements BillingListener {
        private GoogleV3Billing a = new GoogleV3Billing();
        private Activity b;

        public RestorePurchase(Activity activity) {
            this.b = activity;
        }

        public void a() {
            if (this.a != null) {
                this.a.a(this.b, this);
            }
        }

        @Override
        public void a(Consts.ResponseCode responseCode) {
        }

        @Override
        public void a(Consts.ResponseCode responseCode, String string2) {
        }

        @Override
        public void a(boolean bl) {
            if (bl && this.a != null) {
                this.a.b();
            }
        }

        @Override
        public void a(boolean bl, String string2) {
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void b(Consts.ResponseCode responseCode) {
            Log.b(SubscriptionsRestoreHelper.a, "doRestorePurchases returned with responseCode: " + responseCode.name());
            if (responseCode == Consts.ResponseCode.a) {
                SubscriptionsRestoreHelper.a(false, (Context)this.b);
            } else {
                Log.e(SubscriptionsRestoreHelper.a, "Error requesting purchase restore, will try again later!");
                com.smule.android.utils.Toaster.a((Context)this.b, SubscriptionsRestoreHelper.b("subscription_purchase_error"), Toaster.a);
            }
            if (this.a != null) {
                this.a.c();
                this.a = null;
            }
        }
    }

}

