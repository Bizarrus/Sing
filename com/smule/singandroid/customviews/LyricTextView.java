package com.smule.singandroid.customviews;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.models.Lyric.Version;
import com.smule.singandroid.models.LyricLine;
import java.util.ArrayList;
import java.util.Iterator;

public class LyricTextView extends TextView {
    private static final String f21563a = LyricTextView.class.getName();
    private final ArrayList<SpannableLyric> f21564b = new ArrayList(20);
    private LyricLine f21565c;
    private int f21566d;
    private Version f21567e = Version.RAVEN;
    private SpannableString f21568f;
    private int f21569g = 0;
    private int f21570h;
    private int f21571i;
    private int f21572j;
    private int f21573k;

    private class SpannableLyric {
        Lyric f21556a;
        MutableForegroundColorSpan f21557b;
        double f21558c;
        double f21559d;
        double f21560e;
        double f21561f;
        final /* synthetic */ LyricTextView f21562g;

        private SpannableLyric(LyricTextView lyricTextView) {
            this.f21562g = lyricTextView;
            this.f21556a = null;
            this.f21557b = new MutableForegroundColorSpan(255, -16777216);
        }

        public boolean m23187a() {
            return this.f21556a == null || this.f21556a.f23482a == null || this.f21556a.f23482a.isEmpty();
        }

        public void m23186a(Lyric lyric) {
            this.f21556a = lyric;
            this.f21562g.f21570h = this.f21562g.getPendingColor();
            this.f21562g.f21571i = this.f21562g.getOnsetColor();
            this.f21562g.f21572j = this.f21562g.getActiveColor();
            this.f21562g.f21573k = this.f21562g.getPastColor();
            double e = m23192e();
            double f = m23193f();
            double g = m23194g();
            double d = this.f21562g.f21567e == Version.COMMUNITY_V1 ? 0.45d : 0.066d;
            double min = Math.min(0.4d * g, 2.5d);
            this.f21558c = this.f21556a.f23483b;
            if ((this.f21559d + d) + min <= f) {
                this.f21559d = this.f21558c + 0.033d;
                this.f21560e = d + this.f21559d;
                this.f21561f = f - min;
            } else if (this.f21559d + min > g) {
                this.f21559d = (g / 3.0d) + e;
                this.f21561f = ((2.0d * g) / 3.0d) + e;
                this.f21560e = this.f21561f;
            } else {
                this.f21559d = this.f21558c + 0.033d;
                this.f21561f = f - min;
                this.f21560e = this.f21561f;
            }
        }

        public double m23189b() {
            return this.f21556a.f23484c - this.f21561f;
        }

        public int m23190c() {
            return m23187a() ? -1 : this.f21556a.f23488g.f23477a;
        }

        public int m23191d() {
            return m23187a() ? -1 : this.f21556a.f23488g.f23478b;
        }

        public double m23192e() {
            return this.f21556a.f23483b;
        }

        public double m23193f() {
            return this.f21556a.f23484c;
        }

        public double m23194g() {
            return this.f21556a.f23487f;
        }

        public int m23195h() {
            return this.f21557b.getForegroundColor();
        }

        public void m23185a(int i) {
            this.f21557b.m23247a(i);
        }

        public String toString() {
            return this.f21556a.f23482a + ": start=" + m23192e() + ";onset=" + this.f21558c + ";active=" + this.f21559d + ";steady=" + this.f21560e + ";idealFadeOutDur=" + Math.min(m23194g() * 0.4d, 2.5d) + ";past=" + this.f21561f + ";end=" + m23193f() + ";dur=" + m23194g();
        }

        public boolean m23188a(double d) {
            int f;
            if (d >= m23193f()) {
                f = this.f21562g.f21573k;
            } else if (d >= this.f21561f) {
                f = m23184a(this.f21562g.f21572j, this.f21562g.f21573k, this.f21561f, m23193f(), d);
            } else if (d >= this.f21560e) {
                f = this.f21562g.f21572j;
            } else if (d >= this.f21559d) {
                f = m23184a(this.f21562g.f21571i, this.f21562g.f21572j, this.f21559d, this.f21560e, d);
            } else if (d >= this.f21558c) {
                f = m23184a(this.f21562g.f21570h, this.f21562g.f21571i, this.f21558c, this.f21559d, d);
            } else {
                f = this.f21562g.f21570h;
            }
            boolean z = f != m23195h();
            m23185a(f);
            return z;
        }

