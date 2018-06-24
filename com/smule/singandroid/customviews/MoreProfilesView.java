package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;

public class MoreProfilesView extends View {
    Paint f21614a;
    Paint f21615b;
    float f21616c;

    public MoreProfilesView(Context context) {
        super(context);
        m23246a();
    }

    public MoreProfilesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23246a();
    }

    public MoreProfilesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23246a();
    }

    private void m23246a() {
        this.f21614a = new Paint();
        this.f21614a.setColor(Color.rgb(214, 214, 214));
        this.f21615b = new Paint();
        this.f21615b.setColor(-1);
        this.f21616c = TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics());
    }

    protected void onMeasure(int i, int i2) {
        int resolveSizeAndState = resolveSizeAndState((getPaddingLeft() + getPaddingRight()) + getSuggestedMinimumWidth(), i, 1);
        int size = (MeasureSpec.getSize(resolveSizeAndState) + getPaddingBottom()) + getPaddingTop();
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState(MeasureSpec.getSize(resolveSizeAndState), i2, 0));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float min = ((float) Math.min(measuredWidth, measuredHeight)) / 2.0f;
        canvas.drawCircle((float) (measuredWidth / 2), (float) (measuredHeight / 2), min, this.f21614a);
        canvas.drawCircle((float) (measuredWidth / 2), (float) (measuredHeight / 2), this.f21616c, this.f21615b);
        canvas.drawCircle(((float) (measuredWidth / 2)) - ((2.0f * min) / 5.0f), (float) (measuredHeight / 2), this.f21616c, this.f21615b);
        canvas.drawCircle(((float) (measuredWidth / 2)) + ((min * 2.0f) / 5.0f), (float) (measuredHeight / 2), this.f21616c, this.f21615b);
    }
}
