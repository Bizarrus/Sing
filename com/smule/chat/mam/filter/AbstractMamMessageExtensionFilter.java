package com.smule.chat.mam.filter;

import com.smule.chat.mam.packet.MamPacket.AbstractMamExtension;
import com.smule.chat.mam.packet.MamQueryIQ;
import org.jivesoftware.smack.packet.Message;

public abstract class AbstractMamMessageExtensionFilter extends FlexiblePacketTypeFilter<Message> {
    private final String f18362b;

    protected abstract AbstractMamExtension mo6370b(Message message);

    public AbstractMamMessageExtensionFilter(MamQueryIQ mamQueryIQ) {
        super(Message.class);
        this.f18362b = mamQueryIQ.m19743a();
    }

    protected boolean m19726a(Message message) {
        AbstractMamExtension b = mo6370b(message);
        if (b == null) {
            return false;
        }
        String a = b.m19731a();
        if (this.f18362b == null && a == null) {
            return true;
        }
        if (this.f18362b == null || !this.f18362b.equals(a)) {
            return false;
        }
        return true;
    }
}
