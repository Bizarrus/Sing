/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Typeface
 *  android.widget.ImageView
 */
package com.smule.android.console;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.ImageView;
import com.smule.android.console.ConsoleScrollView;
import com.smule.android.console.ConstantData;
import java.util.LinkedList;
import java.util.ListIterator;

public class ConsoleOutputTextView
extends ImageView
implements ConsoleScrollView.ScrollViewListener {
    private int a = 20;
    private int b;
    private final int c;
    private Paint d = new Paint();
    private LinkedList<String> e;
    private StringBuilder f;
    private int g;
    private int h;

    public ConsoleOutputTextView(Context context, int n) {
        super(context);
        this.d.setTypeface(Typeface.MONOSPACE);
        this.d.setColor(-1);
        this.e = new LinkedList();
        this.f = new StringBuilder(512);
        this.c = n;
        this.setFontSize(this.a);
        this.h = 0;
    }

    public void a() {
        int n = this.e.size();
        for (int i = 0; i < n; ++i) {
            this.e.remove(0);
        }
        this.requestLayout();
        this.invalidate();
    }

    @Override
    public void a(int n, int n2, int n3, int n4) {
        this.h = n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(String string2) {
        int n = 0;
        float f = this.getWidth();
        this.f.delete(0, this.f.length());
        if (this.e.size() > 0) {
            this.f.append(this.e.get(this.e.size() - 1));
            this.e.remove(this.e.size() - 1);
        }
        this.f.append(string2);
        int n2 = 0;
        while (n < this.f.length()) {
            int n3;
            int n4 = this.f.indexOf("\n", n2);
            if (n4 != -1) {
                int n5;
                n3 = n5 = this.d.breakText((CharSequence)this.f, n, n4, true, f, null);
                n2 = n4;
                if (n5 < n4 - n) {
                    n2 = -1;
                    n3 = n5;
                }
            } else {
                n3 = this.d.breakText((CharSequence)this.f, n, this.f.length(), true, f, null);
                n2 = n4;
            }
            this.e.add(this.f.substring(n, n + n3));
            n4 = n2 != -1 ? 1 : 0;
            n = n3 + n4 + n;
            if (n2 != -1 && n == this.f.length()) {
                this.e.add("");
            }
            n3 = n;
            n2 = n;
            n = n3;
        }
        if (this.e.size() > 2048) {
            n2 = this.e.size();
            for (n = 0; n < n2 - 2048; ++n) {
                this.e.remove(0);
            }
        }
        this.requestLayout();
        this.invalidate();
    }

    public int getFontSize() {
        return (int)this.d.getTextSize();
    }

    public int getViewableHeight() {
        return this.e.size() * this.b + 16 - this.h;
    }

    protected void onDraw(Canvas canvas) {
        int n;
        int n2 = 0;
        canvas.drawColor(-16777216);
        int n3 = n = this.h / this.b;
        if (n >= this.e.size()) {
            n3 = this.e.size() - 1;
        }
        n = n3;
        if (n3 < 0) {
            n = 0;
        }
        float f = 16.0f + (float)(this.b * n);
        ListIterator<String> listIterator = this.e.listIterator(n);
        n3 = n2;
        do {
            float f2;
            block7 : {
                block6 : {
                    if (!listIterator.hasNext()) break block6;
                    canvas.drawText(listIterator.next(), 0.0f, f, this.d);
                    f2 = this.b;
                    if (++n3 <= this.g) break block7;
                }
                return;
            }
            f = f2 + f;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onMeasure(int n, int n2) {
        int n3 = this.e.size() < this.g ? this.g : this.e.size();
        n2 = ConsoleOutputTextView.getDefaultSize((int)(n3 * this.b), (int)n2);
        this.setMeasuredDimension(ConsoleOutputTextView.getDefaultSize((int)0, (int)n), n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setFontSize(int n) {
        block2 : {
            boolean bl = false;
            ConstantData.ConsoleFontSize[] arrconsoleFontSize = ConstantData.ConsoleFontSize.values();
            int n2 = arrconsoleFontSize.length;
            int n3 = 0;
            do {
                block4 : {
                    boolean bl2;
                    block3 : {
                        bl2 = bl;
                        if (n3 >= n2) break block3;
                        if (n != arrconsoleFontSize[n3].a()) break block4;
                        bl2 = true;
                    }
                    if (!bl2) break;
                    break block2;
                }
                ++n3;
            } while (true);
            n = ConstantData.ConsoleFontSize.b.a();
        }
        this.a = n;
        this.d.setTextSize((float)this.a);
        this.b = this.a + 2;
        this.g = this.c / this.b;
    }
}

