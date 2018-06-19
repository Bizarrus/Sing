package com.smule.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatContainer {
    private int f18014a;
    private ArrayList<Chat> f18015b = new ArrayList(this.f18014a);
    private ChatListener f18016c = new C36961(this);

    class C36961 extends ChatListenerAdapter {
        final /* synthetic */ ChatContainer f18013a;

        C36961(ChatContainer chatContainer) {
            this.f18013a = chatContainer;
        }

        public void m19287a(Chat chat, ChatMessage chatMessage, boolean z) {
            if (chatMessage == chat.m19224l()) {
                this.f18013a.m19289d(chat);
            }
        }

        public void m19286a(Chat chat, ChatMessage chatMessage) {
            if (chatMessage == chat.m19224l()) {
                this.f18013a.m19289d(chat);
            }
        }
    }

    public ChatContainer(int i) {
        this.f18014a = i;
    }

    public void m19290a(Chat chat) {
        chat.m19192a(this.f18016c);
        mo6318c(chat);
    }

    public void m19292b(Chat chat) {
        chat.m19205b(this.f18016c);
        this.f18015b.remove(chat);
    }

    public boolean m19291a() {
        return this.f18015b.size() > this.f18014a;
    }

    public boolean m19293b() {
        return this.f18015b.size() >= this.f18014a;
    }

    public boolean m19295c() {
        return this.f18015b.isEmpty();
    }

    public Chat m19296d() {
        return this.f18015b.size() > 0 ? (Chat) this.f18015b.get(this.f18015b.size() - 1) : null;
    }

    public List<Chat> m19297e() {
        return Collections.unmodifiableList(this.f18015b);
    }

    private void m19289d(Chat chat) {
        mo6318c(chat);
    }

    protected void mo6318c(Chat chat) {
        this.f18015b.remove(chat);
        int binarySearch = Collections.binarySearch(this.f18015b, chat);
        if (binarySearch < 0) {
            this.f18015b.add((-binarySearch) - 1, chat);
        } else {
            this.f18015b.add(chat);
        }
    }
}
