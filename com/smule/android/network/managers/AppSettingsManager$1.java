package com.smule.android.network.managers;

import com.smule.android.logging.Log;

class AppSettingsManager$1 implements Runnable {
    final /* synthetic */ AppSettingsManager f16487a;

    AppSettingsManager$1(AppSettingsManager appSettingsManager) {
        this.f16487a = appSettingsManager;
    }

    public void run() {
        try {
            Log.b(AppSettingsManager.h(), "fetchAllConfigSettings done:" + AppSettingsManager.a(this.f16487a));
        } finally {
            AppSettingsManager.b(this.f16487a).set(false);
            AppSettingsManager.c(this.f16487a);
        }
    }
}
