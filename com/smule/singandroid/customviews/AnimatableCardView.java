package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;

public class AnimatableCardView extends CardView {
    private float f21448a = 0.0f;
    private float f21449b = 0.0f;
    private OnPreDrawListener f21450c = null;

    class C43741 implements OnPreDrawListener {
        final /* synthetic */ AnimatableCardView f21446a;

        C43741(AnimatableCardView animatableCardView) {
            this.f21446a = animatableCardView;
        }

        public boolean onPreDraw() {
            this.f21446a.getViewTreeObserver().removeOnPreDrawListener(this.f21446a.f21450c);
            this.f21446a.setXFraction(this.f21446a.f21448a);
            return true;
        }
    }

    class C43752 implements OnPreDrawListener {
        final /* synthetic */ AnimatableCardView f21447a;

        C43752(AnimatableCardView animatableCardView) {
            this.f21447a = animatableCardView;
        }

        public boolean onPreDraw() {
            this.f21447a.getViewTreeObserver().removeOnPreDrawListener(this.f21447a.f21450c);
            this.f21447a.setYFraction(this.f21447a.f21449b);
            return true;
        }
    }

    public AnimatableCardView(Context context) {
        super(context);
    }

    public AnimatableCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimatableCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setXFraction(float f) {
        this.f21448a = f;
        if (((ViewGroup) getParent()).getWidth() != 0) {
            float width = (float) ((ViewGroup) getParent()).getWidth();
            float width2 = (((float) getWidth()) * getScaleX()) / 2.0f;
            setTranslationX(Math.max(0.0f, (width - (((float) ((MarginLayoutParams) getLayoutParams()).leftMargin) + width2)) * f));
        } else if (this.f21450c == null) {
            this.f21450c = new C43741(this);
            getViewTreeObserver().addOnPreDrawListener(this.f21450c);
        }
    }

    public float getXFraction() {
        return this.f21448a;
    }

    public void setYFraction(float f) {
        this.f21449b = f;
        if (((ViewGroup) getParent()).getHeight() != 0) {
            setTranslationY(Math.max(0.0f, (((float) ((ViewGroup) getParent()).getHeight()) - (((((float) getHeight()) * getScaleY()) / 2.0f) - ((float) ((MarginLayoutParams) getLayoutParams()).topMargin))) * f));
        } else if (this.f21450c == null) {
            this.f21450c = new C43752(this);
            getViewTreeObserver().addOnPreDrawListener(this.f21450c);
        }
    }

    public float getYFraction() {
        return this.f21449b;
    }
}
