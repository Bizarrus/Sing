package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

public class OverlayWithRectangularHoleImageView extends ImageView {
    private RectF f21684a;
    private final Paint f21685b = new Paint(1);
    private final Paint f21686c = new Paint(1);
    private OnClickListener f21687d;

    public OverlayWithRectangularHoleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    public void m23312a(int i, int i2, int i3, int i4) {
        this.f21684a = new RectF((float) i, (float) i3, (float) i2, (float) i4);
        postInvalidate();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f21687d = onClickListener;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f21684a != null) {
            this.f21685b.setColor(getResources().getColor(17170444));
            this.f21685b.setStyle(Style.FILL);
            this.f21685b.setAlpha(CtaButton.WIDTH_DIPS);
            canvas.drawPaint(this.f21685b);
            this.f21686c.setColor(0);
            this.f21686c.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));
            this.f21686c.setAntiAlias(true);
            canvas.drawRect(this.f21684a, this.f21686c);
        }
    }

    private boolean m23311a(MotionEvent motionEvent) {
        return motionEvent.getX() > this.f21684a.left && motionEvent.getX() < this.f21684a.right && motionEvent.getY() > this.f21684a.top && motionEvent.getY() < this.f21684a.bottom;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f21684a == null) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            if (m23311a(motionEvent)) {
                if (this.f21687d == null) {
                    return false;
                }
                this.f21687d.onClick(this);
                return false;
            }
        } else if (!(motionEvent.getAction() != 1 || m23311a(motionEvent) || this.f21687d == null)) {
            this.f21687d.onClick(this);
        }
        return true;
    }
}
