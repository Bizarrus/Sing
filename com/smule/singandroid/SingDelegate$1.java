package com.smule.singandroid;

import com.smule.android.AppDelegate$NotificationParams;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.Type;

class SingDelegate$1 implements Runnable {
    final /* synthetic */ Type f20162a;
    final /* synthetic */ AppDelegate$NotificationParams f20163b;
    final /* synthetic */ SingDelegate f20164c;

    SingDelegate$1(SingDelegate singDelegate, Type type, AppDelegate$NotificationParams appDelegate$NotificationParams) {
        this.f20164c = singDelegate;
        this.f20162a = type;
        this.f20163b = appDelegate$NotificationParams;
    }

    public void run() {
        SingApplication.j().a(Bucket.INBOX, this.f20162a, this.f20163b.f15545g);
    }
}
