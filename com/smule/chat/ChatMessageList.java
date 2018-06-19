package com.smule.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class ChatMessageList implements Iterable<ChatMessage> {
    private NavigableSet<ChatMessage> f18128a = new TreeSet(new C37121(this));

    class C37121 implements Comparator<ChatMessage> {
        final /* synthetic */ ChatMessageList f18127a;

        C37121(ChatMessageList chatMessageList) {
            this.f18127a = chatMessageList;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m19398a((ChatMessage) obj, (ChatMessage) obj2);
        }

        public int m19398a(ChatMessage chatMessage, ChatMessage chatMessage2) {
            if (chatMessage.equals(chatMessage2)) {
                return 0;
            }
            int compareTo = chatMessage.mo6767c().compareTo(chatMessage2.mo6767c());
            return compareTo == 0 ? chatMessage.m19389f().compareTo(chatMessage2.m19389f()) : compareTo;
        }
    }

    public boolean m19400a(ChatMessage chatMessage) {
        if (!this.f18128a.add(chatMessage)) {
            return false;
        }
        chatMessage.m19394k();
        return true;
    }

    public void m19402b(ChatMessage chatMessage) {
        if (this.f18128a.remove(chatMessage)) {
            chatMessage.mo6363l();
        }
    }

    public void m19404c(ChatMessage chatMessage) {
        ChatMessage d = m19405d(chatMessage);
        if (d != null) {
            m19402b(d);
        }
        m19400a(chatMessage);
    }

    public boolean m19399a() {
        return this.f18128a.isEmpty();
    }

    public int m19401b() {
        return this.f18128a.size();
    }

    public void m19403c() {
        for (ChatMessage l : this.f18128a) {
            l.mo6363l();
        }
        this.f18128a.clear();
    }

    public List<ChatMessage> m19406d() {
        return Collections.unmodifiableList(new ArrayList(this.f18128a));
    }

    public ChatMessage m19407e() {
        return (ChatMessage) this.f18128a.last();
    }

    public ChatMessage m19405d(ChatMessage chatMessage) {
        Iterator f = m19408f();
        while (f.hasNext()) {
            ChatMessage chatMessage2 = (ChatMessage) f.next();
            if (chatMessage2.equals(chatMessage)) {
                return chatMessage2;
            }
        }
        return null;
    }

    public Iterator<ChatMessage> iterator() {
        return this.f18128a.iterator();
    }

    public Iterator<ChatMessage> m19408f() {
        return this.f18128a.descendingIterator();
    }
}
