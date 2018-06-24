/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.SmackException$NotConnectedException
 *  org.jivesoftware.smack.StanzaListener
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smack.util.XmlStringBuilder
 */
package com.smule.chat;

import com.smule.android.logging.EventLogger2;
import com.smule.chat.Chat;
import com.smule.chat.ChatConnectionManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.XMPPDelegate;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;

class OutgoingMessageTracker
implements StanzaListener {
    private ChatConnectionManager a;
    private XMPPDelegate b;
    private Map<String, MessageInfo> c;
    private int d;

    public OutgoingMessageTracker(ChatConnectionManager chatConnectionManager, XMPPDelegate xMPPDelegate) {
        this.a = chatConnectionManager;
        this.b = xMPPDelegate;
        this.c = new HashMap<String, MessageInfo>();
        this.d = -1;
    }

    private void a(Stanza object, long l) {
        if ((object = this.c.remove(object.j())) != null) {
            object.b.c(object.c);
            int n = this.a.b();
            if (n != this.d) {
                this.d = n;
                l = (long)((double)(l - object.a) / 1000000.0);
                com.smule.android.logging.EventLogger2.a("xmpp", this.a.e(), "/message", l, object.d, 0, EventLogger2.a, 0, null, null, null, false);
            }
        }
    }

    public void a(Chat chat, ChatMessage chatMessage, Message message) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.a = System.nanoTime();
        messageInfo.b = chat;
        messageInfo.c = chatMessage;
        messageInfo.d = message.h().length();
        this.c.put(chatMessage.f(), messageInfo);
    }

    public void a(final Stanza stanza) throws SmackException.NotConnectedException {
        if (stanza instanceof Message) {
            final long l = System.nanoTime();
            this.b.b(new Runnable(){

                @Override
                public void run() {
                    OutgoingMessageTracker.this.a(stanza, l);
                }
            });
        }
    }

    private static class MessageInfo {
        public long a;
        public Chat b;
        public ChatMessage c;
        public int d;

        private MessageInfo() {
        }
    }

}

