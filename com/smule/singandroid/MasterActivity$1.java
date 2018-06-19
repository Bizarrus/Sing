package com.smule.singandroid;

import com.smule.android.network.managers.UserManager;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatMessage;
import java.util.Date;

class MasterActivity$1 extends ChatListenerAdapter {
    final /* synthetic */ MasterActivity f18853a;

    MasterActivity$1(MasterActivity masterActivity) {
        this.f18853a = masterActivity;
    }

    public void m20321a(Chat chat, ChatMessage chatMessage, boolean z) {
        long time = new Date().getTime();
        if (chat.mo6347b() == Bucket.INBOX && chatMessage.m19385b() != UserManager.a().f() && chatMessage.mo6767c().getTime() >= time - 1000 && this.f18853a.s <= time - 5000) {
        }
    }

    public void m20322d(Chat chat) {
        this.f18853a.A();
    }
}
