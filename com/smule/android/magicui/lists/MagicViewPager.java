/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcelable
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$PageTransformer
 *  android.view.MotionEvent
 *  android.view.View
 */
package com.smule.android.magicui.lists;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import com.smule.android.magicui.lists.MagicDataView;
import com.smule.android.magicui.lists.MagicViewPagerAdapterWrapper;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.RecycledViewPool;

public class MagicViewPager
extends ViewPager
implements MagicDataView {
    boolean a;
    private MagicAdapter b;
    private MagicViewPagerAdapterWrapper c;
    private ViewPager.PageTransformer d;
    private RecycledViewPool e;

    public View a(int n) {
        if (this.b == null) {
            return null;
        }
        return this.findViewWithTag((Object)this.c.a(n));
    }

    public View getCurrentItemView() {
        if (this.b == null) {
            return null;
        }
        return this.a(this.getCurrentItem());
    }

    public ViewPager.PageTransformer getTransformer() {
        return this.d;
    }

    public MagicAdapter getWrappedAdapter() {
        return this.b;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a && super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        if (this.c != null) {
            this.c.a(parcelable);
        }
        return parcelable;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.a && super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        throw new RuntimeException("Use a MagicAdapter");
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setMagicAdapter(MagicAdapter magicAdapter) {
        this.b = magicAdapter;
        if (magicAdapter != null) {
            this.c = new MagicViewPagerAdapterWrapper(this.b);
            this.c.a(this.e);
        } else {
            this.c = null;
        }
        super.setAdapter((PagerAdapter)this.c);
        if (magicAdapter != null && (magicAdapter = this.c.a()) != null) {
            this.onRestoreInstanceState((Parcelable)magicAdapter);
            this.c.a((Parcelable)null);
        }
    }

    public void setPageTransformer(boolean bl, ViewPager.PageTransformer pageTransformer) {
        super.setPageTransformer(bl, pageTransformer);
        this.d = pageTransformer;
    }

    public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
        this.e = recycledViewPool;
        if (this.c != null) {
            this.c.a(recycledViewPool);
        }
    }

    public void setSwipingEnabled(boolean bl) {
        this.a = bl;
    }

    public static class ScaleInOutPagerTransformer
    implements ViewPager.PageTransformer {
        MagicViewPager a;
        private int b;

        /*
         * Enabled aggressive block sorting
         */
        public void transformPage(View view, float f) {
            float f2;
            float f3 = this.a.getScrollX() + this.b;
            float f4 = this.a.getMeasuredWidth();
            f = f2 = (float)view.getLeft();
            if (f2 == 0.0f) {
                f = f4 - (float)this.b;
            }
            if ((f2 = 1.0f - Math.abs((f = (f - f3) / (f4 - (float)(this.b * 2))) * 0.19999999f)) < 0.0f || f2 > 1.0f) {
                return;
            }
            f3 = (1.0f - f2) / 0.19999999f;
            if (f < 0.0f) {
                view.setTranslationX(((float)(this.b / 2) - (f4 - (float)(this.b * 2)) * 0.099999994f) * f3 * -1.0f);
            } else {
                view.setTranslationX(((float)(this.b / 2) - (f4 - (float)(this.b * 2)) * 0.099999994f) * f3);
            }
            view.setScaleX(f2);
            view.setScaleY(f2);
            view.setAlpha(1.0f - 0.6f * f3);
        }
    }

}

