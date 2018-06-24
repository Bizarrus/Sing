package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.smule.singandroid.C1947R;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileImageWithVIPBadgeAndPendingView extends ProfileImageWithVIPBadge {
    @ViewById
    RelativeLayout f21850s;
    @ViewById
    ImageView f21851t;

    class C44241 implements Runnable {
        final /* synthetic */ ProfileImageWithVIPBadgeAndPendingView f21849a;

        C44241(ProfileImageWithVIPBadgeAndPendingView profileImageWithVIPBadgeAndPendingView) {
            this.f21849a = profileImageWithVIPBadgeAndPendingView;
        }

        public void run() {
            this.f21849a.requestLayout();
        }
    }

    public ProfileImageWithVIPBadgeAndPendingView(Context context) {
        super(context);
    }

    public ProfileImageWithVIPBadgeAndPendingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ProfileImageWithVIPBadgeAndPendingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setPending(boolean z) {
        if (z) {
            this.f21850s.setVisibility(0);
        } else {
            this.f21850s.setVisibility(8);
        }
    }

    protected void onMeasure(int i, int i2) {
        this.c.setLayoutParams(this.p);
        this.a.setLayoutParams(this.o);
        this.f21851t.setLayoutParams(this.o);
        super.onMeasure(MeasureSpec.makeMeasureSpec(this.j, 1073741824), MeasureSpec.makeMeasureSpec(this.j, 1073741824));
        mo6798c();
    }

    protected void mo6798c() {
        if (this.l && !this.m && (getLayoutParams() instanceof MarginLayoutParams) && this.k != 0) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin - this.k, (marginLayoutParams.bottomMargin - this.k) + getResources().getDimensionPixelSize(C1947R.dimen.margin_tiny));
            this.m = true;
            post(new C44241(this));
        }
    }
}
