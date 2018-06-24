/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.TextureView
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.singandroid.customviews.AnimatableCardView;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SearchMediaExpandableListViewItem_
extends SearchMediaExpandableListViewItem
implements HasViews,
OnViewChangedListener {
    private boolean I = false;
    private final OnViewChangedNotifier J = new OnViewChangedNotifier();

    public SearchMediaExpandableListViewItem_(Context context) {
        super(context);
        this.h();
    }

    public static SearchMediaExpandableListViewItem b(Context object) {
        object = new SearchMediaExpandableListViewItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void h() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.J);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.ae = (PlayableItemView)hasViews.findViewById(2131755320);
        this.a = (RelativeLayout)hasViews.findViewById(2131756482);
        this.b = (RelativeLayout)hasViews.findViewById(2131755218);
        this.c = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755745);
        this.d = (ImageView)hasViews.findViewById(2131755976);
        this.e = (TextView)hasViews.findViewById(2131755804);
        this.f = (TextView)hasViews.findViewById(2131756483);
        this.g = (TextView)hasViews.findViewById(2131756484);
        this.h = (TextView)hasViews.findViewById(2131755978);
        this.i = (FrameLayout)hasViews.findViewById(2131755759);
        this.j = (RelativeLayout)hasViews.findViewById(2131755760);
        this.k = (RelativeLayout)hasViews.findViewById(2131756453);
        this.l = (ImageView)hasViews.findViewById(2131756485);
        this.m = hasViews.findViewById(2131756486);
        this.n = (RippleBackground)hasViews.findViewById(2131756487);
        this.o = (AnimatableCardView)hasViews.findViewById(2131756488);
        this.p = (ImageView)hasViews.findViewById(2131755281);
        this.q = hasViews.findViewById(2131756491);
        this.r = (TextureView)hasViews.findViewById(2131756086);
        this.s = hasViews.findViewById(2131756493);
        this.t = hasViews.findViewById(2131756494);
        this.u = (Button)hasViews.findViewById(2131755414);
        this.v = (TextView)hasViews.findViewById(2131756496);
        this.w = (TextView)hasViews.findViewById(2131756497);
        this.x = (TextView)hasViews.findViewById(2131755293);
        this.y = (TextView)hasViews.findViewById(2131756489);
        this.z = (TextView)hasViews.findViewById(2131756490);
        this.A = (RelativeLayout)hasViews.findViewById(2131756495);
        this.B = hasViews.findViewById(2131755283);
        this.p();
    }

    public void onFinishInflate() {
        if (!this.I) {
            this.I = true;
            SearchMediaExpandableListViewItem_.inflate((Context)this.getContext(), (int)2130903396, (ViewGroup)this);
            this.J.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

