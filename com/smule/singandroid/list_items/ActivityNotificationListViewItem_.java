/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.ProgressBar
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.models.NotificationListItem;
import com.smule.singandroid.customviews.PlayableItemDetailsView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.ActivityNotificationListViewItem;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ActivityNotificationListViewItem_
extends ActivityNotificationListViewItem
implements HasViews,
OnViewChangedListener {
    private boolean n = false;
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();

    public ActivityNotificationListViewItem_(Context context) {
        super(context);
        this.c();
    }

    public static ActivityNotificationListViewItem b(Context object) {
        object = new ActivityNotificationListViewItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    protected void a(final NotificationListItem notificationListItem, final boolean bl, final boolean bl2, final boolean bl3) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ActivityNotificationListViewItem_.super.a(notificationListItem, bl, bl2, bl3);
            }
        }, (long)0);
    }

    public void a(HasViews hasViews) {
        this.b = (ImageView)hasViews.findViewById(2131755252);
        this.c = (FrameLayout)hasViews.findViewById(2131755245);
        this.d = (ProgressBar)hasViews.findViewById(2131755246);
        this.e = (ImageView)hasViews.findViewById(2131755247);
        this.f = (TextView)hasViews.findViewById(2131755249);
        this.g = (ImageView)hasViews.findViewById(2131755248);
        this.h = hasViews.findViewById(2131755250);
        this.i = (PlayableItemDetailsView)hasViews.findViewById(2131755244);
        this.j = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755241);
        this.k = (TextView)hasViews.findViewById(2131755242);
        this.l = (TextView)hasViews.findViewById(2131755243);
    }

    public void onFinishInflate() {
        if (!this.n) {
            this.n = true;
            ActivityNotificationListViewItem_.inflate((Context)this.getContext(), (int)2130903074, (ViewGroup)this);
            this.o.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

