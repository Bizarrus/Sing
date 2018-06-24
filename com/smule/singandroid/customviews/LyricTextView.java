/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.text.SpannableString
 *  android.util.AttributeSet
 *  android.widget.TextView
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;
import com.smule.singandroid.customviews.MutableForegroundColorSpan;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.models.LyricLine;
import java.util.ArrayList;

public class LyricTextView
extends TextView {
    private static final String a = LyricTextView.class.getName();
    private final ArrayList<SpannableLyric> b = new ArrayList(20);
    private LyricLine c;
    private int d;
    private Lyric.Version e = Lyric.Version.a;
    private SpannableString f;
    private int g = 0;
    private int h;
    private int i;
    private int j;
    private int k;

    public LyricTextView(Context context) {
        super(context);
        this.a();
    }

    public LyricTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a();
    }

    public LyricTextView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int getActiveColor() {
        boolean bl;
        boolean bl2 = bl = true;
        if (this.d != 1) {
            if (this.d != 2) return this.getContext().getResources().getColor(2131689783);
            bl2 = bl;
            if (!bl2) {
                return this.getContext().getResources().getColor(2131689783);
            }
        }
        if (this.c.f == 3) {
            return this.getContext().getResources().getColor(2131689788);
        }
        if (this.c.f != this.d && this.c.f != 0) return this.getContext().getResources().getColor(2131689785);
        return this.getContext().getResources().getColor(2131689783);
    }

    private int getOnsetColor() {
        return this.getResources().getColor(2131689781);
    }

    private int getPastColor() {
        return this.getResources().getColor(2131689786);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int getPendingColor() {
        boolean bl;
        boolean bl2 = bl = true;
        if (this.d != 1) {
            if (this.d != 2) return this.getContext().getResources().getColor(2131689782);
            bl2 = bl;
            if (!bl2) {
                return this.getContext().getResources().getColor(2131689782);
            }
        }
        if (this.c.f == 3) {
            return this.getContext().getResources().getColor(2131689787);
        }
        if (this.c.f != this.d && this.c.f != 0) return this.getContext().getResources().getColor(2131689784);
        return this.getContext().getResources().getColor(2131689782);
    }

    public void a() {
        for (int i = 0; i < 10; ++i) {
            this.b.add(new SpannableLyric());
        }
    }

    public boolean a(double d) {
        if (this.c != null && d >= this.c.b) {
            return true;
        }
        return false;
    }

    public void b(double d) {
        boolean bl = false;
        for (int i = 0; i < this.g; ++i) {
            bl |= this.b.get(i).a(d);
        }
        if (bl) {
            this.setText((CharSequence)this.f);
        }
    }

    public double getDuration() {
        return this.c.c - this.c.b;
    }

    public double getEndTime() {
        if (this.c != null) {
            return this.c.b;
        }
        return Double.NaN;
    }

    public double getLastScrollToPastDuration() {
        if (this.g > 0) {
            return this.b.get(this.g - 1).b();
        }
        return 0.0;
    }

    public double getStartTime() {
        if (this.c != null) {
            return this.c.b;
        }
        return Double.NaN;
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
    }

    public void setLyricVersion(Lyric.Version version) {
        this.e = version;
    }

    public void setLyrics(LyricLine lyricLine) {
        this.c = lyricLine;
        this.g = 0;
        if (lyricLine == null) {
            this.setText((CharSequence)"");
            return;
        }
        Object object = new StringBuilder();
        for (Lyric lyric : lyricLine) {
            lyric.g.a = object.length();
            object.append(lyric.a);
            lyric.g.b = object.length();
            if (this.b.size() <= this.g) {
                this.b.add(new SpannableLyric());
            }
            SpannableLyric spannableLyric = this.b.get(this.g);
            spannableLyric.a(lyric);
            spannableLyric.a(this.h);
            ++this.g;
        }
        this.f = new SpannableString((CharSequence)object.toString());
        for (int i = 0; i < this.g; ++i) {
            object = this.b.get(i);
            if (object.a()) continue;
            this.f.setSpan((Object)object.b, object.c(), object.d(), 33);
        }
        lyricLine.a((int)(lyricLine.a() - 1)).g.b = -1;
        this.setText((CharSequence)this.f);
    }

    public void setSingPart(int n) {
        this.d = n;
    }

    private class SpannableLyric {
        Lyric a;
        MutableForegroundColorSpan b;
        double c;
        double d;
        double e;
        double f;

        private SpannableLyric() {
            this.a = null;
            this.b = new MutableForegroundColorSpan(255, -16777216);
        }

        public int a(int n, int n2, double d, double d2, double d3) {
            float f = Math.min(1.0f, Math.max(0.0f, (float)((d3 - d) / (d2 - d))));
            int n3 = n >> 24 & 255;
            int n4 = n >> 16 & 255;
            int n5 = n >> 8 & 255;
            int n6 = (int)((float)((n2 >> 24 & 255) - n3) * f);
            int n7 = (int)((float)((n2 >> 16 & 255) - n4) * f);
            int n8 = (int)((float)((n2 >> 8 & 255) - n5) * f);
            return (int)(f * (float)((n2 & 255) - n)) + (n &= 255) | (n3 + n6 << 24 | n4 + n7 << 16 | n8 + n5 << 8);
        }

        public void a(int n) {
            this.b.a(n);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(Lyric lyric) {
            this.a = lyric;
            LyricTextView.this.h = LyricTextView.this.getPendingColor();
            LyricTextView.this.i = LyricTextView.this.getOnsetColor();
            LyricTextView.this.j = LyricTextView.this.getActiveColor();
            LyricTextView.this.k = LyricTextView.this.getPastColor();
            double d = this.e();
            double d2 = this.f();
            double d3 = this.g();
            double d4 = LyricTextView.this.e == Lyric.Version.b ? 0.45 : 0.066;
            double d5 = Math.min(0.4 * d3, 2.5);
            this.c = this.a.b;
            if (this.d + d4 + d5 <= d2) {
                this.d = this.c + 0.033;
                this.e = d4 + this.d;
                this.f = d2 - d5;
                return;
            }
            if (this.d + d5 > d3) {
                this.d = d3 / 3.0 + d;
                this.e = this.f = 2.0 * d3 / 3.0 + d;
                return;
            }
            this.d = this.c + 0.033;
            this.e = this.f = d2 - d5;
        }

        public boolean a() {
            if (this.a == null || this.a.a == null || this.a.a.isEmpty()) {
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean a(double d) {
            int n = d >= this.f() ? LyricTextView.this.k : (d >= this.f ? this.a(LyricTextView.this.j, LyricTextView.this.k, this.f, this.f(), d) : (d >= this.e ? LyricTextView.this.j : (d >= this.d ? this.a(LyricTextView.this.i, LyricTextView.this.j, this.d, this.e, d) : (d >= this.c ? this.a(LyricTextView.this.h, LyricTextView.this.i, this.c, this.d, d) : LyricTextView.this.h))));
            boolean bl = n != this.h();
            this.a(n);
            return bl;
        }

        public double b() {
            return this.a.c - this.f;
        }

        public int c() {
            if (this.a()) {
                return -1;
            }
            return this.a.g.a;
        }

        public int d() {
            if (this.a()) {
                return -1;
            }
            return this.a.g.b;
        }

        public double e() {
            return this.a.b;
        }

        public double f() {
            return this.a.c;
        }

        public double g() {
            return this.a.f;
        }

        public int h() {
            return this.b.getForegroundColor();
        }

        public String toString() {
            double d = Math.min(this.g() * 0.4, 2.5);
            return this.a.a + ": start=" + this.e() + ";onset=" + this.c + ";active=" + this.d + ";steady=" + this.e + ";idealFadeOutDur=" + d + ";past=" + this.f + ";end=" + this.f() + ";dur=" + this.g();
        }
    }

}

