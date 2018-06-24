/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageButton
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.list_items.FindFriendsExternalListItem;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsExternalListItem_
extends FindFriendsExternalListItem
implements HasViews,
OnViewChangedListener {
    private boolean h = false;
    private final OnViewChangedNotifier i = new OnViewChangedNotifier();

    public FindFriendsExternalListItem_(Context context) {
        super(context);
        this.a();
    }

    public static FindFriendsExternalListItem a(Context object) {
        object = new FindFriendsExternalListItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.i);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.b = (TextView)hasViews.findViewById(2131755804);
        this.c = (TextView)hasViews.findViewById(2131755803);
        this.d = (ImageView)hasViews.findViewById(2131755802);
        this.e = (ImageButton)hasViews.findViewById(2131755748);
        this.f = (ProgressBar)hasViews.findViewById(2131755806);
        if (this.e != null) {
            this.e.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FindFriendsExternalListItem_.this.a(view);
                }
            });
        }
    }

    @Override
    public void a(final boolean bl) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FindFriendsExternalListItem_.super.a(bl);
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.h) {
            this.h = true;
            FindFriendsExternalListItem_.inflate((Context)this.getContext(), (int)2130903236, (ViewGroup)this);
            this.i.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

