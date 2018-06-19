package com.smule.android.network.managers;

import com.smule.android.network.core.CoreUtil;

class LocalizationManager$1 implements Runnable {
    final /* synthetic */ LocalizationManager$GetLocalizationPackageListener f16691a;
    final /* synthetic */ String f16692b;
    final /* synthetic */ LocalizationManager f16693c;

    LocalizationManager$1(LocalizationManager localizationManager, LocalizationManager$GetLocalizationPackageListener localizationManager$GetLocalizationPackageListener, String str) {
        this.f16693c = localizationManager;
        this.f16691a = localizationManager$GetLocalizationPackageListener;
        this.f16692b = str;
    }

    public void run() {
        CoreUtil.m18079a(this.f16691a, this.f16693c.a(this.f16692b));
    }
}
