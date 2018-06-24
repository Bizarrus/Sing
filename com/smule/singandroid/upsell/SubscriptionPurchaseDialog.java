package com.smule.singandroid.upsell;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.smule.android.logging.Analytics.PaywallType;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.customviews.SubscriptionPurchaseView;
import com.smule.singandroid.customviews.SubscriptionPurchaseView.Mode;
import com.smule.singandroid.dialogs.SmuleDialog;
import com.smule.singandroid.purchases.V3BillingHelper;

public class SubscriptionPurchaseDialog extends SmuleDialog {
    SubscriptionPurchaseView f24587a;
    SKUSelectionView f24588b;
    View f24589c;
    SongbookEntry f24590d;

    class C49771 implements OnClickListener {
        final /* synthetic */ SubscriptionPurchaseDialog f24586a;

        C49771(SubscriptionPurchaseDialog subscriptionPurchaseDialog) {
            this.f24586a = subscriptionPurchaseDialog;
        }

        public void onClick(View view) {
            this.f24586a.cancel();
        }
    }

    public SubscriptionPurchaseDialog(Activity activity, V3BillingHelper v3BillingHelper, SongbookEntry songbookEntry) {
        super(activity, C1947R.style.MagicModal);
        if (SingServerValues.m21676N()) {
            setContentView(C1947R.layout.sku_selection_dialog);
            ((RelativeLayout) findViewById(C1947R.id.root)).setBackgroundResource(C1947R.drawable.sku_selection_dialog_bg);
            this.f24588b = (SKUSelectionView) findViewById(C1947R.id.new_subscription_purchase_view);
            this.f24588b.m25751a(UpsellType.AUDIO_FX, v3BillingHelper);
            this.f24589c = findViewById(C1947R.id.sku_selection_dialog_cancel_button_touchable_area);
        } else {
            setContentView(C1947R.layout.subscription_purchase_dialog);
            this.f24587a = (SubscriptionPurchaseView) findViewById(C1947R.id.new_subscription_purchase_view);
            this.f24587a.setMode(Mode.AUDIO_FX_PURCHASE);
            this.f24587a.setSubsBuyContext(UpsellType.AUDIO_FX.m25792a());
            this.f24587a.setBillingHelper(v3BillingHelper);
            this.f24589c = findViewById(C1947R.id.cancel_button);
        }
        this.f24589c.setOnClickListener(new C49771(this));
        this.f24590d = songbookEntry;
    }

    protected void onStart() {
        super.onStart();
        if (SingServerValues.m21676N()) {
            this.f24588b.m25750a(this.f24590d, PaywallType.HARD);
        } else {
            this.f24587a.m23522a(this.f24590d, PaywallType.HARD);
        }
    }
}
