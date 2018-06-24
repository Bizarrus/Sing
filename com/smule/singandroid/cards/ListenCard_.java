/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.smule.singandroid.cards.ListenCard;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ListenCard_
extends ListenCard
implements HasViews,
OnViewChangedListener {
    private boolean b = false;
    private final OnViewChangedNotifier c = new OnViewChangedNotifier();

    public ListenCard_(Context context) {
        super(context);
        this.c();
    }

    public static ListenCard b(Context object) {
        object = new ListenCard_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.c);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (SquarePerformanceItem)hasViews.findViewById(2131755925);
    }

    public void onFinishInflate() {
        if (!this.b) {
            this.b = true;
            ListenCard_.inflate((Context)this.getContext(), (int)2130903274, (ViewGroup)this);
            this.c.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

