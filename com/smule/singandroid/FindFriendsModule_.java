/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v7.widget.RecyclerView
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.singandroid.FindFriendsModule;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsModule_
extends FindFriendsModule
implements HasViews,
OnViewChangedListener {
    private boolean m = false;
    private final OnViewChangedNotifier n = new OnViewChangedNotifier();

    public FindFriendsModule_(Context context) {
        super(context);
        this.d();
    }

    public static FindFriendsModule a(Context object) {
        object = new FindFriendsModule_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void d() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.n);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.j = (TextView)hasViews.findViewById(2131755818);
        this.k = (RecyclerView)hasViews.findViewById(2131755819);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.m) {
            this.m = true;
            FindFriendsModule_.inflate((Context)this.getContext(), (int)2130903243, (ViewGroup)this);
            this.n.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

