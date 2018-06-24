/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.filter.StanzaFilter
 *  org.jivesoftware.smack.packet.Stanza
 */
package com.smule.chat.mam.filter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

public abstract class FlexiblePacketTypeFilter<P extends Stanza>
implements StanzaFilter {
    protected final Class<P> a;

    public FlexiblePacketTypeFilter() {
        this.a = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public FlexiblePacketTypeFilter(Class<P> class_) {
        this.a = class_;
    }

    protected abstract boolean a(P var1);

    public boolean b(Stanza stanza) {
        if (this.a.isInstance((Object)stanza)) {
            return this.a((P)stanza);
        }
        return false;
    }
}

