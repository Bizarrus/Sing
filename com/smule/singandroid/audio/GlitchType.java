package com.smule.singandroid.audio;

public enum GlitchType {
    NONE("NONE"),
    CHOPPY("CHOP"),
    MODULATED("MOD"),
    CLICKY("CLK");
    
    private final String value;

    private GlitchType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public GlitchType getNone() {
        return NONE;
    }

    public GlitchType getChoppy() {
        return CHOPPY;
    }
}
