package com.smule.singandroid.models;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.smule.singandroid.models.Lyric.Version;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SongLyrics implements Iterable<LyricLine> {
    private final float f23501a = 0.8f;
    private final List<LyricLine> f23502b = new ArrayList();
    private final List<Lyric> f23503c = new ArrayList();
    private final Paint f23504d = new Paint();
    private final int f23505e;
    private final float f23506f;
    private int f23507g = 0;
    private Version f23508h = Version.RAVEN;

    public SongLyrics(Typeface typeface, float f, int i, float f2) {
        this.f23504d.setTypeface(typeface);
        this.f23504d.setTextSize(f);
        this.f23505e = (int) (((float) i) * 0.8f);
        this.f23506f = f2;
    }

    public void m24747a(Lyric lyric) {
        int i = 0;
        if (this.f23508h == Version.COMMUNITY_V1) {
            List arrayList = new ArrayList(1);
            if (lyric.f23484c == 0.0d) {
                arrayList.add(new Lyric(lyric.f23482a, lyric.f23490i, lyric.f23483b, (double) this.f23506f, lyric.f23485d, lyric.f23486e));
            } else {
                arrayList.add(lyric);
            }
            this.f23502b.add(new LyricLine(arrayList));
            return;
        }
        if (lyric.f23486e) {
            this.f23502b.add(new LyricLine(this.f23503c));
            this.f23503c.clear();
            this.f23507g = 0;
        }
        float measureText = this.f23504d.measureText(lyric.f23482a);
        lyric.f23489h = measureText;
        if (((float) this.f23507g) + measureText > ((float) this.f23505e)) {
            Collection arrayList2 = new ArrayList();
            int i2 = 0;
            while (!this.f23503c.isEmpty()) {
                Lyric lyric2 = (Lyric) this.f23503c.get(this.f23503c.size() - 1);
                if (lyric2.f23482a.endsWith(" ")) {
                    break;
                }
                this.f23503c.remove(this.f23503c.size() - 1);
                arrayList2.add(0, lyric2);
                i2 = (int) (lyric2.f23489h + ((float) i2));
            }
            if (this.f23503c.isEmpty()) {
                this.f23503c.addAll(arrayList2);
                arrayList2.clear();
            } else {
                i = i2;
            }
            if (!this.f23503c.isEmpty()) {
                this.f23502b.add(new LyricLine(this.f23503c));
            }
            this.f23503c.clear();
            this.f23503c.addAll(arrayList2);
            this.f23507g = i;
        }
        this.f23503c.add(lyric);
        this.f23507g = (int) (((float) this.f23507g) + measureText);
    }

    public void m24745a() {
        if (!this.f23503c.isEmpty()) {
            LyricLine lyricLine = new LyricLine(this.f23503c);
            if (!lyricLine.m24742b()) {
                this.f23502b.add(lyricLine);
                this.f23503c.clear();
                this.f23507g = 0;
            }
        }
    }

    public void m24748a(String str, double d, double d2, int i) {
        List arrayList = new ArrayList(1);
        arrayList.add(new Lyric(str, i, d, d2, false, false));
        this.f23502b.add(0, new LyricLine(arrayList));
    }

    public void m24750b(String str, double d, double d2, int i) {
        List arrayList = new ArrayList(1);
        arrayList.add(new Lyric(str, i, d, d2, false, false));
        this.f23502b.add(new LyricLine(arrayList));
    }

    public int m24749b() {
        return this.f23502b.size();
    }

    public boolean m24751c() {
        return this.f23502b.isEmpty();
    }

    public LyricLine m24744a(int i) {
        if (i >= 0 || i < this.f23502b.size()) {
            return (LyricLine) this.f23502b.get(i);
        }
        throw new ArrayIndexOutOfBoundsException("Attempt to get lyric line " + i + " of " + this.f23502b.size());
    }

    public LyricLine m24743a(double d) {
        for (LyricLine lyricLine : this.f23502b) {
            if (d < lyricLine.f23492b) {
                return lyricLine;
            }
            if (d >= lyricLine.f23492b && d <= lyricLine.f23493c) {
                return lyricLine;
            }
        }
        return null;
    }

    public Iterator<LyricLine> iterator() {
        return this.f23502b.listIterator();
    }

    public void m24746a(Version version) {
        this.f23508h = version;
    }

    public Version m24752d() {
        return this.f23508h;
    }
}
