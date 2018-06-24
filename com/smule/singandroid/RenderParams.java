/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid;

class RenderParams
implements Cloneable {
    protected float a = 0.0f;
    protected float b = Float.POSITIVE_INFINITY;
    protected float c = 0.0f;
    protected float d = 0.5f;
    protected float e = 1.0f;
    protected float f = 0.0f;
    protected String g = "normal";
    protected boolean h = true;

    public float a() {
        return this.e;
    }

    public float b() {
        return this.a() * 1.0f;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return "playbackStartTime = " + this.a + "; playbackEndTime = " + this.b + "; delay = " + this.c + "; backgroundVolume = " + this.d + "; boostedForegroundVolume = " + this.b() + "; foregroundPan = " + this.f + "; effectId = " + this.g + "; autoPlayAfterRender = " + this.h;
    }
}

