package com.smule.android.console;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.ImageView;
import com.smule.android.console.ConsoleScrollView.ScrollViewListener;
import com.smule.android.console.ConstantData.ConsoleFontSize;
import java.util.LinkedList;
import java.util.ListIterator;

public class ConsoleOutputTextView extends ImageView implements ScrollViewListener {
    private int f15727a = 20;
    private int f15728b;
    private final int f15729c;
    private Paint f15730d = new Paint();
    private LinkedList<String> f15731e;
    private StringBuilder f15732f;
    private int f15733g;
    private int f15734h;

    public ConsoleOutputTextView(Context context, int i) {
        super(context);
        this.f15730d.setTypeface(Typeface.MONOSPACE);
        this.f15730d.setColor(-1);
        this.f15731e = new LinkedList();
        this.f15732f = new StringBuilder(512);
        this.f15729c = i;
        setFontSize(this.f15727a);
        this.f15734h = 0;
    }

    public void m17548a(String str) {
        int i = 0;
        float width = (float) getWidth();
        this.f15732f.delete(0, this.f15732f.length());
        if (this.f15731e.size() > 0) {
            this.f15732f.append((String) this.f15731e.get(this.f15731e.size() - 1));
            this.f15731e.remove(this.f15731e.size() - 1);
        }
        this.f15732f.append(str);
        int i2 = 0;
        while (i < this.f15732f.length()) {
            int indexOf = this.f15732f.indexOf("\n", i2);
            if (indexOf != -1) {
                i2 = this.f15730d.breakText(this.f15732f, i, indexOf, true, width, null);
                if (i2 < indexOf - i) {
                    indexOf = -1;
                }
            } else {
                i2 = this.f15730d.breakText(this.f15732f, i, this.f15732f.length(), true, width, null);
            }
            this.f15731e.add(this.f15732f.substring(i, i + i2));
            i2 = (i2 + (indexOf != -1 ? 1 : 0)) + i;
            if (indexOf != -1 && i2 == this.f15732f.length()) {
                this.f15731e.add("");
            }
            i = i2;
        }
        if (this.f15731e.size() > 2048) {
            int size = this.f15731e.size() - 2048;
            for (i2 = 0; i2 < size; i2++) {
                this.f15731e.remove(0);
            }
        }
        requestLayout();
        invalidate();
    }

    public void m17546a() {
        int size = this.f15731e.size();
        for (int i = 0; i < size; i++) {
            this.f15731e.remove(0);
        }
        requestLayout();
        invalidate();
    }

    public int getViewableHeight() {
        return ((this.f15731e.size() * this.f15728b) + 16) - this.f15734h;
    }

    public int getFontSize() {
        return (int) this.f15730d.getTextSize();
    }

    public void setFontSize(int i) {
        Object obj = null;
        for (ConsoleFontSize a : ConsoleFontSize.values()) {
            if (i == a.m17550a()) {
                obj = 1;
                break;
            }
        }
        if (obj == null) {
            i = ConsoleFontSize.BIG.m17550a();
        }
        this.f15727a = i;
        this.f15730d.setTextSize((float) this.f15727a);
        this.f15728b = this.f15727a + 2;
        this.f15733g = this.f15729c / this.f15728b;
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        canvas.drawColor(-16777216);
        int i2 = this.f15734h / this.f15728b;
        if (i2 >= this.f15731e.size()) {
            i2 = this.f15731e.size() - 1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        float f = 16.0f + ((float) (this.f15728b * i2));
        ListIterator listIterator = this.f15731e.listIterator(i2);
        while (listIterator.hasNext()) {
            canvas.drawText((String) listIterator.next(), 0.0f, f, this.f15730d);
            float f2 = ((float) this.f15728b) + f;
            i++;
            if (i <= this.f15733g) {
                f = f2;
            } else {
                return;
            }
        }
    }

    public void mo6238a(int i, int i2, int i3, int i4) {
        this.f15734h = i2;
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize((this.f15731e.size() < this.f15733g ? this.f15733g : this.f15731e.size()) * this.f15728b, i2));
    }
}
