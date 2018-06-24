package com.smule.singandroid.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatMessage.Type;
import java.util.HashMap;

protected class ChatAnalyticsMonitor$MessagesSentCounter extends ChatAnalyticsMonitor$AnalyticsCounter {
    Long f20773e;
    HashMap<Type, Integer> f20774f = new HashMap();
    HashMap<Type, Integer> f20775g = new HashMap();
    final /* synthetic */ ChatAnalyticsMonitor f20776h;

    public ChatAnalyticsMonitor$MessagesSentCounter(ChatAnalyticsMonitor chatAnalyticsMonitor, Chat chat, Long l) {
        this.f20776h = chatAnalyticsMonitor;
        super(chatAnalyticsMonitor, chat);
        this.f20773e = l;
    }

    protected void mo6743b(ChatAnalyticsMonitor$EventType chatAnalyticsMonitor$EventType, Type type) {
        if (chatAnalyticsMonitor$EventType == ChatAnalyticsMonitor$EventType.SENT) {
            m22416a(this.f20774f, type);
        } else if (chatAnalyticsMonitor$EventType == ChatAnalyticsMonitor$EventType.ACKNOWLEDGED) {
            m22416a(this.f20775g, type);
        } else {
            throw new RuntimeException("recordEvent with bad type");
        }
    }

    protected void mo6742b() {
        if (!this.f20774f.isEmpty() || !this.f20775g.isEmpty()) {
            ChatAnalytics.m22386a(this.c, this.a, this.f20773e, this.b, m22413a(this.f20774f), m22413a(this.f20775g));
        }
    }

    protected void mo6744c() {
        this.f20774f = new HashMap();
        this.f20775g = new HashMap();
    }
}
