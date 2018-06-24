/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Looper
 *  android.widget.Toast
 */
package com.smule.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class IndefiniteToast {
    Context a;
    Toast b;
    long c;
    Handler d = new Handler(Looper.getMainLooper());
    boolean e;
    String f;
    long g;
    public long h = -1;

    @SuppressLint(value={"ShowToast"})
    public IndefiniteToast(Context context, String string2) {
        this.a = context;
        this.f = string2;
        this.b = Toast.makeText((Context)this.a.getApplicationContext(), (CharSequence)string2, (int)1);
    }

    public void a() {
        this.e = false;
        this.g = System.currentTimeMillis();
        this.b();
    }

    public void a(long l) {
        this.c = 1000 * l;
    }

    protected void b() {
        this.b.show();
        this.d.postDelayed(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                if (IndefiniteToast.this.c > IndefiniteToast.this.h && System.currentTimeMillis() - IndefiniteToast.this.g > IndefiniteToast.this.c) {
                    IndefiniteToast.this.c();
                    return;
                } else {
                    if (IndefiniteToast.this.e) return;
                    {
                        IndefiniteToast.this.b();
                        return;
                    }
                }
            }
        }, 1000);
    }

    public void c() {
        this.e = true;
        this.b.cancel();
    }

}

