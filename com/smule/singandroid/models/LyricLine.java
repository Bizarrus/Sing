package com.smule.singandroid.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LyricLine implements Iterable<Lyric> {
    public final List<Lyric> f23491a = new ArrayList();
    public final double f23492b;
    public final double f23493c;
    public final double f23494d;
    public final int f23495e;
    public final int f23496f;

    public LyricLine(List<Lyric> list) {
        int i = 0;
        this.f23491a.addAll(list);
        this.f23492b = ((Lyric) list.get(0)).f23483b;
        this.f23493c = ((Lyric) list.get(list.size() - 1)).f23484c;
        this.f23494d = this.f23493c - this.f23492b;
        int i2 = 0;
        for (Lyric lyric : list) {
            i2 = (int) (lyric.f23489h + ((float) i2));
        }
        this.f23495e = i2;
        for (Lyric lyric2 : list) {
            if (!lyric2.f23485d) {
                i = lyric2.f23490i;
            }
        }
        this.f23496f = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lyric lyric : this.f23491a) {
            stringBuilder.append(lyric.f23482a);
        }
        return stringBuilder.toString();
    }

    public Iterator<Lyric> iterator() {
        return this.f23491a.listIterator();
    }

    public int m24740a() {
        return this.f23491a.size();
    }

    public boolean m24742b() {
        for (Lyric lyric : this.f23491a) {
            if (lyric.f23482a != null && !lyric.f23482a.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Lyric m24741a(int i) {
        return (Lyric) this.f23491a.get(i);
    }
}
