/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.chat.message_views.ChatMessageGroupInviteBody;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingGreyDotView;
import java.util.ArrayList;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatMessageGroupInviteBody_
extends ChatMessageGroupInviteBody
implements HasViews,
OnViewChangedListener {
    private boolean n = false;
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();

    public ChatMessageGroupInviteBody_(Context context) {
        super(context);
        this.c();
    }

    public ChatMessageGroupInviteBody_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public ChatMessageGroupInviteBody_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.c();
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews object) {
        this.b = (TextView)object.findViewById(2131755398);
        this.d = (Button)object.findViewById(2131755405);
        this.e = (LinearLayout)object.findViewById(2131755399);
        this.f = (ImageView)object.findViewById(2131755397);
        this.g = object.findViewById(2131755221);
        ArrayList<Object> arrayList = new ArrayList<Object>();
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755400);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView2 = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755401);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView3 = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755402);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView4 = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755403);
        object = (ProfileImageWithVIPBadgeAndPendingGreyDotView)object.findViewById(2131755404);
        if (profileImageWithVIPBadgeAndPendingGreyDotView != null) {
            arrayList.add((Object)profileImageWithVIPBadgeAndPendingGreyDotView);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView2 != null) {
            arrayList.add((Object)profileImageWithVIPBadgeAndPendingGreyDotView2);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView3 != null) {
            arrayList.add((Object)profileImageWithVIPBadgeAndPendingGreyDotView3);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView4 != null) {
            arrayList.add((Object)profileImageWithVIPBadgeAndPendingGreyDotView4);
        }
        if (object != null) {
            arrayList.add(object);
        }
        this.c = arrayList;
        if (this.d != null) {
            this.d.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatMessageGroupInviteBody_.this.b();
                }
            });
        }
        if (this.g != null) {
            this.g.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatMessageGroupInviteBody_.this.b();
                }
            });
        }
    }

    public void onFinishInflate() {
        if (!this.n) {
            this.n = true;
            ChatMessageGroupInviteBody_.inflate((Context)this.getContext(), (int)2130903108, (ViewGroup)this);
            this.o.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

