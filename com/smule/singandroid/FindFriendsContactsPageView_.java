/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.LinearLayout
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.singandroid.FindFriendsContactsPageView;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsContactsPageView_
extends FindFriendsContactsPageView
implements HasViews,
OnViewChangedListener {
    private boolean n = false;
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();

    public FindFriendsContactsPageView_(Context context) {
        super(context);
        this.g();
    }

    public FindFriendsContactsPageView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g();
    }

    public FindFriendsContactsPageView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.g();
    }

    public static FindFriendsContactsPageView a(Context object) {
        object = new FindFriendsContactsPageView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void g() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.f = (MagicListView)hasViews.findViewById(2131755801);
        this.a = (LinearLayout)hasViews.findViewById(2131755798);
        this.b = (LinearLayout)hasViews.findViewById(2131755799);
        this.c = (LinearLayout)hasViews.findViewById(2131755800);
    }

    @Override
    public void a(final boolean bl) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsContactsPageView_.super.a(bl);
            }
        }, (long)0);
    }

    @Override
    protected void b() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsContactsPageView_.super.b();
            }
        }, (long)0);
    }

    @Override
    protected void d() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsContactsPageView_.super.d();
            }
        }, (long)0);
    }

    @Override
    protected void e() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsContactsPageView_.super.e();
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.n) {
            this.n = true;
            FindFriendsContactsPageView_.inflate((Context)this.getContext(), (int)2130903235, (ViewGroup)this);
            this.o.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

