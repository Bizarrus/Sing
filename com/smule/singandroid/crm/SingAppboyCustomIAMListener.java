/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  com.appboy.models.IInAppMessage
 *  com.appboy.models.MessageButton
 *  com.appboy.ui.inappmessage.AppboyInAppMessageManager
 *  com.appboy.ui.inappmessage.InAppMessageCloser
 *  com.appboy.ui.inappmessage.InAppMessageOperation
 *  com.appboy.ui.inappmessage.listeners.IHtmlInAppMessageActionListener
 *  com.appboy.ui.inappmessage.listeners.IInAppMessageManagerListener
 */
package com.smule.singandroid.crm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.appboy.models.IInAppMessage;
import com.appboy.models.MessageButton;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.ui.inappmessage.InAppMessageCloser;
import com.appboy.ui.inappmessage.InAppMessageOperation;
import com.appboy.ui.inappmessage.listeners.IHtmlInAppMessageActionListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageManagerListener;
import com.smule.android.logging.Log;

public class SingAppboyCustomIAMListener
implements IHtmlInAppMessageActionListener,
IInAppMessageManagerListener {
    private static final String a = SingAppboyCustomIAMListener.class.getName();
    private static SingAppboyCustomIAMListener c;
    private boolean b = true;

    private SingAppboyCustomIAMListener() {
    }

    public static SingAppboyCustomIAMListener a() {
        synchronized (SingAppboyCustomIAMListener.class) {
            if (c == null) {
                c = new SingAppboyCustomIAMListener();
            }
            SingAppboyCustomIAMListener singAppboyCustomIAMListener = c;
            return singAppboyCustomIAMListener;
        }
    }

    @NonNull
    private Boolean b() {
        return AppboyInAppMessageManager.getInstance().requestDisplayInAppMessage();
    }

    public void a(boolean bl) {
        this.b = bl;
        if (bl) {
            this.b();
        }
    }

    public InAppMessageOperation beforeInAppMessageDisplayed(IInAppMessage iInAppMessage) {
        if (!this.b) {
            return InAppMessageOperation.DISPLAY_LATER;
        }
        return InAppMessageOperation.DISPLAY_NOW;
    }

    public void onCloseClicked(IInAppMessage iInAppMessage, String string2, Bundle bundle) {
        Log.c(a, "IAM closed");
    }

    public boolean onCustomEventFired(IInAppMessage iInAppMessage, String string2, Bundle bundle) {
        return false;
    }

    public boolean onInAppMessageButtonClicked(MessageButton messageButton, InAppMessageCloser inAppMessageCloser) {
        return false;
    }

    public boolean onInAppMessageClicked(IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser) {
        return false;
    }

    public void onInAppMessageDismissed(IInAppMessage iInAppMessage) {
    }

    public boolean onInAppMessageReceived(IInAppMessage iInAppMessage) {
        Log.c(a, "New IAM received with trigger" + iInAppMessage.B());
        return false;
    }

    public boolean onNewsfeedClicked(IInAppMessage iInAppMessage, String string2, Bundle bundle) {
        return false;
    }

    public boolean onOtherUrlAction(IInAppMessage iInAppMessage, String string2, Bundle bundle) {
        return false;
    }
}

