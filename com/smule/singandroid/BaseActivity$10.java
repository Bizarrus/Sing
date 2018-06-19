package com.smule.singandroid;

import android.content.Intent;

class BaseActivity$10 implements Runnable {
    final /* synthetic */ Intent f18413a;
    final /* synthetic */ BaseActivity f18414b;

    BaseActivity$10(BaseActivity baseActivity, Intent intent) {
        this.f18414b = baseActivity;
        this.f18413a = intent;
    }

    public void run() {
        BaseActivity.b(this.f18414b, this.f18413a);
    }
}
