/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Handler
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 *  com.smule.singandroid.upsell.PurchaseButtonV2
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.purchases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.BillingListener;
import com.smule.android.purchases.Consts;
import com.smule.android.purchases.GoogleV3Billing;
import com.smule.android.purchases.ServerPurchaseExecutor;
import com.smule.android.purchases.SkuDetails;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.customviews.PurchaseButton;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.upsell.PurchaseButtonV2;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observer;

public class V3BillingHelper {
    private static String E;
    private static Map<String, SkuDetails> F;
    private static Long G;
    private static String a;
    private Handler A = new Handler();
    private boolean B = false;
    private int C = 0;
    private boolean D = false;
    private com.smule.android.purchases.GoogleV3Billing H;
    private String I;
    private Observer J;
    private Observer K;
    private  b;
    private String c;
    private View d;
    private View e;
    private TextView f;
    private TextView g;
    private TextView h;
    private View i;
    private View j;
    private TextView k;
    private TextView l;
    private TextView m;
    private View n;
    private View o;
    private TextView p;
    private TextView q;
    private TextView r;
    private Activity s;
    private  t;
    private View u;
    private View v;
    private boolean w = false;
    private BusyDialog x;
    private String y;
    private String z;

    static {
        a = V3BillingHelper.class.getSimpleName();
        E = null;
        F = null;
        G = 0;
    }

