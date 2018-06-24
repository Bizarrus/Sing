package com.smule.singandroid.crm;

import com.appboy.Appboy;
import com.appboy.AppboyLifecycleCallbackListener;
import com.appboy.models.outgoing.AppboyProperties;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;

public class SingAppboy {
    private static final String f21425a = SingAppboy.class.getName();
    private static SingAppboy f21426c;
    private boolean f21427b = false;

    private SingAppboy() {
    }

    public static synchronized SingAppboy m23096a() {
        SingAppboy singAppboy;
        synchronized (SingAppboy.class) {
            if (f21426c == null) {
                f21426c = new SingAppboy();
            }
            singAppboy = f21426c;
        }
        return singAppboy;
    }

    public void m23099b() {
        if (m23097d() && this.f21427b && !String.valueOf(UserManager.a().f()).equals(Appboy.a(SingApplication.f()).f().a())) {
            Appboy.a(SingApplication.f()).c(String.valueOf(UserManager.a().f()));
        }
    }

    private boolean m23097d() {
        return AppSettingsManager.a().e() && SingServerValues.m21673K();
    }

    public void m23100c() {
        if (m23097d()) {
            SingApplication.g().registerActivityLifecycleCallbacks(new AppboyLifecycleCallbackListener());
            this.f21427b = true;
        }
    }

    public void m23098a(String str, AppboyProperties appboyProperties) {
        if (m23097d() && this.f21427b) {
            Appboy.a(SingApplication.f()).a(str, appboyProperties);
        }
    }
}
