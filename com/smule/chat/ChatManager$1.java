package com.smule.chat;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.debugger.SmackDebugger;
import org.jivesoftware.smack.debugger.SmackDebuggerFactory;
import org.jivesoftware.smackx.debugger.android.AndroidDebugger;

class ChatManager$1 implements SmackDebuggerFactory {
    final /* synthetic */ ChatManager f18043a;

    ChatManager$1(ChatManager chatManager) {
        this.f18043a = chatManager;
    }

    public SmackDebugger m19332a(XMPPConnection xMPPConnection, Writer writer, Reader reader) throws IllegalArgumentException {
        return new AndroidDebugger(xMPPConnection, writer, reader);
    }
}
