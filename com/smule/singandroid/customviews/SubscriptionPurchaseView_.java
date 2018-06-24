package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SubscriptionPurchaseView_ extends SubscriptionPurchaseView implements HasViews, OnViewChangedListener {
    private boolean f22039m = false;
    private final OnViewChangedNotifier f22040n = new OnViewChangedNotifier();

    public SubscriptionPurchaseView_(Context context) {
        super(context);
        m23524b();
    }

    public SubscriptionPurchaseView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23524b();
    }

    public SubscriptionPurchaseView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23524b();
    }

    public static SubscriptionPurchaseView m23523a(Context context) {
        SubscriptionPurchaseView subscriptionPurchaseView_ = new SubscriptionPurchaseView_(context);
        subscriptionPurchaseView_.onFinishInflate();
        return subscriptionPurchaseView_;
    }

    public void onFinishInflate() {
        if (!this.f22039m) {
            this.f22039m = true;
            inflate(getContext(), C1947R.layout.subscription_purchase_view, this);
            this.f22040n.a(this);
        }
        super.onFinishInflate();
    }

    private void m23524b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22040n);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23525a(HasViews hasViews) {
        this.a = hasViews.findViewById(C1947R.id.subscription_purchase_view_header);
        this.b = hasViews.findViewById(C1947R.id.subscription_purchase_view_audio_fx_header);
        this.c = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.subscription_purchase_view_profile_image_view);
        this.d = (TextView) hasViews.findViewById(C1947R.id.subscription_purchase_view_title_text_view);
        this.e = (PurchaseButton) hasViews.findViewById(C1947R.id.subscription_purchase_top_purchase_button);
        this.f = (PurchaseButton) hasViews.findViewById(C1947R.id.subscription_purchase_middle_purchase_button);
        this.g = (PurchaseButton) hasViews.findViewById(C1947R.id.subscription_purchase_bottom_purchase_button);
        this.h = (ProgressBar) hasViews.findViewById(C1947R.id.subscription_purchase_progress_bar);
        this.i = (TextView) hasViews.findViewById(C1947R.id.cannot_connect_textview);
        m23520a();
    }
}
