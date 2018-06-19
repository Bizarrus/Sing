package com.smule.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class IndefiniteToast {
    Context f17775a;
    Toast f17776b;
    long f17777c;
    Handler f17778d = new Handler(Looper.getMainLooper());
    boolean f17779e;
    String f17780f;
    long f17781g;
    public long f17782h = -1;

    class C36671 implements Runnable {
        final /* synthetic */ IndefiniteToast f17774a;

        C36671(IndefiniteToast indefiniteToast) {
            this.f17774a = indefiniteToast;
        }

        public void run() {
            if (this.f17774a.f17777c > this.f17774a.f17782h && System.currentTimeMillis() - this.f17774a.f17781g > this.f17774a.f17777c) {
                this.f17774a.m18981c();
            } else if (!this.f17774a.f17779e) {
                this.f17774a.m18980b();
            }
        }
    }

    @SuppressLint({"ShowToast"})
    public IndefiniteToast(Context context, String str) {
        this.f17775a = context;
        this.f17780f = str;
        this.f17776b = Toast.makeText(this.f17775a.getApplicationContext(), str, 1);
    }

    public void m18979a(long j) {
        this.f17777c = 1000 * j;
    }

    public void m18978a() {
        this.f17779e = false;
        this.f17781g = System.currentTimeMillis();
        m18980b();
    }

    protected void m18980b() {
        this.f17776b.show();
        this.f17778d.postDelayed(new C36671(this), 1000);
    }

    public void m18981c() {
        this.f17779e = true;
        this.f17776b.cancel();
    }
}
