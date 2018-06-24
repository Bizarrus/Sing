/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.MoreProfilesView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.AggregatedNotificationListViewItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class AggregatedNotificationListViewItem_
extends AggregatedNotificationListViewItem
implements HasViews,
OnViewChangedListener {
    private boolean n = false;
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();

    public AggregatedNotificationListViewItem_(Context context) {
        super(context);
        this.b();
    }

    public static AggregatedNotificationListViewItem b(Context object) {
        object = new AggregatedNotificationListViewItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (ImageView)hasViews.findViewById(2131755252);
        this.b = (ImageView)hasViews.findViewById(2131755272);
        this.c = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755241);
        this.d = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755266);
        this.e = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755267);
        this.f = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755268);
        this.g = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755269);
        this.h = hasViews.findViewById(2131755265);
        this.i = (MoreProfilesView)hasViews.findViewById(2131755270);
        this.j = (TextView)hasViews.findViewById(2131755243);
        this.k = (TextView)hasViews.findViewById(2131755271);
        this.l = hasViews.findViewById(2131755250);
    }

    public void onFinishInflate() {
        if (!this.n) {
            this.n = true;
            AggregatedNotificationListViewItem_.inflate((Context)this.getContext(), (int)2130903077, (ViewGroup)this);
            this.o.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

