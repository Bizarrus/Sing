package com.smule.chat;

import com.smule.chat.Chat.ChatPhasedTask;
import com.smule.chat.smerialization.Smerializable;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import com.smule.chat.smerialization.SmerializableUtils;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Date;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smackx.delay.packet.DelayInformation;

public abstract class ChatMessage implements Smerializable {
    private long f18122a;
    private Date f18123b = new Date();
    private State f18124c = State.RAW;
    private ChatStatus f18125d = ChatStatus.OK;
    private String f18126e = StanzaIdUtil.a();

    public enum State {
        RAW,
        READY,
        SENDING,
        ERROR
    }

    public enum Type {
        UNKNOWN,
        TEXT,
        PERFORMANCE,
        f18117d,
        ARRANGEMENT,
        GROUP_INVITATION,
        GROUP_STATUS
    }

    public abstract Type mo6360a();

    public abstract boolean mo6362g();

    public long m19385b() {
        return this.f18122a;
    }

    public Date mo6767c() {
        return this.f18123b;
    }

    public State m19387d() {
        return this.f18124c;
    }

    public ChatStatus m19388e() {
        return this.f18125d;
    }

    public String m19389f() {
        return this.f18126e;
    }

    public String toString() {
        return "from:" + this.f18122a + ", at:" + this.f18123b;
    }

    public int hashCode() {
        return this.f18126e.hashCode() ^ ((int) m19385b());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChatMessage chatMessage = (ChatMessage) obj;
        if (this.f18122a == chatMessage.f18122a && this.f18126e.equals(chatMessage.f18126e)) {
            return true;
        }
        return false;
    }

    Message mo6361a(com.smule.chat.Chat.Type type, String str) {
        Message message = new Message(str);
        message.f(this.f18126e);
        message.g(str);
        message.a(type == com.smule.chat.Chat.Type.PEER ? org.jivesoftware.smack.packet.Message.Type.b : org.jivesoftware.smack.packet.Message.Type.c);
        return message;
    }

    void m19379a(long j) {
        this.f18122a = j;
    }

    void m19384a(Date date) {
        this.f18123b = date;
    }

    void m19380a(Chat chat, State state, ChatStatus chatStatus) {
        this.f18124c = state;
        this.f18125d = chatStatus;
        if (chat != null) {
            chat.m19213d(this);
        }
    }

    void m19391h() {
        this.f18125d = ChatStatus.OK;
        this.f18124c = State.READY;
    }

    void m19381a(ChatStatus chatStatus) {
        this.f18125d = chatStatus;
        this.f18124c = State.ERROR;
    }

    void m19392i() {
        this.f18125d = ChatStatus.OK;
        this.f18124c = State.SENDING;
    }

    void m19393j() {
        this.f18125d = ChatStatus.OK;
        this.f18124c = State.RAW;
    }

    void m19394k() {
    }

    void mo6363l() {
    }

    ChatPhasedTask<Void, Object> mo6359a(Chat chat, boolean z, XMPPDelegate xMPPDelegate) {
        this.f18124c = State.READY;
        return null;
    }

    boolean mo6364m() {
        return false;
    }

    static boolean m19373a(Message message) {
        DelayInformation a = DelayInformation.a(message);
        return a != null && "Offline Storage".equals(a.e());
    }

    static ChatMessage m19372a(Message message, XMPPDelegate xMPPDelegate) {
        ChatMessage groupInvitationChatMessage;
        switch (m19374b(message)) {
            case GROUP_INVITATION:
                groupInvitationChatMessage = new GroupInvitationChatMessage(message);
                break;
            case GROUP_STATUS:
                groupInvitationChatMessage = new GroupStatusChatMessage(message);
                break;
            case PERFORMANCE:
                groupInvitationChatMessage = new PerformanceChatMessage(message);
                break;
            case UNKNOWN:
                groupInvitationChatMessage = new UnknownChatMessage();
                break;
            default:
                groupInvitationChatMessage = new TextChatMessage(message.e());
                break;
        }
        if (message.j() != null) {
            groupInvitationChatMessage.f18126e = message.j();
        }
        groupInvitationChatMessage.m19379a(xMPPDelegate.a(message));
        DelayInformation a = DelayInformation.a(message);
        if (a != null) {
            Date a2 = a.a();
            if (a2 != null) {
                groupInvitationChatMessage.m19384a(a2);
            }
        }
        return groupInvitationChatMessage;
    }

    private static Type m19374b(Message message) {
        if (message.j("jabber:x:conference") != null) {
            return Type.GROUP_INVITATION;
        }
        if (message.c("performance", "urn:x-smule:xmpp") != null) {
            return Type.PERFORMANCE;
        }
        if (message.c("join", "urn:x-smule:xmpp") != null) {
            return Type.GROUP_STATUS;
        }
        if (message.c("leave", "urn:x-smule:xmpp") != null) {
            return Type.GROUP_STATUS;
        }
        if (message.c("invite", "urn:x-smule:xmpp") != null) {
            return Type.GROUP_STATUS;
        }
        if (message.c("remove", "urn:x-smule:xmpp") != null) {
            return Type.GROUP_STATUS;
        }
        if (message.c("rename", "urn:x-smule:xmpp") != null) {
            return Type.GROUP_STATUS;
        }
        if (message.j("urn:x-smule:xmpp") == null || message.e().trim().length() != 0) {
            return Type.TEXT;
        }
        return Type.UNKNOWN;
    }

    public String mo6315O() {
        return null;
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.writeLong(this.f18122a);
        SmerializableUtils.m19761a(smerializableOutputStream, this.f18123b);
        smerializableOutputStream.m19757a(this.f18126e);
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.f18122a = smerializableInputStream.readLong();
        this.f18123b = SmerializableUtils.m19758a(smerializableInputStream);
        this.f18126e = smerializableInputStream.m19751b();
    }

    boolean mo6367n() {
        return true;
    }
}
