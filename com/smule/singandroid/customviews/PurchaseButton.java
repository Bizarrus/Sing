package com.smule.singandroid.customviews;

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
public class PurchaseButton extends RelativeLayout {
    boolean f21900a;
    boolean f21901b;
    @ViewById
    LinearLayout f21902c;
    @ViewById
    TextView f21903d;
    @ViewById
    TextView f21904e;
    @ViewById
    TextView f21905f;

    public PurchaseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PurchaseButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTagVisibility(boolean z) {
        this.f21901b = z;
    }

    public void m23462a(String str, String str2, String str3) {
        this.f21903d.setText(str);
        if (TextUtils.isEmpty(str3) || !this.f21901b) {
            this.f21905f.setVisibility(4);
        } else {
            this.f21905f.setText(str3);
            this.f21905f.setVisibility(0);
        }
        if (TextUtils.isEmpty(str2)) {
            this.f21904e.setVisibility(8);
        } else {
            this.f21904e.setText(str2);
            this.f21904e.setVisibility(0);
        }
        m23461a();
    }

    protected void m23461a() {
        if (this.f21905f.getVisibility() == 0) {
            int right;
            float measureText = new Paint(this.f21903d.getPaint()).measureText(this.f21903d.getText().toString());
            int width = this.f21902c.getWidth();
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(C1947R.dimen.margin_small);
            if (measureText / 2.0f > (((float) (this.f21905f.getLeft() - this.f21902c.getLeft())) - (((float) width) / 2.0f)) - ((float) dimensionPixelOffset)) {
                right = (this.f21902c.getRight() - this.f21905f.getLeft()) + dimensionPixelOffset;
            } else {
                right = dimensionPixelOffset;
            }
            this.f21903d.setPadding(dimensionPixelOffset, 0, right, 0);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        m23461a();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(getResources().getDimensionPixelSize(C1947R.dimen.row_single_height) + getResources().getDimensionPixelSize(C1947R.dimen.margin_medium), 1073741824));
        m23460b();
    }

    private void m23460b() {
        if (!this.f21900a && (getLayoutParams() instanceof MarginLayoutParams)) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin - getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            requestLayout();
            this.f21900a = true;
        }
    }
}
