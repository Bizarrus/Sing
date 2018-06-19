package com.smule.android.network.managers;

import android.app.Activity;
import android.content.Context;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.purchases.BillingListener;
import com.smule.android.purchases.Consts.ResponseCode;
import com.smule.android.purchases.GoogleV3Billing;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;

public class SubscriptionsRestoreHelper {
    public static final String f17176a = SubscriptionsRestoreHelper.class.getName();
    private static SubscriptionsRestoreHelper f17177b;

    private static class RestorePurchase implements BillingListener {
        private GoogleV3Billing f17174a = new GoogleV3Billing();
        private Activity f17175b;

        public RestorePurchase(Activity activity) {
            this.f17175b = activity;
        }

        public void m18445a() {
            if (this.f17174a != null) {
                this.f17174a.a(this.f17175b, this);
            }
        }

        public void mo6274a(boolean z, String str) {
        }

        public void mo6271a(ResponseCode responseCode) {
        }

        public void mo6273a(boolean z) {
            if (z && this.f17174a != null) {
                this.f17174a.b();
            }
        }

        public void mo6275b(ResponseCode responseCode) {
            Log.b(SubscriptionsRestoreHelper.f17176a, "doRestorePurchases returned with responseCode: " + responseCode.name());
            if (responseCode == ResponseCode.RESULT_OK) {
                SubscriptionsRestoreHelper.m18453a(Boolean.valueOf(false), this.f17175b);
            } else {
                Log.e(SubscriptionsRestoreHelper.f17176a, "Error requesting purchase restore, will try again later!");
                Toaster.a(this.f17175b, SubscriptionsRestoreHelper.m18454b("subscription_purchase_error"), Toaster$Duration.SHORT);
            }
            if (this.f17174a != null) {
                this.f17174a.c();
                this.f17174a = null;
            }
        }

        public void mo6272a(ResponseCode responseCode, String str) {
        }
    }

    public static SubscriptionsRestoreHelper m18452a() {
        if (f17177b == null) {
            f17177b = new SubscriptionsRestoreHelper();
        }
        return f17177b;
    }

    public SubscriptionsRestoreHelper() {
        m18455b();
    }

    public void m18457a(Activity activity, boolean z) {
        Context applicationContext = activity.getApplicationContext();
        if (m18456a(applicationContext).booleanValue() && NetworkUtils.m18113a(applicationContext) && !SubscriptionManager.a().b()) {
            Log.b(f17176a, "restorePurchases - attempting purchase restore");
            new RestorePurchase(activity).m18445a();
        }
    }

    public Boolean m18456a(Context context) {
        return Boolean.valueOf(context.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getBoolean("DO_PURCHASE_RESTORE", true));
    }

    public static void m18453a(Boolean bool, Context context) {
        context.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putBoolean("DO_PURCHASE_RESTORE", bool.booleanValue()).apply();
    }

    private static int m18454b(String str) {
        return MagicNetwork.d().getApplicationContext().getResources().getIdentifier(str, "string", MagicNetwork.d().getApplicationContext().getPackageName());
    }

    private void m18455b() {
        if (m18454b("subscription_purchase_error") <= 0) {
            throw new IllegalStateException("No purchase error resource available.");
        }
    }
}
