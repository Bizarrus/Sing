/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio;

import com.smule.singandroid.audio.ScorePartEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScorePartEventManager {
    private static final String a = ScorePartEventManager.class.getName();
    private final ArrayList<ScorePartEvent> b;
    private int c;

    public ScorePartEventManager(ArrayList<ScorePartEvent> arrayList) {
        this.b = arrayList;
        this.b();
    }

    private void b() {
        this.c = 0;
    }

    public int a(float f) {
        if (this.b == null || this.b.isEmpty() || f < this.b.get(0).getTime()) {
            return 3;
        }
        if (f < this.b.get(this.c).getTime()) {
            this.b();
        }
        int n = this.b.size() - 1;
        ScorePartEvent scorePartEvent = this.b.get(n);
        if (this.c == n || f >= scorePartEvent.getTime()) {
            this.c = n;
            return scorePartEvent.getScorePart();
        }
        for (int i = this.c; i < n; ++i) {
            if (this.b.get(i + 1).getTime() <= f) continue;
            this.c = i;
            return this.b.get(i).getScorePart();
        }
        this.c = n;
        return this.b.get(n).getScorePart();
    }

    public List<ScorePartEvent> a() {
        return this.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Object object = stringBuilder.append("numEvents = ");
        int n = this.b == null ? 0 : this.b.size();
        object.append(n).append("; [");
        if (this.b != null && !this.b.isEmpty()) {
            object = this.b.iterator();
            while (object.hasNext()) {
                stringBuilder.append((ScorePartEvent)object.next());
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

