/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.XMPPException
 *  org.jivesoftware.smack.XMPPException$XMPPErrorException
 *  org.jivesoftware.smack.packet.XMPPError
 *  org.jivesoftware.smack.packet.XMPPError$Condition
 */
package com.smule.chat;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.XMPPError;

public enum ChatStatus {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i;
    

    private ChatStatus() {
    }

    static ChatStatus a(Throwable throwable) {
        if (!(throwable instanceof XMPPException.XMPPErrorException)) {
            return b;
        }
        if ((throwable = ((XMPPException.XMPPErrorException)throwable).a()) == null) {
            return b;
        }
        if ((throwable = throwable.a()).equals((Object)XMPPError.Condition.o) || throwable.equals((Object)XMPPError.Condition.d) || throwable.equals((Object)XMPPError.Condition.j)) {
            return c;
        }
        if (throwable.equals((Object)XMPPError.Condition.g)) {
            return d;
        }
        return b;
    }

    public boolean a() {
        if (this == b) {
            return true;
        }
        return false;
    }
}

