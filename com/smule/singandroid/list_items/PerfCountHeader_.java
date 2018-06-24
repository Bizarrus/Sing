/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.singandroid.list_items.PerfCountHeader;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PerfCountHeader_
extends PerfCountHeader
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public PerfCountHeader_(Context context) {
        super(context);
        this.a();
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public static PerfCountHeader b(Context object) {
        object = new PerfCountHeader_((Context)object);
        object.onFinishInflate();
        return object;
    }

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131756223);
        this.b = hasViews.findViewById(2131756222);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            PerfCountHeader_.inflate((Context)this.getContext(), (int)2130903346, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

