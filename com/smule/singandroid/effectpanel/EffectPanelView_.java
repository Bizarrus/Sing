/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.design.widget.TabLayout
 *  android.support.v7.widget.RecyclerView
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.SeekBar
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.effectpanel.AlyceSecondLayerView;
import com.smule.singandroid.effectpanel.EffectPanelView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class EffectPanelView_
extends EffectPanelView
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier A = new OnViewChangedNotifier();
    private boolean z = false;

    public EffectPanelView_(Context context) {
        super(context);
        this.n();
    }

    public EffectPanelView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n();
    }

    private void n() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.A);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (TabLayout)hasViews.findViewById(2131755701);
        this.b = (CustomViewPager)hasViews.findViewById(2131755702);
        this.c = (RelativeLayout)hasViews.findViewById(2131755700);
        this.d = (RelativeLayout)hasViews.findViewById(2131755703);
        this.e = (ImageView)hasViews.findViewById(2131755706);
        this.f = (LinearLayout)hasViews.findViewById(2131755707);
        this.g = (TextView)hasViews.findViewById(2131755705);
        this.h = (RecyclerView)hasViews.findViewById(2131755709);
        this.i = (AlyceSecondLayerView)hasViews.findViewById(2131755708);
        this.j = (RelativeLayout)hasViews.findViewById(2131755710);
        this.k = (TextView)hasViews.findViewById(2131755712);
        this.l = (SeekBar)hasViews.findViewById(2131755713);
        this.m = (RelativeLayout)hasViews.findViewById(2131755714);
        this.n = (TextView)hasViews.findViewById(2131755716);
        this.o = (SeekBar)hasViews.findViewById(2131755717);
        this.p = (RelativeLayout)hasViews.findViewById(2131755718);
        this.q = (TextView)hasViews.findViewById(2131755720);
        this.r = (SeekBar)hasViews.findViewById(2131755721);
        this.s = (RelativeLayout)hasViews.findViewById(2131755722);
        this.t = (TextView)hasViews.findViewById(2131755724);
        this.u = (SeekBar)hasViews.findViewById(2131755725);
        this.v = (RelativeLayout)hasViews.findViewById(2131755726);
        this.w = (TextView)hasViews.findViewById(2131755727);
        this.x = (TextView)hasViews.findViewById(2131755728);
        if (this.e != null) {
            this.e.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    EffectPanelView_.this.m();
                }
            });
        }
    }

    public void onFinishInflate() {
        if (!this.z) {
            this.z = true;
            EffectPanelView_.inflate((Context)this.getContext(), (int)2130903223, (ViewGroup)this);
            this.A.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

