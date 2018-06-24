/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.effectpanel;

import com.smule.singandroid.effectpanel.EffectListItem;

public class VocalEffectListItem
extends EffectListItem {
    private float a;
    private float b;

    public VocalEffectListItem(float f, float f2, boolean bl, boolean bl2) {
        super(bl, bl2);
        this.a = f;
        this.b = f2;
    }

    public float d() {
        return this.a;
    }

    public float e() {
        return this.b;
    }
}

