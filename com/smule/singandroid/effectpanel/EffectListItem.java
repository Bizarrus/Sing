/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.effectpanel;

public class EffectListItem {
    private boolean a;
    private boolean b;
    private boolean c;

    public EffectListItem(boolean bl, boolean bl2) {
        this.a = bl;
        this.b = bl2;
        if (this.b && this.a) {
            this.c = true;
        }
    }

    public final boolean a() {
        return this.b;
    }

    public final boolean b() {
        if (this.a && this.c) {
            return true;
        }
        return false;
    }

    public final void c() {
        if (this.c) {
            return;
        }
        if (this.a) {
            this.c = true;
            this.b = true;
            return;
        }
        this.b = true;
    }

    public static enum EffectType {
        a,
        b;
        

        private EffectType() {
        }
    }

}

