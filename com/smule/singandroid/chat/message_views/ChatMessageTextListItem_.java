/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.chat.message_views.ChatMessageTextListItem;
import com.smule.singandroid.chat.message_views.MessageTimestampStatus;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatMessageTextListItem_
extends ChatMessageTextListItem
implements HasViews,
OnViewChangedListener {
    private boolean p = false;
    private final OnViewChangedNotifier q = new OnViewChangedNotifier();

    public ChatMessageTextListItem_(Context context) {
        super(context);
        this.g();
    }

    public static ChatMessageTextListItem b(Context object) {
        object = new ChatMessageTextListItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void g() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.q);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.b = hasViews.findViewById(2131755423);
        this.c = (LinearLayout)hasViews.findViewById(2131755424);
        this.d = (LinearLayout)hasViews.findViewById(2131755426);
        this.e = (TextView)hasViews.findViewById(2131755353);
        this.f = hasViews.findViewById(2131755431);
        this.g = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755425);
        this.h = (TextView)hasViews.findViewById(2131755427);
        this.i = (MessageTimestampStatus)hasViews.findViewById(2131755417);
        this.o = (TextView)hasViews.findViewById(2131755433);
        if (this.c != null) {
            this.c.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatMessageTextListItem_.this.c();
                }
            });
        }
        this.f();
    }

    @Override
    public void onFinishInflate() {
        if (!this.p) {
            this.p = true;
            ChatMessageTextListItem_.inflate((Context)this.getContext(), (int)2130903116, (ViewGroup)this);
            this.q.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

