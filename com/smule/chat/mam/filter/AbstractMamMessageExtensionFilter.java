/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Stanza
 */
package com.smule.chat.mam.filter;

import com.smule.chat.mam.filter.FlexiblePacketTypeFilter;
import com.smule.chat.mam.packet.MamPacket;
import com.smule.chat.mam.packet.MamQueryIQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

public abstract class AbstractMamMessageExtensionFilter
extends FlexiblePacketTypeFilter<Message> {
    private final String b;

    public AbstractMamMessageExtensionFilter(MamQueryIQ mamQueryIQ) {
        super(Message.class);
        this.b = mamQueryIQ.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected boolean a(Message object) {
        block5 : {
            block4 : {
                if ((object = this.b((Message)object)) == null) break block4;
                object = object.a();
                if (this.b == null && object == null) {
                    return true;
                }
                if (this.b != null && this.b.equals(object)) break block5;
            }
            return false;
        }
        return true;
    }

    protected abstract MamPacket.AbstractMamExtension b(Message var1);
}

