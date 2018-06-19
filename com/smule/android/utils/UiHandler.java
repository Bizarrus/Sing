package com.smule.android.utils;

import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

public class UiHandler extends Handler {
    private UiComponent f17880a;

    public UiHandler(Activity activity) {
        this(UiComponent.m19069a(activity));
    }

    public UiHandler(Fragment fragment) {
        this(UiComponent.m19070a(fragment));
    }

    public UiHandler(View view) {
        this(UiComponent.m19071a(view));
    }

    private UiHandler(UiComponent uiComponent) {
        super(Looper.getMainLooper());
        this.f17880a = uiComponent;
    }

    public boolean m19081a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            m19076b(runnable);
            return true;
        }
        post(runnable);
        return false;
    }

    public boolean m19080a(UiAwareRunnable uiAwareRunnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            m19078d(uiAwareRunnable);
            return true;
        }
        m19082b(uiAwareRunnable);
        return false;
    }

    public void m19082b(UiAwareRunnable uiAwareRunnable) {
        m19079a(uiAwareRunnable, 0);
    }

    public void m19079a(UiAwareRunnable uiAwareRunnable, long j) {
        sendMessageDelayed(m19077c(uiAwareRunnable), j);
    }

    private Message m19077c(UiAwareRunnable uiAwareRunnable) {
        Message obtain = Message.obtain();
        obtain.what = 382904539;
        obtain.obj = uiAwareRunnable;
        return obtain;
    }

    public void dispatchMessage(Message message) {
        if (message.getCallback() != null) {
            m19076b(message.getCallback());
        } else if (message.what == 382904539) {
            m19078d((UiAwareRunnable) message.obj);
        } else {
            super.dispatchMessage(message);
        }
    }

    private void m19076b(Runnable runnable) {
        if (this.f17880a.mo6305a()) {
            runnable.run();
        }
    }

    private void m19078d(UiAwareRunnable uiAwareRunnable) {
        uiAwareRunnable.mo6509a(this.f17880a.mo6305a());
    }
}
