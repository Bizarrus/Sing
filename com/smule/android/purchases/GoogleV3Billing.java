package com.smule.android.purchases;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.media.TransportMediator;
import android.text.TextUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.PurchaseType;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.IabHelper.OnConsumeFinishedListener;
import com.smule.android.purchases.IabHelper.OnIabPurchaseFinishedListener;
import com.smule.android.purchases.IabHelper.QueryInventoryFinishedListener;
import com.smule.android.utils.NotificationCenter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class GoogleV3Billing {
    private static final String f6913d = GoogleV3Billing.class.getName();
    private static String f6914e = null;
    OnIabPurchaseFinishedListener f6915a = new 3(this);
    OnConsumeFinishedListener f6916b = new 4(this);
    QueryInventoryFinishedListener f6917c = new 5(this);
    private BillingListener f6918f;
    private IabHelper f6919g = null;
    private boolean f6920h = false;
    private Activity f6921i;
    private String f6922j;
    private boolean f6923k = false;
    private boolean f6924l = false;

    private DecimalFormat m8332f() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.###", decimalFormatSymbols);
        decimalFormat.setGroupingUsed(false);
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        return decimalFormat;
    }

    public static void m8324a(String str) {
        f6914e = str;
    }

    public boolean m8336a() {
        return this.f6919g == null;
    }

    public void m8339b(String str) {
        if (this.f6921i == null) {
            Log.m7776e(f6913d, "Activity is null. Was created: " + this.f6923k + ". Was destroyed: " + this.f6924l);
            throw new NullPointerException("Activity is null, probably create() isn't called");
        }
        this.f6922j = str;
        m8342d();
        String str2 = str;
        this.f6919g.a(this.f6921i, str2, 1001, this.f6915a, "{\"playerId\":" + UserManager.m8111a().m8205g() + "}");
    }

    public void m8338b() {
        if (!m8336a()) {
            this.f6919g.a(this.f6917c);
        }
    }

    public void m8334a(Activity activity, BillingListener billingListener) {
        if (f6914e == null) {
            throw new NullPointerException("Public key is not set");
        }
        this.f6918f = billingListener;
        this.f6921i = activity;
        this.f6919g = new IabHelper(activity, f6914e);
        this.f6919g.a(true, IabHelper.class.getName());
        this.f6919g.a(new 1(this));
        this.f6923k = true;
    }

    public void m8341c() {
        try {
            if (this.f6919g != null) {
                this.f6919g.a();
            }
        } catch (Throwable e) {
            Log.m7775d(f6913d, "Failed to dispose IabHelper", e);
        }
        this.f6919g = null;
        this.f6921i = null;
        this.f6924l = true;
    }

    public void m8335a(List<String> list, SkuDetailsListener skuDetailsListener) {
        m8325a((List) list, skuDetailsListener, false);
    }

    public void m8340b(List<String> list, SkuDetailsListener skuDetailsListener) {
        m8325a((List) list, skuDetailsListener, true);
    }

    private void m8325a(List<String> list, SkuDetailsListener skuDetailsListener, boolean z) {
        this.f6919g.a(true, list, new 2(this, skuDetailsListener, z));
    }

    public boolean m8337a(int i, int i2, Intent intent) {
        if (this.f6919g != null) {
            return this.f6919g.a(i, i2, intent);
        }
        Log.m7774d(f6913d, "handleActivityResult - mHelper is null; returning false!");
        return false;
    }

    private void m8323a(Boolean bool) {
        NotificationCenter.a().a("AutoRap.PURCHASES_RESTORED", bool);
    }

    public void m8342d() {
        if (this.f6921i != null && !this.f6921i.isFinishing()) {
            this.f6921i.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).edit().putLong("LAST_PURCHASE_FLOW_START_MS", System.currentTimeMillis()).apply();
        }
    }

    private String m8333g() {
        long j = -1;
        long currentTimeMillis = System.currentTimeMillis();
        if (!(this.f6921i == null || this.f6921i.isFinishing())) {
            j = this.f6921i.getSharedPreferences("SUBSCRIPTION_PREFERENCES", 0).getLong("LAST_PURCHASE_FLOW_START_MS", -1);
        }
        if (j < 0) {
            return null;
        }
        return m8332f().format(((double) (currentTimeMillis - j)) / 1000.0d);
    }

    private void m8322a(IabResult iabResult, Purchase purchase, boolean z) {
        String a;
        String b;
        String str;
        String str2;
        Long valueOf;
        String str3;
        Integer num = null;
        int a2 = iabResult.a();
        String b2 = iabResult.b();
        if (z) {
            a = PurchaseType.a.a();
        } else {
            a = PurchaseType.b.a();
        }
        if (b2 != null) {
            b2 = b2.substring(0, Math.min(b2.length(), TransportMediator.KEYCODE_MEDIA_PAUSE));
        }
        if (purchase != null) {
            String c = purchase.c();
            b = purchase.b();
            SubscriptionPack b3 = SubscriptionManager.m8066a().m8086b(purchase.c());
            if (b3 != null) {
                str = b3.period + (b3.trial ? "_trial" : "");
                str2 = b3.f6897b + (((float) b3.f6896a) / 1000000.0f);
            } else {
                str2 = null;
                str = null;
            }
            if (a2 == 0) {
                valueOf = Long.valueOf(purchase.d());
                num = Integer.valueOf(purchase.e());
                str3 = c;
            } else {
                valueOf = null;
                str3 = c;
            }
        } else {
            valueOf = null;
            str2 = null;
            str = null;
            b = null;
            str3 = null;
        }
        Analytics.a(str3, b, str, str2, a2, b2, a, valueOf, num, m8333g());
    }

    private void m8321a(IabResult iabResult, Inventory inventory) {
        int a = iabResult.a();
        String b = iabResult.b();
        String str = null;
        if (inventory != null) {
            str = TextUtils.join(",", inventory.a.keySet());
        }
        Analytics.a(str, a, b);
    }
}
