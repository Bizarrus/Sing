/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.models;

public class Triplet<F, S, T> {
    private F a;
    private S b;
    private T c;

    public Triplet(F f, S s, T t) {
        this.a = f;
        this.b = s;
        this.c = t;
    }

    public F a() {
        return this.a;
    }

    public S b() {
        return this.b;
    }

    public T c() {
        return this.c;
    }
}

