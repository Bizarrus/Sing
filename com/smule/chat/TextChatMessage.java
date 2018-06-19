package com.smule.chat;

import com.smule.chat.ChatMessage.Type;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import org.jivesoftware.smack.packet.Message;

public class TextChatMessage extends ChatMessage {
    private String f18350a;

    public TextChatMessage(String str) {
        this.f18350a = str;
    }

    public Type mo6360a() {
        return Type.TEXT;
    }

    public String m19687o() {
        return this.f18350a;
    }

    public boolean mo6362g() {
        return true;
    }

    public String toString() {
        return super.toString() + ", message:{" + this.f18350a + "}";
    }

    Message mo6361a(Chat.Type type, String str) {
        Message a = super.mo6361a(type, str);
        a.c(m19687o());
        return a;
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.mo6316a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.f18350a = smerializableInputStream.m19751b();
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.mo6317a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.m19757a(this.f18350a);
    }
}
