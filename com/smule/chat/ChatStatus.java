package com.smule.chat;

import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;

public enum ChatStatus {
    OK,
    NETWORK_ERROR,
    NON_MEMBER,
    CHAT_NOT_FOUND,
    BAD_REQUEST,
    QUEUE_FULL,
    REJECTED,
    DELIVERY_FAILED,
    DELETED;

    public boolean m19417a() {
        return this == NETWORK_ERROR;
    }

    static ChatStatus m19416a(Throwable th) {
        if (!(th instanceof XMPPErrorException)) {
            return NETWORK_ERROR;
        }
        XMPPError a = ((XMPPErrorException) th).a();
        if (a == null) {
            return NETWORK_ERROR;
        }
        Condition a2 = a.a();
        if (a2.equals(Condition.o) || a2.equals(Condition.d) || a2.equals(Condition.j)) {
            return NON_MEMBER;
        }
        if (a2.equals(Condition.g)) {
            return CHAT_NOT_FOUND;
        }
        return NETWORK_ERROR;
    }
}
