/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.RelativeLayout
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.chat.Chat;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.customviews.MessageCenterListView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MessageCenterListView_
extends MessageCenterListView
implements HasViews,
OnViewChangedListener {
    private boolean i = false;
    private final OnViewChangedNotifier j = new OnViewChangedNotifier();

    public MessageCenterListView_(Context context, MessageCenterFragment messageCenterFragment, Chat.Bucket bucket) {
        super(context, messageCenterFragment, bucket);
        this.d();
    }

    public static MessageCenterListView b(Context object, MessageCenterFragment messageCenterFragment, Chat.Bucket bucket) {
        object = new MessageCenterListView_((Context)object, messageCenterFragment, bucket);
        object.onFinishInflate();
        return object;
    }

    private void d() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.j);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (ChatListView)hasViews.findViewById(2131755924);
        this.b = (SwipeRefreshLayout)hasViews.findViewById(2131755965);
        this.c = (RelativeLayout)hasViews.findViewById(2131755966);
        this.d = (TextView)hasViews.findViewById(2131755968);
        this.e = (TextView)hasViews.findViewById(2131755969);
        if ((hasViews = hasViews.findViewById(2131755967)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    MessageCenterListView_.this.a();
                }
            });
        }
        this.c();
    }

    public void onFinishInflate() {
        if (!this.i) {
            this.i = true;
            MessageCenterListView_.inflate((Context)this.getContext(), (int)2130903286, (ViewGroup)this);
            this.j.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

