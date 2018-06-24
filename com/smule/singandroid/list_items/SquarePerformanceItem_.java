/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
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
import android.widget.TextView;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SquarePerformanceItem_
extends SquarePerformanceItem
implements HasViews,
OnViewChangedListener {
    private boolean f = false;
    private final OnViewChangedNotifier g = new OnViewChangedNotifier();

    public SquarePerformanceItem_(Context context) {
        super(context);
        this.c();
    }

    public SquarePerformanceItem_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public SquarePerformanceItem_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.c();
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.g);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.ae = (PlayableItemView)hasViews.findViewById(2131755320);
        this.a = (TextView)hasViews.findViewById(2131756732);
        this.b = (TextView)hasViews.findViewById(2131756733);
        this.c = (TextView)hasViews.findViewById(2131756497);
        this.d = (TextView)hasViews.findViewById(2131756734);
        this.p();
    }

    public void onFinishInflate() {
        if (!this.f) {
            this.f = true;
            SquarePerformanceItem_.inflate((Context)this.getContext(), (int)2130903437, (ViewGroup)this);
            this.g.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

