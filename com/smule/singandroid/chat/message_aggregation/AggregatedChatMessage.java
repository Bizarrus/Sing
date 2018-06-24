/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.chat.message_aggregation;

import com.smule.chat.ChatMessage;
import com.smule.singandroid.chat.message_aggregation.ChatMessageAggregator;
import java.util.HashSet;

abstract class AggregatedChatMessage
extends ChatMessage {
    protected HashSet<String> a = new HashSet();

    AggregatedChatMessage() {
    }

    public boolean a(ChatMessage chatMessage) {
        return this.c(chatMessage);
    }

    public boolean b(ChatMessage chatMessage) {
        if (this.a(chatMessage)) {
            this.a.add(chatMessage.f());
            return true;
        }
        return false;
    }

    protected boolean c(ChatMessage chatMessage) {
        return ChatMessageAggregator.a(this, chatMessage);
    }

    @Override
    public boolean g() {
        return false;
    }
}

