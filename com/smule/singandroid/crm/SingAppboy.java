/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.app.Application$ActivityLifecycleCallbacks
 *  android.content.Context
 *  com.appboy.Appboy
 *  com.appboy.AppboyLifecycleCallbackListener
 *  com.appboy.AppboyUser
 *  com.appboy.models.outgoing.AppboyProperties
 *  com.appboy.ui.inappmessage.AppboyInAppMessageManager
 *  com.appboy.ui.inappmessage.listeners.IHtmlInAppMessageActionListener
 *  com.appboy.ui.inappmessage.listeners.IInAppMessageManagerListener
 */
package com.smule.singandroid.crm;

import android.app.Application;
import android.content.Context;
import com.appboy.Appboy;
import com.appboy.AppboyLifecycleCallbackListener;
import com.appboy.AppboyUser;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.ui.inappmessage.listeners.IHtmlInAppMessageActionListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageManagerListener;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.notifications.MagicNotifications;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.crm.SingAppboyCustomIAMListener;

public class SingAppboy {
    private static final String a = SingAppboy.class.getName();
    private static SingAppboy c;
    private boolean b = false;

    private SingAppboy() {
    }

    public static SingAppboy a() {
        synchronized (SingAppboy.class) {
            if (c == null) {
                c = new SingAppboy();
            }
            SingAppboy singAppboy = c;
            return singAppboy;
        }
    }

    private boolean f() {
        if (AppSettingsManager.a().e() && new SingServerValues().X()) {
            return true;
        }
        return false;
    }

    public void a(String string2, AppboyProperties appboyProperties) {
        if (this.f() && this.b) {
            Appboy.a((Context)SingApplication.g()).a(string2, appboyProperties);
        }
    }

    public void b() {
        if (this.f() && this.b && !String.valueOf(UserManager.a().f()).equals(Appboy.a((Context)SingApplication.g()).f().a())) {
            Appboy.a((Context)SingApplication.g()).c(String.valueOf(UserManager.a().f()));
        }
    }

    public void c() {
        if (this.f()) {
            SingApplication.h().registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)new AppboyLifecycleCallbackListener());
            String string2 = MagicNotifications.a().c();
            Appboy.a((Context)SingApplication.g()).d(string2);
            AppboyInAppMessageManager.getInstance().setCustomInAppMessageManagerListener((IInAppMessageManagerListener)SingAppboyCustomIAMListener.a());
            AppboyInAppMessageManager.getInstance().setCustomHtmlInAppMessageActionListener((IHtmlInAppMessageActionListener)SingAppboyCustomIAMListener.a());
            this.b = true;
        }
    }

    public void d() {
        SingAppboyCustomIAMListener.a().a(false);
    }

    public void e() {
        SingAppboyCustomIAMListener.a().a(true);
    }
}

