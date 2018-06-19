package com.smule.chat.mam.filter;

import com.smule.chat.mam.packet.MamPacket.AbstractMamExtension;
import com.smule.chat.mam.packet.MamPacket.MamFinExtension;
import com.smule.chat.mam.packet.MamQueryIQ;
import org.jivesoftware.smack.packet.Message;

public class MamMessageFinFilter extends AbstractMamMessageExtensionFilter {
    public MamMessageFinFilter(MamQueryIQ mamQueryIQ) {
        super(mamQueryIQ);
    }

    protected AbstractMamExtension mo6370b(Message message) {
        return MamFinExtension.m19733a(message);
    }
}
