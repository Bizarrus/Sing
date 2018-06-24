/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio;

public enum GlitchType {
    NONE("NONE"),
    CHOPPY("CHOP"),
    MODULATED("MOD"),
    CLICKY("CLK");
    
    private final String value;

    private GlitchType(String string3) {
        this.value = string3;
    }

    public GlitchType getChoppy() {
        return CHOPPY;
    }

    public GlitchType getNone() {
        return NONE;
    }

    public String getValue() {
        return this.value;
    }
}

