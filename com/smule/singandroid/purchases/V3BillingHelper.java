package com.smule.singandroid.purchases;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.GoogleV3Billing;
import com.smule.android.purchases.ServerPurchaseExecutor;
import com.smule.android.purchases.SkuDetails;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PurchaseButton;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.upsell.PurchaseButtonV2;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

public class V3BillingHelper {
    private static String f7128D = V3BillingHelper.class.getSimpleName();
    private static String f7129E = null;
    private static Map<String, SkuDetails> f7130F = null;
    private static Long f7131G = Long.valueOf(0);
    boolean f7132A = false;
    int f7133B = 0;
    boolean f7134C = false;
    private GoogleV3Billing f7135H;
    private String f7136I;
    private Observer f7137J = new 1(this);
    private Observer f7138K = new 2(this);
    V3BillingListener f7139a;
    String f7140b;
    View f7141c;
    View f7142d;
    TextView f7143e;
    TextView f7144f;
    TextView f7145g;
    View f7146h;
    View f7147i;
    TextView f7148j;
    TextView f7149k;
    TextView f7150l;
    View f7151m;
    View f7152n;
    TextView f7153o;
    TextView f7154p;
    TextView f7155q;
    Activity f7156r;
    SkuDetailsErrorListener f7157s;
    View f7158t;
    View f7159u;
    boolean f7160v = false;
    BusyDialog f7161w;
    String f7162x;
    String f7163y;
    Handler f7164z = new Handler();

    public void m8871a(String str) {
        this.f7140b = str;
    }

    @Deprecated
    public void m8868a(Activity activity, String str, String str2, V3BillingListener v3BillingListener) {
        m8867a(activity, str2, v3BillingListener);
    }

    public void m8867a(Activity activity, String str, V3BillingListener v3BillingListener) {
        this.f7156r = activity;
        this.f7139a = v3BillingListener;
        this.f7136I = str;
        Log.m7770b(f7128D, "Creating GoogleV3Billing instance");
        this.f7134C = false;
        this.f7133B = 0;
        NotificationCenter.a().a(ServerPurchaseExecutor.a, this.f7137J);
        NotificationCenter.a().a(ServerPurchaseExecutor.b, this.f7138K);
        this.f7135H = new GoogleV3Billing();
        this.f7135H.m8334a(activity, new SubscriptionBillingListener(this, null));
        this.f7160v = true;
    }

    public void m8866a() {
    }

    public void m8873b() {
        if (this.f7160v) {
            if (this.f7161w != null && this.f7161w.isShowing()) {
                this.f7161w.dismiss();
            }
            this.f7161w = null;
        }
    }

    public void m8874c() {
        Log.m7770b(f7128D, "onDestroy called");
        NotificationCenter.a().b(ServerPurchaseExecutor.a, this.f7137J);
        NotificationCenter.a().b(ServerPurchaseExecutor.b, this.f7138K);
        this.f7134C = true;
        m8865k();
    }

    private void m8853a(SubscriptionPack subscriptionPack, SkuDetails skuDetails) {
        if (this.f7156r == null) {
            Log.m7774d(f7128D, "mActivity was null");
        } else if (this.f7133B <= 0 && this.f7160v) {
            if (skuDetails == null) {
                Log.m7774d(f7128D, "skuDetails null");
                return;
            }
            m8863i();
            this.f7163y = subscriptionPack.period;
            this.f7162x = subscriptionPack.sku;
            m8854a(subscriptionPack, skuDetails.b(), this.f7140b);
        }
    }

    public void m8870a(SubscriptionPack subscriptionPack, SkuDetails skuDetails, View view, View view2, TextView textView, TextView textView2, TextView textView3) {
        int i;
        if (textView != null) {
            textView.setText(subscriptionPack.labelKey != null ? subscriptionPack.labelKey : subscriptionPack.label);
        }
        if (textView2 == null) {
            i = 0;
        } else if (TextUtils.isEmpty(subscriptionPack.descriptionKey)) {
            textView2.setVisibility(8);
            i = 0;
        } else {
            textView2.setText(subscriptionPack.descriptionKey);
            i = 1;
        }
        if (textView3 != null) {
            textView3.setText(skuDetails.b());
        }
        if (view2 != null) {
            String str = subscriptionPack.trial ? subscriptionPack.trialLabelKey : subscriptionPack.labelKey != null ? subscriptionPack.labelKey : subscriptionPack.label;
            String format = String.format(str, new Object[]{skuDetails.b()});
            CharSequence format2 = subscriptionPack.trial ? MessageFormat.format(subscriptionPack.trialDescriptionKey, new Object[]{skuDetails.b()}) : null;
            OnClickListener 3 = new 3(this, subscriptionPack, skuDetails);
            if (view2 instanceof PurchaseButton) {
                ((PurchaseButton) view2).a(format, format2, subscriptionPack.descriptionKey);
                view.setOnClickListener(3);
            } else if (view2 instanceof PurchaseButtonV2) {
                ((PurchaseButtonV2) view2).a(format, format2, subscriptionPack.descriptionKey);
                if (i == 0 && TextUtils.isEmpty(format2)) {
                    view.findViewById(C1947R.id.purchase_button_title).setOnClickListener(3);
                } else {
                    view.setOnClickListener(3);
                }
            } else {
                Log.m7776e(f7128D, "purchaseButton " + view2 + " must be instance of PurchaseButton or PurchaseButtonV2");
            }
        }
        view.setVisibility(0);
    }

