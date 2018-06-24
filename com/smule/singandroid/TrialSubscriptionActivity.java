/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.text.TextUtils
 *  android.widget.Button
 *  android.widget.TextView
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.SubscriptionPack;
import com.smule.android.purchases.BillingListener;
import com.smule.android.purchases.Consts;
import com.smule.android.purchases.GoogleV3Billing;
import com.smule.android.purchases.SkuDetails;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@SuppressLint(value={"Registered"})
@EActivity
public class TrialSubscriptionActivity
extends BaseActivity {
    private static final String k = TrialSubscriptionActivity.class.getName();
    @ViewById
    Button g;
    @ViewById
    Button h;
    @ViewById
    TextView i;
    protected BusyScreenDialogWithBackPress j;
    private com.smule.android.purchases.GoogleV3Billing l;
    private String m;
    private BillingState n = BillingState.a;

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(Context context) {
        boolean bl = MagicPreferences.b(context, "SEEN_TRIALS_POPUP", false);
        boolean bl2 = !SubscriptionManager.a().b() && !SubscriptionManager.a().h();
        if (!SubscriptionManager.a().c()) return false;
        if (bl2 && !bl) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void a() {
        SingAnalytics.C();
        if (TextUtils.isEmpty((CharSequence)this.m)) {
            this.t();
            return;
        }
        Log.b(k, "getTrial:mBillingState:" + (Object)((Object)this.n));
        if (this.n == BillingState.a) {
            this.n = BillingState.c;
            this.j = new BusyScreenDialogWithBackPress((Context)this, this.getString(2131296698), null);
            this.j.show();
            return;
        } else {
            if (this.n != BillingState.b) return;
            {
                this.n = BillingState.d;
                this.u();
                this.l.b(this.m);
                return;
            }
        }
    }

    @Click
    protected void b() {
        this.t();
    }

    @UiThread
    protected void c(String string2) {
        this.i.setText((CharSequence)MessageFormat.format(this.getString(2131297563), string2));
    }

    @Override
    protected void f() {
        super.f();
        MagicPreferences.a((Context)this, "SEEN_TRIALS_POPUP", true);
        Analytics.t();
        this.c("-");
        this.l = new com.smule.android.purchases.GoogleV3Billing();
        this.l.a((Activity)this, new BillingListener(){

            @Override
            public void a(Consts.ResponseCode responseCode) {
                TrialSubscriptionActivity.this.n = BillingState.b;
                if (responseCode == Consts.ResponseCode.a) {
                    Log.c(k, "purchase request was successfully sent to server");
                    return;
                }
                if (responseCode == Consts.ResponseCode.b) {
                    Log.c(k, "user canceled purchase");
                    return;
                }
                Log.c(k, "purchase request failed!");
            }

            @Override
            public void a(Consts.ResponseCode responseCode, String string2) {
            }

            @Override
            public void a(boolean bl) {
                if (bl) {
                    for (SubscriptionPack subscriptionPack : SubscriptionManager.a().e()) {
                        if (!subscriptionPack.trial) continue;
                        TrialSubscriptionActivity.this.m = subscriptionPack.sku;
                        TrialSubscriptionActivity.this.l.a(Arrays.asList(TrialSubscriptionActivity.this.m), new GoogleV3Billing(){

                            /*
                             * Enabled aggressive block sorting
                             */
                            @Override
                            public void a(Map<String, SkuDetails> object) {
                                if (object == null) {
                                    TrialSubscriptionActivity.this.n = BillingState.e;
                                    TrialSubscriptionActivity.this.u();
                                    TrialSubscriptionActivity.this.v();
                                    return;
                                }
                                if ((object = (SkuDetails)object.get(TrialSubscriptionActivity.this.m)) != null) {
                                    TrialSubscriptionActivity.this.c(object.b());
                                }
                                Log.b(k, "sku details:mBillingState:" + (Object)((Object)TrialSubscriptionActivity.this.n));
                                if (TrialSubscriptionActivity.this.n == BillingState.a) {
                                    TrialSubscriptionActivity.this.n = BillingState.b;
                                    return;
                                } else {
                                    if (TrialSubscriptionActivity.this.n != BillingState.c) return;
                                    {
                                        TrialSubscriptionActivity.this.n = BillingState.d;
                                        TrialSubscriptionActivity.this.u();
                                        TrialSubscriptionActivity.this.l.b(TrialSubscriptionActivity.this.m);
                                        return;
                                    }
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void a(boolean bl, String string2) {
                if (bl) {
                    TrialSubscriptionActivity.this.t();
                }
            }

            @Override
            public void b(Consts.ResponseCode responseCode) {
            }

        });
    }

    protected void onActivityResult(int n, int n2, Intent intent) {
        if (!this.l.a(n, n2, intent)) {
            super.onActivityResult(n, n2, intent);
        }
    }

    @Override
    public void onBackPressed() {
        Log.b(k, "onBackPressed");
        this.t();
    }

    @Override
    public void onDestroy() {
        this.l.c();
        super.onDestroy();
    }

    @UiThread
    protected void t() {
        this.startActivity(MasterActivity.a((Context)this));
        this.finish();
    }

    protected void u() {
        if (this.j != null) {
            this.j.dismiss();
            this.j = null;
        }
    }

    protected void v() {
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this, this.getString(2131297559), this.getString(2131297558), true, false);
        textAlertDialog.a(this.getString(2131296705), "");
        textAlertDialog.a(new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                TrialSubscriptionActivity.this.t();
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                TrialSubscriptionActivity.this.t();
            }
        });
        textAlertDialog.show();
    }

    static enum BillingState {
        a,
        b,
        c,
        d,
        e;
        

        private BillingState() {
        }
    }

    private class BusyScreenDialogWithBackPress
    extends BusyScreenDialog {
        public BusyScreenDialogWithBackPress(Context context, String string2, String string3) {
            super(context, string2, string3);
        }

        @Override
        public void onBackPressed() {
            TrialSubscriptionActivity.this.onBackPressed();
        }
    }

}

