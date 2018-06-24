/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.os.Looper
 *  android.util.AttributeSet
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.util.AttributeSet;
import com.smule.singandroid.customviews.CoverPhotoImageView;
import com.smule.singandroid.customviews.CoverPhotoImageView_;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CoverPhotoImageView_
extends CoverPhotoImageView
implements HasViews {
    private boolean b = false;
    private final OnViewChangedNotifier c = new OnViewChangedNotifier();

    public CoverPhotoImageView_(Context context) {
        super(context);
        this.a();
    }

    public CoverPhotoImageView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a();
    }

    public CoverPhotoImageView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a();
    }

    private void a() {
        OnViewChangedNotifier.a((OnViewChangedNotifier)OnViewChangedNotifier.a((OnViewChangedNotifier)this.c));
    }

    static /* synthetic */ void b(CoverPhotoImageView_ coverPhotoImageView_, int n, int n2) {
        super.a(n, n2);
    }

    static /* synthetic */ void b(CoverPhotoImageView_ coverPhotoImageView_, Bitmap bitmap) {
        super.setPhoto(bitmap);
    }

    @Deprecated
    @Override
    public void a(int n, int n2) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            CoverPhotoImageView_.super.a(n, n2);
            return;
        }
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(this, n, n2){
            final /* synthetic */ int a;
            final /* synthetic */ int b;
            final /* synthetic */ CoverPhotoImageView_ c;
            {
                this.c = coverPhotoImageView_;
                this.a = n;
                this.b = n2;
            }

            public void run() {
                CoverPhotoImageView_.b(this.c, this.a, this.b);
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.b) {
            this.b = true;
            this.c.a((HasViews)this);
        }
        super.onFinishInflate();
    }

    @Override
    public void setPhoto(Bitmap bitmap) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            CoverPhotoImageView_.super.setPhoto(bitmap);
            return;
        }
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(this, bitmap){
            final /* synthetic */ Bitmap a;
            final /* synthetic */ CoverPhotoImageView_ b;
            {
                this.b = coverPhotoImageView_;
                this.a = bitmap;
            }

            public void run() {
                CoverPhotoImageView_.b(this.b, this.a);
            }
        }, (long)0);
    }
}

