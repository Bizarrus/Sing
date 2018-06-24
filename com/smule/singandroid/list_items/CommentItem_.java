/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.network.models.PerformancePost;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.CommentItem;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CommentItem_
extends CommentItem
implements HasViews,
OnViewChangedListener {
    private boolean g = false;
    private final OnViewChangedNotifier h = new OnViewChangedNotifier();

    public CommentItem_(Context context) {
        super(context);
        this.b();
    }

    public static CommentItem b(Context object) {
        object = new CommentItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.h);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    protected void a() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                CommentItem_.super.a();
            }
        }, (long)0);
    }

    @Override
    public void a(final BaseFragment baseFragment, final PerformancePost performancePost, final boolean bl, final boolean bl2, final boolean bl3) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                CommentItem_.super.a(baseFragment, performancePost, bl, bl2, bl3);
            }
        }, (long)0);
    }

    public void a(HasViews hasViews) {
        this.a = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755619);
        this.b = (TextView)hasViews.findViewById(2131755620);
        this.c = (TextView)hasViews.findViewById(2131755622);
        this.d = (TextView)hasViews.findViewById(2131755243);
        this.e = hasViews.findViewById(2131755623);
        this.f = hasViews.findViewById(2131755618);
    }

    @Override
    protected void a(final boolean bl) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                CommentItem_.super.a(bl);
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.g) {
            this.g = true;
            CommentItem_.inflate((Context)this.getContext(), (int)2130903184, (ViewGroup)this);
            this.h.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

