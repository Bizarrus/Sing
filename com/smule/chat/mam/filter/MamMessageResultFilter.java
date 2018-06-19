package com.smule.chat.mam.filter;

import com.smule.chat.mam.packet.MamPacket.AbstractMamExtension;
import com.smule.chat.mam.packet.MamPacket.MamResultExtension;
import com.smule.chat.mam.packet.MamQueryIQ;
import org.jivesoftware.smack.packet.Message;

public class MamMessageResultFilter extends AbstractMamMessageExtensionFilter {
    public MamMessageResultFilter(MamQueryIQ mamQueryIQ) {
        super(mamQueryIQ);
    }

    protected AbstractMamExtension mo6370b(Message message) {
        return MamResultExtension.m19739a(message);
    }
}
