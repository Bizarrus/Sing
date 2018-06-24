/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.CheckBox
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.UserInviteItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UserInviteItem_
extends UserInviteItem
implements HasViews,
OnViewChangedListener {
    private boolean f;
    private final OnViewChangedNotifier g;

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131755218);
        this.b = (TextView)hasViews.findViewById(2131755298);
        this.c = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755297);
        this.d = (CheckBox)hasViews.findViewById(2131756776);
        this.e = (TextView)hasViews.findViewById(2131756777);
    }

    @Override
    public void onFinishInflate() {
        if (!this.f) {
            this.f = true;
            UserInviteItem_.inflate((Context)this.getContext(), (int)2130903457, (ViewGroup)this);
            this.g.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

