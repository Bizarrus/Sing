package com.smule.android.magicui.lists;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.MotionEvent;
import android.view.View;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.RecycledViewPool;

public class MagicViewPager extends ViewPager implements MagicDataView {
    boolean f16399a;
    private MagicAdapter f16400b;
    private MagicViewPagerAdapterWrapper f16401c;
    private PageTransformer f16402d;
    private RecycledViewPool f16403e;

    public static class ScaleInOutPagerTransformer implements PageTransformer {
        MagicViewPager f16397a;
        private int f16398b;

        public void transformPage(View view, float f) {
            float scrollX = (float) (this.f16397a.getScrollX() + this.f16398b);
            float measuredWidth = (float) this.f16397a.getMeasuredWidth();
            float left = (float) view.getLeft();
            if (left == 0.0f) {
                left = measuredWidth - ((float) this.f16398b);
            }
            left = (left - scrollX) / (measuredWidth - ((float) (this.f16398b * 2)));
            scrollX = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - Math.abs(left * 0.19999999f);
            if (scrollX >= 0.0f && scrollX <= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                float f2 = (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - scrollX) / 0.19999999f;
                if (left < 0.0f) {
                    view.setTranslationX(((((float) (this.f16398b / 2)) - ((measuredWidth - ((float) (this.f16398b * 2))) * 0.099999994f)) * f2) * -1.0f);
                } else {
                    view.setTranslationX((((float) (this.f16398b / 2)) - ((measuredWidth - ((float) (this.f16398b * 2))) * 0.099999994f)) * f2);
                }
                view.setScaleX(scrollX);
                view.setScaleY(scrollX);
                view.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - (0.6f * f2));
            }
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        throw new RuntimeException("Use a MagicAdapter");
    }

    public void setMagicAdapter(MagicAdapter magicAdapter) {
        this.f16400b = magicAdapter;
        if (magicAdapter != null) {
            this.f16401c = new MagicViewPagerAdapterWrapper(this.f16400b);
            this.f16401c.m18022a(this.f16403e);
        } else {
            this.f16401c = null;
        }
        super.setAdapter(this.f16401c);
        if (magicAdapter != null) {
            Parcelable a = this.f16401c.m18018a();
            if (a != null) {
                onRestoreInstanceState(a);
                this.f16401c.m18021a(null);
            }
        }
    }

    public MagicAdapter getWrappedAdapter() {
        return this.f16400b;
    }

    public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
        this.f16403e = recycledViewPool;
        if (this.f16401c != null) {
            this.f16401c.m18022a(recycledViewPool);
        }
    }

    public View getCurrentItemView() {
        if (this.f16400b == null) {
            return null;
        }
        return m18014a(getCurrentItem());
    }

    public View m18014a(int i) {
        if (this.f16400b == null) {
            return null;
        }
        return findViewWithTag(this.f16401c.m18020a(i));
    }

    public void setPageTransformer(boolean z, PageTransformer pageTransformer) {
        super.setPageTransformer(z, pageTransformer);
        this.f16402d = pageTransformer;
    }

    public PageTransformer getTransformer() {
        return this.f16402d;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (this.f16401c != null) {
            this.f16401c.m18021a(onSaveInstanceState);
        }
        return onSaveInstanceState;
    }

    public void setSwipingEnabled(boolean z) {
        this.f16399a = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f16399a && super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f16399a && super.onInterceptTouchEvent(motionEvent);
    }
}
