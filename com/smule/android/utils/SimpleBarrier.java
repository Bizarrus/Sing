/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.utils;

import com.smule.android.logging.Log;

public class SimpleBarrier {
    private static final String e = SimpleBarrier.class.getName();
    Runnable a;
    volatile int b;
    Runnable c;
    boolean d;

    public SimpleBarrier(int n, Runnable runnable) {
        this.c = new Runnable(){

            @Override
            public void run() {
                SimpleBarrier.this.a();
            }
        };
        this.d = false;
        this.b = n;
        this.a = runnable;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a() {
        synchronized (this) {
            --this.b;
            if (this.b == 0) {
                if (this.a != null) {
                    this.a.run();
                }
            } else if (this.b < 0 && !this.d) {
                String string2 = e;
                StringBuilder stringBuilder = new StringBuilder().append("Too many workers called 'done'. Callback ");
                String string3 = this.a != null ? this.a.getClass().getName() : "";
                Log.d(string2, stringBuilder.append(string3).toString());
            }
            return;
        }
    }

    public Runnable b() {
        return this.c;
    }

    public void c() {
        synchronized (this) {
            this.a = null;
            return;
        }
    }

    public void d() {
        synchronized (this) {
            ++this.b;
            return;
        }
    }

}

