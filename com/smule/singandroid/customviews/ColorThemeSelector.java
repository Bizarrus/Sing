/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  com.smule.singandroid.utils.ColorSelectorDrawableUtil
 *  com.smule.singandroid.utils.ColorSelectorDrawableUtil$Type
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.smule.singandroid.profile.Theme;
import com.smule.singandroid.utils.ColorSelectorDrawableUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ColorThemeSelector
extends LinearLayout {
    @ViewById
    protected LinearLayout a;
    private Theme b;
    private ArrayList<ImageView> c = new ArrayList();
    private OnThemeSelectedListener d;
    private View.OnClickListener e;

    public ColorThemeSelector(Context context) {
        this(context, null);
    }

    public ColorThemeSelector(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorThemeSelector(Context context, @Nullable AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.e = new View.OnClickListener(){

            public void onClick(View view) {
                ((ImageView)ColorThemeSelector.this.c.get(ColorThemeSelector.this.b.ordinal())).setSelected(false);
                ColorThemeSelector.this.b = (Theme)((Object)view.getTag());
                view.setSelected(true);
                if (ColorThemeSelector.this.d != null) {
                    ColorThemeSelector.this.d.a(ColorThemeSelector.this.b);
                }
            }
        };
        this.setOnClickListener(null);
        this.b = Theme.a;
    }

    @AfterViews
    protected void a() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        for (Theme theme : Theme.values()) {
            Drawable drawable2 = ColorSelectorDrawableUtil.a((Context)this.getContext(), (int)theme.a(), (int)theme.b(), (ColorSelectorDrawableUtil.Type)ColorSelectorDrawableUtil.Type.a);
            ImageView imageView = new ImageView(this.getContext());
            imageView.setImageDrawable(drawable2);
            imageView.setTag((Object)theme);
            imageView.setOnClickListener(this.e);
            this.a.addView((View)imageView, (ViewGroup.LayoutParams)layoutParams);
            this.c.add(imageView);
        }
    }

    public Theme getTheme() {
        return this.b;
    }

    public void setOnThemeSelectedListener(OnThemeSelectedListener onThemeSelectedListener) {
        this.d = onThemeSelectedListener;
    }

    public void setTheme(Theme theme) {
        Iterator<ImageView> iterator = this.c.iterator();
        while (iterator.hasNext()) {
            iterator.next().setSelected(false);
        }
        this.b = theme;
        this.c.get(theme.ordinal()).setSelected(true);
    }

    public static interface OnThemeSelectedListener {
        public void a(@NonNull Theme var1);
    }

}

