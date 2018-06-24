/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Paint
 *  android.graphics.Typeface
 */
package com.smule.singandroid.models;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.models.LyricLine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SongLyrics
implements Iterable<LyricLine> {
    private final float a = 0.8f;
    private final List<LyricLine> b = new ArrayList<LyricLine>();
    private final List<Lyric> c = new ArrayList<Lyric>();
    private final Paint d = new Paint();
    private final int e;
    private final float f;
    private int g = 0;
    private Lyric.Version h = Lyric.Version.a;

    public SongLyrics(Typeface typeface, float f, int n, float f2) {
        this.d.setTypeface(typeface);
        this.d.setTextSize(f);
        this.e = (int)((float)n * 0.8f);
        this.f = f2;
    }

    public LyricLine a(double d) {
        for (LyricLine lyricLine : this.b) {
            if (d < lyricLine.b) {
                return lyricLine;
            }
            if (d < lyricLine.b || d > lyricLine.c) continue;
            return lyricLine;
        }
        return null;
    }

    public LyricLine a(int n) {
        if (n < 0 && n >= this.b.size()) {
            throw new ArrayIndexOutOfBoundsException("Attempt to get lyric line " + n + " of " + this.b.size());
        }
        return this.b.get(n);
    }

    public void a() {
        LyricLine lyricLine;
        if (!this.c.isEmpty() && !(lyricLine = new LyricLine(this.c)).b()) {
            this.b.add(lyricLine);
            this.c.clear();
            this.g = 0;
        }
    }

    public void a(Lyric.Version version) {
        this.h = version;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Lyric lyric) {
        float f;
        block10 : {
            int n = 0;
            if (this.h == Lyric.Version.b) {
                ArrayList<Lyric> arrayList = new ArrayList<Lyric>(1);
                if (lyric.c == 0.0) {
                    arrayList.add(new Lyric(lyric.a, lyric.i, lyric.b, this.f, lyric.d, lyric.e));
                } else {
                    arrayList.add(lyric);
                }
                this.b.add(new LyricLine(arrayList));
                return;
            }
            if (lyric.e) {
                this.b.add(new LyricLine(this.c));
                this.c.clear();
                this.g = 0;
            }
            lyric.h = f = this.d.measureText(lyric.a);
            if ((float)this.g + f <= (float)this.e) break block10;
            ArrayList<Lyric> arrayList = new ArrayList<Lyric>();
            int n2 = 0;
            do {
                Lyric lyric2;
                block12 : {
                    block11 : {
                        if (this.c.isEmpty()) break block11;
                        lyric2 = this.c.get(this.c.size() - 1);
                        if (!lyric2.a.endsWith(" ")) break block12;
                    }
                    if (this.c.isEmpty()) {
                        this.c.addAll(arrayList);
                        arrayList.clear();
                        n2 = n;
                    }
                    if (!this.c.isEmpty()) {
                        this.b.add(new LyricLine(this.c));
                    }
                    this.c.clear();
                    this.c.addAll(arrayList);
                    this.g = n2;
                    break;
                }
                this.c.remove(this.c.size() - 1);
                arrayList.add(0, lyric2);
                float f2 = n2;
                n2 = (int)(lyric2.h + f2);
            } while (true);
        }
        this.c.add(lyric);
        this.g = (int)((float)this.g + f);
    }

    public void a(String string2, double d, double d2, int n) {
        ArrayList<Lyric> arrayList = new ArrayList<Lyric>(1);
        arrayList.add(new Lyric(string2, n, d, d2, false, false));
        this.b.add(0, new LyricLine(arrayList));
    }

    public int b() {
        return this.b.size();
    }

    public void b(String string2, double d, double d2, int n) {
        ArrayList<Lyric> arrayList = new ArrayList<Lyric>(1);
        arrayList.add(new Lyric(string2, n, d, d2, false, false));
        this.b.add(new LyricLine(arrayList));
    }

    public boolean c() {
        return this.b.isEmpty();
    }

    public Lyric.Version d() {
        return this.h;
    }

    @Override
    public Iterator<LyricLine> iterator() {
        return this.b.listIterator();
    }
}