        public int m23184a(int i, int i2, double d, double d2, double d3) {
            float min = Math.min(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, Math.max(0.0f, (float) ((d3 - d) / (d2 - d))));
            int i3 = (i >> 24) & 255;
            int i4 = (i >> 16) & 255;
            int i5 = (i >> 8) & 255;
            int i6 = i & 255;
            return (((int) (min * ((float) ((i2 & 255) - i6)))) + i6) | ((((i3 + ((int) (((float) (((i2 >> 24) & 255) - i3)) * min))) << 24) | ((i4 + ((int) (((float) (((i2 >> 16) & 255) - i4)) * min))) << 16)) | ((((int) (((float) (((i2 >> 8) & 255) - i5)) * min)) + i5) << 8));
        }
    }

    public LyricTextView(Context context) {
        super(context);
        m23209a();
    }

    public LyricTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23209a();
    }

    public LyricTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23209a();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void m23209a() {
        for (int i = 0; i < 10; i++) {
            this.f21564b.add(new SpannableLyric());
        }
    }

    public void setLyrics(LyricLine lyricLine) {
        this.f21565c = lyricLine;
        this.f21569g = 0;
        if (lyricLine == null) {
            setText("");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = lyricLine.iterator();
        while (it.hasNext()) {
            Lyric lyric = (Lyric) it.next();
            lyric.f23488g.f23477a = stringBuilder.length();
            stringBuilder.append(lyric.f23482a);
            lyric.f23488g.f23478b = stringBuilder.length();
            if (this.f21564b.size() <= this.f21569g) {
                this.f21564b.add(new SpannableLyric());
            }
            SpannableLyric spannableLyric = (SpannableLyric) this.f21564b.get(this.f21569g);
            spannableLyric.m23186a(lyric);
            spannableLyric.m23185a(this.f21570h);
            this.f21569g++;
        }
        this.f21568f = new SpannableString(stringBuilder.toString());
        for (int i = 0; i < this.f21569g; i++) {
            SpannableLyric spannableLyric2 = (SpannableLyric) this.f21564b.get(i);
            if (!spannableLyric2.m23187a()) {
                this.f21568f.setSpan(spannableLyric2.f21557b, spannableLyric2.m23190c(), spannableLyric2.m23191d(), 33);
            }
        }
        lyricLine.m24741a(lyricLine.m24740a() - 1).f23488g.f23478b = -1;
        setText(this.f21568f);
    }

    public void setLyricVersion(Version version) {
        this.f21567e = version;
    }

    private int getPendingColor() {
        Object obj = 1;
        if (!(this.f21566d == 1 || this.f21566d == 2)) {
            obj = null;
        }
        if (obj == null) {
            return getContext().getResources().getColor(C1947R.color.lyrics_my_part);
        }
        if (this.f21565c.f23496f == 3) {
            return getContext().getResources().getColor(C1947R.color.lyrics_together);
        }
        if (this.f21565c.f23496f == this.f21566d || this.f21565c.f23496f == 0) {
            return getContext().getResources().getColor(C1947R.color.lyrics_my_part);
        }
        return getContext().getResources().getColor(C1947R.color.lyrics_other_part);
    }

    private int getActiveColor() {
        Object obj = 1;
        if (!(this.f21566d == 1 || this.f21566d == 2)) {
            obj = null;
        }
        if (obj == null) {
            return getContext().getResources().getColor(C1947R.color.lyrics_my_part_active);
        }
        if (this.f21565c.f23496f == 3) {
            return getContext().getResources().getColor(C1947R.color.lyrics_together_active);
        }
        if (this.f21565c.f23496f == this.f21566d || this.f21565c.f23496f == 0) {
            return getContext().getResources().getColor(C1947R.color.lyrics_my_part_active);
        }
        return getContext().getResources().getColor(C1947R.color.lyrics_other_part_active);
    }

    private int getOnsetColor() {
        return getResources().getColor(C1947R.color.lyrics_bright);
    }

    private int getPastColor() {
        return getResources().getColor(C1947R.color.lyrics_past);
    }

    public double getLastScrollToPastDuration() {
        if (this.f21569g > 0) {
            return ((SpannableLyric) this.f21564b.get(this.f21569g - 1)).m23189b();
        }
        return 0.0d;
    }

    public void setSingPart(int i) {
        this.f21566d = i;
    }

    public double getStartTime() {
        return this.f21565c != null ? this.f21565c.f23492b : Double.NaN;
    }

    public double getEndTime() {
        return this.f21565c != null ? this.f21565c.f23492b : Double.NaN;
    }

    public double getDuration() {
        return this.f21565c.f23493c - this.f21565c.f23492b;
    }

    public boolean m23210a(double d) {
        return this.f21565c != null && d >= this.f21565c.f23492b;
    }

    public void m23211b(double d) {
        int i = 0;
        for (int i2 = 0; i2 < this.f21569g; i2++) {
            i |= ((SpannableLyric) this.f21564b.get(i2)).m23188a(d);
        }
        if (i != 0) {
            setText(this.f21568f);
        }
    }
}
