/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.Stanza
 */
package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.chat.ChatManager;
import java.util.LinkedList;
import java.util.Queue;
import org.jivesoftware.smack.packet.Stanza;

class ChatStanzaQueue {
    private static final String d = ChatStanzaQueue.class.getName();
    private final ChatManager a;
    private final Queue<Stanza> b;
    private int c;

    public ChatStanzaQueue(ChatManager chatManager) {
        this.a = chatManager;
        this.b = new LinkedList<Stanza>();
    }

    private void a() {
        if (!this.b.isEmpty()) {
            this.a.b(new Runnable(){

                @Override
                public void run() {
                    ChatStanzaQueue.this.b();
                }
            });
        }
    }

    private void b() {
        if (this.c > 0) {
            return;
        }
        Stanza stanza = this.b.poll();
        if (stanza != null) {
            this.a.b(stanza);
        }
        this.a();
    }

    public void a(Stanza stanza) {
        this.b.add(stanza);
        this.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl) {
        int n = this.c;
        int n2 = bl ? 1 : -1;
        this.c = n2 + n;
        if (this.c == 0) {
            this.a();
            return;
        } else {
            if (this.c >= 0) return;
            {
                Log.e(d, "unpaused too many times");
                this.c = 0;
                return;
            }
        }
    }

}

