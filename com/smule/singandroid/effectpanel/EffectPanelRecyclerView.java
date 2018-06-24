/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.Nullable
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$Adapter
 *  android.support.v7.widget.RecyclerView$LayoutManager
 *  android.util.AttributeSet
 *  android.widget.LinearLayout
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.smule.singandroid.common.CenterLayoutManager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class EffectPanelRecyclerView
extends LinearLayout {
    @ViewById
    protected RecyclerView a;

    public EffectPanelRecyclerView(Context context) {
        this(context, null);
    }

    public EffectPanelRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EffectPanelRecyclerView(Context context, @Nullable AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    @AfterViews
    protected void a() {
        this.a.setLayoutManager((RecyclerView.LayoutManager)new CenterLayoutManager(this.getContext(), 0, false, CenterLayoutManager.ScrollType.b));
    }

    public void a(int n) {
        this.a.smoothScrollToPosition(n);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.a.setAdapter(adapter);
    }
}

