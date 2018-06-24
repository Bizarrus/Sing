/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.PurchaseButton;
import com.smule.singandroid.customviews.SubscriptionPurchaseView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SubscriptionPurchaseView_
extends SubscriptionPurchaseView
implements HasViews,
OnViewChangedListener {
    private boolean m = false;
    private final OnViewChangedNotifier n = new OnViewChangedNotifier();

    public SubscriptionPurchaseView_(Context context) {
        super(context);
        this.b();
    }

    public SubscriptionPurchaseView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public SubscriptionPurchaseView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b();
    }

    public static SubscriptionPurchaseView a(Context object) {
        object = new SubscriptionPurchaseView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.n);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = hasViews.findViewById(2131756745);
        this.b = hasViews.findViewById(2131756750);
        this.c = (ProfileImageWithVIPBadge)hasViews.findViewById(2131756747);
        this.d = (TextView)hasViews.findViewById(2131756748);
        this.e = (PurchaseButton)hasViews.findViewById(2131756283);
        this.f = (PurchaseButton)hasViews.findViewById(2131756751);
        this.g = (PurchaseButton)hasViews.findViewById(2131756290);
        this.h = (ProgressBar)hasViews.findViewById(2131756280);
        this.i = (TextView)hasViews.findViewById(2131756691);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.m) {
            this.m = true;
            SubscriptionPurchaseView_.inflate((Context)this.getContext(), (int)2130903443, (ViewGroup)this);
            this.n.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

