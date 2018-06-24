/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.SimpleTypeTabs;
import java.util.ArrayList;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SimpleTypeTabs_
extends SimpleTypeTabs
implements HasViews,
OnViewChangedListener {
    private boolean e = false;
    private final OnViewChangedNotifier f = new OnViewChangedNotifier();

    public SimpleTypeTabs_(Context context) {
        super(context);
        this.d();
    }

    public SimpleTypeTabs_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d();
    }

    private void d() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.f);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        ArrayList<View> arrayList = new ArrayList<View>();
        View view = hasViews.findViewById(2131756598);
        View view2 = hasViews.findViewById(2131756602);
        View view3 = hasViews.findViewById(2131756606);
        View view4 = hasViews.findViewById(2131756610);
        ArrayList<TextView> arrayList2 = new ArrayList<TextView>();
        TextView textView = (TextView)hasViews.findViewById(2131756599);
        TextView textView2 = (TextView)hasViews.findViewById(2131756603);
        TextView textView3 = (TextView)hasViews.findViewById(2131756607);
        TextView textView4 = (TextView)hasViews.findViewById(2131756611);
        ArrayList<TextView> arrayList3 = new ArrayList<TextView>();
        TextView textView5 = (TextView)hasViews.findViewById(2131756600);
        TextView textView6 = (TextView)hasViews.findViewById(2131756604);
        TextView textView7 = (TextView)hasViews.findViewById(2131756608);
        TextView textView8 = (TextView)hasViews.findViewById(2131756612);
        ArrayList<Object> arrayList4 = new ArrayList<Object>();
        ImageView imageView = (ImageView)hasViews.findViewById(2131756601);
        ImageView imageView2 = (ImageView)hasViews.findViewById(2131756605);
        ImageView imageView3 = (ImageView)hasViews.findViewById(2131756609);
        hasViews = (ImageView)hasViews.findViewById(2131756613);
        if (view != null) {
            arrayList.add(view);
        }
        if (view2 != null) {
            arrayList.add(view2);
        }
        if (view3 != null) {
            arrayList.add(view3);
        }
        if (view4 != null) {
            arrayList.add(view4);
        }
        this.a = arrayList;
        if (textView != null) {
            arrayList2.add(textView);
        }
        if (textView2 != null) {
            arrayList2.add(textView2);
        }
        if (textView3 != null) {
            arrayList2.add(textView3);
        }
        if (textView4 != null) {
            arrayList2.add(textView4);
        }
        this.b = arrayList2;
        if (textView5 != null) {
            arrayList3.add(textView5);
        }
        if (textView6 != null) {
            arrayList3.add(textView6);
        }
        if (textView7 != null) {
            arrayList3.add(textView7);
        }
        if (textView8 != null) {
            arrayList3.add(textView8);
        }
        this.c = arrayList3;
        if (imageView != null) {
            arrayList4.add((Object)imageView);
        }
        if (imageView2 != null) {
            arrayList4.add((Object)imageView2);
        }
        if (imageView3 != null) {
            arrayList4.add((Object)imageView3);
        }
        if (hasViews != null) {
            arrayList4.add((Object)hasViews);
        }
        this.d = arrayList4;
        this.a();
        this.b();
    }

    public void onFinishInflate() {
        if (!this.e) {
            this.e = true;
            SimpleTypeTabs_.inflate((Context)this.getContext(), (int)2130903408, (ViewGroup)this);
            this.f.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

