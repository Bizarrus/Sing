/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.BottomNavView;
import java.util.ArrayList;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class BottomNavView_
extends BottomNavView
implements HasViews,
OnViewChangedListener {
    private boolean o = false;
    private final OnViewChangedNotifier p = new OnViewChangedNotifier();

    public BottomNavView_(Context context) {
        super(context);
        this.f();
    }

    public BottomNavView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f();
    }

    public BottomNavView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.f();
    }

    private void f() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.p);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.b = hasViews.findViewById(2131755329);
        this.c = (TextView)hasViews.findViewById(2131755339);
        this.d = hasViews.findViewById(2131755334);
        this.e = hasViews.findViewById(2131755335);
        this.f = hasViews.findViewById(2131755336);
        this.g = hasViews.findViewById(2131755338);
        this.h = (ImageView)hasViews.findViewById(2131755337);
        ArrayList<Object> arrayList = new ArrayList<Object>();
        ImageButton imageButton = (ImageButton)hasViews.findViewById(2131755327);
        ImageButton imageButton2 = (ImageButton)hasViews.findViewById(2131755330);
        ImageButton imageButton3 = (ImageButton)hasViews.findViewById(2131755331);
        ImageButton imageButton4 = (ImageButton)hasViews.findViewById(2131755332);
        hasViews = (ImageButton)hasViews.findViewById(2131755340);
        if (imageButton != null) {
            arrayList.add((Object)imageButton);
        }
        if (imageButton2 != null) {
            arrayList.add((Object)imageButton2);
        }
        if (imageButton3 != null) {
            arrayList.add((Object)imageButton3);
        }
        if (imageButton4 != null) {
            arrayList.add((Object)imageButton4);
        }
        if (hasViews != null) {
            arrayList.add((Object)hasViews);
        }
        this.a = arrayList;
        this.a();
    }

    @Override
    public void b() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                BottomNavView_.super.b();
            }
        }, (long)0);
    }

    @Override
    public void c() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                BottomNavView_.super.c();
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.o) {
            this.o = true;
            BottomNavView_.inflate((Context)this.getContext(), (int)2130903094, (ViewGroup)this);
            this.p.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

