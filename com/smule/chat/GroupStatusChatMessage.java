package com.smule.chat;

import com.smule.chat.ChatMessage.Type;
import com.smule.chat.extensions.GroupStatusExtension.Invite;
import com.smule.chat.extensions.GroupStatusExtension.Join;
import com.smule.chat.extensions.GroupStatusExtension.Leave;
import com.smule.chat.extensions.GroupStatusExtension.Remove;
import com.smule.chat.extensions.GroupStatusExtension.Rename;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import org.jivesoftware.smack.packet.Message;

public class GroupStatusChatMessage extends ChatMessage {
    private Status f18273a;
    private String f18274b;
    private String f18275c;
    private String f18276d;

    public enum Status {
        JOINED(0),
        LEFT(1),
        REMOVED(2),
        INVITED(3),
        RENAMED(4);
        
        private final int f18272f;

        private Status(int i) {
            this.f18272f = i;
        }

        public void m19617a(SmerializableOutputStream smerializableOutputStream) throws IOException {
            smerializableOutputStream.writeInt(0);
            smerializableOutputStream.writeInt(this.f18272f);
        }

        public static Status m19616a(SmerializableInputStream smerializableInputStream) throws IOException {
            if (smerializableInputStream.readInt() != 0) {
                throw new InvalidClassException("bad version");
            }
            int readInt = smerializableInputStream.readInt();
            if (readInt == JOINED.f18272f) {
                return JOINED;
            }
            if (readInt == LEFT.f18272f) {
                return LEFT;
            }
            if (readInt == REMOVED.f18272f) {
                return REMOVED;
            }
            if (readInt == INVITED.f18272f) {
                return INVITED;
            }
            if (readInt == RENAMED.f18272f) {
                return RENAMED;
            }
            throw new InvalidClassException("bad value: " + readInt);
        }
    }

    public Type mo6360a() {
        return Type.GROUP_STATUS;
    }

    public Status m19629o() {
        return this.f18273a;
    }

    public String m19630p() {
        return this.f18274b;
    }

    public String m19631q() {
        return this.f18275c;
    }

    public boolean mo6362g() {
        return this.f18273a == Status.JOINED || this.f18273a == Status.RENAMED;
    }

    static GroupStatusChatMessage m19622r() {
        return new GroupStatusChatMessage(Status.JOINED);
    }

    static GroupStatusChatMessage m19623s() {
        return new GroupStatusChatMessage(Status.LEFT);
    }

    static GroupStatusChatMessage m19619a(String str) {
        GroupStatusChatMessage groupStatusChatMessage = new GroupStatusChatMessage(Status.REMOVED);
        groupStatusChatMessage.f18274b = str;
        return groupStatusChatMessage;
    }

    static GroupStatusChatMessage m19618a(long j, String str) {
        GroupStatusChatMessage groupStatusChatMessage = new GroupStatusChatMessage(Status.REMOVED);
        groupStatusChatMessage.f18274b = str;
        groupStatusChatMessage.m19379a(j);
        return groupStatusChatMessage;
    }

    static GroupStatusChatMessage m19621b(String str) {
        GroupStatusChatMessage groupStatusChatMessage = new GroupStatusChatMessage(Status.INVITED);
        groupStatusChatMessage.f18274b = str;
        return groupStatusChatMessage;
    }

    static GroupStatusChatMessage m19620a(String str, String str2) {
        GroupStatusChatMessage groupStatusChatMessage = new GroupStatusChatMessage(Status.RENAMED);
        groupStatusChatMessage.f18276d = str;
        groupStatusChatMessage.f18275c = str2;
        return groupStatusChatMessage;
    }

    private GroupStatusChatMessage(Status status) {
        this.f18273a = status;
    }

    GroupStatusChatMessage(Message message) {
        if (message.c("join", "urn:x-smule:xmpp") != null) {
            this.f18273a = Status.JOINED;
        } else if (message.c("leave", "urn:x-smule:xmpp") != null) {
            this.f18273a = Status.LEFT;
        } else {
            Invite invite = (Invite) message.c("invite", "urn:x-smule:xmpp");
            if (invite != null) {
                this.f18273a = Status.INVITED;
                this.f18274b = invite.m19695a();
                return;
            }
            Remove remove = (Remove) message.c("remove", "urn:x-smule:xmpp");
            if (remove != null) {
                this.f18273a = Status.REMOVED;
                this.f18274b = remove.m19702a();
                return;
            }
            Rename rename = (Rename) message.c("rename", "urn:x-smule:xmpp");
            if (rename != null) {
                this.f18273a = Status.RENAMED;
                this.f18275c = rename.m19707a();
                return;
            }
            throw new IllegalArgumentException("can't decode " + message);
        }
    }

    Message mo6361a(Chat.Type type, String str) {
        Message a = super.mo6361a(type, str);
        a.c(" ");
        switch (this.f18273a) {
            case JOINED:
                a.a(new Join());
                break;
            case LEFT:
                a.a(new Leave());
                break;
            case INVITED:
                a.a(new Invite(this.f18274b));
                break;
            case REMOVED:
                a.a(new Remove(this.f18274b));
                break;
            case RENAMED:
                a.a(new Rename(this.f18276d, this.f18275c));
                break;
        }
        return a;
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.mo6316a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.f18273a = Status.m19616a(smerializableInputStream);
        this.f18274b = smerializableInputStream.m19751b();
        this.f18276d = smerializableInputStream.m19751b();
        this.f18275c = smerializableInputStream.m19751b();
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.mo6317a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        this.f18273a.m19617a(smerializableOutputStream);
        smerializableOutputStream.m19757a(this.f18274b);
        smerializableOutputStream.m19757a(this.f18276d);
        smerializableOutputStream.m19757a(this.f18275c);
    }
}
