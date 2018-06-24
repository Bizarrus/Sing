/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.smule.singandroid.customviews.BetweenRelativeLayout;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class OrDivider
extends BetweenRelativeLayout {
    @ViewById
    protected View b;
    @ViewById
    protected TextView c;
    @ViewById
    protected View d;

    public OrDivider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OrDivider(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public void a() {
        this.b.setBackgroundColor(-1);
        this.d.setBackgroundColor(-1);
        this.c.setTextColor(-1);
    }
}

