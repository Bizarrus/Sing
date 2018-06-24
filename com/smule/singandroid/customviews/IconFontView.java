/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Typeface
 *  android.support.annotation.Nullable
 *  android.support.v7.widget.AppCompatTextView
 *  android.util.AttributeSet
 *  com.smule.singandroid.utils.TypefaceUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EView
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.smule.singandroid.utils.TypefaceUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EView;

@EView
public class IconFontView
extends AppCompatTextView {
    public IconFontView(Context context) {
        this(context, null);
    }

    public IconFontView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconFontView(Context context, @Nullable AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    @AfterViews
    protected void a() {
        this.setTypeface(TypefaceUtils.d((Context)this.getContext()));
    }
}

