package com.smule.android.network.managers;

import com.smule.android.logging.Log;

class LocalizationManager$2 implements Runnable {
    final /* synthetic */ LocalizationManager f16694a;

    LocalizationManager$2(LocalizationManager localizationManager) {
        this.f16694a = localizationManager;
    }

    public void run() {
        LocalizationManager.a(this.f16694a, System.currentTimeMillis());
        LocalizationManager.b(this.f16694a, UserManager.a().f());
        Log.b(LocalizationManager.b(), "reloadAll: done");
        LocalizationManager.a(this.f16694a).set(false);
        LocalizationManager.b(this.f16694a);
    }
}
