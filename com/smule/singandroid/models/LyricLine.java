/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.models;

import com.smule.singandroid.models.Lyric;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LyricLine
implements Iterable<Lyric> {
    public final List<Lyric> a;
    public final double b;
    public final double c;
    public final double d;
    public final int e;
    public final int f;

    public LyricLine(List<Lyric> object) {
        int n = 0;
        this.a = new ArrayList<Lyric>();
        this.a.addAll((Collection<Lyric>)object);
        this.b = object.get((int)0).b;
        this.c = object.get((int)(object.size() - 1)).c;
        this.d = this.c - this.b;
        Object object2 = object.iterator();
        int n2 = 0;
        while (object2.hasNext()) {
            Lyric lyric = object2.next();
            float f = n2;
            n2 = (int)(lyric.h + f);
        }
        this.e = n2;
        object = object.iterator();
        n2 = n;
        while (object.hasNext()) {
            object2 = (Lyric)object.next();
            if (object2.d) continue;
            n2 = object2.i;
        }
        this.f = n2;
    }

    public int a() {
        return this.a.size();
    }

    public Lyric a(int n) {
        return this.a.get(n);
    }

    public boolean b() {
        for (Lyric lyric : this.a) {
            if (lyric.a == null || lyric.a.isEmpty()) continue;
            return false;
        }
        return true;
    }

    @Override
    public Iterator<Lyric> iterator() {
        return this.a.listIterator();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Lyric> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next().a);
        }
        return stringBuilder.toString();
    }
}