    public V3BillingHelper() {
        this.J = new Observer(this){
            final /* synthetic */ V3BillingHelper a;
            {
                this.a = v3BillingHelper;
            }

            public void update(java.util.Observable observable, Object object) {
                V3BillingHelper.d(this.a).post(new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        if (V3BillingHelper.a(this.a.a) != null) {
                            V3BillingHelper.a(this.a.a).a();
                        }
                        if (V3BillingHelper.b(this.a.a) == null || V3BillingHelper.b(this.a.a).isFinishing()) {
                            return;
                        }
                        if (V3BillingHelper.c(this.a.a) != null) {
                            V3BillingHelper.c(this.a.a).dismiss();
                        }
                        V3BillingHelper.a(this.a.a, new BusyDialog(V3BillingHelper.b(this.a.a), V3BillingHelper.b(this.a.a).getResources().getString(2131297554)));
                        V3BillingHelper.c(this.a.a).a(false);
                    }
                });
            }
        };
        this.K = new Observer(this){
            final /* synthetic */ V3BillingHelper a;
            {
                this.a = v3BillingHelper;
            }

            public void update(java.util.Observable observable, Object object) {
                V3BillingHelper.d(this.a).post(new Runnable(this, object){
                    final /* synthetic */ Object a;
                    final /* synthetic */  b;
                    {
                        this.b = var1_1;
                        this.a = object;
                    }

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void run() {
                        boolean bl = (java.lang.Boolean)((Map)this.a).get("result");
                        if (V3BillingHelper.c(this.b.a) != null) {
                            if (bl) {
                                V3BillingHelper.c(this.b.a).dismiss();
                            } else {
                                String string2 = V3BillingHelper.b(this.b.a).getResources().getString(2131297549);
                                V3BillingHelper.c(this.b.a).a(2, string2, null);
                            }
                            V3BillingHelper.a(this.b.a, null);
                        }
                        if (V3BillingHelper.a(this.b.a) != null) {
                            V3BillingHelper.a(this.b.a).a(bl);
                        }
                    }
                });
            }
        };
    }

    static /* synthetic */ BusyDialog a(V3BillingHelper v3BillingHelper, BusyDialog busyDialog) {
        v3BillingHelper.x = busyDialog;
        return busyDialog;
    }

    static /* synthetic */  a(V3BillingHelper v3BillingHelper) {
        return v3BillingHelper.b;
    }

    @NonNull
    private String a(List<SubscriptionPack> object, List<String> object2) {
        Object object3 = object.iterator();
        object = "";
        while (object3.hasNext()) {
            SubscriptionPack subscriptionPack = object3.next();
            object = (String)object + subscriptionPack.sku + ":";
        }
        object2 = object2.iterator();
        while (object2.hasNext()) {
            object3 = (String)object2.next();
            object = (String)object + (String)object3 + ":";
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(SubscriptionPack subscriptionPack, SkuDetails skuDetails) {
        if (this.s == null) {
            Log.d(a, "mActivity was null");
            return;
        }
        if (this.C > 0 || !this.w) return;
        {
            if (skuDetails == null) {
                Log.d(a, "skuDetails null");
                return;
            }
        }
        this.i();
        this.z = subscriptionPack.period;
        this.y = subscriptionPack.sku;
        this.a(subscriptionPack, skuDetails.b(), this.c);
    }

    private void a(SubscriptionPack subscriptionPack, String string2, String string3) {
        SingAnalytics.a((String)subscriptionPack.sku, (String)string3, (String)subscriptionPack.period, (String)string2);
        this.H.d();
        Log.b(a, "Requesting purchase of sku: " + subscriptionPack.sku);
        this.H.b(subscriptionPack.sku);
        E = null;
    }

    static /* synthetic */ void a(V3BillingHelper v3BillingHelper, List list, Map map) {
        v3BillingHelper.a(list, map);
    }

    static /* synthetic */ void a(V3BillingHelper v3BillingHelper, Map map, String string2) {
        v3BillingHelper.a(map, string2);
    }

    private void a(List<String> list, List<SubscriptionPack> list2, List<String> list3, String string2) {
        this.i();
        this.i();
        this.H.b(list, new GoogleV3Billing(this, list2, string2){
            final /* synthetic */ List a;
            final /* synthetic */ String b;
            final /* synthetic */ V3BillingHelper c;
            {
                this.c = v3BillingHelper;
                this.a = list;
                this.b = string2;
            }

            public void a(Map<String, SkuDetails> map) {
                V3BillingHelper.h(this.c);
                ArrayList<SubscriptionPack> arrayList = new ArrayList<SubscriptionPack>();
                for (SubscriptionPack subscriptionPack : this.a) {
                    if (map != null && map.get(subscriptionPack.sku) != null) {
                        arrayList.add(subscriptionPack);
                        continue;
                    }
                    Log.e(V3BillingHelper.f(), "sku: " + subscriptionPack.sku + " did not have a corresponding Google Play sku");
                }
                V3BillingHelper.a(this.c, arrayList, map);
                if (map != null && map.size() != 0) {
                    V3BillingHelper.a(this.c, map, this.b);
                }
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(List<SubscriptionPack> object, Map<String, SkuDetails> map) {
        Object object2;
        SubscriptionPack subscriptionPack;
        Log.c(a, "Details : " + map);
        if (object.size() > 0 && this.e != null) {
            subscriptionPack = (SubscriptionPack)object.get(0);
            object2 = map == null ? null : map.get(subscriptionPack.sku);
            if (object2 != null) {
                this.a(subscriptionPack, (SkuDetails)object2, this.e, this.d, this.f, this.g, this.h);
            }
        }
        if (object.size() > 1 && this.j != null) {
            subscriptionPack = (SubscriptionPack)object.get(1);
            object2 = map == null ? null : map.get(subscriptionPack.sku);
            if (object2 != null) {
                this.a(subscriptionPack, (SkuDetails)object2, this.j, this.i, this.k, this.l, this.m);
            }
        }
        if (object.size() > 2 && this.o != null) {
            object2 = (SubscriptionPack)object.get(2);
            object = map == null ? null : map.get(object2.sku);
            if (object != null) {
                this.a((SubscriptionPack)object2, (SkuDetails)object, this.o, this.n, this.p, this.q, this.r);
            }
        }
        boolean bl = this.e != null && this.d.getVisibility() == 0 || this.j != null && this.i.getVisibility() == 0 || this.o != null && this.n.getVisibility() == 0;
        if (this.v != null) {
            this.v.setVisibility(0);
        }
        if (this.u != null) {
            this.u.setVisibility(8);
        }
    }

    private void a(Map<String, SkuDetails> map, String string2) {
        F = map;
        E = string2;
        G = System.currentTimeMillis();
    }

    private boolean a(SubscriptionPack subscriptionPack, TextView textView) {
        block3 : {
            boolean bl;
            block2 : {
                bl = false;
                if (textView == null) break block2;
                if (TextUtils.isEmpty((CharSequence)subscriptionPack.descriptionKey)) break block3;
                textView.setText((CharSequence)subscriptionPack.descriptionKey);
                bl = true;
            }
            return bl;
        }
        textView.setVisibility(8);
        return false;
    }

    static /* synthetic */ Activity b(V3BillingHelper v3BillingHelper) {
        return v3BillingHelper.s;
    }

    @NonNull
    private List<String> b(@Nullable List<SubscriptionPack> iterator, @Nullable List<String> list) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (iterator != null) {
            iterator = iterator.iterator();
            while (iterator.hasNext()) {
                arrayList.add(((SubscriptionPack)iterator.next()).sku);
            }
        }
        if (list != null) {
            iterator = list.iterator();
            while (iterator.hasNext()) {
                arrayList.add(iterator.next());
            }
        }
        return arrayList;
    }

    static /* synthetic */ BusyDialog c(V3BillingHelper v3BillingHelper) {
        return v3BillingHelper.x;
    }

    static /* synthetic */ Handler d(V3BillingHelper v3BillingHelper) {
        return v3BillingHelper.A;
    }

    private void h() {
        List<String> list;
        List<SubscriptionPack> list2 = SubscriptionManager.a().e();
        List<String> list3 = Collections.singletonList("promote.test.sku.2017.05");
        Log.b(a, "Configure purchase options started");
        if (list2 != null) {
            Log.b(a, "Size of subscription packs JSON: " + list2.size());
        }
        if ((list = this.b(list2, list3)).size() == 0) {
            return;
        }
        String string2 = this.a(list2, list3);
        if (string2.equals(E) && System.currentTimeMillis() < G + 30000) {
            this.a(list2, F);
            return;
        }
        this.a(list, list2, list3, string2);
    }

    private void i() {
        ++this.C;
    }

    private void j() {
        --this.C;
        this.k();
    }

    private void k() {
        if (this.C == 0 && this.D && this.w) {
            Log.b(a, "Destroying GoogleV3Billing instance");
            this.H.c();
            this.w = false;
        }
    }

    public void a() {
    }

    public void a(Activity activity, String object,  v3BillingListener) {
        this.s = activity;
        this.b = v3BillingListener;
        this.I = object;
        Log.b(a, "Creating GoogleV3Billing instance");
        this.D = false;
        this.C = 0;
        object = NotificationCenter.a();
        object.a(ServerPurchaseExecutor.a, this.J);
        object.a(ServerPurchaseExecutor.b, this.K);
        this.H = new com.smule.android.purchases.GoogleV3Billing();
        this.H.a(activity, new BillingListener(){
            boolean a;
            {
                this.a = false;
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(Consts.ResponseCode responseCode) {
                if (responseCode == Consts.ResponseCode.a) {
                    Log.c(a, "purchase request was successfully sent to server");
                } else if (responseCode == Consts.ResponseCode.b) {
                    Log.c(a, "user canceled purchase");
                } else {
                    Log.c(a, "purchase request failed!");
                }
                V3BillingHelper.this.j();
            }

            @Override
            public void a(Consts.ResponseCode responseCode, String string2) {
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(boolean bl) {
                block5 : {
                    block4 : {
                        if (this.a) break block4;
                        this.a = true;
                        if (bl) {
                            V3BillingHelper.this.h();
                            V3BillingHelper.this.B = true;
                            return;
                        }
                        Log.d(a, "Subscription purchasing not supported.");
                        if (V3BillingHelper.this.u == null) break block4;
                        V3BillingHelper.this.u.setVisibility(8);
                        if (V3BillingHelper.this.t != null) break block5;
                    }
                    return;
                }
                V3BillingHelper.this.t.a();
            }

            @Override
            public void a(boolean bl, String string2) {
                if (!bl) {
                    Log.e(a, "unexpected purchase state for " + string2);
                    return;
                }
                Log.b(a, "purchase state success for " + string2);
                Log.b(a, "purchaseDidSucceed called; finishing this activity to return to previous activity");
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void b(Consts.ResponseCode object) {
                Log.b(a, "doRestorePurchases returned with responseCode: " + object.name());
                V3BillingHelper.this.j();
                if (V3BillingHelper.this.I != null) {
                    for (SubscriptionPack subscriptionPack : SubscriptionManager.a().e()) {
                        object = F == null ? null : (SkuDetails)F.get(subscriptionPack.sku);
                        if (!(V3BillingHelper.this.I.equals("weekly") && subscriptionPack.period.equals("1w") || V3BillingHelper.this.I.equals("monthly") && subscriptionPack.period.equals("1m")) && (!V3BillingHelper.this.I.equals("yearly") || !subscriptionPack.period.equals("1y"))) continue;
                        V3BillingHelper.this.a(subscriptionPack, (SkuDetails)object);
                    }
                }
            }
        });
        this.w = true;
    }

    @Deprecated
    public void a(Activity activity, String string2, String string3,  v3BillingListener) {
        this.a(activity, string3, v3BillingListener);
    }

    public void a(View view, View view2, View view3, View view4,  skuDetailsErrorListener) {
        this.d = view;
        this.e = view;
        this.e.setVisibility(8);
        if (view2 != null) {
            this.i = view2;
            this.j = view2;
            this.j.setVisibility(8);
        }
        if (view3 != null) {
            this.n = view3;
            this.o = view3;
            this.o.setVisibility(8);
        }
        this.u = view4;
        this.u.setVisibility(0);
        this.t = skuDetailsErrorListener;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(SubscriptionPack subscriptionPack, SkuDetails object, View view, View view2, TextView object2, TextView object3, TextView textView) {
        Object var10_8 = null;
        if (object2 != null) {
            String string2 = subscriptionPack.labelKey != null ? subscriptionPack.labelKey : subscriptionPack.label;
            object2.setText((CharSequence)string2);
        }
        boolean bl = this.a(subscriptionPack, (TextView)object3);
        if (textView != null) {
            textView.setText((CharSequence)object.b());
        }
        if (view2 != null) {
            if (object.c() != null) {
                object2 = subscriptionPack.labelKey != null ? subscriptionPack.labelKey : subscriptionPack.label;
                object3 = String.format((String)object2, object.c());
                object2 = subscriptionPack.trialDescriptionKey != null ? MessageFormat.format(subscriptionPack.trialDescriptionKey, object.b()) : null;
            } else {
                object2 = subscriptionPack.trial ? subscriptionPack.trialLabelKey : (subscriptionPack.labelKey != null ? subscriptionPack.labelKey : subscriptionPack.label);
                object3 = String.format((String)object2, object.b());
                object2 = var10_8;
                if (subscriptionPack.trial) {
                    object2 = MessageFormat.format(subscriptionPack.trialDescriptionKey, object.b());
                }
            }
            object = new View.OnClickListener(this, subscriptionPack, (SkuDetails)object){
                final /* synthetic */ SubscriptionPack a;
                final /* synthetic */ SkuDetails b;
                final /* synthetic */ V3BillingHelper c;
                {
                    this.c = v3BillingHelper;
                    this.a = subscriptionPack;
                    this.b = skuDetails;
                }

                public void onClick(View view) {
                    V3BillingHelper.a(this.c, this.a, this.b);
                }
            };
            if (view2 instanceof PurchaseButton) {
                ((PurchaseButton)view2).a((String)object3, (String)object2, subscriptionPack.descriptionKey);
                view.setOnClickListener((View.OnClickListener)object);
            } else if (view2 instanceof PurchaseButtonV2) {
                ((PurchaseButtonV2)view2).a((String)object3, (String)object2, subscriptionPack.descriptionKey);
                if (bl || !TextUtils.isEmpty((CharSequence)object2)) {
                    view.setOnClickListener((View.OnClickListener)object);
                } else {
                    view.findViewById(2131756423).setOnClickListener((View.OnClickListener)object);
                }
            } else {
                Log.e(a, "purchaseButton " + (Object)view2 + " must be instance of PurchaseButton or PurchaseButtonV2");
            }
        }
        view.setVisibility(0);
    }

    public void a(String string2) {
        this.c = string2;
    }

    public boolean a(int n, int n2, Intent intent) {
        if (this.w && this.H.a(n, n2, intent)) {
            return true;
        }
        return false;
    }

    public void b() {
        if (!this.w) {
            return;
        }
        if (this.x != null && this.x.isShowing()) {
            this.x.dismiss();
        }
        this.x = null;
    }

    public void c() {
        Log.b(a, "onDestroy called");
        NotificationCenter notificationCenter = NotificationCenter.a();
        notificationCenter.b(ServerPurchaseExecutor.a, this.J);
        notificationCenter.b(ServerPurchaseExecutor.b, this.K);
        this.D = true;
        this.k();
    }

    public void d() {
        if (this.B && this.C == 0 && this.w) {
            this.i();
            this.H.b();
        }
    }

    public void e() {
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.s, this.s.getString(2131297223), this.s.getString(2131297224));
        textAlertDialog.a(this.s.getString(2131296714), this.s.getString(2131296702));
        textAlertDialog.a(new Runnable(this){
            final /* synthetic */ V3BillingHelper a;
            {
                this.a = v3BillingHelper;
            }

            public void run() {
                this.a.d();
            }
        });
        textAlertDialog.show();
    }

}

