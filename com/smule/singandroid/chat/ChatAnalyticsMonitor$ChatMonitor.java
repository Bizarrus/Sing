package com.smule.singandroid.chat;

import com.smule.android.network.managers.UserManager;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatMessage;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.PeerChat;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

protected class ChatAnalyticsMonitor$ChatMonitor {
    Map<Long, ChatAnalyticsMonitor$MessagesSentCounter> f20767a = new HashMap();
    final /* synthetic */ ChatAnalyticsMonitor f20768b;

    public ChatAnalyticsMonitor$ChatMonitor(ChatAnalyticsMonitor chatAnalyticsMonitor) {
        this.f20768b = chatAnalyticsMonitor;
    }

    void m22424a(Chat chat, ChatMessage chatMessage) {
        if (chatMessage instanceof GroupInvitationChatMessage) {
            ChatAnalytics.m22381a(chat, chat.mo6341f());
        }
        for (ChatAnalyticsMonitor$MessagesSentCounter a : m22421a(chat)) {
            a.m22415a(ChatAnalyticsMonitor$EventType.SENT, chatMessage.mo6360a());
        }
    }

    void m22425b(Chat chat, ChatMessage chatMessage) {
        for (ChatAnalyticsMonitor$MessagesSentCounter a : m22421a(chat)) {
            a.m22415a(ChatAnalyticsMonitor$EventType.ACKNOWLEDGED, chatMessage.mo6360a());
        }
    }

    private Set<ChatAnalyticsMonitor$MessagesSentCounter> m22421a(Chat chat) {
        if (chat.mo6335a() == Type.PEER) {
            return Collections.singleton(m22420a(chat, Long.valueOf(((PeerChat) chat).mo6341f())));
        }
        if (chat.mo6335a() == Type.GROUP) {
            Set<Long> R = ((GroupChat) chat).mo6333R();
            Set<ChatAnalyticsMonitor$MessagesSentCounter> hashSet = new HashSet();
            long f = UserManager.a().f();
            for (Long l : R) {
                if (l.longValue() != f) {
                    hashSet.add(m22420a(chat, l));
                }
            }
            return hashSet.isEmpty() ? Collections.singleton(m22420a(chat, null)) : hashSet;
        } else {
            throw new RuntimeException("Invalid group type" + chat.mo6335a());
        }
    }

    private ChatAnalyticsMonitor$MessagesSentCounter m22420a(Chat chat, Long l) {
        ChatAnalyticsMonitor$MessagesSentCounter chatAnalyticsMonitor$MessagesSentCounter = (ChatAnalyticsMonitor$MessagesSentCounter) this.f20767a.get(l);
        if (chatAnalyticsMonitor$MessagesSentCounter != null) {
            return chatAnalyticsMonitor$MessagesSentCounter;
        }
        chatAnalyticsMonitor$MessagesSentCounter = new ChatAnalyticsMonitor$MessagesSentCounter(this.f20768b, chat, l);
        this.f20767a.put(l, chatAnalyticsMonitor$MessagesSentCounter);
        return chatAnalyticsMonitor$MessagesSentCounter;
    }

    void m22423a() {
        m22422b();
    }

    private void m22422b() {
        for (ChatAnalyticsMonitor$MessagesSentCounter a : this.f20767a.values()) {
            a.m22414a();
        }
    }
}