    public void m8869a(View view, View view2, View view3, View view4, SkuDetailsErrorListener skuDetailsErrorListener) {
        this.f7141c = view;
        this.f7142d = view;
        this.f7142d.setVisibility(8);
        if (view2 != null) {
            this.f7146h = view2;
            this.f7147i = view2;
            this.f7147i.setVisibility(8);
        }
        if (view3 != null) {
            this.f7151m = view3;
            this.f7152n = view3;
            this.f7152n.setVisibility(8);
        }
        this.f7158t = view4;
        this.f7158t.setVisibility(0);
        this.f7157s = skuDetailsErrorListener;
    }

    public void m8875d() {
        List<SubscriptionPack> e = SubscriptionManager.m8066a().m8090e();
        Log.m7770b(f7128D, "Configure subscription purchase options started");
        if (e != null) {
            Log.m7770b(f7128D, "Size of subscription packs JSON: " + e.size());
        }
        if (e != null && e.size() != 0) {
            List arrayList = new ArrayList();
            String str = "";
            for (SubscriptionPack subscriptionPack : e) {
                arrayList.add(subscriptionPack.sku);
                str = str + subscriptionPack.sku + ":";
            }
            if (!str.equals(f7129E) || System.currentTimeMillis() >= f7131G.longValue() + 30000) {
                m8863i();
                m8863i();
                this.f7135H.m8340b(arrayList, new 5(this, e, str));
                return;
            }
            m8858a((List) e, f7130F);
        }
    }

    private void m8858a(List<SubscriptionPack> list, Map<String, SkuDetails> map) {
        SubscriptionPack subscriptionPack;
        SkuDetails skuDetails;
        int i;
        Log.m7772c(f7128D, "Details : " + map);
        if (list.size() > 0 && this.f7142d != null) {
            subscriptionPack = (SubscriptionPack) list.get(0);
            skuDetails = map == null ? null : (SkuDetails) map.get(subscriptionPack.sku);
            if (skuDetails != null) {
                m8870a(subscriptionPack, skuDetails, this.f7142d, this.f7141c, this.f7143e, this.f7144f, this.f7145g);
            }
        }
        if (list.size() > 1 && this.f7147i != null) {
            subscriptionPack = (SubscriptionPack) list.get(1);
            skuDetails = map == null ? null : (SkuDetails) map.get(subscriptionPack.sku);
            if (skuDetails != null) {
                m8870a(subscriptionPack, skuDetails, this.f7147i, this.f7146h, this.f7148j, this.f7149k, this.f7150l);
            }
        }
        if (list.size() > 2 && this.f7152n != null) {
            subscriptionPack = (SubscriptionPack) list.get(2);
            skuDetails = map == null ? null : (SkuDetails) map.get(subscriptionPack.sku);
            if (skuDetails != null) {
                m8870a(subscriptionPack, skuDetails, this.f7152n, this.f7151m, this.f7153o, this.f7154p, this.f7155q);
            }
        }
        if ((this.f7142d == null || this.f7141c.getVisibility() != 0) && ((this.f7147i == null || this.f7146h.getVisibility() != 0) && (this.f7152n == null || this.f7151m.getVisibility() != 0))) {
            i = 0;
        } else {
            i = 1;
        }
        if (this.f7159u != null) {
            this.f7159u.setVisibility(0);
        } else if (i == 0 && this.f7157s != null) {
            this.f7157s.a();
        }
        if (this.f7158t != null) {
            this.f7158t.setVisibility(8);
        }
    }

    private void m8854a(SubscriptionPack subscriptionPack, String str, String str2) {
        SingAnalytics.a(subscriptionPack.sku, str2, subscriptionPack.period, str);
        this.f7135H.m8342d();
        Log.m7770b(f7128D, "Requesting purchase of sku: " + subscriptionPack.sku);
        this.f7135H.m8339b(subscriptionPack.sku);
        f7129E = null;
    }

    public boolean m8872a(int i, int i2, Intent intent) {
        return this.f7160v && this.f7135H.m8337a(i, i2, intent);
    }

    public void m8876e() {
        if (this.f7132A && this.f7133B == 0 && this.f7160v) {
            m8863i();
            this.f7135H.m8338b();
        }
    }

    public void m8877f() {
        TextAlertDialog textAlertDialog = new TextAlertDialog(this.f7156r, this.f7156r.getString(C1947R.string.purchasing_restore_subscription), this.f7156r.getString(C1947R.string.purchasing_restore_subscription_desc));
        textAlertDialog.a(this.f7156r.getString(C1947R.string.core_restore), this.f7156r.getString(C1947R.string.core_no_thanks));
        textAlertDialog.a(new 6(this));
        textAlertDialog.show();
    }

    private void m8863i() {
        this.f7133B++;
    }

    private void m8864j() {
        this.f7133B--;
        m8865k();
    }

    private void m8865k() {
        if (this.f7133B == 0 && this.f7134C && this.f7160v) {
            Log.m7770b(f7128D, "Destroying GoogleV3Billing instance");
            this.f7135H.m8341c();
            this.f7160v = false;
        }
    }
}
