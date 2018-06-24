/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.constraint.ConstraintLayout
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
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.singandroid.FindFriendsFacebookPageView;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsFacebookPageView_
extends FindFriendsFacebookPageView
implements HasViews,
OnViewChangedListener {
    private boolean n = false;
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();

    public FindFriendsFacebookPageView_(Context context) {
        super(context);
        this.i();
    }

    public FindFriendsFacebookPageView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i();
    }

    public FindFriendsFacebookPageView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.i();
    }

    public static FindFriendsFacebookPageView a(Context object) {
        object = new FindFriendsFacebookPageView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void i() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.f = (MagicListView)hasViews.findViewById(2131755801);
        this.a = (ConstraintLayout)hasViews.findViewById(2131755808);
        this.b = (ConstraintLayout)hasViews.findViewById(2131755809);
        this.c = (LinearLayout)hasViews.findViewById(2131755810);
    }

    @Override
    public void a(final boolean bl) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsFacebookPageView_.super.a(bl);
            }
        }, (long)0);
    }

    @Override
    protected void b() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsFacebookPageView_.super.b();
            }
        }, (long)0);
    }

    @Override
    protected void d() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsFacebookPageView_.super.d();
            }
        }, (long)0);
    }

    @Override
    protected void e() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsFacebookPageView_.super.e();
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.n) {
            this.n = true;
            FindFriendsFacebookPageView_.inflate((Context)this.getContext(), (int)2130903240, (ViewGroup)this);
            this.o.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

