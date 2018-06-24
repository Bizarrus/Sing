/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatStatus;
import java.util.HashSet;
import java.util.Set;

class ChatReadyCallback
extends ChatListenerAdapter
implements ChatManager {
    private static final Set<ChatReadyCallback> b = new HashSet<ChatReadyCallback>();
    private ChatManager a;

    public ChatReadyCallback(ChatManager chatCallback) {
        this.a = chatCallback;
        b.add(this);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(Chat chat, Chat.ChatState chatState) {
        if (chatState == Chat.ChatState.c) {
            chat.b(this);
            b.remove(this);
            this.a.a(null, chat.e());
            return;
        } else {
            if (chatState != Chat.ChatState.b) return;
            {
                chat.b(this);
                b.remove(this);
                this.a.a(chat, ChatStatus.a);
                return;
            }
        }
    }

    @Override
    public void a(Chat chat, ChatStatus chatStatus) {
        if (chat == null || chat.d() != Chat.ChatState.a) {
            this.a.a(chat, chatStatus);
            b.remove(this);
            return;
        }
        chat.a(this);
    }
}

