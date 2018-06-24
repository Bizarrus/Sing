/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  android.view.View
 */
package com.smule.android.utils;

import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import com.smule.android.utils.UiAwareRunnable;
import com.smule.android.utils.UiComponent;

public class UiHandler
extends Handler {
    private UiComponent a;

    public UiHandler(Activity activity) {
        this(UiComponent.a(activity));
    }

    public UiHandler(Fragment fragment) {
        this(UiComponent.a(fragment));
    }

    public UiHandler(View view) {
        this(UiComponent.a(view));
    }

    private UiHandler(UiComponent uiComponent) {
        super(Looper.getMainLooper());
        this.a = uiComponent;
    }

    private void b(Runnable runnable) {
        if (this.a.a()) {
            runnable.run();
        }
    }

    private Message c(UiAwareRunnable uiAwareRunnable) {
        Message message = Message.obtain();
        message.what = 382904539;
        message.obj = uiAwareRunnable;
        return message;
    }

    private void d(UiAwareRunnable uiAwareRunnable) {
        uiAwareRunnable.a(this.a.a());
    }

    public void a(UiAwareRunnable uiAwareRunnable, long l) {
        this.sendMessageDelayed(this.c(uiAwareRunnable), l);
    }

    public boolean a(UiAwareRunnable uiAwareRunnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.d(uiAwareRunnable);
            return true;
        }
        this.b(uiAwareRunnable);
        return false;
    }

    public boolean a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.b(runnable);
            return true;
        }
        this.post(runnable);
        return false;
    }

    public void b(UiAwareRunnable uiAwareRunnable) {
        this.a(uiAwareRunnable, 0);
    }

    public void dispatchMessage(Message message) {
        if (message.getCallback() != null) {
            this.b(message.getCallback());
            return;
        }
        if (message.what == 382904539) {
            this.d((UiAwareRunnable)message.obj);
            return;
        }
        super.dispatchMessage(message);
    }
}

