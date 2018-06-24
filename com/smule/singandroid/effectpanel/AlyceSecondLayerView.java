/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.Nullable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.LinearLayout
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.customviews.CyclableStateItemView;
import com.smule.singandroid.customviews.CyclableStateItemViewModel;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class AlyceSecondLayerView
extends LinearLayout {
    @ViewById
    protected CyclableStateItemView a;
    @ViewById
    protected CyclableStateItemView b;

    public AlyceSecondLayerView(Context context) {
        this(context, null);
    }

    public AlyceSecondLayerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AlyceSecondLayerView(Context context, @Nullable AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public void a(int n) {
        this.a.a(n);
    }

    public void a(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.a.setOnClickListener(onClickListener);
        this.b.setOnClickListener(onClickListener2);
    }

    public void a(List<CyclableStateItemViewModel> list, int n, List<CyclableStateItemViewModel> list2, int n2) {
        this.a.a(list, n);
        if (new SingServerValues().U()) {
            this.b.a(list2, n2);
            this.b.setVisibility(0);
            return;
        }
        this.b.setVisibility(8);
    }

    public void b(int n) {
        this.b.a(n);
    }

    public int getColorSelectorPostion() {
        return this.a.getPosition();
    }

    public int getIntensitySelectorPosition() {
        return this.b.getPosition();
    }

    public void setShowNextIconOnColorSelectorClick(boolean bl) {
        this.a.setShowNextIcon(bl);
    }

    public void setShowNextIconOnIntensitySelectorClick(boolean bl) {
        this.b.setShowNextIcon(bl);
    }
}

