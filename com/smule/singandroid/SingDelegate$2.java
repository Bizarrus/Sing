package com.smule.singandroid;

import android.app.Notification;
import android.content.Context;
import com.smule.android.AppDelegate$NotificationParams;
import com.smule.singandroid.chat.ChatNotification.NotificationReadyCallback;

class SingDelegate$2 implements NotificationReadyCallback {
    final /* synthetic */ Context f20165a;
    final /* synthetic */ AppDelegate$NotificationParams f20166b;
    final /* synthetic */ SingDelegate f20167c;

    SingDelegate$2(SingDelegate singDelegate, Context context, AppDelegate$NotificationParams appDelegate$NotificationParams) {
        this.f20167c = singDelegate;
        this.f20165a = context;
        this.f20166b = appDelegate$NotificationParams;
    }

    public void mo6622a(Notification notification) {
        this.f20167c.postNotification(this.f20165a, this.f20166b.f15545g, this.f20166b.f15545g.hashCode(), notification);
    }
}
