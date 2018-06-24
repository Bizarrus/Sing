package com.smule.singandroid.upsell;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PurchaseButtonV2 extends RelativeLayout {
    boolean f24552a;
    boolean f24553b;
    boolean f24554c;
    @ViewById
    LinearLayout f24555d;
    @ViewById
    TextView f24556e;
    @ViewById
    TextView f24557f;
    @ViewById
    TextView f24558g;

    public PurchaseButtonV2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PurchaseButtonV2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTagVisibility(boolean z) {
        this.f24553b = z;
    }

    public void m25745a(String str, String str2, String str3) {
        this.f24556e.setText(str);
        if (TextUtils.isEmpty(str3) || !this.f24553b) {
            this.f24558g.setVisibility(4);
        } else {
            this.f24558g.setText(str3);
            this.f24558g.setVisibility(0);
        }
        if (TextUtils.isEmpty(str2)) {
            this.f24557f.setVisibility(8);
        } else {
            this.f24557f.setText(str2);
            this.f24557f.setVisibility(0);
        }
        m25744a();
    }

    public void setButtonColor(int i) {
        this.f24556e.setBackgroundColor(i);
    }

    public void setButtonTextColor(int i) {
        this.f24556e.setTextColor(i);
    }

    public void setSubtitleTextColor(int i) {
        this.f24557f.setTextColor(i);
    }

    protected void m25744a() {
        if (this.f24558g.getVisibility() == 0) {
            int right;
            float measureText = new Paint(this.f24556e.getPaint()).measureText(this.f24556e.getText().toString());
            int width = this.f24556e.getWidth();
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(C1947R.dimen.margin_small);
            if (measureText / 2.0f > (((float) (this.f24558g.getLeft() - this.f24556e.getLeft())) - (((float) width) / 2.0f)) - ((float) dimensionPixelOffset)) {
                right = (this.f24556e.getRight() - this.f24558g.getLeft()) + dimensionPixelOffset;
            } else {
                right = dimensionPixelOffset;
            }
            this.f24556e.setPadding(dimensionPixelOffset, 0, right, 0);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        m25744a();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(getResources().getDimensionPixelSize(C1947R.dimen.purchase_button_v2_total_height) + getResources().getDimensionPixelSize(C1947R.dimen.margin_medium), 1073741824));
        m25743b();
    }

    private void m25743b() {
        if (!this.f24552a && (getLayoutParams() instanceof MarginLayoutParams)) {
            int dimensionPixelOffset;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
            if (this.f24554c) {
                dimensionPixelOffset = marginLayoutParams.topMargin - getResources().getDimensionPixelOffset(C1947R.dimen.margin_larger);
            } else {
                dimensionPixelOffset = marginLayoutParams.topMargin - getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium);
            }
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, dimensionPixelOffset, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            requestLayout();
            this.f24552a = true;
        }
    }

    public void setHasSmallerTopMargin(boolean z) {
        this.f24554c = z;
    }
}
