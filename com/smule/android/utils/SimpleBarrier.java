package com.smule.android.utils;

import com.smule.android.logging.Log;

public class SimpleBarrier {
    private static final String f17838e = SimpleBarrier.class.getName();
    Runnable f17839a;
    volatile int f17840b;
    Runnable f17841c = new C36701(this);
    boolean f17842d = false;

    class C36701 implements Runnable {
        final /* synthetic */ SimpleBarrier f17837a;

        C36701(SimpleBarrier simpleBarrier) {
            this.f17837a = simpleBarrier;
        }

        public void run() {
            this.f17837a.m19034a();
        }
    }

    public SimpleBarrier(int i, Runnable runnable) {
        this.f17840b = i;
        this.f17839a = runnable;
    }

    public synchronized void m19034a() {
        this.f17840b--;
        if (this.f17840b == 0) {
            if (this.f17839a != null) {
                this.f17839a.run();
            }
        } else if (this.f17840b < 0 && !this.f17842d) {
            String name;
            String str = f17838e;
            StringBuilder append = new StringBuilder().append("Too many workers called 'done'. Callback ");
            if (this.f17839a != null) {
                name = this.f17839a.getClass().getName();
            } else {
                name = "";
            }
            Log.d(str, append.append(name).toString());
        }
    }

    public Runnable m19035b() {
        return this.f17841c;
    }

    public synchronized void m19036c() {
        this.f17839a = null;
    }

    public synchronized void m19037d() {
        this.f17840b++;
    }
}
