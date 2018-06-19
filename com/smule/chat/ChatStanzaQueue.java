package com.smule.chat;

import com.smule.android.logging.Log;
import java.util.LinkedList;
import java.util.Queue;
import org.jivesoftware.smack.packet.Stanza;

class ChatStanzaQueue {
    private static final String f18132d = ChatStanzaQueue.class.getName();
    private final ChatManager f18133a;
    private final Queue<Stanza> f18134b = new LinkedList();
    private int f18135c;

    class C37131 implements Runnable {
        final /* synthetic */ ChatStanzaQueue f18131a;

        C37131(ChatStanzaQueue chatStanzaQueue) {
            this.f18131a = chatStanzaQueue;
        }

        public void run() {
            this.f18131a.m19413b();
        }
    }

    public ChatStanzaQueue(ChatManager chatManager) {
        this.f18133a = chatManager;
    }

    public void m19414a(Stanza stanza) {
        this.f18134b.add(stanza);
        m19411a();
    }

    public void m19415a(boolean z) {
        this.f18135c = (z ? 1 : -1) + this.f18135c;
        if (this.f18135c == 0) {
            m19411a();
        } else if (this.f18135c < 0) {
            Log.e(f18132d, "unpaused too many times");
            this.f18135c = 0;
        }
    }

    private void m19411a() {
        if (!this.f18134b.isEmpty()) {
            this.f18133a.b(new C37131(this));
        }
    }

    private void m19413b() {
        if (this.f18135c <= 0) {
            Stanza stanza = (Stanza) this.f18134b.poll();
            if (stanza != null) {
                this.f18133a.b(stanza);
            }
            m19411a();
        }
    }
}
