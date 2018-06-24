package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;

public class BubbleTooltipViewWithDropShadow extends LinearLayout implements OnPreDrawListener {
    ImageView f21484a;
    TextView f21485b;
    ImageView f21486c;
    CardView f21487d;
    View f21488e;
    int f21489f;
    private int f21490g;
    private int f21491h;
    private boolean f21492i;
    private String f21493j;

    class C43841 implements OnClickListener {
        final /* synthetic */ BubbleTooltipViewWithDropShadow f21481a;

        C43841(BubbleTooltipViewWithDropShadow bubbleTooltipViewWithDropShadow) {
            this.f21481a = bubbleTooltipViewWithDropShadow;
        }

        public void onClick(View view) {
            if (this.f21481a.f21493j != null) {
                SingApplication.f().getSharedPreferences(BubbleTooltipViewWithDropShadow.class.getName(), 0).edit().putBoolean(this.f21481a.f21493j, true).apply();
                this.f21481a.m23144b();
                this.f21481a.f21492i = true;
            }
        }
    }

    class C43852 implements AnimationListener {
        final /* synthetic */ BubbleTooltipViewWithDropShadow f21482a;

        C43852(BubbleTooltipViewWithDropShadow bubbleTooltipViewWithDropShadow) {
            this.f21482a = bubbleTooltipViewWithDropShadow;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    class C43863 implements AnimationListener {
        final /* synthetic */ BubbleTooltipViewWithDropShadow f21483a;

        C43863(BubbleTooltipViewWithDropShadow bubbleTooltipViewWithDropShadow) {
            this.f21483a = bubbleTooltipViewWithDropShadow;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f21483a.f21487d.setVisibility(8);
            this.f21483a.f21484a.setVisibility(8);
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public BubbleTooltipViewWithDropShadow(Context context) {
        super(context);
        m23142c();
    }

    public BubbleTooltipViewWithDropShadow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23142c();
    }

    public BubbleTooltipViewWithDropShadow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23142c();
    }

    private void m23142c() {
        LayoutInflater.from(getContext()).inflate(C1947R.layout.performance_save_tooltip_layout, this, true);
        getViewTreeObserver().addOnPreDrawListener(this);
        this.f21484a = (ImageView) findViewById(C1947R.id.tooltip_triangle);
        this.f21485b = (TextView) findViewById(C1947R.id.tooltip_text);
        this.f21486c = (ImageView) findViewById(C1947R.id.close_button);
        this.f21487d = (CardView) findViewById(C1947R.id.cardview);
        this.f21486c.setOnClickListener(new C43841(this));
    }

    public void setSharedPreferenceKey(String str) {
        this.f21493j = str;
        this.f21492i = SingApplication.f().getSharedPreferences(BubbleTooltipViewWithDropShadow.class.getName(), 0).getBoolean(str, false);
    }

    public boolean onPreDraw() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        if (this.f21488e != null) {
            int[] iArr = new int[2];
            this.f21488e.getLocationOnScreen(iArr);
            this.f21488e.getWindowVisibleDisplayFrame(new Rect());
            int[] iArr2 = new int[2];
            ((View) getParent()).getLocationOnScreen(iArr2);
            int measuredWidth = this.f21488e.getMeasuredWidth();
            int measuredHeight = this.f21488e.getMeasuredHeight();
            this.f21491h = iArr[0] - iArr2[0];
            this.f21490g = iArr[1] - iArr2[1];
            measuredWidth = (measuredWidth / 2) + this.f21491h;
            setTranslationY((float) Math.max(0, (this.f21490g + measuredHeight) + getResources().getDimensionPixelSize(C1947R.dimen.margin_small)));
            this.f21484a.setX((float) ((measuredWidth - (this.f21484a.getMeasuredWidth() / 2)) - ((int) getX())));
            this.f21484a.bringToFront();
            if (VERSION.SDK_INT >= 21) {
                this.f21487d.setElevation(5.0f);
                this.f21484a.setElevation(10.0f);
            }
        }
        return true;
    }

    public void setAnchoringView(View view) {
        this.f21488e = view;
    }

    public void setColor(int i) {
        this.f21489f = i;
        this.f21484a.setColorFilter(this.f21489f, Mode.MULTIPLY);
        this.f21487d.setCardBackgroundColor(this.f21489f);
    }

    public void setText(int i) {
        this.f21485b.setText(i);
    }

    public void m23143a() {
        if (this.f21484a.getVisibility() != 0 && !this.f21492i) {
            this.f21487d.setVisibility(0);
            this.f21484a.setVisibility(0);
            this.f21484a.clearAnimation();
            this.f21487d.clearAnimation();
            Animation animationSet = new AnimationSet(false);
            Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            alphaAnimation.setDuration(300);
            alphaAnimation.setFillAfter(true);
            Animation scaleAnimation = new ScaleAnimation(0.3f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.3f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1, 0.0f);
            scaleAnimation.setDuration(300);
            scaleAnimation.setFillAfter(true);
            animationSet.addAnimation(alphaAnimation);
            animationSet.addAnimation(scaleAnimation);
            animationSet.setStartOffset(200);
            scaleAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            scaleAnimation.setDuration(300);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setStartOffset(200);
            animationSet.setAnimationListener(new C43852(this));
            this.f21487d.startAnimation(animationSet);
            this.f21484a.startAnimation(scaleAnimation);
        }
    }

    public void m23144b() {
        if (this.f21484a.getVisibility() != 8 && !this.f21492i) {
            this.f21484a.clearAnimation();
            this.f21487d.clearAnimation();
            Animation animationSet = new AnimationSet(false);
            Animation alphaAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
            alphaAnimation.setDuration(300);
            alphaAnimation.setFillAfter(true);
            Animation scaleAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.3f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.3f, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1, 0.0f);
            scaleAnimation.setDuration(300);
            scaleAnimation.setFillAfter(true);
            animationSet.addAnimation(alphaAnimation);
            animationSet.addAnimation(scaleAnimation);
            animationSet.setStartOffset(200);
            scaleAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
            scaleAnimation.setDuration(300);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setStartOffset(200);
            animationSet.setAnimationListener(new C43863(this));
            this.f21487d.startAnimation(animationSet);
            this.f21484a.startAnimation(scaleAnimation);
        }
    }
}
