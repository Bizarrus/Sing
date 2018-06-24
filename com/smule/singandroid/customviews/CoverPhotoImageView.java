/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.graphics.drawable.LayerDrawable
 *  android.support.v4.content.ContextCompat
 *  android.support.v7.widget.AppCompatImageView
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  com.nostra13.universalimageloader.core.DisplayImageOptions
 *  com.nostra13.universalimageloader.core.DisplayImageOptions$Builder
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.display.BitmapDisplayer
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 *  org.androidannotations.annotations.EView
 *  org.androidannotations.annotations.UiThread
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Log;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.customviews.CoverPhotoImageView;
import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.UiThread;

@EView
public class CoverPhotoImageView
extends AppCompatImageView {
    public static final String a = CoverPhotoImageView.class.getSimpleName();
    private final LayerDrawable b;
    private final GradientDrawable c;
    private String d;
    private final int[] e = new int[2];
    private final int[] f = new int[2];

    public CoverPhotoImageView(Context context) {
        this(context, null);
    }

    public CoverPhotoImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CoverPhotoImageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b = (LayerDrawable)this.getDrawable();
        this.c = (GradientDrawable)this.b.getDrawable(1);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Deprecated
    @UiThread
    public void a(int n, int n2) {
        this.e[0] = n;
        this.e[1] = n2;
        this.f[0] = this.e[0];
        this.f[1] = this.getBitmap() != null ? ContextCompat.getColor((Context)this.getContext(), (int)2131689964) : this.e[1];
        this.c.setColors(this.f);
    }

    public Bitmap getBitmap() {
        Drawable drawable2 = this.b.getDrawable(0);
        if (drawable2 instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable2).getBitmap();
        }
        return null;
    }

    @UiThread
    public void setPhoto(Bitmap bitmap) {
        if (bitmap != null) {
            this.b.setDrawableByLayerId(2131756831, (Drawable)new BitmapDrawable(this.getResources(), bitmap));
        }
        this.invalidate();
    }

    public void setUrl(String string2) {
        block3 : {
            block2 : {
                Log.b(a, "setUrl:" + string2);
                if (this.d != null && this.d.equals(string2)) break block2;
                this.d = string2;
                if (!TextUtils.isEmpty((CharSequence)this.d)) break block3;
                this.setPhoto(null);
            }
            return;
        }
        ImageLoader.a().a(string2, new DisplayImageOptions.Builder().a(true).b(true).a(new BitmapDisplayer(string2){
            final String a;
            final BitmapDisplayer b;
            {
                this.a = string2;
                this.b = com.nostra13.universalimageloader.core.DefaultConfigurationFactory.c();
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Converted monitor instructions to comments
             * Lifted jumps to return sites
             */
            public void a(Bitmap object, com.nostra13.universalimageloader.core.imageaware.ImageAware imageAware, com.nostra13.universalimageloader.core.assist.LoadedFrom loadedFrom) {
                this.b.a((Bitmap)object, imageAware, loadedFrom);
                if (loadedFrom != com.nostra13.universalimageloader.core.assist.LoadedFrom.a) {
                    com.smule.android.utils.ImageUtils.b(this.a, true);
                    return;
                }
                long l = java.lang.System.currentTimeMillis();
                object = b;
                // MONITORENTER : object
                imageAware = (android.support.v4.util.Pair)b.get(this.a);
                // MONITOREXIT : object
                if (imageAware != null && imageAware.first != null && imageAware.second != null) {
                    com.smule.android.logging.EventLogger2.a(this.a, l - (java.lang.Long)imageAware.first, 0, ((java.lang.Integer)imageAware.second).intValue(), com.smule.android.logging.EventLogger2$ErrorDomain.a, 0, null, null, null, false);
                }
                com.smule.android.utils.ImageUtils.b(this.a, false);
            }
        }).a(), (ImageLoadingListener)new SimpleImageLoadingListener(this){
            final /* synthetic */ CoverPhotoImageView a;
            {
                this.a = coverPhotoImageView;
            }

            public void a(String string2, android.view.View view, Bitmap bitmap) {
                this.a.setPhoto(bitmap);
            }
        });
    }
}

