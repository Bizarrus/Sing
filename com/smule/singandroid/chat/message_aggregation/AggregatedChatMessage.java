package com.smule.singandroid.chat.message_aggregation;

import com.smule.chat.ChatMessage;
import java.util.HashSet;

abstract class AggregatedChatMessage extends ChatMessage {
    protected HashSet<String> f21305a = new HashSet();

    AggregatedChatMessage() {
    }

    public boolean mo6765a(ChatMessage chatMessage) {
        return m22973c(chatMessage);
    }

    public boolean mo6766b(ChatMessage chatMessage) {
        if (!mo6765a(chatMessage)) {
            return false;
        }
        this.f21305a.add(chatMessage.m19389f());
        return true;
    }

    public boolean mo6362g() {
        return false;
    }

    protected boolean m22973c(ChatMessage chatMessage) {
        return ChatMessageAggregator.m22985a(this, chatMessage);
    }
}
