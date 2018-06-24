/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.LayoutUtils
 */
package com.smule.singandroid.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.ui.OverlayWithHoleImageView;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.utils.LayoutUtils;

public class ChatTooltipDialog
extends SmuleDialog {
    protected RelativeLayout a;
    protected TextView b;
    protected RelativeLayout c;
    protected View d;
    protected OverlayWithHoleImageView e;
    private ViewGroup f;
    private int g;
    private int h;
    private View.OnClickListener i;
    private View.OnClickListener j;

    public ChatTooltipDialog(BaseActivity baseActivity, int n, int n2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super((Context)baseActivity, 16973840, false);
        this.f = (ViewGroup)LayoutInflater.from((Context)baseActivity).inflate(2130903120, null, false);
        this.setContentView((View)this.f);
        this.a = (RelativeLayout)this.f.findViewById(2131755459);
        this.b = (TextView)this.f.findViewById(2131755463);
        this.c = (RelativeLayout)this.f.findViewById(2131755461);
        this.d = this.f.findViewById(2131755462);
        this.e = (OverlayWithHoleImageView)this.f.findViewById(2131755460);
        this.a(n, n2, onClickListener, onClickListener2);
    }

    protected void a() {
        int n = LayoutUtils.a((int)24, (Context)this.getContext());
        this.e.a(this.i, this.j);
        this.e.a(this.g, this.h, n);
        this.d.setX((float)(this.g - this.d.getWidth() / 2));
    }

    public void a(int n) {
        this.b.setText(n);
    }

    public void a(int n, int n2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.g = n;
        this.h = n2;
        this.i = onClickListener;
        this.j = onClickListener2;
        this.a();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}

