/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$LayoutManager
 *  org.androidannotations.annotations.EViewGroup
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.smule.singandroid.common.CenterLayoutManager;
import com.smule.singandroid.effectpanel.EffectPanelRecyclerView;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup
public class VocalEffectPanelRecyclerView
extends EffectPanelRecyclerView {
    public VocalEffectPanelRecyclerView(Context context) {
        super(context);
    }

    @Override
    protected void a() {
        this.a.setLayoutManager((RecyclerView.LayoutManager)new CenterLayoutManager(this.getContext(), 0, false, CenterLayoutManager.ScrollType.a));
    }
}

