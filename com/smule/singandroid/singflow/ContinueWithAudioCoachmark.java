/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.constraint.ConstraintLayout
 *  android.view.View
 *  android.view.ViewParent
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  com.smule.singandroid.utils.LayoutUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 */
package com.smule.singandroid.singflow;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.utils.LayoutUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup
public class ContinueWithAudioCoachmark
extends ConstraintLayout {
    private WeakListener.OnGlobalLayoutListener a;
    private int b;

    public ContinueWithAudioCoachmark(Context context, int n) {
        super(context);
        this.b = n;
    }

    private void b() {
        this.setY((float)(this.b - this.getHeight()));
    }

    @AfterViews
    protected void a() {
        this.a = new WeakListener.OnGlobalLayoutListener((Object)this.getParent(), new ViewTreeObserver.OnGlobalLayoutListener(){

            public void onGlobalLayout() {
                LayoutUtils.b((View)ContinueWithAudioCoachmark.this, (WeakListener.OnGlobalLayoutListener)ContinueWithAudioCoachmark.this.a);
                ContinueWithAudioCoachmark.this.b();
            }
        });
        LayoutUtils.a((View)this, (WeakListener.OnGlobalLayoutListener)this.a);
    }

}

