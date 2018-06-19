package com.smule.singandroid;

import android.content.Intent;

class BaseActivity$8 implements Runnable {
    final /* synthetic */ Intent f18439a;
    final /* synthetic */ BaseActivity f18440b;

    BaseActivity$8(BaseActivity baseActivity, Intent intent) {
        this.f18440b = baseActivity;
        this.f18439a = intent;
    }

    public void run() {
        BaseActivity.a(this.f18440b, this.f18439a);
    }
}
