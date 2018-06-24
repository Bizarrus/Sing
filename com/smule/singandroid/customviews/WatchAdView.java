/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.support.v4.content.ContextCompat
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Button
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.SingServerValues;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class WatchAdView
extends LinearLayout {
    @ViewById
    protected TextView a;
    @ViewById
    protected TextView b;
    @ViewById
    protected Button c;
    private Context d;

    public WatchAdView(Context context) {
        super(context);
        this.d = context;
    }

    @AfterViews
    protected void a() {
        if (new SingServerValues().aa()) {
            this.a.setTextColor(-1);
            this.b.setTextColor(-1);
            this.c.setTextColor(ContextCompat.getColor((Context)this.d, (int)2131689974));
            this.c.setBackground(ContextCompat.getDrawable((Context)this.d, (int)2130837677));
            ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
            layoutParams.height = this.d.getResources().getDimensionPixelOffset(2131427760);
            this.c.setLayoutParams(layoutParams);
        }
    }

    public void a(View.OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void setButtonText(String string2) {
        this.c.setText((CharSequence)string2);
    }

    public void setSubtitleText(String string2) {
        this.b.setText((CharSequence)string2);
    }

    public void setTitleText(String string2) {
        this.a.setText((CharSequence)string2);
    }
}

