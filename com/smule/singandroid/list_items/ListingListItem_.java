/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.list_items.ListingListItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ListingListItem_
extends ListingListItem
implements HasViews,
OnViewChangedListener {
    private boolean q = false;
    private final OnViewChangedNotifier r = new OnViewChangedNotifier();

    public ListingListItem_(Context context) {
        super(context);
        this.f();
    }

    public static ListingListItem b(Context object) {
        object = new ListingListItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void f() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.r);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.ae = (PlayableItemView)hasViews.findViewById(2131755320);
        this.a = (LinearLayout)hasViews.findViewById(2131755221);
        this.b = (TextView)hasViews.findViewById(2131755928);
        this.c = (ViewGroup)hasViews.findViewById(2131755929);
        this.d = hasViews.findViewById(2131755283);
        this.e = (TextView)hasViews.findViewById(2131755933);
        this.f = (TextView)hasViews.findViewById(2131755935);
        this.g = (TextView)hasViews.findViewById(2131755932);
        this.h = (ImageView)hasViews.findViewById(2131755939);
        this.i = (TextView)hasViews.findViewById(2131755936);
        this.j = (TextView)hasViews.findViewById(2131755937);
        this.k = (TextView)hasViews.findViewById(2131755938);
        this.l = hasViews.findViewById(2131755218);
        this.m = (Button)hasViews.findViewById(2131755931);
        this.n = hasViews.findViewById(2131756222);
        this.o = (TextView)hasViews.findViewById(2131756223);
        this.p();
    }

    public void onFinishInflate() {
        if (!this.q) {
            this.q = true;
            ListingListItem_.inflate((Context)this.getContext(), (int)2130903276, (ViewGroup)this);
            this.r.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

