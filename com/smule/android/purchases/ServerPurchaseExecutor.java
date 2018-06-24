/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 */
package com.smule.android.purchases;

import android.content.Context;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.PurchasesManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.SubscriptionsRestoreHelper;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.Consts;
import com.smule.android.utils.NotificationCenter;

public class ServerPurchaseExecutor {
    public static String a;
    public static String b;
    private static final String c;

    static {
        c = ServerPurchaseExecutor.class.getSimpleName();
        a = "ServerPurchaseExecutor.purchase.start";
        b = "ServerPurchaseExecutor.purchase.done";
    }

    public static boolean a(Consts.PurchaseState purchaseState, String string2, String string3, long l, String string4, String string5) {
        if (purchaseState != Consts.PurchaseState.a) {
            return false;
        }
        NotificationCenter.a().b(a, "productId", string2);
        boolean bl = ServerPurchaseExecutor.b(purchaseState, string2, string3, l, string4, string5);
        NotificationCenter.a().b(b, "productId", string2, "result", bl);
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean b(Consts.PurchaseState object, String string2, String string3, long l, String string4, String string5) {
        boolean bl = true;
        boolean bl2 = true;
        boolean bl3 = false;
        if (object != Consts.PurchaseState.a) return bl3;
        Log.b(c, "purchase receipt received from GP: " + string4);
        object = PurchasesManager.a();
        if (PurchasesManager.a().a(string2).booleanValue()) {
            Log.b(c, "coin pack found, reporting");
            return object.a(string2, string3, l, string4, string5);
        }
        object = SubscriptionManager.a().b(string2);
        if (object != null) {
            Log.b(c, "subscription pack " + object.sku + " found, reporting");
            bl = SubscriptionManager.a().a(string2, string3, l, string4);
            bl3 = !bl ? bl2 : false;
            SubscriptionsRestoreHelper.a(bl3, MagicNetwork.d().getApplicationContext());
            return bl;
        }
        Log.b(c, "no exisiting sku found for " + string2 + ", reporting anyways");
        bl2 = SubscriptionManager.a().a(string2, string3, l, string4);
        bl3 = !bl2 ? bl : false;
        SubscriptionsRestoreHelper.a(bl3, MagicNetwork.d().getApplicationContext());
        return bl2;
    }
}

