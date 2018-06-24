package com.smule.singandroid.chat;

import com.smule.android.network.managers.UserManager;
import com.smule.chat.Chat;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatMessage;

class ChatAnalyticsMonitor$2 extends ChatListenerAdapter {
    final /* synthetic */ ChatAnalyticsMonitor f20762a;

    ChatAnalyticsMonitor$2(ChatAnalyticsMonitor chatAnalyticsMonitor) {
        this.f20762a = chatAnalyticsMonitor;
    }

    public void m22411a(Chat chat, ChatMessage chatMessage, boolean z) {
        if (!z && !chat.mo6350v() && UserManager.a().f() == chatMessage.m19385b()) {
            ChatAnalyticsMonitor.a(this.f20762a, chat, true).m22424a(chat, chatMessage);
        }
    }

    public void m22412c(Chat chat, ChatMessage chatMessage) {
        if (!chat.mo6350v()) {
            ChatAnalyticsMonitor$ChatMonitor a = ChatAnalyticsMonitor.a(this.f20762a, chat, false);
            if (a != null) {
                a.m22425b(chat, chatMessage);
            }
        }
    }
}
