/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapFactory
 *  android.graphics.Canvas
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.support.v4.content.ContextCompat
 *  android.support.v4.graphics.drawable.RoundedBitmapDrawable
 *  android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
 *  android.util.AttributeSet
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$VideoStyleType
 *  com.smule.singandroid.video.VideoFilterManager
 *  com.smule.singandroid.video.VideoFilterManager$VideoStyleItem
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.VocalEffect;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.effectpanel.ButtonState;
import com.smule.singandroid.effectpanel.EffectListItem;
import com.smule.singandroid.video.VideoEffects;
import com.smule.singandroid.video.VideoFilterManager;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class EffectPanelButtonIcon
extends FrameLayout {
    @ViewById
    protected ImageView a;
    @ViewById
    protected ImageView b;
    @ViewById
    protected IconFontView c;
    @ViewById
    protected ImageView d;
    @ViewById
    protected TextView e;
    private EffectListItem.EffectType f;
    private VideoFilterManager.VideoStyleItem g;
    private VocalEffect h;
    private ButtonState i;
    private ButtonState j = ButtonState.a;
    private boolean k;
    private boolean l;

    public EffectPanelButtonIcon(Context context) {
        super(context);
    }

    public EffectPanelButtonIcon(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EffectPanelButtonIcon(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    private RoundedBitmapDrawable a(Bitmap bitmap, float f) {
        bitmap = RoundedBitmapDrawableFactory.create((Resources)this.getResources(), (Bitmap)bitmap);
        bitmap.setCornerRadius(f);
        bitmap.setAntiAlias(true);
        return bitmap;
    }

    private String a(String string2) {
        return "video_style_" + string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a() {
        String string2 = this.j == ButtonState.a ? "not_active" : "active";
        switch (.a[this.f.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.setContentDescription((CharSequence)(this.h.b() + ":" + string2));
                return;
            }
            case 2: 
        }
        this.setContentDescription((CharSequence)(this.g.a.a() + ":" + string2));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(boolean bl) {
        int n;
        Object object;
        int n2 = -1;
        int n3 = this.getContext().getResources().getIdentifier("review_fx_" + this.h.b() + "_bg", "color", this.getContext().getPackageName());
        n3 = ContextCompat.getColor((Context)this.getContext(), (int)n3);
        if (this.b.getVisibility() == 0) {
            object = this.b;
            n = bl ? -1 : n3;
            object.setColorFilter(n, PorterDuff.Mode.MULTIPLY);
        }
        if (this.c.getVisibility() == 0) {
            object = this.c;
            n = bl ? n2 : n3;
            object.setTextColor(n);
        }
        this.a.setColorFilter(n3, PorterDuff.Mode.MULTIPLY);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b() {
        this.b.setVisibility(0);
        String string2 = this.a(this.g.a.a());
        int n = this.getContext().getResources().getIdentifier(string2, "drawable", this.getContext().getPackageName());
        string2 = BitmapFactory.decodeResource((Resources)this.getResources(), (int)n);
        int n2 = this.getResources().getDimensionPixelSize(2131427505);
        n = this.getResources().getDimensionPixelSize(2131428164);
        this.d.setPadding(n, n, n, n);
        this.b.setPadding(n, n, n, n);
        this.b.setImageDrawable((Drawable)this.a((Bitmap)string2, n2));
        this.a.setVisibility(8);
        n = !this.l || this.g.a == VideoEffects.VideoStyleType.a ? 1 : 0;
        if (this.g.c || n == 0) {
            this.d.setImageDrawable((Drawable)this.a(this.getDisabledOverlayBitmap(), n2));
            this.d.setVisibility(0);
            this.e.setVisibility(0);
            if (n == 0) {
                this.e.setText((CharSequence)this.getResources().getString(2131296767));
            }
            return;
        }
        switch (.b[this.j.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.d.setVisibility(8);
                this.c.setVisibility(8);
                return;
            }
            case 2: 
            case 3: 
        }
        this.d.setImageDrawable((Drawable)this.a(this.getOverlayBitmap(), n2));
        this.d.setVisibility(0);
        this.c.setText(2131297759);
        this.c.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c() {
        boolean bl;
        Context context;
        int n = 2130837810;
        this.b.setVisibility(8);
        int n2 = this.h.c();
        switch (.b[this.j.ordinal()]) {
            default: {
                context = null;
                bl = false;
                break;
            }
            case 1: {
                context = this.getContext();
                n = this.k ? 2130837809 : 2130837807;
                context = ContextCompat.getDrawable((Context)context, (int)n).mutate();
                bl = false;
                break;
            }
            case 2: {
                context = this.getContext();
                if (!this.k) {
                    n = 2130837808;
                }
                context = ContextCompat.getDrawable((Context)context, (int)n).mutate();
                bl = true;
                break;
            }
            case 3: {
                context = this.getContext();
                if (!this.k) {
                    n = 2130837808;
                }
                context = ContextCompat.getDrawable((Context)context, (int)n).mutate();
                n2 = 2131297759;
                bl = true;
            }
        }
        if (this.h.a()) {
            n = this.getResources().getDimensionPixelSize(2131428164);
            this.a.setPadding(n, n, n, n);
        }
        this.c.setText(n2);
        this.a.setImageDrawable((Drawable)context);
        this.a(bl);
    }

    private Bitmap getBaseOverlayBitmap() {
        int n = this.getResources().getDimensionPixelSize(2131427507);
        return Bitmap.createBitmap((int)n, (int)n, (Bitmap.Config)Bitmap.Config.ARGB_8888);
    }

    private Bitmap getDisabledOverlayBitmap() {
        Bitmap bitmap = this.getBaseOverlayBitmap();
        new Canvas(bitmap).drawColor(ContextCompat.getColor((Context)this.getContext(), (int)2131689535));
        return bitmap;
    }

    private Bitmap getOverlayBitmap() {
        Bitmap bitmap = this.getBaseOverlayBitmap();
        new Canvas(bitmap).drawColor(ContextCompat.getColor((Context)this.getContext(), (int)2131689722));
        return bitmap;
    }

    public void a(VideoFilterManager.VideoStyleItem videoStyleItem, boolean bl) {
        this.f = EffectListItem.EffectType.a;
        this.g = videoStyleItem;
        this.l = bl;
        this.b();
        this.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setButtonState(ButtonState buttonState) {
        if (this.i == buttonState) {
            return;
        }
        this.i = buttonState;
        this.j = buttonState;
        switch (.a[this.f.ordinal()]) {
            case 1: {
                this.c();
            }
            default: {
                break;
            }
            case 2: {
                this.b();
            }
        }
        this.a();
    }

    public void setVocalEffect(VocalEffect vocalEffect) {
        this.f = EffectListItem.EffectType.b;
        this.h = vocalEffect;
        this.k = vocalEffect.a();
        this.c();
        this.a();
    }

}

