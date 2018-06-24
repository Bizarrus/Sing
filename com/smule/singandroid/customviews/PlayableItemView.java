/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.widget.FrameLayout
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.smule.singandroid.R;
import com.smule.singandroid.SingApplication;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PlayableItemView
extends FrameLayout {
    @ViewById
    public ImageView a;
    @ViewById
    public View b;
    @ViewById
    public ImageView c;
    @ViewById
    public View d;
    @ViewById
    public View e;
    @ViewById
    public ProgressBar f;
    @ViewById
    public ImageView g;
    @ViewById
    protected View h;
    @ViewById
    public ImageButton i;
    @ViewById
    public ImageButton j;
    @ViewById
    public ImageView k;
    @ViewById
    public ImageView l;
    @ViewById
    public ImageView m;
    private Drawable n;
    private Drawable o;
    private Drawable p;
    private boolean q;
    private boolean r = false;
    private boolean s;
    private boolean t;
    private boolean u;

    public PlayableItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet);
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void a() {
        int n = 0;
        this.m.setVisibility(8);
        if (this.t) {
            if (this.n != null) {
                this.a.setImageDrawable(this.n);
            }
        } else {
            this.a.setImageDrawable(null);
            this.d.setBackgroundColor(0);
        }
        if (this.o != null) {
            this.c.setImageDrawable(this.o);
        }
        if (this.p != null) {
            this.g.setImageDrawable(this.p);
        }
        View view = this.h;
        int n2 = this.q ? 0 : 8;
        view.setVisibility(n2);
        view = this.e;
        n2 = this.q ? n : 8;
        view.setVisibility(n2);
        this.b();
    }

    protected void a(Context context, AttributeSet attributeSet) {
        context = context.obtainStyledAttributes(attributeSet, R.PlayableItemView);
        this.n = context.getDrawable(0);
        attributeSet = context.getDrawable(1);
        if (attributeSet != null) {
            this.o = attributeSet;
        }
        if ((attributeSet = context.getDrawable(2)) != null) {
            this.p = attributeSet;
        }
        this.q = context.getBoolean(3, false);
        this.t = context.getBoolean(4, true);
        this.u = context.getBoolean(5, true);
        context.recycle();
    }

    public void a(boolean bl) {
        this.r = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl, int n) {
        int n2 = 0;
        if (!this.u) {
            return;
        }
        boolean bl2 = bl && SingApplication.n();
        this.k.setImageResource(n);
        ImageView imageView = this.k;
        int n3 = bl2 ? 0 : 8;
        imageView.setVisibility(n3);
        this.l.setImageResource(n);
        imageView = this.l;
        n = bl2 ? n2 : 8;
        imageView.setVisibility(n);
    }

    public void b() {
        this.b.setVisibility(8);
        this.d.setVisibility(8);
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.h.setVisibility(8);
        this.e.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void c() {
        int n = 0;
        View view = this.d;
        int n2 = this.r ? 8 : 0;
        view.setVisibility(n2);
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        view = this.h;
        n2 = this.q && !this.s ? 0 : 8;
        view.setVisibility(n2);
        view = this.e;
        n2 = this.q && !this.s ? n : 8;
        view.setVisibility(n2);
    }

    public void d() {
        this.b.setVisibility(8);
        this.d.setVisibility(8);
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        this.e.setVisibility(8);
    }
}

