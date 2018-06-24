/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.annotation.Nullable
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.RelativeLayout
 *  com.smule.singandroid.utils.AnimatorEndListener
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.smule.android.logging.Log;
import com.smule.singandroid.customviews.QuickSelectControlItemView;
import com.smule.singandroid.customviews.QuickSelectControlItemViewModel;
import com.smule.singandroid.customviews.QuickSelectControlItemView_;
import com.smule.singandroid.utils.AnimatorEndListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class QuickSelectControlView
extends RelativeLayout {
    private static final String c = QuickSelectControlView.class.getName();
    @ViewById
    protected FrameLayout a;
    @ViewById
    protected LinearLayout b;
    private boolean d = true;
    private final List<QuickSelectControlItemViewModel> e = new ArrayList<QuickSelectControlItemViewModel>();
    private String f = "";
    private int g;
    private ValueAnimator h;
    private ValueAnimator i;
    private boolean j;
    private boolean k;
    private Handler l = new Handler(Looper.getMainLooper());
    private Runnable m;
    private Runnable n;

    public QuickSelectControlView(Context context) {
        super(context);
        this.n = new Runnable(){

            @Override
            public void run() {
                QuickSelectControlView.this.a(false);
            }
        };
    }

    public QuickSelectControlView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = new ;
    }

    public QuickSelectControlView(Context context, @Nullable AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.n = new ;
    }

    private void c() {
        this.b.removeAllViews();
        this.e.clear();
        this.g = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n) {
        if (n < 0 || n > this.e.size() - 1) {
            Log.d(c, "out of range", new IllegalArgumentException());
        }
        this.g = n;
        n = 0;
        while (n < this.e.size()) {
            QuickSelectControlItemView quickSelectControlItemView = (QuickSelectControlItemView)this.b.getChildAt(n);
            QuickSelectControlItemViewModel quickSelectControlItemViewModel = this.e.get(n);
            boolean bl = n == this.g;
            quickSelectControlItemViewModel.a(bl);
            quickSelectControlItemView.setSelected(quickSelectControlItemViewModel.d());
            ++n;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(String object, List<QuickSelectControlItemViewModel> list) {
        if (!this.f.equals(object)) {
            this.a.setOnClickListener(null);
            this.c();
            this.f = object;
            this.e.addAll(list);
            for (int i = 0; i < list.size(); ++i) {
                object = QuickSelectControlItemView_.a(this.getContext());
                object.setupView(list.get(i));
                object.setTag((Object)(this.f + "." + i));
                if (list.get(i).d()) {
                    this.g = i;
                }
                object.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(this.getResources().getDimensionPixelOffset(2131427764), -1));
                int n = this.d ? this.getResources().getDimensionPixelOffset(2131427768) : 0;
                object.setPadding(0, n, 0, 0);
                this.b.addView((View)object);
            }
        }
        this.setOnClickListener(null);
    }

    @UiThread
    public void a(boolean bl) {
        this.k = bl;
        if (this.i == null) {
            this.i = ValueAnimator.ofFloat((float[])new float[]{1.0f, 0.0f});
            this.i.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

                public void onAnimationUpdate(ValueAnimator object) {
                    object = (Float)object.getAnimatedValue();
                    QuickSelectControlView.this.setAlpha(object.floatValue());
                }
            });
            this.i.addListener((Animator.AnimatorListener)new AnimatorEndListener(){

                public void onAnimationEnd(Animator animator2) {
                    if (QuickSelectControlView.this.j) {
                        QuickSelectControlView.this.j = false;
                        return;
                    }
                    if (QuickSelectControlView.this.m != null) {
                        QuickSelectControlView.this.m.run();
                    }
                    QuickSelectControlView.this.setVisibility(8);
                    QuickSelectControlView.this.k = false;
                }
            });
            this.i.setDuration((long)this.getResources().getInteger(2131623968));
        }
        this.i.start();
    }

    public boolean a() {
        if (this.m != null) {
            return true;
        }
        return false;
    }

    @UiThread
    public void b() {
        if (this.k) {
            return;
        }
        if (this.h == null) {
            this.h = ValueAnimator.ofFloat((float[])new float[]{0.0f, 1.0f});
            this.h.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

                public void onAnimationUpdate(ValueAnimator object) {
                    object = (Float)object.getAnimatedValue();
                    QuickSelectControlView.this.setAlpha(object.floatValue());
                }
            });
            this.h.addListener((Animator.AnimatorListener)new AnimatorEndListener(){

                public void onAnimationEnd(Animator animator2) {
                    QuickSelectControlView.this.l.postDelayed(QuickSelectControlView.this.n, (long)QuickSelectControlView.this.getResources().getInteger(2131623969));
                }
            });
            this.h.setDuration((long)this.getResources().getInteger(2131623968));
        }
        if (this.i != null && this.i.isRunning()) {
            this.j = true;
            this.i.cancel();
            this.setAlpha(1.0f);
            return;
        }
        this.l.removeCallbacks(this.n);
        if (this.getVisibility() == 8) {
            this.setVisibility(0);
            this.h.start();
            return;
        }
        this.l.postDelayed(this.n, (long)this.getResources().getInteger(2131623969));
    }

    public int getSelectedPosition() {
        return this.g;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.k;
    }

    public void setOnAnimationEndRunnable(Runnable runnable) {
        this.m = runnable;
    }

    public void setShouldHaveExtraTouchArea(boolean bl) {
        this.d = bl;
    }

}

