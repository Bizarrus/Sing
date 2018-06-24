package com.smule.singandroid.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatManager$ConnectionStatus;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.GroupChat;

class ChatAnalyticsMonitor$1 implements ChatManagerListener {
    final /* synthetic */ ChatAnalyticsMonitor f20761a;

    ChatAnalyticsMonitor$1(ChatAnalyticsMonitor chatAnalyticsMonitor) {
        this.f20761a = chatAnalyticsMonitor;
    }

    public void mo6319a() {
    }

    public void mo6322e(Chat chat) {
    }

    public void mo6323f(Chat chat) {
    }

    public void mo6320a(ChatManager$ConnectionStatus chatManager$ConnectionStatus) {
    }

    public void mo6324g(Chat chat) {
        if (!chat.mo6350v() && (chat instanceof GroupChat)) {
            ChatAnalytics.m22389a(chat.m19209c());
        }
    }

    public void mo6325h(Chat chat) {
        if (!chat.mo6350v()) {
            ChatAnalytics.m22401e(chat);
        }
    }

    public void mo6321a(GroupChat groupChat) {
        if (!groupChat.mo6350v()) {
            ChatAnalytics.m22384a(groupChat);
        }
    }
}
