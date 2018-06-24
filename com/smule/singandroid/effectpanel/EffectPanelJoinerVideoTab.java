/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.widget.FrameLayout
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.content.res.Resources;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class EffectPanelJoinerVideoTab
extends FrameLayout {
    @ViewById
    protected ProgressBar a;
    @ViewById
    protected TextView b;

    public EffectPanelJoinerVideoTab(Context context) {
        super(context);
    }

    public void a(String string2, String string3) {
        this.b.setText((CharSequence)this.getContext().getResources().getString(2131296763, new Object[]{string2, string3}));
        this.a.setVisibility(8);
        this.b.setVisibility(0);
    }
}

