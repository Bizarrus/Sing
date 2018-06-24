package com.smule.singandroid.audio;

import java.util.ArrayList;
import java.util.Iterator;

public class ScorePartEventManager {
    private static final String f20673a = ScorePartEventManager.class.getName();
    private final ArrayList<ScorePartEvent> f20674b;
    private int f20675c;

    public ScorePartEventManager(ArrayList<ScorePartEvent> arrayList) {
        this.f20674b = arrayList;
        m22291a();
    }

    public int m22292a(float f) {
        if (this.f20674b == null || this.f20674b.isEmpty() || f < ((ScorePartEvent) this.f20674b.get(0)).getTime()) {
            return 3;
        }
        if (f < ((ScorePartEvent) this.f20674b.get(this.f20675c)).getTime()) {
            m22291a();
        }
        int size = this.f20674b.size() - 1;
        ScorePartEvent scorePartEvent = (ScorePartEvent) this.f20674b.get(size);
        if (this.f20675c == size || f >= scorePartEvent.getTime()) {
            this.f20675c = size;
            return scorePartEvent.getScorePart();
        }
        for (int i = this.f20675c; i < size; i++) {
            if (((ScorePartEvent) this.f20674b.get(i + 1)).getTime() > f) {
                this.f20675c = i;
                return ((ScorePartEvent) this.f20674b.get(i)).getScorePart();
            }
        }
        this.f20675c = size;
        return ((ScorePartEvent) this.f20674b.get(size)).getScorePart();
    }

    private void m22291a() {
        this.f20675c = 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("numEvents = ").append(this.f20674b == null ? 0 : this.f20674b.size()).append("; [");
        if (!(this.f20674b == null || this.f20674b.isEmpty())) {
            Iterator it = this.f20674b.iterator();
            while (it.hasNext()) {
                stringBuilder.append((ScorePartEvent) it.next());
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
