package com.smule.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class OverlayWithHoleImageView extends ImageView {
    private RectF f17694a;
    private int f17695b;
    private int f17696c;
    private int f17697d;
    private final Paint f17698e = new Paint(1);
    private final PorterDuffXfermode f17699f = new PorterDuffXfermode(Mode.CLEAR);
    private OnClickListener f17700g;
    private OnClickListener f17701h;

    public OverlayWithHoleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayerType(1, null);
    }

    public void m18874a(int i, int i2, int i3) {
        RectF rectF = new RectF((float) (i - i3), (float) (i2 - i3), (float) (i + i3), (float) (i2 + i3));
        this.f17695b = i;
        this.f17696c = i2;
        this.f17694a = rectF;
        this.f17697d = i3;
        postInvalidate();
    }

    public void m18875a(OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.f17701h = onClickListener;
        this.f17700g = onClickListener2;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f17694a != null) {
            this.f17698e.setXfermode(this.f17699f);
            canvas.drawRoundRect(this.f17694a, (float) this.f17697d, (float) this.f17697d, this.f17698e);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (Math.abs(motionEvent.getX() - ((float) this.f17695b)) >= ((float) this.f17697d) || Math.abs(motionEvent.getY() - ((float) this.f17696c)) >= ((float) this.f17697d)) {
                if (this.f17700g != null) {
                    this.f17700g.onClick(null);
                }
            } else if (this.f17701h != null) {
                this.f17701h.onClick(null);
            }
        }
        return true;
    }
}
