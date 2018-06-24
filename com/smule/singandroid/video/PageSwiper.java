package com.smule.singandroid.video;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.DecelerateInterpolator;
import com.mopub.volley.DefaultRetryPolicy;

public class PageSwiper implements OnTouchListener {
    private ChangeListener f25377a;
    private float f25378b = 0.0f;
    private ValueAnimator f25379c;
    private VelocityTracker f25380d;
    private float f25381e;
    private float f25382f;

    public interface ChangeListener {
        void mo6580a();

        void mo6581a(float f);

        void mo6582b();
    }

    class C50501 implements AnimatorUpdateListener {
        final /* synthetic */ PageSwiper f25373a;

        C50501(PageSwiper pageSwiper) {
            this.f25373a = pageSwiper;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f25373a.f25377a.mo6581a(-((Number) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public void m26526a(ChangeListener changeListener) {
        this.f25377a = changeListener;
    }

    private void m26525a(boolean z, boolean z2) {
        this.f25379c = null;
        if (z2) {
            this.f25377a.mo6580a();
        }
        if (z) {
            this.f25377a.mo6582b();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        float f = 0.0f;
        float x = motionEvent.getX();
        float width = (x - this.f25378b) / ((float) view.getWidth());
        switch (motionEvent.getAction()) {
            case 0:
                if (this.f25379c != null) {
                    this.f25379c.end();
                    this.f25379c = null;
                }
                if (this.f25380d == null) {
                    this.f25380d = VelocityTracker.obtain();
                } else {
                    this.f25380d.clear();
                }
                this.f25380d.addMovement(motionEvent);
                this.f25378b = x;
                this.f25381e = motionEvent.getRawX();
                this.f25382f = motionEvent.getRawY();
                view.onTouchEvent(motionEvent);
                break;
            case 1:
            case 3:
                this.f25380d.computeCurrentVelocity(1000);
                x = VelocityTrackerCompat.getXVelocity(this.f25380d, motionEvent.getPointerId(motionEvent.getActionIndex()));
                this.f25380d.recycle();
                this.f25380d = null;
                int i = Math.abs(x) > 500.0f ? 1 : 0;
                boolean z = i != 0 ? x > 0.0f && width > 0.0f : ((double) width) > 0.5d;
                boolean z2 = i != 0 ? x < 0.0f && width < 0.0f : ((double) width) < -0.5d;
                if (z) {
                    f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
                } else if (z2) {
                    f = -1.0f;
                }
                this.f25379c = ValueAnimator.ofFloat(new float[]{width, f});
                if (i != 0) {
                    this.f25379c.setInterpolator(new DecelerateInterpolator());
                }
                this.f25379c.setDuration(300);
                this.f25379c.addUpdateListener(new C50501(this));
                this.f25379c.addListener(new AnimatorListener(this) {
                    final /* synthetic */ PageSwiper f25376c;

                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        this.f25376c.m26525a(z, z2);
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.f25376c.m26525a(z, z2);
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                this.f25379c.start();
                if (Math.abs(this.f25381e - motionEvent.getRawX()) < 50.0f && Math.abs(this.f25382f - motionEvent.getRawY()) < 50.0f) {
                    return false;
                }
                break;
            case 2:
                this.f25380d.addMovement(motionEvent);
                this.f25377a.mo6581a(-width);
                break;
        }
        return true;
    }
}
