/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.TextureView
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.FrameLayout
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
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.list_items.FeedListViewItem;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FeedListViewItem_
extends FeedListViewItem
implements HasViews,
OnViewChangedListener {
    private boolean ag = false;
    private final OnViewChangedNotifier ah = new OnViewChangedNotifier();

    public FeedListViewItem_(Context context) {
        super(context);
        this.v();
    }

    public static FeedListViewItem b(Context object) {
        object = new FeedListViewItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void v() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.ah);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.ae = (PlayableItemView)hasViews.findViewById(2131755320);
        this.a = (ImageView)hasViews.findViewById(2131755252);
        this.b = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755776);
        this.c = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755266);
        this.d = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755267);
        this.e = (ImageView)hasViews.findViewById(2131755318);
        this.f = (TextView)hasViews.findViewById(2131755243);
        this.g = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755756);
        this.i = (ImageView)hasViews.findViewById(2131755757);
        this.j = (TextView)hasViews.findViewById(2131755758);
        this.k = hasViews.findViewById(2131755770);
        this.l = (TextView)hasViews.findViewById(2131755772);
        this.m = (TextView)hasViews.findViewById(2131755773);
        this.n = hasViews.findViewById(2131755774);
        this.o = (TextView)hasViews.findViewById(2131755775);
        this.p = (ImageButton)hasViews.findViewById(2131755777);
        this.q = (ProgressBar)hasViews.findViewById(2131755778);
        this.r = hasViews.findViewById(2131755296);
        this.s = (TextView)hasViews.findViewById(2131755779);
        this.t = (TextView)hasViews.findViewById(2131755781);
        this.u = (TextView)hasViews.findViewById(2131755780);
        this.v = (TextView)hasViews.findViewById(2131755783);
        this.w = (TextView)hasViews.findViewById(2131755784);
        this.x = (TextView)hasViews.findViewById(2131755785);
        this.y = (Button)hasViews.findViewById(2131755321);
        this.z = (ImageView)hasViews.findViewById(2131755322);
        this.A = hasViews.findViewById(2131755782);
        this.B = (ImageView)hasViews.findViewById(2131755761);
        this.C = (RippleBackground)hasViews.findViewById(2131755762);
        this.D = (FrameLayout)hasViews.findViewById(2131755760);
        this.E = (TextureView)hasViews.findViewById(2131755763);
        this.F = hasViews.findViewById(2131755764);
        this.G = hasViews.findViewById(2131755765);
        this.H = (TextView)hasViews.findViewById(2131755769);
        this.I = hasViews.findViewById(2131755768);
        this.J = hasViews.findViewById(2131755786);
        this.K = (TextView)hasViews.findViewById(2131755787);
        this.L = (TextView)hasViews.findViewById(2131755788);
        this.M = (TextView)hasViews.findViewById(2131755789);
        this.N = (TextView)hasViews.findViewById(2131755790);
        this.O = hasViews.findViewById(2131755791);
        this.P = hasViews.findViewById(2131755793);
        this.Q = hasViews.findViewById(2131755792);
        this.R = (ImageView)hasViews.findViewById(2131755767);
        this.S = hasViews.findViewById(2131755759);
        if (this.e != null) {
            this.e.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FeedListViewItem_.this.e();
                }
            });
        }
        this.p();
    }

    @Override
    public void a(final boolean bl, final boolean bl2, final boolean bl3) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                FeedListViewItem_.super.a(bl, bl2, bl3);
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.ag) {
            this.ag = true;
            FeedListViewItem_.inflate((Context)this.getContext(), (int)2130903229, (ViewGroup)this);
            this.ah.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

