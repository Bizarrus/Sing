package com.smule.singandroid;

class BaseActivity$3 implements Runnable {
    final /* synthetic */ Runnable f18428a;
    final /* synthetic */ BaseActivity f18429b;

    BaseActivity$3(BaseActivity baseActivity, Runnable runnable) {
        this.f18429b = baseActivity;
        this.f18428a = runnable;
    }

    public void run() {
        this.f18429b.a(this.f18428a);
    }
}
