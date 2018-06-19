package com.smule.singandroid;

import com.mopub.volley.DefaultRetryPolicy;

class RenderParams implements Cloneable {
    protected float f19556a = 0.0f;
    protected float f19557b = Float.POSITIVE_INFINITY;
    protected float f19558c = 0.0f;
    protected float f19559d = 0.5f;
    protected float f19560e = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    protected float f19561f = 0.0f;
    protected String f19562g = "normal";
    protected boolean f19563h = true;

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public float m21142a() {
        return this.f19560e;
    }

    public float m21143b() {
        return m21142a() * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    }

    public String toString() {
        return "playbackStartTime = " + this.f19556a + "; playbackEndTime = " + this.f19557b + "; delay = " + this.f19558c + "; backgroundVolume = " + this.f19559d + "; boostedForegroundVolume = " + m21143b() + "; foregroundPan = " + this.f19561f + "; effectId = " + this.f19562g + "; autoPlayAfterRender = " + this.f19563h;
    }
}
