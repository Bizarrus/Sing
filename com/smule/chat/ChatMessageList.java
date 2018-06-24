/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat;

import com.smule.chat.ChatMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class ChatMessageList
implements Iterable<ChatMessage> {
    private NavigableSet<ChatMessage> a;

    public ChatMessageList() {
        this.a = new TreeSet<ChatMessage>(new Comparator<ChatMessage>(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public int a(ChatMessage chatMessage, ChatMessage chatMessage2) {
                int n;
                if (chatMessage.equals(chatMessage2)) {
                    return 0;
                }
                int n2 = n = chatMessage.c().compareTo(chatMessage2.c());
                if (n != 0) return n2;
                return chatMessage.f().compareTo(chatMessage2.f());
            }

            @Override
            public /* synthetic */ int compare(Object object, Object object2) {
                return this.a((ChatMessage)object, (ChatMessage)object2);
            }
        });
    }

    public boolean a() {
        return this.a.isEmpty();
    }

    public boolean a(ChatMessage chatMessage) {
        if (this.a.add(chatMessage)) {
            chatMessage.k();
            return true;
        }
        return false;
    }

    public int b() {
        return this.a.size();
    }

    public void b(ChatMessage chatMessage) {
        if (this.a.remove(chatMessage)) {
            chatMessage.l();
        }
    }

    public void c() {
        Iterator<ChatMessage> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            iterator.next().l();
        }
        this.a.clear();
    }

    public void c(ChatMessage chatMessage) {
        ChatMessage chatMessage2 = this.d(chatMessage);
        if (chatMessage2 != null) {
            this.b(chatMessage2);
        }
        this.a(chatMessage);
    }

    public ChatMessage d(ChatMessage chatMessage) {
        Iterator<ChatMessage> iterator = this.f();
        while (iterator.hasNext()) {
            ChatMessage chatMessage2 = iterator.next();
            if (!chatMessage2.equals(chatMessage)) continue;
            return chatMessage2;
        }
        return null;
    }

    public List<ChatMessage> d() {
        return Collections.unmodifiableList(new ArrayList<ChatMessage>(this.a));
    }

    public ChatMessage e() {
        return this.a.last();
    }

    public Iterator<ChatMessage> f() {
        return this.a.descendingIterator();
    }

    @Override
    public Iterator<ChatMessage> iterator() {
        return this.a.iterator();
    }

}

