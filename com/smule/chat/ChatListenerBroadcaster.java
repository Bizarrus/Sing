/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatMessage;
import com.smule.chat.GroupChat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class ChatListenerBroadcaster
implements ChatListener,
ChatManagerListener {
    private ArrayList<WeakReference<ChatListener>> a = new ArrayList();

    ChatListenerBroadcaster() {
    }

    private List<WeakReference<ChatListener>> b() {
        return new ArrayList<WeakReference<ChatListener>>(this.a);
    }

    @Override
    public void a() {
    }

    @Override
    public void a(Chat chat) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.a(chat);
        }
    }

    @Override
    public void a(Chat chat, Chat.ChatState chatState) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.a(chat, chatState);
        }
    }

    @Override
    public void a(Chat chat, ChatMessage chatMessage) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.a(chat, chatMessage);
        }
    }

    @Override
    public void a(Chat chat, ChatMessage chatMessage, boolean bl) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.a(chat, chatMessage, bl);
        }
    }

    void a(ChatListener chatListener) {
        this.a.add(new WeakReference<ChatListener>(chatListener));
    }

    @Override
    public void a(ChatManager connectionStatus) {
    }

    @Override
    public void a(GroupChat groupChat) {
    }

    @Override
    public void b(Chat chat) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.b(chat);
        }
    }

    @Override
    public void b(Chat chat, ChatMessage chatMessage) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.b(chat, chatMessage);
        }
    }

    void b(ChatListener chatListener) {
        int n = 0;
        do {
            block4 : {
                block3 : {
                    if (n >= this.a.size()) break block3;
                    if (this.a.get(n).get() != chatListener) break block4;
                    this.a.remove(n);
                }
                return;
            }
            ++n;
        } while (true);
    }

    @Override
    public void c(Chat chat) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.c(chat);
        }
    }

    @Override
    public void c(Chat chat, ChatMessage chatMessage) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.c(chat, chatMessage);
        }
    }

    @Override
    public void d(Chat chat) {
        Iterator<WeakReference<ChatListener>> iterator = this.b().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.d(chat);
        }
    }

    @Override
    public void e(Chat chat) {
        chat.a(this);
    }

    @Override
    public void f(Chat chat) {
        chat.b(this);
    }

    @Override
    public void g(Chat chat) {
    }

    @Override
    public void h(Chat chat) {
    }
}

