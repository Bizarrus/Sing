/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.FrameLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.smule.singandroid.list_items.PerfCountHeader_;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PerfCountHeader
extends FrameLayout {
    @ViewById
    TextView a;
    @ViewById
    View b;

    public PerfCountHeader(Context context) {
        super(context);
    }

    public static PerfCountHeader a(Context context) {
        return PerfCountHeader_.b(context);
    }

    public TextView getTextView() {
        return this.a;
    }

    public void setBackgroundColor(int n) {
        this.b.setBackgroundColor(n);
    }

    public void setText(String string2) {
        this.a.setText((CharSequence)string2);
    }
}

