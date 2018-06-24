package com.smule.singandroid;

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
import com.smule.android.purchases.Consts.ResponseCode;
import com.smule.android.purchases.GoogleV3Billing;
import com.smule.android.purchases.GoogleV3Billing$SkuDetailsListener;
import com.smule.android.purchases.SkuDetails;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity
public class TrialSubscriptionActivity extends BaseActivity {
    private static final String f20417k = TrialSubscriptionActivity.class.getName();
    @ViewById
    Button f20418g;
    @ViewById
    Button f20419h;
    @ViewById
    TextView f20420i;
    protected BusyScreenDialogWithBackPress f20421j;
    private GoogleV3Billing f20422l;
    private String f20423m;
    private BillingState f20424n = BillingState.RETRIEVING_SKU_DETAILS;

    class C42011 implements BillingListener {
        final /* synthetic */ TrialSubscriptionActivity f20405a;

        class C42001 implements GoogleV3Billing$SkuDetailsListener {
            final /* synthetic */ C42011 f20404a;

            C42001(C42011 c42011) {
                this.f20404a = c42011;
            }

            public void mo6672a(Map<String, SkuDetails> map) {
                if (map != null) {
                    SkuDetails skuDetails = (SkuDetails) map.get(this.f20404a.f20405a.f20423m);
                    if (skuDetails != null) {
                        this.f20404a.f20405a.mo6674c(skuDetails.m18681b());
                    }
                    Log.b(TrialSubscriptionActivity.f20417k, "sku details:mBillingState:" + this.f20404a.f20405a.f20424n);
                    if (this.f20404a.f20405a.f20424n == BillingState.RETRIEVING_SKU_DETAILS) {
                        this.f20404a.f20405a.f20424n = BillingState.COMPLETED_SKU_DETAILS;
                        return;
                    } else if (this.f20404a.f20405a.f20424n == BillingState.TRIAL_CLICKED_WHILE_RETRIEVING_SKU_DETAILS) {
                        this.f20404a.f20405a.f20424n = BillingState.REQUEST_SUBSCRIPTION;
                        this.f20404a.f20405a.m21956r();
                        this.f20404a.f20405a.f20422l.b(this.f20404a.f20405a.f20423m);
                        return;
                    } else {
                        return;
                    }
                }
                this.f20404a.f20405a.f20424n = BillingState.ERROR_SKU_DETAILS;
                this.f20404a.f20405a.m21956r();
                this.f20404a.f20405a.m21957s();
            }
        }

        C42011(TrialSubscriptionActivity trialSubscriptionActivity) {
            this.f20405a = trialSubscriptionActivity;
        }

        public void mo6273a(boolean z) {
            if (z) {
                for (SubscriptionPack subscriptionPack : SubscriptionManager.a().e()) {
                    if (subscriptionPack.trial) {
                        this.f20405a.f20423m = subscriptionPack.sku;
                        this.f20405a.f20422l.a(Arrays.asList(new String[]{this.f20405a.f20423m}), new C42001(this));
                    }
                }
            }
        }

        public void mo6274a(boolean z, String str) {
            if (z) {
                this.f20405a.mo6675q();
            }
        }

        public void mo6271a(ResponseCode responseCode) {
            this.f20405a.f20424n = BillingState.COMPLETED_SKU_DETAILS;
            if (responseCode == ResponseCode.RESULT_OK) {
                Log.c(TrialSubscriptionActivity.f20417k, "purchase request was successfully sent to server");
            } else if (responseCode == ResponseCode.RESULT_USER_CANCELED) {
                Log.c(TrialSubscriptionActivity.f20417k, "user canceled purchase");
            } else {
                Log.c(TrialSubscriptionActivity.f20417k, "purchase request failed!");
            }
        }

        public void mo6275b(ResponseCode responseCode) {
        }

        public void mo6272a(ResponseCode responseCode, String str) {
        }
    }

    class C42022 implements CustomAlertDialogListener {
        final /* synthetic */ TrialSubscriptionActivity f20406a;

        C42022(TrialSubscriptionActivity trialSubscriptionActivity) {
            this.f20406a = trialSubscriptionActivity;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            this.f20406a.mo6675q();
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            this.f20406a.mo6675q();
        }
    }

    enum BillingState {
        RETRIEVING_SKU_DETAILS,
        COMPLETED_SKU_DETAILS,
        TRIAL_CLICKED_WHILE_RETRIEVING_SKU_DETAILS,
        REQUEST_SUBSCRIPTION,
        ERROR_SKU_DETAILS
    }

    private class BusyScreenDialogWithBackPress extends BusyScreenDialog {
        final /* synthetic */ TrialSubscriptionActivity f20416a;

        public BusyScreenDialogWithBackPress(TrialSubscriptionActivity trialSubscriptionActivity, Context context, String str, String str2) {
            this.f20416a = trialSubscriptionActivity;
            super(context, str, str2);
        }

        public void onBackPressed() {
            this.f20416a.onBackPressed();
        }
    }

    public static boolean m21947a(Context context) {
        boolean b = MagicPreferences.m20317b(context, "SEEN_TRIALS_POPUP", false);
        boolean z;
        if (SubscriptionManager.a().b() || SubscriptionManager.a().h()) {
            z = false;
        } else {
            z = true;
        }
        if (SubscriptionManager.a().c() && r0 && !b) {
            return true;
        }
        return false;
    }

    @Click
    protected void m21951a() {
        SingAnalytics.m26160x();
        if (TextUtils.isEmpty(this.f20423m)) {
            mo6675q();
            return;
        }
        Log.b(f20417k, "getTrial:mBillingState:" + this.f20424n);
        if (this.f20424n == BillingState.RETRIEVING_SKU_DETAILS) {
            this.f20424n = BillingState.TRIAL_CLICKED_WHILE_RETRIEVING_SKU_DETAILS;
            this.f20421j = new BusyScreenDialogWithBackPress(this, this, getString(C1947R.string.core_loading), null);
            this.f20421j.show();
        } else if (this.f20424n == BillingState.COMPLETED_SKU_DETAILS) {
            this.f20424n = BillingState.REQUEST_SUBSCRIPTION;
            m21956r();
            this.f20422l.b(this.f20423m);
        }
    }

    public void onBackPressed() {
        Log.b(f20417k, "onBackPressed");
        mo6675q();
    }

    @Click
    protected void m21952b() {
        mo6675q();
    }

    @UiThread
    protected void mo6675q() {
        startActivity(MasterActivity.a(this));
        finish();
    }

    @UiThread
    protected void mo6674c(String str) {
        this.f20420i.setText(MessageFormat.format(getString(C1947R.string.trial_subs_only_xxx_after), new Object[]{str}));
    }

    protected void m21954d() {
        super.d();
        MagicPreferences.m20304a((Context) this, "SEEN_TRIALS_POPUP", true);
        Analytics.m17908o();
        mo6674c("-");
        this.f20422l = new GoogleV3Billing();
        this.f20422l.a(this, new C42011(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (!this.f20422l.a(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public void onDestroy() {
        this.f20422l.c();
        super.onDestroy();
    }

    protected void m21956r() {
        if (this.f20421j != null) {
            this.f20421j.dismiss();
            this.f20421j = null;
        }
    }

    protected void m21957s() {
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context) this, getString(C1947R.string.trial_subs_error_sku_title), getString(C1947R.string.trial_subs_error_sku_body), true, false);
        textAlertDialog.m19806a(getString(C1947R.string.core_ok), "");
        textAlertDialog.m19803a(new C42022(this));
        textAlertDialog.show();
    }
}
