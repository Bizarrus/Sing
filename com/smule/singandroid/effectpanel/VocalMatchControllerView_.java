/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.SeekBar
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.singandroid.effectpanel.VocalMatchControllerView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class VocalMatchControllerView_
extends VocalMatchControllerView
implements HasViews,
OnViewChangedListener {
    private boolean j = false;
    private final OnViewChangedNotifier k = new OnViewChangedNotifier();

    public VocalMatchControllerView_(Context context) {
        super(context);
        this.c();
    }

    public static VocalMatchControllerView a(Context object) {
        object = new VocalMatchControllerView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.k);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131756802);
        this.b = (SeekBar)hasViews.findViewById(2131756803);
        this.c = (TextView)hasViews.findViewById(2131756807);
        this.d = (FrameLayout)hasViews.findViewById(2131756804);
        this.e = (ImageView)hasViews.findViewById(2131756805);
        this.f = (ImageView)hasViews.findViewById(2131756806);
        this.g = (FrameLayout)hasViews.findViewById(2131756808);
        this.h = (ImageView)hasViews.findViewById(2131756809);
        this.i = (ImageView)hasViews.findViewById(2131756810);
        if (this.c != null) {
            this.c.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    VocalMatchControllerView_.this.b();
                }
            });
        }
    }

    public void onFinishInflate() {
        if (!this.j) {
            this.j = true;
            VocalMatchControllerView_.inflate((Context)this.getContext(), (int)2130903462, (ViewGroup)this);
            this.k.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

