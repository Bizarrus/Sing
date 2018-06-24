/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.widget.LinearLayout
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class VolumeControllerView
extends LinearLayout {
    @ViewById
    protected TextView a;
    @ViewById
    protected TextView b;
    @ViewById
    protected SeekBar c;

    public VolumeControllerView(Context context) {
        super(context);
    }

    public void a(boolean bl, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.c.setThumbOffset(this.getContext().getResources().getDimensionPixelOffset(2131427517));
        this.c.setOnSeekBarChangeListener(onSeekBarChangeListener);
        if (bl) {
            this.a.setText(2131296771);
        }
    }

    public int getMax() {
        return this.c.getMax();
    }

    public int getProgress() {
        return this.c.getProgress();
    }

    public void setMax(int n) {
        this.c.setMax(n);
    }

    public void setProgress(int n) {
        this.c.setProgress(n);
    }

    public void setVolumeControlText(String string2) {
        this.b.setText((CharSequence)string2);
    }
}

