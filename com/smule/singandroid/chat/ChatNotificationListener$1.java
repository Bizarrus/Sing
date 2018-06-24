package com.smule.singandroid.chat;

import android.app.Notification;
import com.smule.android.network.core.MagicNetwork;
import com.smule.singandroid.chat.ChatNotification.NotificationReadyCallback;

class ChatNotificationListener$1 implements NotificationReadyCallback {
    final /* synthetic */ String f20960a;
    final /* synthetic */ ChatNotificationListener f20961b;

    ChatNotificationListener$1(ChatNotificationListener chatNotificationListener, String str) {
        this.f20961b = chatNotificationListener;
        this.f20960a = str;
    }

    public void mo6622a(Notification notification) {
        MagicNetwork.d().postNotification(this.f20961b.a, this.f20960a, this.f20960a.hashCode(), notification);
    }
}
