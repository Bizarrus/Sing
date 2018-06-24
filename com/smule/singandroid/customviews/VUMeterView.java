package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.smule.singandroid.C1947R;

public class VUMeterView extends View {
    private final int[] f22041a;
    private final Paint f22042b;
    private final DecelerateInterpolator f22043c;
    private int f22044d;

    public VUMeterView(Context context) {
        super(context);
        this.f22041a = new int[19];
        this.f22042b = new Paint();
        this.f22043c = new DecelerateInterpolator(2.0f);
        m23526a();
    }

    public VUMeterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f22041a = new int[19];
        this.f22042b = new Paint();
        this.f22043c = new DecelerateInterpolator(2.0f);
        m23526a();
    }

    public VUMeterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f22041a = new int[19];
        this.f22042b = new Paint();
        this.f22043c = new DecelerateInterpolator(2.0f);
        m23526a();
    }

    public void m23527a(double d) {
        int min = Math.min((int) (((double) (this.f22043c.getInterpolation((float) d) * 19.0f)) + 0.5d), 19);
        if (min != this.f22044d) {
            this.f22044d = min;
            invalidate();
        }
    }

    public float getVolumeFraction() {
        return ((float) this.f22044d) / 19.0f;
    }

    private void m23526a() {
        float[] fArr = new float[3];
        float[] fArr2 = new float[3];
        float[] fArr3 = new float[3];
        Color.colorToHSV(getResources().getColor(C1947R.color.vu_meter_initial), fArr);
        Color.colorToHSV(getResources().getColor(C1947R.color.vu_meter_final), fArr2);
        float abs = Math.abs(fArr2[0] - fArr[0]) / RadialCountdown.TEXT_SIZE_SP;
        float abs2 = Math.abs(fArr[0] - fArr2[0]) / RadialCountdown.TEXT_SIZE_SP;
        if (abs > abs2) {
            abs = Math.signum(fArr[0] - fArr2[0]) * abs2;
        } else {
            abs *= Math.signum(fArr2[0] - fArr[0]);
        }
        float f = ((fArr2[1] - fArr[1]) / RadialCountdown.TEXT_SIZE_SP) * ((float) (fArr[1] < fArr2[1] ? 1 : -1));
        float f2 = ((fArr2[2] - fArr[2]) / RadialCountdown.TEXT_SIZE_SP) * ((float) (fArr[2] < fArr2[2] ? 1 : -1));
        for (int i = 0; i < 19; i++) {
            fArr3[0] = fArr[0] + (((float) i) * abs);
            fArr3[1] = fArr[1] + (((float) i) * f);
            fArr3[2] = fArr[2] + (((float) i) * f2);
            this.f22041a[i] = Color.HSVToColor(fArr3);
        }
        this.f22042b.setAntiAlias(true);
        this.f22042b.setStyle(Style.FILL);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = (float) canvas.getWidth();
        float height = ((float) canvas.getHeight()) / 19.0f;
        float f = 0.5f * ((width < height ? width : height) * 0.75f);
        width /= 2.0f;
        for (int i = 0; i < this.f22044d; i++) {
            this.f22042b.setColor(this.f22041a[i]);
            canvas.drawCircle(width, ((float) canvas.getHeight()) - ((((float) i) * height) + f), f, this.f22042b);
        }
    }
}
