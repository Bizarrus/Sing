/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Message
 */
package com.smule.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.extensions.GroupStatusExtension;
import com.smule.chat.extensions.SmuleExtension;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;

public class GroupStatusChatMessage
extends ChatMessage {
    private Status a;
    private String b;
    private String c;
    private String d;

    public GroupStatusChatMessage() {
    }

    private GroupStatusChatMessage(Status status) {
        this.a = status;
    }

    GroupStatusChatMessage(Message message) {
        if (message.c("join", "urn:x-smule:xmpp") != null) {
            this.a = Status.a;
            return;
        }
        if (message.c("leave", "urn:x-smule:xmpp") != null) {
            this.a = Status.b;
            return;
        }
        SmuleExtension smuleExtension = (GroupStatusExtension.Invite)message.c("invite", "urn:x-smule:xmpp");
        if (smuleExtension != null) {
            this.a = Status.d;
            this.b = smuleExtension.a();
            return;
        }
        smuleExtension = (GroupStatusExtension.Remove)message.c("remove", "urn:x-smule:xmpp");
        if (smuleExtension != null) {
            this.a = Status.c;
            this.b = smuleExtension.a();
            return;
        }
        smuleExtension = (GroupStatusExtension.Rename)message.c("rename", "urn:x-smule:xmpp");
        if (smuleExtension != null) {
            this.a = Status.e;
            this.c = smuleExtension.a();
            return;
        }
        throw new IllegalArgumentException("can't decode " + (Object)message);
    }

    static GroupStatusChatMessage a(long l, String string2) {
        GroupStatusChatMessage groupStatusChatMessage = new GroupStatusChatMessage(Status.c);
        groupStatusChatMessage.b = string2;
        groupStatusChatMessage.a(l);
        return groupStatusChatMessage;
    }

    static GroupStatusChatMessage a(String string2) {
        GroupStatusChatMessage groupStatusChatMessage = new GroupStatusChatMessage(Status.c);
        groupStatusChatMessage.b = string2;
        return groupStatusChatMessage;
    }

    static GroupStatusChatMessage a(String string2, String string3) {
        GroupStatusChatMessage groupStatusChatMessage = new GroupStatusChatMessage(Status.e);
        groupStatusChatMessage.d = string2;
        groupStatusChatMessage.c = string3;
        return groupStatusChatMessage;
    }

    static GroupStatusChatMessage b(String string2) {
        GroupStatusChatMessage groupStatusChatMessage = new GroupStatusChatMessage(Status.d);
        groupStatusChatMessage.b = string2;
        return groupStatusChatMessage;
    }

    static GroupStatusChatMessage r() {
        return new GroupStatusChatMessage(Status.a);
    }

    static GroupStatusChatMessage s() {
        return new GroupStatusChatMessage(Status.b);
    }

    @Override
    public ChatMessage.Type a() {
        return ChatMessage.Type.g;
    }

    @Override
    Message a(Chat.Type type, String string2) {
        type = super.a(type, string2);
        type.c(" ");
        switch (.a[this.a.ordinal()]) {
            default: {
                return type;
            }
            case 1: {
                type.a((ExtensionElement)new GroupStatusExtension.Join());
                return type;
            }
            case 2: {
                type.a((ExtensionElement)new GroupStatusExtension.Leave());
                return type;
            }
            case 3: {
                type.a((ExtensionElement)new GroupStatusExtension.Invite(this.b));
                return type;
            }
            case 4: {
                type.a((ExtensionElement)new GroupStatusExtension.Remove(this.b));
                return type;
            }
            case 5: 
        }
        type.a((ExtensionElement)new GroupStatusExtension.Rename(this.d, this.c));
        return type;
    }

    @Override
    public void a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.a = Status.a(smerializableInputStream);
        this.b = smerializableInputStream.b();
        this.d = smerializableInputStream.b();
        this.c = smerializableInputStream.b();
    }

    @Override
    public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        this.a.a(smerializableOutputStream);
        smerializableOutputStream.a(this.b);
        smerializableOutputStream.a(this.d);
        smerializableOutputStream.a(this.c);
    }

    @Override
    public boolean g() {
        if (this.a == Status.a || this.a == Status.e) {
            return true;
        }
        return false;
    }

    public Status o() {
        return this.a;
    }

    public String p() {
        return this.b;
    }

    public String q() {
        return this.c;
    }

    public static enum Status {
        a(0),
        b(1),
        c(2),
        d(3),
        e(4);
        
        private final int f;

        private Status(int n2) {
            this.f = n2;
        }

        public static Status a(SmerializableInputStream smerializableInputStream) throws IOException {
            if (smerializableInputStream.readInt() != 0) {
                throw new InvalidClassException("bad version");
            }
            int n = smerializableInputStream.readInt();
            if (n == Status.a.f) {
                return a;
            }
            if (n == Status.b.f) {
                return b;
            }
            if (n == Status.c.f) {
                return c;
            }
            if (n == Status.d.f) {
                return d;
            }
            if (n == Status.e.f) {
                return e;
            }
            throw new InvalidClassException("bad value: " + n);
        }

        public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
            smerializableOutputStream.writeInt(0);
            smerializableOutputStream.writeInt(this.f);
        }
    }

}

