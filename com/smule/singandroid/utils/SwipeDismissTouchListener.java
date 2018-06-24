package com.smule.singandroid.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import com.mopub.volley.DefaultRetryPolicy;

public class SwipeDismissTouchListener implements OnTouchListener {
    private int f25089a;
    private int f25090b;
    private int f25091c;
    private long f25092d;
    private View f25093e;
    private DismissCallbacks f25094f;
    private int f25095g = 1;
    private float f25096h;
    private float f25097i;
    private boolean f25098j;
    private int f25099k;
    private Object f25100l;
    private VelocityTracker f25101m;
    private float f25102n;
    private OnClickListener f25103o;

    public interface DismissCallbacks {
        void mo6539a(View view, Object obj);

        boolean mo6540a(Object obj);
    }

    class C50321 extends AnimatorListenerAdapter {
        final /* synthetic */ SwipeDismissTouchListener f25088a;

        C50321(SwipeDismissTouchListener swipeDismissTouchListener) {
            this.f25088a = swipeDismissTouchListener;
        }

        public void onAnimationEnd(Animator animator) {
            this.f25088a.m26186a();
        }
    }

    public SwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks, OnClickListener onClickListener) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        this.f25089a = viewConfiguration.getScaledTouchSlop();
        this.f25090b = viewConfiguration.getScaledMinimumFlingVelocity() * 16;
        this.f25091c = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f25092d = (long) view.getContext().getResources().getInteger(17694720);
        this.f25093e = view;
        this.f25100l = obj;
        this.f25094f = dismissCallbacks;
        this.f25103o = onClickListener;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = true;
        motionEvent.offsetLocation(this.f25102n, 0.0f);
        if (this.f25095g < 2) {
            this.f25095g = this.f25093e.getWidth();
        }
        float rawX;
        float abs;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.f25096h = motionEvent.getRawX();
                this.f25097i = motionEvent.getRawY();
                if (!this.f25094f.mo6540a(this.f25100l)) {
                    return true;
                }
                this.f25101m = VelocityTracker.obtain();
                this.f25101m.addMovement(motionEvent);
                return true;
            case 1:
                if (this.f25101m != null) {
                    boolean z2;
                    rawX = motionEvent.getRawX() - this.f25096h;
                    this.f25101m.addMovement(motionEvent);
                    this.f25101m.computeCurrentVelocity(1000);
                    float xVelocity = this.f25101m.getXVelocity();
                    abs = Math.abs(xVelocity);
                    float abs2 = Math.abs(this.f25101m.getYVelocity());
                    this.f25101m.recycle();
                    this.f25101m = null;
                    if (Math.abs(rawX) > ((float) (this.f25095g / 2)) && this.f25098j) {
                        z2 = rawX > 0.0f;
                    } else if (((float) this.f25090b) > abs || abs > ((float) this.f25091c) || abs2 >= abs || abs2 >= abs || !this.f25098j) {
                        z2 = false;
                        z = false;
                    } else {
                        boolean z3;
                        if (xVelocity < 0.0f) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z2 = z3 == ((rawX > 0.0f ? 1 : (rawX == 0.0f ? 0 : -1)) < 0);
                        if (xVelocity <= 0.0f) {
                            z = false;
                        }
                        boolean z4 = z;
                        z = z2;
                        z2 = z4;
                    }
                    if (z) {
                        this.f25093e.animate().translationX(z2 ? (float) this.f25095g : (float) (-this.f25095g)).alpha(0.0f).setDuration(this.f25092d).setListener(new C50321(this));
                    } else if (this.f25098j) {
                        this.f25093e.animate().translationX(0.0f).alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(this.f25092d).setListener(null);
                    } else {
                        this.f25103o.onClick(this.f25093e);
                    }
                    this.f25102n = 0.0f;
                    this.f25096h = 0.0f;
                    this.f25097i = 0.0f;
                    this.f25098j = false;
                    break;
                }
                break;
            case 2:
                if (this.f25101m != null) {
                    this.f25101m.addMovement(motionEvent);
                    abs = motionEvent.getRawX() - this.f25096h;
                    rawX = motionEvent.getRawY() - this.f25097i;
                    if (Math.abs(abs) > ((float) this.f25089a) && Math.abs(rawX) < Math.abs(abs) / 2.0f) {
                        int i;
                        this.f25098j = true;
                        if (abs > 0.0f) {
                            i = this.f25089a;
                        } else {
                            i = -this.f25089a;
                        }
                        this.f25099k = i;
                        this.f25093e.getParent().requestDisallowInterceptTouchEvent(true);
                        MotionEvent obtain = MotionEvent.obtain(motionEvent);
                        obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                        this.f25093e.onTouchEvent(obtain);
                        obtain.recycle();
                    }
                    if (this.f25098j) {
                        this.f25102n = abs;
                        this.f25093e.setTranslationX(abs - ((float) this.f25099k));
                        this.f25093e.setAlpha(Math.max(0.0f, Math.min(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - ((2.0f * Math.abs(abs)) / ((float) this.f25095g)))));
                        return true;
                    }
                }
                break;
            case 3:
                if (this.f25101m != null) {
                    this.f25093e.animate().translationX(0.0f).alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(this.f25092d).setListener(null);
                    this.f25101m.recycle();
                    this.f25101m = null;
                    this.f25102n = 0.0f;
                    this.f25096h = 0.0f;
                    this.f25097i = 0.0f;
                    this.f25098j = false;
                    break;
                }
                break;
        }
        return false;
    }

    private void m26186a() {
        this.f25094f.mo6539a(this.f25093e, this.f25100l);
    }
}
