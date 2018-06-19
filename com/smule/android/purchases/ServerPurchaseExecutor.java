package com.smule.android.purchases;

import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.PurchasesManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.SubscriptionsRestoreHelper;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.Consts.PurchaseState;
import com.smule.android.utils.NotificationCenter;

public class ServerPurchaseExecutor {
    public static String f17578a = "ServerPurchaseExecutor.purchase.start";
    public static String f17579b = "ServerPurchaseExecutor.purchase.done";
    private static final String f17580c = ServerPurchaseExecutor.class.getSimpleName();

    public static boolean m18678a(PurchaseState purchaseState, String str, String str2, long j, String str3, String str4) {
        if (purchaseState != PurchaseState.PURCHASED) {
            return false;
        }
        NotificationCenter.m19011a().m19017b(f17578a, "productId", str);
        boolean b = m18679b(purchaseState, str, str2, j, str3, str4);
        NotificationCenter.m19011a().m19017b(f17579b, "productId", str, "result", Boolean.valueOf(b));
        return b;
    }

    private static boolean m18679b(PurchaseState purchaseState, String str, String str2, long j, String str3, String str4) {
        boolean z = true;
        if (purchaseState != PurchaseState.PURCHASED) {
            return false;
        }
        Log.b(f17580c, "purchase receipt received from GP: " + str3);
        PurchasesManager a = PurchasesManager.m18267a();
        if (PurchasesManager.m18267a().m18273a(str).booleanValue()) {
            Log.b(f17580c, "coin pack found, reporting");
            return Boolean.valueOf(a.m18275a(str, str2, j, str3, str4)).booleanValue();
        }
        SubscriptionPack b = SubscriptionManager.a().b(str);
        if (b != null) {
            boolean a2;
            Log.b(f17580c, "subscription pack " + b.sku + " found, reporting");
            a2 = SubscriptionManager.a().a(str, str2, j, str3);
            if (a2) {
                z = false;
            }
            SubscriptionsRestoreHelper.m18453a(Boolean.valueOf(z), MagicNetwork.d().getApplicationContext());
            return a2;
        }
        Log.b(f17580c, "no exisiting sku found for " + str + ", reporting anyways");
        a2 = SubscriptionManager.a().a(str, str2, j, str3);
        if (a2) {
            z = false;
        }
        SubscriptionsRestoreHelper.m18453a(Boolean.valueOf(z), MagicNetwork.d().getApplicationContext());
        return a2;
    }
}
