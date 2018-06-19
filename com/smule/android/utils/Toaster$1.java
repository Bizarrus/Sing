package com.smule.android.utils;

import android.content.Context;
import android.widget.Toast;
import java.lang.ref.WeakReference;

class Toaster$1 implements Runnable {
    final /* synthetic */ Context f17868a;
    final /* synthetic */ String f17869b;
    final /* synthetic */ Toaster$Duration f17870c;
    final /* synthetic */ int f17871d;
    final /* synthetic */ int f17872e;
    final /* synthetic */ int f17873f;

    Toaster$1(Context context, String str, Toaster$Duration toaster$Duration, int i, int i2, int i3) {
        this.f17868a = context;
        this.f17869b = str;
        this.f17870c = toaster$Duration;
        this.f17871d = i;
        this.f17872e = i2;
        this.f17873f = i3;
    }

    public void run() {
        Toaster.a();
        Toast makeText = Toast.makeText(this.f17868a.getApplicationContext(), this.f17869b, this.f17870c == Toaster$Duration.LONG ? 1 : 0);
        if (this.f17871d != 0) {
            makeText.setGravity(this.f17871d, this.f17872e, this.f17873f);
        }
        makeText.show();
        Toaster.a = new WeakReference(makeText);
    }
}
