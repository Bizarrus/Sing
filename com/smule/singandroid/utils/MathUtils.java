package com.smule.singandroid.utils;

public class MathUtils {
    public static float m25870a(float f) {
        return (float) (((double) f) <= 0.0d ? -60.0d : Math.log((double) f) / 0.11512925d);
    }

    public static float m25871b(float f) {
        return (float) (f < -59.0f ? 0.0d : Math.exp(((double) f) * 0.11512925d));
    }
}
