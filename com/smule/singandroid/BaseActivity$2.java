package com.smule.singandroid;

import java.util.Observable;
import java.util.Observer;

class BaseActivity$2 implements Observer {
    final /* synthetic */ BaseActivity f18427a;

    BaseActivity$2(BaseActivity baseActivity) {
        this.f18427a = baseActivity;
    }

    public void update(Observable observable, Object obj) {
        if (obj instanceof Runnable) {
            final Runnable runnable = (Runnable) obj;
            this.f18427a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ BaseActivity$2 f18426b;

                public void run() {
                    if (this.f18426b.f18427a.f()) {
                        runnable.run();
                    }
                }
            });
        }
    }
}
