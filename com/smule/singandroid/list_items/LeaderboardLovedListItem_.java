/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.VideoUploadStatusView;
import com.smule.singandroid.list_items.LeaderboardLovedListItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LeaderboardLovedListItem_
extends LeaderboardLovedListItem
implements HasViews,
OnViewChangedListener {
    private boolean p = false;
    private final OnViewChangedNotifier q = new OnViewChangedNotifier();

    public LeaderboardLovedListItem_(Context context) {
        super(context);
        this.v();
    }

    public static LeaderboardLovedListItem a(Context object) {
        object = new LeaderboardLovedListItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void v() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.q);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.ae = (PlayableItemView)hasViews.findViewById(2131755320);
        this.C = (VideoUploadStatusView)hasViews.findViewById(2131755316);
        this.c = hasViews.findViewById(2131755240);
        this.d = (TextView)hasViews.findViewById(2131755897);
        this.e = (TextView)hasViews.findViewById(2131755898);
        this.f = (TextView)hasViews.findViewById(2131755899);
        this.g = (TextView)hasViews.findViewById(2131756226);
        this.h = (TextView)hasViews.findViewById(2131755900);
        this.i = hasViews.findViewById(2131755218);
        this.j = (TextView)hasViews.findViewById(2131756223);
        this.k = (ProfileImageWithVIPBadge)hasViews.findViewById(2131756228);
        this.l = (TextView)hasViews.findViewById(2131756227);
        this.m = hasViews.findViewById(2131755893);
        this.n = hasViews.findViewById(2131756229);
        this.a = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755895);
        this.b = (TextView)hasViews.findViewById(2131755894);
        if (this.n != null) {
            this.n.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    LeaderboardLovedListItem_.this.k();
                }
            });
        }
        this.p();
    }

    public void onFinishInflate() {
        if (!this.p) {
            this.p = true;
            LeaderboardLovedListItem_.inflate((Context)this.getContext(), (int)2130903263, (ViewGroup)this);
            this.q.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

