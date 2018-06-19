package com.smule.chat;

import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$ErrorDomain;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

class OutgoingMessageTracker implements StanzaListener {
    private ChatConnectionManager f18289a;
    private XMPPDelegate f18290b;
    private Map<String, MessageInfo> f18291c = new HashMap();
    private int f18292d = -1;

    private static class MessageInfo {
        public long f18285a;
        public Chat f18286b;
        public ChatMessage f18287c;
        public int f18288d;

        private MessageInfo() {
        }
    }

    public OutgoingMessageTracker(ChatConnectionManager chatConnectionManager, XMPPDelegate xMPPDelegate) {
        this.f18289a = chatConnectionManager;
        this.f18290b = xMPPDelegate;
    }

    public void m19641a(Chat chat, ChatMessage chatMessage, Message message) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.f18285a = System.nanoTime();
        messageInfo.f18286b = chat;
        messageInfo.f18287c = chatMessage;
        messageInfo.f18288d = message.h().length();
        this.f18291c.put(chatMessage.m19389f(), messageInfo);
    }

    private void m19640a(Stanza stanza, long j) {
        MessageInfo messageInfo = (MessageInfo) this.f18291c.remove(stanza.j());
        if (messageInfo != null) {
            messageInfo.f18286b.m19210c(messageInfo.f18287c);
            int b = this.f18289a.m19275b();
            if (b != this.f18292d) {
                this.f18292d = b;
                EventLogger2.a("xmpp", this.f18289a.m19280e(), "/message", (long) (((double) (j - messageInfo.f18285a)) / 1000000.0d), (long) messageInfo.f18288d, 0, EventLogger2$ErrorDomain.NONE, 0, null, null, null, false);
            }
        }
    }

    public void m19642a(final Stanza stanza) throws NotConnectedException {
        if (stanza instanceof Message) {
            final long nanoTime = System.nanoTime();
            this.f18290b.b(new Runnable(this) {
                final /* synthetic */ OutgoingMessageTracker f18284c;

                public void run() {
                    this.f18284c.m19640a(stanza, nanoTime);
                }
            });
        }
    }
}
