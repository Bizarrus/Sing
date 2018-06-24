package com.smule.singandroid.customviews;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.singandroid.C1947R;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class CustomTabIndicator extends LinearLayout {
    protected LayoutInflater f21514a;
    @ViewById
    protected View f21515b;
    @ViewById
    protected View f21516c;
    @ViewById
    protected TextView f21517d;
    @ViewById
    protected TextView f21518e;
    @ViewById
    protected LinearLayout f21519f;
    protected boolean f21520g;
    protected List<String> f21521h;
    protected int f21522i;
    protected AnimatorSet f21523j;
    protected boolean f21524k;
    protected Context f21525l;
    private boolean f21526m;

    public CustomTabIndicator(Context context) {
        this(context, null, 0);
    }

    public CustomTabIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomTabIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f21520g = false;
        this.f21524k = true;
        this.f21525l = context;
        this.f21514a = LayoutInflater.from(context);
    }

    public void m23162a(List<String> list, int i) {
        if (list.isEmpty()) {
            m23164b();
            this.f21520g = true;
            return;
        }
        this.f21521h = list;
        this.f21522i = i;
        setTitleText((String) list.get(i));
        m23160a(this.f21521h.size(), i);
        m23167e();
    }

    public void m23158a() {
        if (!this.f21520g) {
            this.f21515b.setVisibility(0);
            this.f21516c.setVisibility(0);
            this.f21517d.setVisibility(0);
            this.f21518e.setVisibility(0);
            this.f21519f.setVisibility(0);
            m23167e();
        }
    }

    public void m23164b() {
        if (!this.f21520g) {
            this.f21515b.setVisibility(8);
            this.f21516c.setVisibility(8);
            this.f21517d.setVisibility(8);
            this.f21518e.setVisibility(8);
            this.f21519f.setVisibility(8);
        }
    }

    protected void m23160a(int i, int i2) {
        int i3 = 0;
        while (i3 < i) {
            View inflate = this.f21514a.inflate(C1947R.layout.tab_indicator, null);
            inflate.findViewById(C1947R.id.tab_indicator).setBackgroundResource(i3 == i2 ? C1947R.drawable.tab_indicator_selected : C1947R.drawable.tab_indicator_default);
            this.f21519f.addView(inflate);
            i3++;
        }
    }

    public void m23159a(int i) {
        if (!this.f21526m) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f21515b.getLayoutParams();
            marginLayoutParams.topMargin += (int) getResources().getDimension(i);
            marginLayoutParams = (MarginLayoutParams) this.f21517d.getLayoutParams();
            marginLayoutParams.topMargin += (int) getResources().getDimension(i);
            this.f21526m = true;
        }
    }

    public void setHideIndicatorsWithAnimation(boolean z) {
        this.f21524k = z;
    }

    public void setTitleTextViewVisibility(int i) {
        this.f21517d.setVisibility(i);
    }

    public void setIndicatorLayoutVisibility(int i) {
        this.f21519f.setVisibility(i);
    }

    public void setShadowVisibility(int i) {
        this.f21515b.setVisibility(i);
        this.f21516c.setVisibility(i);
    }

    protected void setTitleText(String str) {
        if (!this.f21520g) {
            m23161a(str, 0);
        }
    }

    protected void m23161a(String str, int i) {
        if (!this.f21520g) {
            this.f21517d.setText(str);
            this.f21517d.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
        }
    }

    public void m23165c() {
        if (!this.f21520g) {
            m23157g();
            this.f21522i = getNextIndex();
            setTitleText((String) this.f21521h.get(this.f21522i));
            m23167e();
        }
    }

    public void m23166d() {
        if (!this.f21520g) {
            m23156f();
            this.f21522i = getPreviousIndex();
            setTitleText((String) this.f21521h.get(this.f21522i));
            m23167e();
        }
    }

    private void m23156f() {
        m23155c(this.f21522i);
        m23155c(getPreviousIndex());
    }

    private void m23157g() {
        m23155c(this.f21522i);
        m23155c(getNextIndex());
    }

    private void m23155c(int i) {
        View childAt = ((FrameLayout) this.f21519f.getChildAt(i)).getChildAt(0);
        if (i == this.f21522i) {
            childAt.setBackgroundResource(C1947R.drawable.tab_indicator_default);
        } else {
            childAt.setBackgroundResource(C1947R.drawable.tab_indicator_selected);
        }
    }

    private int getPreviousIndex() {
        int i = this.f21522i;
        if (i == 0) {
            return this.f21521h.size() - 1;
        }
        return i - 1;
    }

    private int getNextIndex() {
        int i = this.f21522i;
        if (i == this.f21521h.size() - 1) {
            return 0;
        }
        return i + 1;
    }

    public void m23167e() {
        if (!this.f21520g) {
            this.f21517d.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f21519f.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f21515b.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f21516c.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f21518e.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            if (this.f21523j == null) {
                this.f21523j = new AnimatorSet();
            }
            if (this.f21523j.isStarted()) {
                this.f21523j.cancel();
                this.f21523j = new AnimatorSet();
            }
            this.f21523j.setDuration((long) getResources().getInteger(17694721));
            this.f21523j.setStartDelay((long) getResources().getInteger(C1947R.integer.vfx_fadeout_delay));
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f21517d, View.ALPHA, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f21518e, View.ALPHA, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f});
            if (this.f21524k) {
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f21519f, View.ALPHA, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f});
                this.f21523j.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
            } else {
                this.f21523j.playTogether(new Animator[]{ofFloat, ofFloat2});
            }
            this.f21523j.start();
        }
    }

    protected String getCurrentItemTitle() {
        if (this.f21520g) {
            return null;
        }
        return (String) this.f21521h.get(this.f21522i);
    }

    public View m23163b(int i) {
        return findViewById(i);
    }
}
