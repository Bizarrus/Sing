/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.constraint.ConstraintLayout
 *  android.support.constraint.ConstraintLayout$LayoutParams
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  com.smule.singandroid.utils.LayoutUtils
 */
package com.smule.singandroid.singflow;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.smule.android.ui.OverlayWithHoleImageView;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.utils.LayoutUtils;

public class AddVideoCoachmarkDialog
extends SmuleDialog {
    private FrameLayout a;
    private OverlayWithHoleImageView b;
    private ConstraintLayout c;
    private ImageView d;
    private boolean e;
    private int f;
    private int g;
    private final View.OnClickListener h;
    private WeakListener.OnGlobalLayoutListener i;

    public AddVideoCoachmarkDialog(Context context, int[] arrn, boolean bl) {
        super(context, 2131493246, false);
        this.h = new View.OnClickListener(){

            public void onClick(View view) {
                AddVideoCoachmarkDialog.this.dismiss();
            }
        };
        this.a = (FrameLayout)LayoutInflater.from((Context)context).inflate(2130903076, null, false);
        this.setContentView((View)this.a);
        this.f = arrn[0];
        this.g = arrn[1];
        this.e = bl;
        this.a();
        this.b();
    }

    private int a(int n) {
        return n - this.c.getWidth();
    }

    private void a() {
        this.b = (OverlayWithHoleImageView)this.a.findViewById(2131755258);
        this.d = (ImageView)this.a.findViewById(2131755156);
        View view = this.a.findViewById(2131755262);
        View view2 = this.a.findViewById(2131755261);
        this.c = (ConstraintLayout)this.a.findViewById(2131755259);
        if (this.e) {
            view.setVisibility(8);
            view2.setVisibility(0);
            view2.setOnClickListener(this.h);
            return;
        }
        view.setVisibility(0);
        view2.setVisibility(8);
        view.setOnClickListener(this.h);
    }

    private int b(int n) {
        if (this.e) {
            return n - this.c.getHeight() / 2;
        }
        return n - (((ViewGroup.MarginLayoutParams)this.d.getLayoutParams()).topMargin + this.d.getHeight() / 2);
    }

    private void b() {
        this.i = new WeakListener.OnGlobalLayoutListener((Object)this.c, new ViewTreeObserver.OnGlobalLayoutListener(){

            public void onGlobalLayout() {
                LayoutUtils.b((View)AddVideoCoachmarkDialog.this.c, (WeakListener.OnGlobalLayoutListener)AddVideoCoachmarkDialog.this.i);
                AddVideoCoachmarkDialog.this.c();
            }
        });
        LayoutUtils.a((View)this.a, (WeakListener.OnGlobalLayoutListener)this.i);
    }

    private void c() {
        this.d();
        this.b.a(this.h, this.h);
    }

    private void d() {
        Resources resources = this.getContext().getResources();
        int n = resources.getDimensionPixelOffset(2131427952);
        int n2 = resources.getDimensionPixelOffset(2131428177);
        this.b.a(this.f, this.g, n);
        this.e();
        int n3 = this.f;
        this.c.setX((float)this.a(n3 - (n2 + n)));
        this.c.setY((float)this.b(this.g));
    }

    private void e() {
        if (this.e) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)this.d.getLayoutParams();
            layoutParams.topMargin = 0;
            layoutParams.bottomToBottom = 0;
            this.d.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        }
    }

}

