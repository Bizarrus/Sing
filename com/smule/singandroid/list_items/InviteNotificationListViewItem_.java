/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.VideoUploadStatusView;
import com.smule.singandroid.list_items.InviteNotificationListViewItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class InviteNotificationListViewItem_
extends InviteNotificationListViewItem
implements HasViews,
OnViewChangedListener {
    private boolean E = false;
    private final OnViewChangedNotifier F = new OnViewChangedNotifier();

    public InviteNotificationListViewItem_(Context context) {
        super(context);
        this.x();
    }

    public static InviteNotificationListViewItem b(Context object) {
        object = new InviteNotificationListViewItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void x() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.F);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.ae = (PlayableItemView)hasViews.findViewById(2131755320);
        this.C = (VideoUploadStatusView)hasViews.findViewById(2131755316);
        this.f = hasViews.findViewById(2131755315);
        this.g = (TextView)hasViews.findViewById(2131755323);
        this.h = (TextView)hasViews.findViewById(2131756207);
        this.i = (TextView)hasViews.findViewById(2131755317);
        this.j = (TextView)hasViews.findViewById(2131755242);
        this.k = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755241);
        this.l = (TextView)hasViews.findViewById(2131755822);
        this.m = (Button)hasViews.findViewById(2131755321);
        this.n = (ImageView)hasViews.findViewById(2131755322);
        this.o = (TextView)hasViews.findViewById(2131756209);
        this.p = (TextView)hasViews.findViewById(2131755243);
        this.q = hasViews.findViewById(2131756206);
        this.r = hasViews.findViewById(2131755239);
        this.s = hasViews.findViewById(2131755318);
        this.t = (Button)hasViews.findViewById(2131756210);
        this.u = hasViews.findViewById(2131755244);
        this.v = hasViews.findViewById(2131756205);
        this.w = hasViews.findViewById(2131756208);
        this.x = (TextView)hasViews.findViewById(2131755325);
        this.y = (TextView)hasViews.findViewById(2131755326);
        this.z = hasViews.findViewById(2131755218);
        this.A = (TextView)hasViews.findViewById(2131756223);
        this.B = hasViews.findViewById(2131756222);
        this.a = (ImageView)hasViews.findViewById(2131755252);
        this.b = hasViews.findViewById(2131755250);
        if (this.s != null) {
            this.s.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteNotificationListViewItem_.this.h();
                }
            });
        }
        if (this.t != null) {
            this.t.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteNotificationListViewItem_.this.l();
                }
            });
        }
        this.p();
    }

    public void onFinishInflate() {
        if (!this.E) {
            this.E = true;
            InviteNotificationListViewItem_.inflate((Context)this.getContext(), (int)2130903256, (ViewGroup)this);
            this.F.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

