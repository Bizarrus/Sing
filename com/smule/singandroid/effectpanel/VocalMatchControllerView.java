/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.ColorFilter
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffColorFilter
 *  android.graphics.drawable.Drawable
 *  android.support.v4.content.ContextCompat
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  android.widget.TextView
 *  com.smule.singandroid.utils.UIHelper
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.utils.UIHelper;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class VocalMatchControllerView
extends RelativeLayout
implements View.OnTouchListener {
    @ViewById
    protected TextView a;
    @ViewById
    protected SeekBar b;
    @ViewById
    protected TextView c;
    @ViewById
    protected FrameLayout d;
    @ViewById
    protected ImageView e;
    @ViewById
    protected ImageView f;
    @ViewById
    protected FrameLayout g;
    @ViewById
    protected ImageView h;
    @ViewById
    protected ImageView i;
    private boolean j;

    public VocalMatchControllerView(Context context) {
        super(context);
    }

    public void a() {
        if (!this.j) {
            this.c.setTextColor(-1);
            this.j = true;
        }
    }

    public void a(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        UIHelper.a((ProgressBar)this.b, (int)ContextCompat.getColor((Context)this.getContext(), (int)2131689727));
        this.b.setRotation(180.0f);
        this.b.setThumbOffset(this.getContext().getResources().getDimensionPixelOffset(2131427517));
        this.b.setOnSeekBarChangeListener(onSeekBarChangeListener);
        this.d.setOnTouchListener((View.OnTouchListener)this);
        this.g.setOnTouchListener((View.OnTouchListener)this);
    }

    @Click
    protected void b() {
        this.j = false;
        this.c.setTextColor(ContextCompat.getColor((Context)this.getContext(), (int)2131689726));
        NotificationCenter.a().b("NOTIFICATION_RESET_VOCAL_MATCH", new Object[0]);
    }

    public int getMax() {
        return this.b.getMax();
    }

    public int getProgress() {
        return this.b.getProgress();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int n;
        ImageView imageView;
        switch (view.getId()) {
            default: {
                return false;
            }
            case 2131756804: {
                view = this.e;
                ImageView imageView2 = this.f;
                n = 1;
                imageView = view;
                view = imageView2;
                break;
            }
            case 2131756808: {
                imageView = this.h;
                view = this.i;
                n = -1;
            }
        }
        switch (motionEvent.getAction()) {
            default: {
                return false;
            }
            case 0: {
                imageView.setVisibility(0);
                motionEvent = new PorterDuffColorFilter(ContextCompat.getColor((Context)this.getContext(), (int)2131689724), PorterDuff.Mode.MULTIPLY);
                view.getDrawable().mutate().setColorFilter((ColorFilter)motionEvent);
                return true;
            }
            case 1: 
            case 3: 
        }
        imageView.setVisibility(8);
        view.setImageResource(2130837873);
        this.b.setProgress(n * 10 + this.b.getProgress());
        this.a();
        NotificationCenter.a().b("NOTIFICATION_ADJUST_VOCAL_MATCH", new Object[0]);
        return false;
    }

    public void setMax(int n) {
        this.b.setMax(n);
    }

    public void setProgress(int n) {
        this.b.setProgress(n);
    }

    public void setVocalMatchText(String string2) {
        this.a.setText((CharSequence)string2);
    }
}

