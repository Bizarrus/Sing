/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.singandroid.FindFriendsRecommendedPageView;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsRecommendedPageView_
extends FindFriendsRecommendedPageView
implements HasViews,
OnViewChangedListener {
    private boolean n = false;
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();

    public FindFriendsRecommendedPageView_(Context context) {
        super(context);
        this.f();
    }

    public static FindFriendsRecommendedPageView a(Context object) {
        object = new FindFriendsRecommendedPageView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void f() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (MagicListView)hasViews.findViewById(2131755831);
    }

    @Override
    protected void e() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsRecommendedPageView_.super.e();
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.n) {
            this.n = true;
            FindFriendsRecommendedPageView_.inflate((Context)this.getContext(), (int)2130903247, (ViewGroup)this);
            this.o.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

