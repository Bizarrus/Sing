/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.Message
 */
package com.smule.chat.mam.filter;

import com.smule.chat.mam.filter.AbstractMamMessageExtensionFilter;
import com.smule.chat.mam.packet.MamPacket;
import com.smule.chat.mam.packet.MamQueryIQ;
import org.jivesoftware.smack.packet.Message;

public class MamMessageFinFilter
extends AbstractMamMessageExtensionFilter {
    public MamMessageFinFilter(MamQueryIQ mamQueryIQ) {
        super(mamQueryIQ);
    }

    @Override
    protected MamPacket.AbstractMamExtension b(Message message) {
        return MamPacket.MamFinExtension.a(message);
    }
}

