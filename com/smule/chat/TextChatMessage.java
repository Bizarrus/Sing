/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.Message
 */
package com.smule.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import org.jivesoftware.smack.packet.Message;

public class TextChatMessage
extends ChatMessage {
    private String a;

    public TextChatMessage() {
    }

    public TextChatMessage(String string2) {
        this.a = string2;
    }

    @Override
    public ChatMessage.Type a() {
        return ChatMessage.Type.b;
    }

    @Override
    Message a(Chat.Type type, String string2) {
        type = super.a(type, string2);
        type.c(this.o());
        return type;
    }

    @Override
    public void a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.a = smerializableInputStream.b();
    }

    @Override
    public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.a(this.a);
    }

    @Override
    public boolean g() {
        return true;
    }

    public String o() {
        return this.a;
    }

    @Override
    public String toString() {
        return super.toString() + ", message:{" + this.a + "}";
    }
}

