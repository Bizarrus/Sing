/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.ActionBarCustomView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ActionBarCustomView_
extends ActionBarCustomView
implements HasViews,
OnViewChangedListener {
    private boolean o = false;
    private final OnViewChangedNotifier p = new OnViewChangedNotifier();

    public ActionBarCustomView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public ActionBarCustomView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.c();
    }

    public static ActionBarCustomView a(Context object, AttributeSet attributeSet) {
        object = new ActionBarCustomView_((Context)object, attributeSet);
        object.onFinishInflate();
        return object;
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.p);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.g = hasViews.findViewById(2131755224);
        this.h = (ImageView)hasViews.findViewById(2131755226);
        this.i = (TextView)hasViews.findViewById(2131755228);
        this.j = (TextView)hasViews.findViewById(2131755229);
        this.k = (TextView)hasViews.findViewById(2131755225);
        this.l = (ImageView)hasViews.findViewById(2131755230);
        this.m = (TextView)hasViews.findViewById(2131755231);
        this.b = hasViews.findViewById(2131755233);
        this.c = hasViews.findViewById(2131755964);
        this.d = (ViewGroup)hasViews.findViewById(2131755232);
        this.e = hasViews.findViewById(2131755234);
        if (this.i != null) {
            this.i.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ActionBarCustomView_.this.b();
                }
            });
        }
        if (this.h != null) {
            this.h.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ActionBarCustomView_.this.b();
                }
            });
        }
    }

    public void onFinishInflate() {
        if (!this.o) {
            this.o = true;
            ActionBarCustomView_.inflate((Context)this.getContext(), (int)2130903070, (ViewGroup)this);
            this.p.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

