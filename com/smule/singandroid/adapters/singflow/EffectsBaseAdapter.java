/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$Adapter
 *  android.support.v7.widget.RecyclerView$ViewHolder
 *  android.view.View
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 */
package com.smule.singandroid.adapters.singflow;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.effectpanel.EffectPanelButtonIcon;

public abstract class EffectsBaseAdapter<VH extends RecyclerView.ViewHolder>
extends RecyclerView.Adapter<VH> {

    public class EffectsViewHolder
    extends RecyclerView.ViewHolder {
        public EffectPanelButtonIcon a;
        public FrameLayout b;
        public TextView c;
        public ProgressBar d;
        public ImageView e;

        public EffectsViewHolder(View view) {
            super(view);
            this.a = (EffectPanelButtonIcon)view.findViewById(2131755688);
            this.b = (FrameLayout)view.findViewById(2131755692);
            this.c = (TextView)view.findViewById(2131755690);
            this.d = (ProgressBar)view.findViewById(2131755691);
            this.e = (ImageView)view.findViewById(2131755689);
        }

        protected void a() {
        }

        protected void b() {
        }

        protected void c() {
        }
    }

}

