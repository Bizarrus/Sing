/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.support.annotation.ColorInt
 *  android.support.v4.content.ContextCompat
 *  android.support.v7.widget.SwitchCompat
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.TypefaceUtils
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.ui.TextDrawable;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.utils.TypefaceUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class AutoTuneControllerView
extends RelativeLayout {
    @ViewById
    protected SwitchCompat a;
    @ViewById
    protected TextView b;
    @ColorInt
    private int c;
    @ColorInt
    private int d;
    @ColorInt
    private int e;
    private TextDrawable f;

    public AutoTuneControllerView(Context context) {
        super(context);
    }

    public void a(@ColorInt int n) {
        this.c = n;
        if (this.a()) {
            this.b();
            return;
        }
        this.c();
    }

    public void a(CompoundButton.OnCheckedChangeListener onCheckedChangeListener, @ColorInt int n) {
        this.a.setOnCheckedChangeListener(onCheckedChangeListener);
        this.d = this.getResources().getColor(2131689910);
        this.e = this.getResources().getColor(2131689908);
        boolean bl = MagicPreferences.b(this.getContext(), "PREFS_LAST_AUTO_TUNE_ENABLED", new SingServerValues().as());
        this.a.setChecked(bl);
        this.a(n);
    }

    public boolean a() {
        return this.a.isChecked();
    }

    public TextDrawable b(@ColorInt int n) {
        int n2 = this.getResources().getDimensionPixelSize(2131427411);
        return TextDrawable.a().b().a(TypefaceUtils.d((Context)this.getContext())).d((int)((double)n2 * 0.75)).a(n2).b(n2).c(n).a().a(this.getResources().getString(2131297907), ContextCompat.getColor((Context)this.getContext(), (int)2131689494));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b() {
        if (this.c == this.d) {
            this.a.setThumbDrawable((Drawable)this.b(this.e));
            this.a.getTrackDrawable().setColorFilter(this.e, PorterDuff.Mode.SRC_ATOP);
        } else {
            this.a.setThumbDrawable((Drawable)this.b(this.getToggleColor()));
            this.a.getTrackDrawable().setColorFilter(this.getToggleColor(), PorterDuff.Mode.SRC_ATOP);
        }
        this.b.setTextColor(this.getResources().getColor(2131689496));
    }

    public void c() {
        if (this.f == null) {
            this.f = this.b(this.getResources().getColor(2131689498));
        }
        this.a.setThumbDrawable((Drawable)this.f);
        this.a.getTrackDrawable().setColorFilter(this.getResources().getColor(2131689497), PorterDuff.Mode.DST);
        this.b.setTextColor(this.getResources().getColor(2131689495));
    }

    public void d() {
        this.a.setClickable(false);
    }

    public void e() {
        this.a.setClickable(true);
    }

    public int getToggleColor() {
        return this.c;
    }
}

