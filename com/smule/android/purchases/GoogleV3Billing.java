/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.text.TextUtils
 */
package com.smule.android.purchases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.BillingListener;
import com.smule.android.purchases.GoogleV3Billing;
import com.smule.android.purchases.IabHelper;
import com.smule.android.purchases.IabResult;
import com.smule.android.purchases.Inventory;
import com.smule.android.purchases.Purchase;
import com.smule.android.purchases.SkuDetails;
import com.smule.android.utils.NotificationCenter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class GoogleV3Billing {
    private static final String d = GoogleV3Billing.class.getName();
    private static String e = null;
    IabHelper.OnIabPurchaseFinishedListener a;
    IabHelper.OnConsumeFinishedListener b;
    IabHelper.QueryInventoryFinishedListener c;
    private BillingListener f;
    private IabHelper g = null;
    private boolean h = false;
    private Activity i;
    private String j;
    private boolean k = false;
    private boolean l = false;

    public GoogleV3Billing() {
        this.a = new IabHelper.OnIabPurchaseFinishedListener(this){
            final /* synthetic */ GoogleV3Billing a;
            {
                this.a = googleV3Billing;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void a(IabResult iabResult, Purchase purchase) {
                Log.b(GoogleV3Billing.e(), "Purchase finished: " + iabResult + ", purchase: " + purchase);
                GoogleV3Billing.a(this.a, iabResult, purchase, false);
                if (iabResult.c()) {
                    if (com.smule.android.network.managers.PurchasesManager.a().a(purchase.c()).booleanValue()) {
                        GoogleV3Billing.a(this.a).a(purchase, this.a.b);
                    } else {
                        NotificationCenter.a().b("SUBSCRIPTION_PURCHASED", new Object[0]);
                        purchase.f();
                        GoogleV3Billing.b(this.a).a(true, purchase.c());
                    }
                } else if (GoogleV3Billing.b(this.a) != null && GoogleV3Billing.d(this.a) != null) {
                    GoogleV3Billing.b(this.a).a(com.smule.android.purchases.Consts$ResponseCode.a(iabResult.a()), GoogleV3Billing.d(this.a));
                    GoogleV3Billing.a(this.a, null);
                }
                GoogleV3Billing.b(this.a).a(com.smule.android.purchases.Consts$ResponseCode.a(iabResult.a()));
            }
        };
        this.b = new IabHelper.OnConsumeFinishedListener(this){
            final /* synthetic */ GoogleV3Billing a;
            {
                this.a = googleV3Billing;
            }

            public void a(Purchase purchase, IabResult iabResult) {
                if (GoogleV3Billing.b(this.a) != null && !GoogleV3Billing.e(this.a).isFinishing()) {
                    Log.b(GoogleV3Billing.e(), "Consume finished: " + iabResult + ", purchase: " + purchase);
                    GoogleV3Billing.b(this.a).a(iabResult.c(), purchase.c());
                }
            }
        };
        this.c = new IabHelper.QueryInventoryFinishedListener(this){
            final /* synthetic */ GoogleV3Billing a;
            {
                this.a = googleV3Billing;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void a(IabResult iabResult, Inventory inventory) {
                if (iabResult.c()) {
                    List<String> list = inventory.b("subs");
                    if (list.size() == 0) {
                        GoogleV3Billing.a(this.a, (Boolean)false);
                    } else {
                        com.smule.android.utils.SimpleBarrier simpleBarrier = new com.smule.android.utils.SimpleBarrier(list.size(), new java.lang.Runnable(this){
                            final /* synthetic */  a;
                            {
                                this.a = var1_1;
                            }

                            public void run() {
                                GoogleV3Billing.a(this.a.a, (Boolean)true);
                            }
                        });
                        list = list.iterator();
                        while (list.hasNext()) {
                            com.smule.android.network.core.MagicNetwork.a(new java.lang.Runnable(this, inventory.a((String)list.next()), iabResult, simpleBarrier){
                                final /* synthetic */ Purchase a;
                                final /* synthetic */ IabResult b;
                                final /* synthetic */ com.smule.android.utils.SimpleBarrier c;
                                final /* synthetic */  d;
                                {
                                    this.d = var1_1;
                                    this.a = purchase;
                                    this.b = iabResult;
                                    this.c = simpleBarrier;
                                }

                                public void run() {
                                    try {
                                        if (com.smule.android.purchases.ServerPurchaseExecutor.a(com.smule.android.purchases.Consts$PurchaseState.a(this.a.e()), this.a.c(), this.a.b(), this.a.d(), this.a.h(), this.a.i())) {
                                            GoogleV3Billing.a(this.d.a, this.b, this.a, true);
                                        }
                                        return;
                                    }
                                    finally {
                                        this.c.a();
                                    }
                                }
                            });
                        }
                    }
                }
                if (GoogleV3Billing.b(this.a) != null) {
                    GoogleV3Billing.b(this.a).b(com.smule.android.purchases.Consts$ResponseCode.a(iabResult.a()));
                }
            }
        };
    }

    static /* synthetic */ IabHelper a(GoogleV3Billing googleV3Billing) {
        return googleV3Billing.g;
    }

    static /* synthetic */ String a(GoogleV3Billing googleV3Billing, String string2) {
        googleV3Billing.j = string2;
        return string2;
    }

    static /* synthetic */ void a(GoogleV3Billing googleV3Billing, IabResult iabResult, Inventory inventory) {
        googleV3Billing.a(iabResult, inventory);
    }

    static /* synthetic */ void a(GoogleV3Billing googleV3Billing, IabResult iabResult, Purchase purchase, boolean bl) {
        googleV3Billing.a(iabResult, purchase, bl);
    }

    static /* synthetic */ void a(GoogleV3Billing googleV3Billing, Boolean bl) {
        googleV3Billing.a(bl);
    }

    private void a(IabResult object, Inventory inventory) {
        int n = object.a();
        String string2 = object.b();
        object = null;
        if (inventory != null) {
            object = TextUtils.join((CharSequence)",", inventory.a.keySet());
        }
        com.smule.android.logging.Analytics.a((String)object, n, string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(IabResult object, Purchase object2, boolean bl) {
        String string2;
        Object object3;
        Object object4;
        Integer n = null;
        int n2 = object.a();
        object = object.b();
        String string3 = bl ? Analytics.a.a() : Analytics.b.a();
        Object object5 = object;
        if (object != null) {
            object5 = object.substring(0, Math.min(object.length(), 127));
        }
        if (object2 != null) {
            StringBuilder stringBuilder;
            object3 = object2.c();
            string2 = object2.b();
            object4 = SubscriptionManager.a().b(object2.c());
            if (object4 != null) {
                stringBuilder = new StringBuilder().append(object4.period);
                object = object4.trial ? "_trial" : "";
                object = stringBuilder.append((String)object).toString();
                object4 = object4.b + (float)object4.a / 1000000.0f;
            } else {
                object4 = null;
                object = null;
            }
            if (n2 == 0) {
                stringBuilder = Long.valueOf(object2.d());
                n = object2.e();
                object2 = object3;
                object3 = stringBuilder;
            } else {
                stringBuilder = null;
                object2 = object3;
                object3 = stringBuilder;
            }
        } else {
            object3 = null;
            object4 = null;
            object = null;
            string2 = null;
            object2 = null;
        }
        com.smule.android.logging.Analytics.a((String)object2, string2, (String)object, (String)object4, n2, (String)object5, string3, (Long)object3, n, this.g());
    }

    private void a(Boolean bl) {
        NotificationCenter.a().a("AutoRap.PURCHASES_RESTORED", (Object)bl);
    }

    public static void a(String string2) {
        e = string2;
    }

    private void a(List<String> list,  skuDetailsListener, boolean bl) {
        this.g.a(true, list, new IabHelper.QueryInventoryFinishedListener(this, skuDetailsListener, bl){
            final /* synthetic */  a;
            final /* synthetic */ boolean b;
            final /* synthetic */ GoogleV3Billing c;
            {
                this.c = googleV3Billing;
                this.a = skuDetailsListener;
                this.b = bl;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void a(IabResult iabResult, Inventory inventory) {
                GoogleV3Billing.a(this.c, iabResult, inventory);
                if (iabResult.d()) {
                    this.a.a(null);
                    if (!this.b) return;
                    {
                        this.c.c.a(iabResult, inventory);
                        return;
                    }
                } else {
                    for (java.util.Map$Entry<String, SkuDetails> entry : inventory.a.entrySet()) {
                        SkuDetails skuDetails = entry.getValue();
                        SubscriptionManager.a().a(entry.getKey(), skuDetails.e, skuDetails.f);
                    }
                    this.a.a(inventory.a);
                    if (!this.b) return;
                    {
                        this.c.c.a(iabResult, inventory);
                        return;
                    }
                }
            }
        });
    }

    static /* synthetic */ boolean a(GoogleV3Billing googleV3Billing, boolean bl) {
        googleV3Billing.h = bl;
        return bl;
    }

    static /* synthetic */ BillingListener b(GoogleV3Billing googleV3Billing) {
        return googleV3Billing.f;
    }

    static /* synthetic */ boolean c(GoogleV3Billing googleV3Billing) {
        return googleV3Billing.h;
    }

    static /* synthetic */ String d(GoogleV3Billing googleV3Billing) {
        return googleV3Billing.j;
    }

    static /* synthetic */ Activity e(GoogleV3Billing googleV3Billing) {
        return googleV3Billing.i;
    }

    static /* synthetic */ String e() {
        return d;
    }

    private DecimalFormat f() {
        Cloneable cloneable = new DecimalFormatSymbols(Locale.US);
        cloneable.setDecimalSeparator('.');
        cloneable = new DecimalFormat("#.###", (DecimalFormatSymbols)cloneable);
        cloneable.setGroupingUsed(false);
        cloneable.setRoundingMode(RoundingMode.CEILING);
        return cloneable;
    }

    private String g() {
        long l = -1;
        long l2 = System.currentTimeMillis();
        long l3 = l;
        if (this.i != null) {
            l3 = l;
            if (!this.i.isFinishing()) {
                l3 = this.i.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getLong("LAST_PURCHASE_FLOW_START_MS", -1);
            }
        }
        if (l3 < 0) {
            return null;
        }
        return this.f().format((double)(l2 - l3) / 1000.0);
    }

    public void a(Activity activity, BillingListener billingListener) {
        if (e == null) {
            throw new NullPointerException("Public key is not set");
        }
        this.f = billingListener;
        this.i = activity;
        this.g = new IabHelper((Context)activity, e);
        this.g.a(true, IabHelper.class.getName());
        this.g.a(new IabHelper.OnIabSetupFinishedListener(this){
            final /* synthetic */ GoogleV3Billing a;
            {
                this.a = googleV3Billing;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void a(IabResult iabResult) {
                if (!iabResult.c()) {
                    Log.e(GoogleV3Billing.e(), "Problem setting up In-app Billing: " + iabResult);
                }
                Log.b(GoogleV3Billing.e(), "Setup complete");
                if (GoogleV3Billing.a(this.a) != null && GoogleV3Billing.b(this.a) != null) {
                    GoogleV3Billing googleV3Billing = this.a;
                    boolean bl = iabResult.c() && GoogleV3Billing.a(this.a).b();
                    GoogleV3Billing.a(googleV3Billing, bl);
                    GoogleV3Billing.b(this.a).a(GoogleV3Billing.c(this.a));
                }
            }
        });
        this.k = true;
    }

    public void a(List<String> list,  skuDetailsListener) {
        this.a(list, skuDetailsListener, false);
    }

    public boolean a() {
        if (this.g == null) {
            return true;
        }
        return false;
    }

    public boolean a(int n, int n2, Intent intent) {
        if (this.g == null) {
            Log.d(d, "handleActivityResult - mHelper is null; returning false!");
            return false;
        }
        return this.g.a(n, n2, intent);
    }

    public void b() {
        if (!this.a()) {
            this.g.a(this.c);
        }
    }

    public void b(String string2) {
        if (this.i == null) {
            Log.e(d, "Activity is null. Was created: " + this.k + ". Was destroyed: " + this.l);
            throw new NullPointerException("Activity is null, probably create() isn't called");
        }
        this.j = string2;
        this.d();
        String string3 = "{\"playerId\":" + UserManager.a().g() + "}";
        this.g.a(this.i, string2, 1001, this.a, string3);
    }

    public void b(List<String> list,  skuDetailsListener) {
        this.a(list, skuDetailsListener, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void c() {
        try {
            if (this.g != null) {
                this.g.a();
            }
        }
        catch (Exception exception) {
            Log.d(d, "Failed to dispose IabHelper", exception);
        }
        this.g = null;
        this.i = null;
        this.l = true;
    }

    public void d() {
        if (this.i != null && !this.i.isFinishing()) {
            this.i.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putLong("LAST_PURCHASE_FLOW_START_MS", System.currentTimeMillis()).apply();
        }
    }

}

