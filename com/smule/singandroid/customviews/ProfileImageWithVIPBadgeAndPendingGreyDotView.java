package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.C1947R;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileImageWithVIPBadgeAndPendingGreyDotView extends ProfileImageWithVIPBadge {
    @ViewById
    SNPImageView f21846s;

    public ProfileImageWithVIPBadgeAndPendingGreyDotView(Context context) {
        super(context);
    }

    public ProfileImageWithVIPBadgeAndPendingGreyDotView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ProfileImageWithVIPBadgeAndPendingGreyDotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void mo6795a(float f, float f2, float f3) {
        this.q = new LayoutParams((int) f3, (int) f3);
        this.q.setMargins((int) (f + f2), (int) f, 0, 0);
    }

    protected void onMeasure(int i, int i2) {
        m23403d();
        this.c.setLayoutParams(this.p);
        this.a.setLayoutParams(this.o);
        this.f21846s.setLayoutParams(this.o);
        this.b.setLayoutParams(this.q);
        super.onMeasure(MeasureSpec.makeMeasureSpec(this.j, 1073741824), MeasureSpec.makeMeasureSpec(this.j, 1073741824));
        mo6798c();
    }

    protected void m23403d() {
        if ((getLayoutParams() instanceof MarginLayoutParams) && this.k != 0) {
            ((MarginLayoutParams) getLayoutParams()).setMargins(0, 0, 0, 0);
        }
    }

    public void m23402a(boolean z) {
        if (z) {
            this.f21846s.setVisibility(0);
            setVIP(true);
            return;
        }
        this.f21846s.setVisibility(8);
        setVIP(false);
    }

    public void mo6796a(int i, boolean z, OnClickListener onClickListener) {
        this.d.m18974a(this.a);
        this.d.m18973a();
        this.a.setImageDrawable(getResources().getDrawable(z ? C1947R.drawable.solid_grey_circle : C1947R.drawable.solid_grey_circle_with_border));
        setVIP(false);
        if (i == 0) {
            this.c.setClickable(false);
        } else {
            this.c.setOnClickListener(onClickListener);
        }
        setPerformanceCount(i);
    }
}
