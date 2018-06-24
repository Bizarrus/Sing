/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.customviews.StorageWarningView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class StorageWarningView_
extends StorageWarningView
implements HasViews,
OnViewChangedListener {
    private boolean h = false;
    private final OnViewChangedNotifier i = new OnViewChangedNotifier();

    public StorageWarningView_(Context context, boolean bl, View.OnClickListener onClickListener) {
        super(context, bl, onClickListener);
        this.f();
    }

    public static StorageWarningView a(Context object, boolean bl, View.OnClickListener onClickListener) {
        object = new StorageWarningView_((Context)object, bl, onClickListener);
        object.onFinishInflate();
        return object;
    }

    private void f() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.i);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = hasViews.findViewById(2131756736);
        this.b = (LinearLayout)hasViews.findViewById(2131756739);
        this.c = (TextView)hasViews.findViewById(2131756742);
        this.d = (Button)hasViews.findViewById(2131756743);
        this.e = (LinearLayout)hasViews.findViewById(2131756737);
        this.f = (FrameLayout)hasViews.findViewById(2131756738);
        this.g = (FrameLayout)hasViews.findViewById(2131756741);
        this.b();
    }

    public void onFinishInflate() {
        if (!this.h) {
            this.h = true;
            StorageWarningView_.inflate((Context)this.getContext(), (int)2130903440, (ViewGroup)this);
            this.i.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

