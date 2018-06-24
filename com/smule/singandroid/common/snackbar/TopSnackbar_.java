/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.common.snackbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.singandroid.common.snackbar.TopSnackbar;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class TopSnackbar_
extends TopSnackbar
implements HasViews,
OnViewChangedListener {
    private boolean b = false;
    private final OnViewChangedNotifier c = new OnViewChangedNotifier();

    public TopSnackbar_(Context context) {
        super(context);
        this.b();
    }

    public TopSnackbar_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public TopSnackbar_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b();
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.c);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131756760);
        if ((hasViews = hasViews.findViewById(2131756761)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    TopSnackbar_.this.a();
                }
            });
        }
    }

    public void onFinishInflate() {
        if (!this.b) {
            this.b = true;
            TopSnackbar_.inflate((Context)this.getContext(), (int)2130903448, (ViewGroup)this);
            this.c.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

