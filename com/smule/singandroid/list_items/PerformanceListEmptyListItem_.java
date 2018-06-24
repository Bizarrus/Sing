/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PerformanceListEmptyListItem_
extends PerformanceListEmptyListItem
implements HasViews,
OnViewChangedListener {
    private boolean d = false;
    private final OnViewChangedNotifier e = new OnViewChangedNotifier();

    public PerformanceListEmptyListItem_(Context context) {
        super(context);
        this.a();
    }

    public PerformanceListEmptyListItem_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a();
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.e);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public static PerformanceListEmptyListItem b(Context object) {
        object = new PerformanceListEmptyListItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    public void a(HasViews hasViews) {
        this.a = (ImageView)hasViews.findViewById(2131756224);
        this.b = (TextView)hasViews.findViewById(2131755271);
        this.c = (Button)hasViews.findViewById(2131756225);
    }

    public void onFinishInflate() {
        if (!this.d) {
            this.d = true;
            PerformanceListEmptyListItem_.inflate((Context)this.getContext(), (int)2130903347, (ViewGroup)this);
            this.e.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

