package com.smule.chat.mam.filter;

import java.lang.reflect.ParameterizedType;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

public abstract class FlexiblePacketTypeFilter<P extends Stanza> implements StanzaFilter {
    protected final Class<P> f18361a;

    protected abstract boolean mo6369a(P p);

    public FlexiblePacketTypeFilter(Class<P> cls) {
        this.f18361a = cls;
    }

    public FlexiblePacketTypeFilter() {
        this.f18361a = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public boolean m19725b(Stanza stanza) {
        if (this.f18361a.isInstance(stanza)) {
            return mo6369a(stanza);
        }
        return false;
    }
}
