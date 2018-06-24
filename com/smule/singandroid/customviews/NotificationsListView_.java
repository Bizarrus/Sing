/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.customviews.NotificationsListView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NotificationsListView_
extends NotificationsListView
implements HasViews,
OnViewChangedListener {
    private boolean n = false;
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();

    public NotificationsListView_(Context context) {
        super(context);
        this.c();
    }

    public static NotificationsListView a(Context object) {
        object = new NotificationsListView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.d = hasViews.findViewById(2131755305);
        this.e = (TextView)hasViews.findViewById(2131755306);
        this.f = (SwipeRefreshLayout)hasViews.findViewById(2131755750);
        this.g = (MediaListView)hasViews.findViewById(2131756080);
        this.h = hasViews.findViewById(2131756079);
        if ((hasViews = hasViews.findViewById(2131755307)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NotificationsListView_.this.b();
                }
            });
        }
        this.a();
    }

    public void onFinishInflate() {
        if (!this.n) {
            this.n = true;
            NotificationsListView_.inflate((Context)this.getContext(), (int)2130903328, (ViewGroup)this);
            this.o.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

